package org.caloidoscope.vaxseen.controller;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.dto.request.RegisterRequest;
import org.caloidoscope.vaxseen.dto.response.AuthResponse;
import org.caloidoscope.vaxseen.dto.request.LoginRequest;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.util.JwtUtil;
import org.caloidoscope.vaxseen.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return ResponseEntity.ok(new AuthResponse("Login successful",
                jwtUtil.generateToken(request.username())));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest userRequest, @AuthenticationPrincipal User requestor) {
        if(!requestor.getRole().canRegister(userRequest.role())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to register a user with the role " + userRequest.role());
        }
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User registered successfully!");
    }
}
