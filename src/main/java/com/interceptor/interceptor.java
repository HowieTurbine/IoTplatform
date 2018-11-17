package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
            HttpSession session = request.getSession();
            String status = (String) session.getAttribute("status");
            System.out.println(status);
            if(status!=null){
                //登陆成功的用户
                return true;
            }else{
                //没有登陆，转向登陆界面
                try {
                    response.sendRedirect("/IoTPlatform/page/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
}
