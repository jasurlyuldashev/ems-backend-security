package net.java.emsbackendsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import net.java.emsbackendsecurity.dto.EmployeeDto;
import net.java.emsbackendsecurity.entity.Employee;
import net.java.emsbackendsecurity.entity.User;
import net.java.emsbackendsecurity.exeption.ResourceNotFoundException;
import net.java.emsbackendsecurity.mapper.EmployeeMapper;
import net.java.emsbackendsecurity.repository.UserRepository;
import net.java.emsbackendsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    @Override
    public UserDetailsService userDetailsService(){
        return repo::findByEmail;
    }


}
