package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.AuthorDTO;
import ua.hillel.bookstore.persistence.entity.Author;
import ua.hillel.bookstore.persistence.mapper.AuthorMapper;
import ua.hillel.bookstore.persistence.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService extends GenericQueryDSL<Author> {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        repository.findAll().forEach(author -> authorDTOs.add(mapper.toDTO(author)));
        return authorDTOs;
    }

    public AuthorDTO get(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public boolean delete(Integer id) {
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Author save(AuthorDTO author) {
        final Author entity = mapper.toEntity(author);
        return repository.save(entity);
    }
}
