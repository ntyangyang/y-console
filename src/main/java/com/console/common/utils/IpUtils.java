package com.console.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

/**
 * @Author: yang
 * @Description: IP工具
 * @Date:Create In 1:59 2018/8/6
 * @Modified By:
 */
@Component
public class IpUtils {
    private final static String UNKNOWN = "unknown";
    private final static String LOCALHOST_IPV4 = "127.0.0.1";
    private final static String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private IpUtils() {

    }

    public static String getIpAddress() {
        return getIpAddress(HttpContextUtil.getHttpServletRequest());
    }

    /**
     * 获取请求中的 ip 地址
     *
     * @param request request
     * @return IP
     */
    public static String getIpAddress(final HttpServletRequest request) {
        String ip = LOCALHOST_IPV4;
        if (request != null) {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                // request.getRemoteAddr() 获取客户端的 IP 地址在大部分情况下都是有效的
                // 但是在通过了 Apache，Squid 等反向代理软件就不能获取到客户端的真实 IP 地址
                // 如果通过了多级反向代理的话 X-Forwarded-For 的值并不止一个
                // 而是一串 IP 值，例如：192.168.1.110,192.168.1.120,192.168.1.130,192.168.1.100
                // 其中第一个 192.168.1.110 才是用户真实的 IP
                if (LOCALHOST_IPV4.equals(ip) || LOCALHOST_IPV6.equals(ip)) {
                    // 根据网卡取本机配置的 IP，而不是环回地址
                    try {
                        ip = InetAddress.getLocalHost().getHostAddress();
                    } catch (final UnknownHostException ignored) {
                    }
                }
            }
            // 多个 IP 中取第一个
            final String ch = ",";
            if (!StringUtils.isEmpty(ip) && ip.contains(ch)) {
                ip = ip.substring(0, ip.indexOf(ch));
            }
        }
        return ip;
    }

    /**
     * 通过 IP 获取相关信息(需要联网，调用淘宝的IP库)
     *
     * @param ip ip
     * @return IP相关信息
     */
    public static String getInfoByIP(final String ip) {
        try {
            final URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            final InputStream in = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            final StringBuilder buffer = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                buffer.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            final JSONObject obj = (JSONObject) JSON.parse(buffer.toString());
            final LinkedHashMap<String, Object> info = new LinkedHashMap<>();
            final int responseCode = obj.getIntValue("code");
            final Gson g = new Gson();
            if (responseCode == 0) {
                final JSONObject data = obj.getJSONObject("data");
                info.put("country", data.getString("country"));
                info.put("region", data.getString("region"));
                info.put("city", data.getString("city"));
                info.put("isp", data.getString("isp"));
            }
            return g.toJson(info);
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}