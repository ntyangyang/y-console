package com.console.modules.sys.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 17:34 2018/9/17
 * @Modified By:
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

