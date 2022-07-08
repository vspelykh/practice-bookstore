package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.*;
import ua.hillel.bookstore.service.CartService;
import ua.hillel.bookstore.service.WishlistService;
import ua.hillel.bookstore.utils.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartAndWishlistController {

    private final CartService cartService;
    private final WishlistService wishlistService;

    //TODO: userId for getCart
    @GetMapping("/rest/cart")
    public ResponseEntity<CartDTO> getCart(int id) {

        return new ResponseEntity<>(cartService.get(id), HttpStatus.OK);
    }

    @GetMapping("/rest/cart/editQuantity")
    public void editQuantity(int id, int quantity){

        cartService.editQuantity(id, quantity);
    }

    @GetMapping("/rest/cart/count")
    public int getCapacity(int userId) {
        return cartService.getCartItems(userId).size();
    }

    @GetMapping("/rest/cart/items")
    public List<CartItemDTO> getCartItems(int userId) {
        return cartService.getCartItems(userId);
    }

    @GetMapping("/rest/wishlist/books")
    public List<WishlistItemDTO> getBooksFromWishlist(int userId){

        return wishlistService.getBooksFromWishlist(userId);
    }

    public int getCartSum(int userId) {
        int sum = 0;
        for (CartItemDTO item : cartService.getCartItems(userId)){
            sum += (item.getBook().getPrice().intValue() * item.getQuantity());
        }
        return sum;
    }

    public void addItemToCart(BookDTO book) {
        cartService.addToCart(SecurityUtil.getFakeAuthUserId(), book);
    }

    public void deleteFromCart(int id) {

        cartService.deleteCartItem(id);
    }
}
