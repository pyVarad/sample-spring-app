package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Roles;
import com.app.aeportal.domain.Users;
import com.app.aeportal.dto.request.UserRequestDto;
import com.app.aeportal.dto.response.UserResponseDto;
import com.app.aeportal.repository.RolesRepository;
import com.app.aeportal.repository.UserRepository;
import com.app.aeportal.services.UserService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RolesRepository rolesRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Users getUserForGivenId(Long id) {
        Optional<Users> users = this.userRepository.findById(id);
        return users.orElseThrow(() -> {
            throw new NoSuchElementFoundException("The user id is not valid.");
        });
    }

    private Roles getRoleForGivenId(Long id) {
        Optional<Roles> roles = this.rolesRepository.findById(id);
        return roles.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    private Collection<Roles> getAllRolesForTheGivenRequest(Collection<Long> roleIds) {
        return roleIds.stream().map(this::getRoleForGivenId).collect(Collectors.toList());
    }

    private Users createUserRequest(UserRequestDto request) {
        Users user = request.toUsers();
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        Collection<Roles> roles = this.getAllRolesForTheGivenRequest(request.getRoles());
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.userRepository.findByUsername(username);
        if(users == null) {
            throw new UsernameNotFoundException("The given username doesn't exist. Invalid username");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        users.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
        );
        User user = new User(users.getUsername(), users.getPassword(), authorities);
        return user;
    }

    @Override
    public UserResponseDto[] getUsers() {
        return new ModelMapper().map(this.userRepository.findAll(), UserResponseDto[].class);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return new ModelMapper().map(this.getUserForGivenId(id), UserResponseDto.class);
    }

    @Override
    public UserResponseDto addNewUser(UserRequestDto request) {
        Users user = this.createUserRequest(request);
        return new ModelMapper().map(this.userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUserInfo(Long id, UserRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Users user = this.getUserForGivenId(id);
        modelMapper.map(request.toUsers(), user);
        return new ModelMapper().map(this.userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public void deleteUserInfo(Long id) {
        Users user = this.getUserForGivenId(id);
        this.userRepository.delete(user);
    }

    @Override
    public UserResponseDto getUserByUserName(String username) {
        Users users = this.userRepository.findByUsername(username);
        if(users != null) {
            return new ModelMapper().map(users, UserResponseDto.class);
        }
        throw new NoSuchElementFoundException("The given username is not valid.");
    }
}
