package com.taskmanagement.service;

import com.taskmanagement.dto.UserReqDto;
import com.taskmanagement.enums.Role;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    public void adduser(UserReqDto userReqDto) {
        User user=new User();
        user.setUsername(userReqDto.username());
        user.setPassword(passwordEncoder.encode(userReqDto.password()));
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
    }
}
