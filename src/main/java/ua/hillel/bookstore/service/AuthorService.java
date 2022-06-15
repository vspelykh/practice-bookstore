package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.dto.AuthorDTO;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.mapper.AuthorMapper;
import ua.hillel.bookstore.model.Author;
import ua.hillel.bookstore.model.Book;
import ua.hillel.bookstore.repository.author.AuthorRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService extends GenericQueryDSL<Author> {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public List<AuthorDTO> getAllAuthors(){
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        repository.getAll().forEach(author -> authorDTOs.add(mapper.toDTO(author)));
        return authorDTOs;
    }

    public AuthorDTO get(Integer id) {
        return mapper.toDTO(repository.get(id));
    }

    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Author save(AuthorDTO author) {
        final Author entity = mapper.toEntity(author);
        return repository.save(entity);
    }
}
