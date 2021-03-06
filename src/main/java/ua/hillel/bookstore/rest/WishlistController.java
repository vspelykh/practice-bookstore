package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.service.WishlistService;

import java.util.List;

@RestController("/api-wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserController userController;

    @GetMapping("/items")
    public List<WishlistItemDTO> getBooksFromWishlist() {

        return wishlistService.getWishlistItems(userController.getAuthUserId());
    }

    @PostMapping("/addToWish")
    public void addToWishlist(BookDTO book) {
        wishlistService.addToWishlist(userController.getAuthUserId(), book);
    }

    @DeleteMapping("/deleteFromWish")
    public void removeFromWishlist(Integer id) {
        wishlistService.deleteWishlistItem(id);
    }


}
