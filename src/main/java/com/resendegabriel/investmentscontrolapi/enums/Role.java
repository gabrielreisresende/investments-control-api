package com.resendegabriel.investmentscontrolapi.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public enum Role {

    USER {
        @Override
        public Collection<? extends GrantedAuthority> getSimpleGrantedAuthority() {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    };

    public abstract Collection<? extends GrantedAuthority> getSimpleGrantedAuthority();
}
