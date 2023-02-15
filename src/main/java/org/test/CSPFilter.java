package org.test;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "CSPFilter")
public class CSPFilter implements javax.servlet.Filter {
    public static final String POLICY = "strict-dynamic;";

    public CSPFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            System.out.println(".");
            ((HttpServletResponse) response).setHeader("Content-Security-Policy", "strict-dynamic;");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
