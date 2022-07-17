package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.persistence.entity.CartItem;
import ua.hillel.bookstore.persistence.mapper.CartItemMapper;
import ua.hillel.bookstore.persistence.repository.CartItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository itemRepository;
    private final CartItemMapper itemMapper;

    public CartItemDTO getCartItem(Integer id) {
        return itemMapper.toDTO(itemRepository.getReferenceById(id));
    }

    public List<CartItemDTO> getCartItems(Integer userId) {
        return itemRepository.getByUserId(userId).stream()
                .map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public void clearCart(Integer id) {
        List<CartItem> items = itemRepository.getByUserId(id);
        itemRepository.deleteAll(items);
    }

    public void addToCart(UserDTO user, BookDTO bookDTO) {
        CartItem item = itemMapper.toEntity(new CartItemDTO(user, bookDTO));
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
