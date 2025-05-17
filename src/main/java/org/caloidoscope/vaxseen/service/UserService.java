package org.caloidoscope.vaxseen.service;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.dto.request.RegisterRequest;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.mapper.UserMapper;
import org.caloidoscope.vaxseen.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void saveUser(RegisterRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DataIntegrityViolationException ("User with this e-mail already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
