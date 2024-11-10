package net.java.emsbackendsecurity.repository;

import net.java.emsbackendsecurity.entity.Role;
import net.java.emsbackendsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findByEmail(String email);

    User findByRole(Role role);
    UserDetails findByEmail(String email);

}
