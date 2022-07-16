package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    public UserDTO get(Integer id){
        return service.getUser(id);
    }
}
