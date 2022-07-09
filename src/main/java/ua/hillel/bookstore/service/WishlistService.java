package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.WishlistDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.persistence.entity.Wishlist;
import ua.hillel.bookstore.persistence.entity.WishlistItem;
import ua.hillel.bookstore.persistence.mapper.WishlistItemMapper;
import ua.hillel.bookstore.persistence.mapper.WishlistMapper;
import ua.hillel.bookstore.persistence.repository.WishlistItemRepository;
import ua.hillel.bookstore.persistence.repository.WishlistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistMapper mapper;
    private final WishlistItemMapper itemMapper;
    private final WishlistRepository repository;
    private final WishlistItemRepository itemRepository;

    public WishlistDTO getWishlist(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public WishlistItemDTO getWishlistItem(Integer id) {
        return itemMapper.toDTO(itemRepository.getReferenceById(id));
    }

    public List<WishlistItemDTO> getWishlistItems(Integer wishlistId) {
        return repository.getReferenceById(wishlistId).getWishlistItems().stream()
                .map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public void clearWishlist(Integer id) {
        Wishlist wishlist = repository.getReferenceById(id);
        wishlist.getWishlistItems().clear();
        repository.save(wishlist);
    }

    public void addToWishlist(Integer wishlistId, BookDTO bookDTO) {
        WishlistItem item = itemMapper.toEntity(new WishlistItemDTO(mapper.toDTO(repository.getReferenceById(wishlistId)), bookDTO));

        List<WishlistItemDTO> wishlistItems = getWishlistItems(wishlistId);
        for (WishlistItemDTO itemInWishlist : wishlistItems) {
            if (itemInWishlist.getBook().equals(bookDTO)) {
                return;
            }
        }
        itemRepository.save(item);
    }

    public void deleteWishlistItem(int id) {
        itemRepository.deleteById(id);
    }
}
