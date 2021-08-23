package com.demobackend.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    Collection<? extends GrantedAuthority> getAuthorities(String email);
}
