package ua.hillel.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.hillel.bookstore.persistence.dto.CartItemDTO;
import ua.hillel.bookstore.persistence.dto.OrderDTO;
import ua.hillel.bookstore.persistence.mapper.UserMapper;
import ua.hillel.bookstore.persistence.repository.UserRepository;
import ua.hillel.bookstore.rest.*;
import ua.hillel.bookstore.utils.SecurityUtil;

import java.util.List;

@Controller()
@RequiredArgsConstructor
public class AdminController {

    private final BookRestController bookRestController;
    private final CategoryController categoryController;
    private final PublisherController publisherController;
    private final OrderController orderController;
    private final CartController cartController;
    //TODO userController or userId in UserDTO, Status in OrderDTO
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("books", bookRestController.getAll().getBody());

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

    @GetMapping("getAllOrders")
    public String getOrders(Model model){

        model.addAttribute("orders", orderController.getAllOrders());
        return "orders";
    }

    @GetMapping("/ordering")
    public String ordering(){

        return "orderForm";
    }

    @PostMapping("/order/create")
    public String createOrder(@ModelAttribute("name") String name, @ModelAttribute("address") String address,
                              @ModelAttribute("post") String post){

        List<CartItemDTO> cartItems = cartController.getCartItems();
        OrderDTO order = new OrderDTO(null, userMapper.toDTO(userRepository.getReferenceById(SecurityUtil.getFakeAuthUserId()))
        , address, post);
        order.setStatus("NEW");
        orderController.createOrder(order, cartItems);
        return "successPage";
    }
}
