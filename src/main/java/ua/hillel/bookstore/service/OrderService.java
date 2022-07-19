package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.OrderDTO;
import ua.hillel.bookstore.persistence.dto.OrderItemDTO;
import ua.hillel.bookstore.persistence.entity.Status;
import ua.hillel.bookstore.persistence.mapper.OrderItemMapper;
import ua.hillel.bookstore.persistence.mapper.OrderMapper;
import ua.hillel.bookstore.persistence.repository.OrderItemRepository;
import ua.hillel.bookstore.persistence.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper mapper;
    private final OrderItemMapper itemMapper;
    private final OrderRepository repository;
    private final OrderItemRepository itemRepository;

    public List<OrderDTO> getAllOrders() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByStatus(Status status) {
        return repository.findAllByStatus(status).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int id) {
        return mapper.toDTO(repository.getReferenceById(id));
    }

    public List<OrderItemDTO> getOrderItemsByOrderId(int id) {
        return itemRepository.getAllByOrderId(id).stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO saveOrder(OrderDTO order) {
        return mapper.toDTO(repository.save(mapper.toEntity(order)));
    }

    public void saveOrderItems(List<OrderItemDTO> orderItems) {
        itemRepository.saveAll(orderItems.stream().map(itemMapper::toEntity).collect(Collectors.toList()));
    }

    public List<OrderDTO> getAllOrders(int userId) {
        return repository.getOrdersByUserId(userId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
