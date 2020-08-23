package com.lagou.interceptor;

import com.lagou.annotation.Security;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ValidInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            if (method.isAnnotationPresent(Security.class)) {
                Security security = method.getAnnotation(Security.class);
                return permissionCheck(security, request, response);
            }
        }
        return true;
    }

    private boolean permissionCheck(Security security, HttpServletRequest request, HttpServletResponse response){

        String[] permissions = security.value();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER_SESSION");
        if (permissions.length == 0){
            // 不填写默认所有人可以访问
            return true;
        }

        List<String> list = Arrays.asList(permissions);
        if (list.contains(username)){
            // 允许当前用户访问
            return true;
        }

        //request.setAttribute("msg", "您没有权限！");
        //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        try {
            //response.sendRedirect("invalid?msg=您没有权限！");
            request.setAttribute("msg", "您没有权限！");
            request.getRequestDispatcher("invalid").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
