package org.caloidoscope.vaxseen.config;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.service.UserService;
import org.caloidoscope.vaxseen.util.CurrentUserHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class CurrentUserInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request,
                             @Nullable HttpServletResponse response,
                             @Nullable Object handler) {
        User user = getCurrentUser();
        if (user != null) {
            CurrentUserHolder.set(user);
        }
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request,
                                @Nullable HttpServletResponse response,
                                @Nullable Object handler,
                                Exception ex) {
        CurrentUserHolder.clear();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            return userService.loadUserByUsername(user.getUsername());
        }
        return null;
    }
}
