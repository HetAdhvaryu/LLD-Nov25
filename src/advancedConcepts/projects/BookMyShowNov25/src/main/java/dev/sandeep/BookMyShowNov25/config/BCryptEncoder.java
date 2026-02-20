package dev.sandeep.BookMyShowNov25.config;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder implements PasswordEncoder {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }
    // A -> encode(A) -> B
    // matches(A, B) -> true
    // matches(C, B) -> false

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
