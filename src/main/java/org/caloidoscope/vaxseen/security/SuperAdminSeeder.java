package org.caloidoscope.vaxseen.security;

import org.caloidoscope.vaxseen.entity.Role;
import org.caloidoscope.vaxseen.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.caloidoscope.vaxseen.repository.UserRepository;

@Configuration
public class SuperAdminSeeder {

    @Bean
    public CommandLineRunner initSuperAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "superadmin@vaxseen.com";
            if (userRepository.findByEmail(email).isEmpty()) {
                User superAdmin = new User();
                superAdmin.setEmail(email);
                superAdmin.setPassword(passwordEncoder.encode("supersecurepassword"));
                superAdmin.setRole(Role.SUPER_ADMIN);
                superAdmin.setFirstName("Super Admin");
                superAdmin.setUsername("superadmin");
                userRepository.save(superAdmin);
                System.out.println("✅ Super Admin created.");
            } else {
                System.out.println("ℹ️ Super Admin already exists.");
            }
        };
    }
}