package com.combat.arena.services.security.utils;

import com.combat.arena.core.Role;
import com.combat.arena.core.User;
import com.combat.arena.services.security.AuthoritiesConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.util.StringUtils;

import java.util.Collection;

@Slf4j
public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static boolean hasRole(String roleName) {
        User user = getCurrentUser();
        return user != null && user.getRoles().contains(new Role(roleName));
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        return getCurrentUserName(authentication);
    }

    private static String getCurrentUserName(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String userName) {
            return userName;
        } else {
            return null;
        }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) return false;
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAdministrator() {
        return isUserHasRole("ROLE_ADMIN");
    }

    public static boolean isAdministrator(User user) {
        return isUserHasRole(user, AuthoritiesConstants.ADMIN);
    }

    public static boolean isUserHasRole(String roleName) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getAuthorities().contains(new Role(roleName));
        }
        return false;
    }

    public static boolean isUserHasRole(User user, String roleName) {
        if (user == null) return false;
        return user.getRoles()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .contains(roleName);
    }

    public static String getIPAddress(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = httpServletRequest.getRemoteAddr();
        }
        if (ip.contains(",")) {
            // removing second of all remaining IP addresses from the chain
            ip = StringUtils.substringBefore(ip, ",");
        }
        return ip;
    }
}