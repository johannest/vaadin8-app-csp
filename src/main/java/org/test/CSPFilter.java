package org.test;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "CSPFilter")
public class CSPFilter implements javax.servlet.Filter {

    private String[] hashes = {
            "f91AD8UHA1EWIfeF6b5WVuny0nZigv9y2cmlXhcqb1Y=",
            "tVTYphMU4jCroNXhvIbrYnLWI7AAZSAP/5AiuVFTJuE=",
            "RmOirPgzujLV7KMudlEsreSakuCLzWJbNs6xHrsRGiY=",
            "xIfh6hdQpyEfEfB/p2rlsEV1gg1wJUSF5fe5CA9YLV0=",
            "+owkkYPQQydJP6xKNE9XFIomEWYkH9yfS0WgDQwr6Dc=",
            "ot3MJdaPEvVm+6p76iF1WL+S2kmgPsMYa9Z50OwVc6I="
    };

    public CSPFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            StringBuilder sb = new StringBuilder("script-src ");
            for (String hash : hashes) {
                sb.append("'").append("sha256-").append(hash).append("'").append(" ");
            }
            sb.append("'strict-dynamic';");
            ((HttpServletResponse) response).setHeader("Content-Security-Policy", sb.toString());
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
