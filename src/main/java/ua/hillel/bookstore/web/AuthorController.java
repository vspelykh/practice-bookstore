package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.model.Author;
import ua.hillel.bookstore.repository.author.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository repository;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Author>> getAll(){
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }
}
