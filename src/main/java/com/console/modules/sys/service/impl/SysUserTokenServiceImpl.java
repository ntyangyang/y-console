package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.modules.sys.mapper.SysUserTokenMapper;
import com.console.modules.sys.model.SysUserToken;
import com.console.modules.sys.service.SysUserTokenService;
import com.console.modules.sys.shiro.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:37 2018/9/18
 * @Modified By:
 */
@Slf4j
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public Result createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserToken userToken = this.getById(userId);
        if (userToken == null) {
            userToken = new SysUserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //保存token
            this.save(userToken);
        } else {
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            //更新token
            this.updateById(userToken);
        }

        return ResultGenerator.genOkResult(token);
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserToken userToken = new SysUserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        this.updateById(userToken);
    }
}
