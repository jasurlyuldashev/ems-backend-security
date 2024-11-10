package net.java.emsbackendsecurity;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import net.java.emsbackendsecurity.entity.Role;
import net.java.emsbackendsecurity.entity.User;
import net.java.emsbackendsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;

@SpringBootApplication
public class EmsBackendSecurityApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmsBackendSecurityApplication.class, args);



    }

    @Override
    public void run(String... args) throws Exception {
       User admin = userRepository.findByRole(Role.ADMIN);
       if(admin == null) {
           User user = new User();

           user.setEmail("admin@gmail.com");
           user.setRole(Role.ADMIN);
           user.setFirstName("admin");
           user.setLastName("admin");
           user.setPassword(new BCryptPasswordEncoder().encode("admin"));
           userRepository.save(user);
       }
    }
}
