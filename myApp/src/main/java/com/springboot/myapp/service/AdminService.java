package com.springboot.myapp.service;

import com.springboot.myapp.dto.AdminAddDto;
import com.springboot.myapp.enums.Role;
import com.springboot.myapp.model.User;
import com.springboot.myapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public void addAdmin(AdminAddDto adminAdddto) {
        User user=new User();
        user.setUsername(adminAdddto.username());
        user.setPassword(passwordEncoder.encode(adminAdddto.password()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public long getCount() {
        return userRepository.count();
    }
}
