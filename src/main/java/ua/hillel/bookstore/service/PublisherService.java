package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.dto.AuthorDTO;
import ua.hillel.bookstore.dto.PublisherDTO;
import ua.hillel.bookstore.mapper.PublisherMapper;
import ua.hillel.bookstore.model.Author;
import ua.hillel.bookstore.model.Publisher;
import ua.hillel.bookstore.repository.publisher.PublisherRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService extends GenericQueryDSL<Publisher> {

    public final PublisherRepository repository;
    private final PublisherMapper mapper;

    public List<PublisherDTO> getAllPublishers(){
        List<PublisherDTO> publisherDTOs = new ArrayList<>();
        repository.getAll().forEach(publisher -> publisherDTOs.add(mapper.toDTO(publisher)));
        return publisherDTOs;
    }

    public PublisherDTO get(Integer id) {
        return mapper.toDTO(repository.get(id));
    }

    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Publisher save(PublisherDTO publisher) {
        final Publisher entity = mapper.toEntity(publisher);
        return repository.save(entity);
    }
}
