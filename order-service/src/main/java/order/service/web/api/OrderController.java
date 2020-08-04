package order.service.web.api;

import order.service.services.OrderDTO;
import order.service.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.orders}")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderDTO> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @GetMapping("/users")
    public Page<OrderDTO> getByUser(Pageable pageable){
        return orderService.getByUser(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO save(@RequestBody OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable("id") Long id,
                           @RequestBody OrderDTO orderDTO) {
        return orderService.update(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @DeleteMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromOrders(@PathVariable("productId") Long productId) {
        orderService.deleteProductFromOrders(productId);
    }

}
