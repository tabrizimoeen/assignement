package org.imdb.platform.technicalassignment.config;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestCounterFilter implements Filter {

    public static final AtomicLong COUNTER =
            new AtomicLong();

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        COUNTER.incrementAndGet();

        chain.doFilter(request, response);
    }
}