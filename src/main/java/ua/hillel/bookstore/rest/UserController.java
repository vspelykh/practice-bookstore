package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/getUser")
    public UserDTO get(Integer id) {
        return service.getUser(id);
    }

    @GetMapping("/getAuthUser")
    public Integer getAuthUserId() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof User){
            username = ((User) principal).getUsername();
        }
        else if (principal instanceof Principal) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (username.equals("anonymousUser")){
            return 0;
        }
        return service.getUserByEmail(username).getId();
    }
}