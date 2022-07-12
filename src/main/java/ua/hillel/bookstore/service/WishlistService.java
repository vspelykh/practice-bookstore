package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.BookDTO;
import ua.hillel.bookstore.persistence.dto.WishlistItemDTO;
import ua.hillel.bookstore.persistence.entity.WishlistItem;
import ua.hillel.bookstore.persistence.mapper.UserMapper;
import ua.hillel.bookstore.persistence.mapper.WishlistItemMapper;
import ua.hillel.bookstore.persistence.repository.UserRepository;
import ua.hillel.bookstore.persistence.repository.WishlistItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistItemMapper mapper;
    private final WishlistItemRepository repository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public WishlistItemDTO getWishlistItem(Integer id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public List<WishlistItemDTO> getWishlistItems(Integer userId) {
        return repository.findAllByUserId(userId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public void clearWishlist(Integer userId) {
        repository.deleteAllByUserId(userId);
    }

    public void addToWishlist(Integer userId, BookDTO bookDTO) {
        WishlistItem item = mapper.toEntity(new WishlistItemDTO(userMapper.toDTO(
                userRepository.getReferenceById(userId)), bookDTO));
        List<WishlistItemDTO> wishlistItems = getWishlistItems(userId);
        for (WishlistItemDTO itemInWishlist : wishlistItems) {
            if (itemInWishlist.getBook().equals(bookDTO)) {
                return;
            }
        }
        repository.save(item);
    }

    public void deleteWishlistItem(int id) {
        repository.deleteById(id);
    }
}
