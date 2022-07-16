package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.entity.Cart;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.CartItemMapper;
import ua.hillel.bookstore.persistence.mapper.CartMapper;
import ua.hillel.bookstore.persistence.repository.CartItemRepository;
import ua.hillel.bookstore.persistence.repository.CartRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final CartItemRepository itemRepository;
    private final CartItemMapper itemMapper;
    private final CartMapper mapper;

    public CartDTO getCart(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public CartItemDTO getCartItem(Integer id) {
        return itemMapper.toDTO(itemRepository.getReferenceById(id));
    }

    public List<CartItemDTO> getCartItems(Integer cartId) {
        return repository.getReferenceById(cartId).getItems().stream()
                .map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public void clearCart(Integer id) {
        Cart cart = repository.getReferenceById(id);
        Set<CartItem> items = cart.getItems();
        itemRepository.deleteAll(items);
    }

    public void addToCart(Integer cartId, BookDTO bookDTO) {
        CartItem item = itemMapper.toEntity(new CartItemDTO(mapper.toDTO(repository.getReferenceById(cartId)), bookDTO));
        item.setQuantity(1);
        itemRepository.save(item);

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
