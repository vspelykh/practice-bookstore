package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.bookstore.dto.AuthorDTO;
import ua.hillel.bookstore.dto.PublisherDTO;
import ua.hillel.bookstore.model.Author;
import ua.hillel.bookstore.model.Publisher;
import ua.hillel.bookstore.service.AuthorService;
import ua.hillel.bookstore.service.PublisherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService service;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublisherDTO>> getAllAuthors(
            @RequestParam(required = false) String name) {

        List<PublisherDTO> publishers = service.getAllPublishers();
        publishers = publishers.stream()
                .filter(publisher -> name == null || publisher.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getById(@PathVariable Integer id) {
        return service != null
                ? new ResponseEntity<>(service.get(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping
    public Publisher createOrEditBook(@RequestBody PublisherDTO publisher) {
        return service.save(publisher);
    }
}