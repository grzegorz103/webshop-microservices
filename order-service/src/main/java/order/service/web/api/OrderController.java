package order.service.web.api;

import order.service.services.OrderDTO;
import order.service.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderDTO> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PostMapping
    public OrderDTO save(@RequestBody OrderDTO orderDTO){
        return orderService.create(orderDTO);
    }

}
