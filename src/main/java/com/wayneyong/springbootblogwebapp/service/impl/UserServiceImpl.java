package com.wayneyong.springbootblogwebapp.service.impl;

import com.wayneyong.springbootblogwebapp.dto.RegistrationDto;
import com.wayneyong.springbootblogwebapp.entity.Role;
import com.wayneyong.springbootblogwebapp.entity.User;
import com.wayneyong.springbootblogwebapp.repository.RoleRepository;
import com.wayneyong.springbootblogwebapp.repository.UserRepository;
import com.wayneyong.springbootblogwebapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {

        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        //use spring security to encrypt the password
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
