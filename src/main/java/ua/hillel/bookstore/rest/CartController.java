package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.service.CartService;
import ua.hillel.bookstore.utils.SecurityUtil;

import java.util.List;

import static ua.hillel.bookstore.utils.SecurityUtil.getFakeAuthUserId;

@RestController("/api-cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/getCart")
    public ResponseEntity<CartDTO> getCart(){

        return new ResponseEntity<>(cartService.getCart(SecurityUtil.getFakeAuthUserId()), HttpStatus.OK);
    }

    @GetMapping("/editQuantity")
    public void editQuantity(int itemId, int quantity){

        cartService.editQuantity(itemId, quantity);
    }

    @GetMapping("/count")
    public int getCapacity() {

        return cartService.getCartItems(getFakeAuthUserId()).size();
    }

    @GetMapping("/cartItems")
    public List<CartItemDTO> getCartItems() {
        return cartService.getCartItems(getFakeAuthUserId());
    }

    @GetMapping("/sum")
    public int getCartSum() {
        int sum = 0;
        for (CartItemDTO item : cartService.getCartItems(getFakeAuthUserId())){
            sum += (item.getBook().getPrice().intValue() * item.getQuantity());
        }
        return sum;
    }

    @PostMapping("/addToCart")
    public void addItemToCart(BookDTO book) {
        cartService.addToCart(getFakeAuthUserId(), book);
    }

    @DeleteMapping("/deleteFromCart")
    public void deleteFromCart(int id) {

        cartService.deleteCartItem(id);
    }

    @PostMapping("/cleanCart")
    public void cleanCart() {
        cartService.clearCart(SecurityUtil.getFakeAuthUserId());
    }
}
