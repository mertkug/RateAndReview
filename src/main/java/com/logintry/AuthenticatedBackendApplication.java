package com.logintry;

import com.logintry.models.ApplicationUser;
import com.logintry.models.Post;
import com.logintry.models.Role;
import com.logintry.repository.RoleRepository;
import com.logintry.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticatedBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatedBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
        return args ->{
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            Set<Post> posts = new HashSet<>();

            ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("password"), roles, posts);

            userRepository.save(admin);
        };
    }

}
