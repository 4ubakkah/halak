package com.halak.service.impl;

import com.halak.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//TODO add unit test
@Service
public class KalahUserDetailsServiceServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(userName)
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.getUsername(),
                        u.getPassword(),
                        u.isEnabled(),
                        u.isEnabled(),
                        u.isEnabled(),
                        u.isEnabled(),
                        AuthorityUtils.createAuthorityList(
                                u.getAuthorities()
                                        .stream()
                                        .map(r -> "ROLE_" + r.getName().toUpperCase())
                                        .collect(Collectors.toList())
                                        .toArray(new String[]{}))))
                .orElseThrow(() -> new UsernameNotFoundException("No user with "
                        + "the name " + userName + "was found in the database"));
    }
}
