package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.OrderDTO;
import ua.hillel.bookstore.persistence.entity.Status;
import ua.hillel.bookstore.rest.*;
import ua.hillel.bookstore.utils.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller()
@RequiredArgsConstructor
public class AdminController {

    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final OrderController orderController;
    private final CartController cartController;
    private final UserController userController;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("books", bookRestController.getAll().getBody());
        model.addAttribute("newOrders",
                orderController.getAllOrders(Status.NEW).getBody() == null ? 0 :
                        orderController.getAllOrders(Status.NEW).getBody().size());
        return "admin";
    }

    @PostMapping("/form/author")
    public String authorForm(Model model, @ModelAttribute("action") String action, @ModelAttribute("id") Integer id) {
        if (action.equals("edit")) {
            model.addAttribute("author", bookRestController.getById(id).getBody());
        }
        return "authorForm";
    }

    @PostMapping("/form/publisher")
    public String publisherForm(Model model, @ModelAttribute("action") String action, @ModelAttribute("id") Integer id) {
        if (action.equals("edit")) {
            model.addAttribute("author", bookRestController.getById(id).getBody());
        }
        return "publisherForm";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {

        model.addAttribute("orders", orderController.getAllOrders().getBody());
        model.addAttribute("statuses", Status.values());
        return "orders";
    }
    @GetMapping("/order/{id}")
    public String getOrder(Model model, @PathVariable("id") Integer orderId) {
        model.addAttribute("order", orderController.getOrder(orderId).getBody());
        model.addAttribute("statuses", Status.values());
        return "orderManage";
    }

    @GetMapping("/my-orders")
    public String userOrders(Model model){
        model.addAttribute("orders", Objects.requireNonNull(orderController.getUserOrders().getBody()));
        return "userOrders";
    }

    @GetMapping("/ordering")
    public String ordering(Model model) {

        List<CartItemDTO> cartItems = cartController.getCartItems();
        int totalSum = 0;
        for (CartItemDTO item : cartItems) {
            int itemSum = item.getBook().getPrice().intValue() * item.getQuantity();
            totalSum += itemSum;
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalSum", totalSum);
        return "orderForm";
    }

    @PostMapping("/order/create")
    public String createOrder(@ModelAttribute("name") String name, @ModelAttribute("surname") String surname,
                              @ModelAttribute("street") String street, @ModelAttribute("city") String city,
                              @ModelAttribute("region") String region, @ModelAttribute("zip") String zip,
                              @ModelAttribute("country") String country, @ModelAttribute("post") String post,
                              @ModelAttribute("comment") String comment) {

        List<CartItemDTO> cartItems = cartController.getCartItems();
        String address = "Street: " + street + ", city: " + city + ", region: " + region + ", postal: " + zip +
                ", " + country;
        OrderDTO
                order = new OrderDTO(null, userController.get(SecurityUtil.getFakeAuthUserId()), address, post
                , LocalDateTime.now(), comment);
        order.setStatus(Status.NEW);
        int sum = 0;
        for (CartItemDTO item : cartItems){
            sum += (item.getQuantity() * item.getBook().getPrice().intValue());
        }
        order.setSum(sum);
        orderController.createOrder(order, cartItems, userController.get(SecurityUtil.getFakeAuthUserId()));
        bookRestController.editAmountAfterOrdering(cartItems);
        cartController.cleanCart();
        return "successPage";
    }
}
