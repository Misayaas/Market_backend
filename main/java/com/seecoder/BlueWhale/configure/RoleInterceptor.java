package com.seecoder.BlueWhale.configure;
import com.seecoder.BlueWhale.annotations.RequiresRoles;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.exception.BlueWhaleException;
import com.seecoder.BlueWhale.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresRoles requiresRoles = handlerMethod.getMethodAnnotation(RequiresRoles.class);
            if (requiresRoles != null) {
                // 获取用户的角色，这通常从请求的会话或令牌中获取
                RoleEnum userRole = tokenUtil.getUser(token).getRole();
                for (RoleEnum role : requiresRoles.value()) {
                    if (role == userRole) {
                        return true;
                    }
                }
                // 如果用户的角色不在@RequiresRoles的参数中，阻止请求被处理
               throw BlueWhaleException.forbidden();
            }
        }
        return true;
    }

}
