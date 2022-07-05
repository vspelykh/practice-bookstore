package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.CartDTO;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //TODO: userId for getCart
    @GetMapping("/rest/cart")
    public ResponseEntity<CartDTO> getCart(int id) {

        return new ResponseEntity<>(cartService.get(id), HttpStatus.OK);
    }

    @GetMapping("/addToCart")
    public void addToCart(@RequestParam("book") BookDTO book, @RequestParam int quantity) {
        cartService.addToCart(1, new CartItemDTO(null, book, quantity));
    }

    @GetMapping("/rest/cart/count")
    public int getCapacity(int id) {
        return cartService.getCartItems(id).size();
    }

    @GetMapping("/rest/cart/items")
    public List<CartItemDTO> getCartItems(int i) {
        return cartService.getCartItems(i);
    }
}
