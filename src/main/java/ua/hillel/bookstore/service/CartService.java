package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.CartItemMapper;
import ua.hillel.bookstore.persistence.mapper.CartMapper;
import ua.hillel.bookstore.persistence.repository.CartItemRepository;
import ua.hillel.bookstore.persistence.repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final CartItemRepository itemRepository;
    private final CartItemMapper itemMapper;
    private final CartMapper mapper;

    public CartDTO get(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public void clearCart(Integer id) {
        repository.getReferenceById(id).getItems().clear();
    }

    public void addToCart(Integer userId, CartItemDTO item) {
        repository.getReferenceById(userId).getItems().add(itemMapper.toEntity(item));
    }

    public List<CartItemDTO> getCartItems(int id){
        return repository.getReferenceById(id).getItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }
}
