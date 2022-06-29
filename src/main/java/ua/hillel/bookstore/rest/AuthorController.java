package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.persistence.dto.AuthorDTO;
import ua.hillel.bookstore.persistence.entity.Author;
import ua.hillel.bookstore.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(
            @RequestParam(required = false) String name) {

        List<AuthorDTO> authors = service.getAllAuthors();
        authors = authors.stream()
                .filter(author -> name == null || author.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Integer id) {
        return service != null
                ? new ResponseEntity<>(service.get(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping
    public Author createOrEditBook(@RequestBody AuthorDTO author) {
        return service.save(author);
    }
}
