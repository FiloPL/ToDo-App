package ttsw.filopl.todoapp.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

/**
 * Created by T. Filo Zegarlicki on 03.11.2022
 **/

@Configuration
class MvcConfiguration implements WebMvcConfigurer {

    private Set<HandlerInterceptor> interceptors;

    MvcConfiguration(final Set<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        interceptors.forEach(registry::addInterceptor);
    }
}
