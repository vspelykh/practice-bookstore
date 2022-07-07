package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.*;
import ua.hillel.bookstore.persistence.entity.Book;
import ua.hillel.bookstore.persistence.entity.Wishlist;
import ua.hillel.bookstore.persistence.entity.WishlistItem;
import ua.hillel.bookstore.persistence.mapper.BookMapper;
import ua.hillel.bookstore.persistence.mapper.WishlistItemMapper;
import ua.hillel.bookstore.persistence.mapper.WishlistMapper;
import ua.hillel.bookstore.persistence.repository.BookRepository;
import ua.hillel.bookstore.persistence.repository.WishlistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistMapper mapper;
    private final WishlistItemMapper itemMapper;
    private final WishlistRepository repository;

    public WishlistDTO get(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public void clearWishlist(Integer id) {
        repository.getReferenceById(id).getWishlistItems().clear();
    }

    public void addToWishlist(Integer userId, WishlistItemDTO item) {
        repository.getReferenceById(userId).getWishlistItems().add(itemMapper.toEntity(item));
    }

    public List<WishlistItemDTO> getBooksFromWishlist(int userId){
        return repository.getReferenceById(userId).getWishlistItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }
}
