package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.BookMapper;
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

    public void addToCart(Integer cartId, BookDTO bookDTO) {
        CartItem item = itemMapper.toEntity(new CartItemDTO(mapper.toDTO(repository.getReferenceById(cartId)), bookDTO));
        item.setQuantity(1);
        itemRepository.save(item);

    }

    public List<CartItemDTO> getCartItems(int userId){
        return repository.getReferenceById(userId).getItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public void editQuantity(int id, int quantity) {
        CartItem cartItem = itemRepository.getReferenceById(id);
        cartItem.setQuantity(quantity);
        if (quantity == 0) {
            itemRepository.delete(cartItem);

        } else itemRepository.save(cartItem);
    }

    public void deleteCartItem(int id) {
        itemRepository.deleteById(id);
    }
}
