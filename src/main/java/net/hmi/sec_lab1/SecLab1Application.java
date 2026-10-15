package net.hmi.sec_lab1;

import net.hmi.sec_lab1.entity.AppUser;
import net.hmi.sec_lab1.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(SecLab1Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByEmail("admin@test.com").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setEmail("admin@test.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                repo.save(admin);
            }

            if (repo.findByEmail("user@test.com").isEmpty()) {
                AppUser user = new AppUser();
                user.setEmail("user@test.com");
                user.setPassword(encoder.encode("user123"));
                user.setRole("USER");
                repo.save(user);
            }
        };
    }
}