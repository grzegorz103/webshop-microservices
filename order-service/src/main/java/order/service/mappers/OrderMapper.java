package order.service.mappers;

import order.service.persistence.Order;
import order.service.services.OrderDTO;
import order.service.services.feign.OrderOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toModel(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);

    OrderOut toOrderOut(Order order);

}
