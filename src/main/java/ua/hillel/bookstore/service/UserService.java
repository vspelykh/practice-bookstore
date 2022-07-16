package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.persistence.mapper.UserMapper;
import ua.hillel.bookstore.persistence.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO getUser(int id){
        return mapper.toDTO(repository.getReferenceById(id));
    }
}
