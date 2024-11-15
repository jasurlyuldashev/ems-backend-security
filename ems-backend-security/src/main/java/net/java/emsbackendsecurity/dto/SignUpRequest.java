package net.java.emsbackendsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
