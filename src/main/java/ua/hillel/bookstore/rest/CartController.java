package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.service.CartService;

import java.util.List;

@RestController("/api-cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserController userController;


    @GetMapping("/editQuantity")
    public void editQuantity(int itemId, int quantity) {

        cartService.editQuantity(itemId, quantity);
    }

    @GetMapping("/count")
    public int getCapacity() {

        return cartService.getCartItems(userController.getAuthUserId()).size();
    }

    @GetMapping("/cartItems")
    public List<CartItemDTO> getCartItems() {
        return cartService.getCartItems(userController.getAuthUserId());
    }

    @GetMapping("/sum")
    public int getCartSum() {
        int sum = 0;
        for (CartItemDTO item : cartService.getCartItems(userController.getAuthUserId())) {
            sum += (item.getBook().getPrice().intValue() * item.getQuantity());
        }
        return sum;
    }

    @PostMapping("/addToCart")
    public void addItemToCart(BookDTO book) {
        cartService.addToCart(userController.getAuthUser(), book);
    }

    @DeleteMapping("/deleteFromCart")
    public void deleteFromCart(int id) {

        cartService.deleteCartItem(id);
    }

    @PostMapping("/cleanCart")
    public void cleanCart() {
        cartService.clearCart(userController.getAuthUserId());
    }
}
