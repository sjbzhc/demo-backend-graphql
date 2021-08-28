package com.demobackend.demo.security;

import com.demobackend.demo.domain.exceptions.ResourceNotFoundException;
import com.demobackend.demo.models.User;
import com.demobackend.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String email) {
        return userRepository.getRoles(email).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public UserDetails loadUserById(String id) {
        User user = userRepository.findOptionalById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
