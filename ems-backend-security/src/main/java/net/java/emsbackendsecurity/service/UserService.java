package net.java.emsbackendsecurity.service;

import net.java.emsbackendsecurity.dto.EmployeeDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDetailsService userDetailsService();


}
