package com.ineuron.carservice.config;

import com.ineuron.carservice.constant.MessageConstant;
import com.ineuron.carservice.exception.InvalidDataException;
import com.ineuron.carservice.model.User;
import com.ineuron.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthProviderConfig implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final User user = userService.loadUserByUsername(username);

        System.out.println("===pass===emp001="+passwordEncoder.encode("emp001"));


        if (passwordEncoder.matches(password, user.getPassword())) {

            user.setLastLoginDate(LocalDateTime.now());
            userService.saveOrUpdate(user);

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    user, password, user.getAuthorities());
        }
        throw new InvalidDataException(MessageConstant.INVALID_USERNAME_PASSWORD);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
