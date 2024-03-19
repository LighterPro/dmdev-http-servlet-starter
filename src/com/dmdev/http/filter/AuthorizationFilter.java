package com.dmdev.http.filter;

import com.dmdev.http.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.dmdev.http.util.UrlPaths.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATHS = Set.of(LOGIN_URL, REGISTRATION_URL, IMAGES_URL);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(previousPage != null ? previousPage : LOGIN_URL);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return userDto != null;
    }
}
