package ttsw.filopl.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by T. Filo Zegarlicki on 21.10.2022
 **/

@Component
class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("[preHandle] " + request.getMethod() + " " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}


