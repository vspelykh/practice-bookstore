package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.SubCategoryDTO;
import ua.hillel.bookstore.persistence.entity.SubCategory;
import ua.hillel.bookstore.persistence.mapper.SubCategoryMapper;
import ua.hillel.bookstore.persistence.repository.SubCategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository repository;
    private final SubCategoryMapper mapper;

    public SubCategoryDTO get(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public boolean delete(Integer id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public SubCategory save(SubCategoryDTO subCategory) {
        final SubCategory entity = mapper.toEntity(subCategory);
        return repository.save(entity);
    }

    public List<SubCategoryDTO> findAll() {
        List<SubCategory> entities = repository.findAll();
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<SubCategoryDTO> findByCategory(int categoryId){
        return repository.getAllByCategory(categoryId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
