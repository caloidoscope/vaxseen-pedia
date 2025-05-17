package org.caloidoscope.vaxseen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.caloidoscope.vaxseen.dto.request.LoginRequest;
import org.caloidoscope.vaxseen.dto.request.RegisterRequest;
import org.caloidoscope.vaxseen.entity.Role;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.mapper.UserMapper;
import org.caloidoscope.vaxseen.repository.UserRepository;
import org.caloidoscope.vaxseen.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    private String jwtToken;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        // Pre-register a user for login testing
        User user = new User();
        user.setEmail("superadmin@gmail.com");
        user.setUsername("superadmin");
        user.setPassword(passwordEncoder.encode("supersecurepassword"));
        user.setRole(Role.SUPER_ADMIN);
        userRepository.save(user);

        jwtToken = jwtUtil.generateToken("superadmin");
    }

    @Test
    void testRegisterUser() throws Exception {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setPassword("newpass");
        newUser.setEmail("newuser@gmail.com");
        newUser.setRole(Role.PARENT);
        RegisterRequest newUserRequest = userMapper.toRegisterRequest(newUser);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserRequest))
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest("superadmin", "supersecurepassword");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void testLoginFailure() throws Exception {
        LoginRequest loginRequest = new LoginRequest("wronguser", "wrongpass");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testLguAdminRegisterSuperAdminFailure() throws Exception {
        User user = new User();
        user.setEmail("lguadmin@gmail.com");
        user.setUsername("lguadmin");
        user.setPassword(passwordEncoder.encode("lguadmin"));
        user.setRole(Role.LGU_ADMIN);
        userRepository.save(user);

        jwtToken = jwtUtil.generateToken("lguadmin");

        User newUser = new User();
        newUser.setEmail("superadmin1@gmail.com");
        newUser.setUsername("superadmin1");
        newUser.setPassword("superadmin1");
        newUser.setRole(Role.SUPER_ADMIN);
        RegisterRequest newUserRequest = userMapper.toRegisterRequest(newUser);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserRequest))
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isForbidden())
                .andExpect(content().string("You are not allowed to register a user with the role SUPER_ADMIN"));
    }

    @Test
    void testExistingUserRegisterFailure() throws Exception {
        User newUser = new User();
        newUser.setEmail("lguadmin@gmail.com");
        newUser.setUsername("lguadmin");
        newUser.setPassword(passwordEncoder.encode("lguadmin"));
        newUser.setRole(Role.LGU_ADMIN);
        RegisterRequest newUserRequest = userMapper.toRegisterRequest(newUser);
        userRepository.save(newUser);
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserRequest))
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isConflict())
                .andExpect(content().string("Username already exists."));
    }
}
