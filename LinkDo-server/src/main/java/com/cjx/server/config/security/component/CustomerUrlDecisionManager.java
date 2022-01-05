package com.cjx.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 判断用户角色
 * 权限控制
 */
@Component
public class CustomerUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            //当前url需要的角色
            String needRole = configAttribute.getAttribute();
            //判断角色是否登录即可访问角色，在CustomerFilter中设置
            if ("ROLE_LOGIN".equals(needRole)){
                //如果等于AnonymousAuthenticationToken 则表示仍未登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登陆，请进行登录操作！");
                } else {
                    return;
                }
            }
            //判断用户是否为url所需角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //判断用户角色与url所需角色是否一致
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        //否则
        throw new AccessDeniedException("权限不足，请联系您的上级");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
