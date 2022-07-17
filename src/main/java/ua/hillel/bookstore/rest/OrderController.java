package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.OrderDTO;
import ua.hillel.bookstore.persistence.dto.OrderItemDTO;
import ua.hillel.bookstore.persistence.dto.UserDTO;
import ua.hillel.bookstore.persistence.entity.Status;
import ua.hillel.bookstore.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController("api-order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final UserController userController;

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {

        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/getAllByStatus")
    public ResponseEntity<List<OrderDTO>> getAllOrders(Status status) {

        return new ResponseEntity<>(service.getAllOrdersByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<OrderDTO> getOrder(Integer id) {

        return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/getItems")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(Integer orderId) {

        return new ResponseEntity<>(service.getOrderItemsByOrderId(orderId), HttpStatus.OK);
    }

    @PostMapping("/saveOrder")
    public OrderDTO createOrder(@RequestBody OrderDTO order, List<CartItemDTO> cartItems, UserDTO user) {
        order = service.saveOrder(order);
        List<OrderItemDTO> orderItems = new ArrayList<>();
        for (CartItemDTO cartItem : cartItems) {
            OrderItemDTO orderItem =
                    new OrderItemDTO(null, order, user, cartItem.getBook(), cartItem.getBook().getPrice().intValue());
            orderItems.add(orderItem);
        }
        service.saveOrderItems(orderItems);
        return order;
    }

    @PostMapping("/changeStatus")
    public OrderDTO changeOrderStatus(OrderDTO order, Status status) {
        order.setStatus(status);
        return service.saveOrder(order);
    }

    @GetMapping("/userOrders")
    public ResponseEntity<List<OrderDTO>> getUserOrders() {

        return new ResponseEntity<>(service.getAllOrders(userController.getAuthUserId()), HttpStatus.OK);
    }
}
