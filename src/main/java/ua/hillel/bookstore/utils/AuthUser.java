package ua.hillel.bookstore.utils;

import org.springframework.security.core.GrantedAuthority;
import ua.hillel.bookstore.persistence.dto.UserDTO;

import java.util.Collection;

public class AuthUser extends org.springframework.security.core.userdetails.User{

    private UserDTO userDTO;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public int getId() {
        return userDTO.getId();
    }
}
