package ttsw.filopl.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by T. Filo Zegarlicki on 21.10.2022
 **/

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class LoggerFilter implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if( request instanceof HttpServletRequest) {
            var httpRequest = (HttpServletRequest) request;
            logger.info("[doFilter] " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
        }
        chain.doFilter(request, response);
    }

}
