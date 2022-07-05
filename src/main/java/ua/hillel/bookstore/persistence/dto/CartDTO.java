package ua.hillel.bookstore.persistence.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class CartDTO implements Serializable {
    private final Integer id;

}
