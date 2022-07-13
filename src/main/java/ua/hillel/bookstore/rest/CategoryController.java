package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.CategoryDTO;
import ua.hillel.bookstore.persistence.dto.SubCategoryDTO;
import ua.hillel.bookstore.service.CategoryService;
import ua.hillel.bookstore.service.SubCategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/allSub")
    public ResponseEntity<List<SubCategoryDTO>> getSubCategories(List<CategoryDTO> categories) {
        List<SubCategoryDTO> subCategories = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
            for (CategoryDTO category : categories) {
                subCategories.addAll(subCategoryService.findByCategory(category.getId()));
            }
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(subCategoryService.findAll(), HttpStatus.OK);
        }
    }
    @GetMapping("/getById")
    public SubCategoryDTO getSubCategoryById(Integer subCategory) {
        return subCategoryService.get(subCategory);
    }
}
