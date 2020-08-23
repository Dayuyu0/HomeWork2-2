package com.lagou.interceptor;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 会在handler方法业务逻辑执行之前执行
     * 往往在这里完成权限校验工作
     * @return  返回值boolean代表是否放行，true代表放行，false代表中止
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证登录
        String uri = request.getRequestURI();
        if (uri.indexOf("/login") >= 0) {
            return true;
        }

        // 获取session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER_SESSION");
        if (!StringUtils.isEmpty(username)){
            return true;
        }else {
            request.setAttribute("msg", "您还没有登录，请先登录！");
            //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            response.sendRedirect("login");
            return false;
        }

    }


    /**
     * 会在handler方法业务逻辑执行之后尚未跳转页面时执行
     * @param modelAndView  封装了视图和数据，此时尚未跳转页面呢，你可以在这里针对返回的数据和视图信息进行修改
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 页面已经跳转渲染完毕之后执行
     * @param ex  可以在这里捕获异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
