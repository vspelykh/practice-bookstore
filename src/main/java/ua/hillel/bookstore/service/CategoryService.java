package ua.hillel.bookstore.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CategoryDTO;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.entity.Category;
import ua.hillel.bookstore.persistence.mapper.CategoryMapper;
import ua.hillel.bookstore.persistence.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryDTO get(Integer id) {
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
    public Category save(CategoryDTO category) {
        final Category entity = mapper.toEntity(category);
        return repository.save(entity);
    }

    public List<CategoryDTO> findAll() {
        List<Category> entities = repository.findAll();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
