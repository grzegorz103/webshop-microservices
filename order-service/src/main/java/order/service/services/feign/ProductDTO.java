package order.service.services.feign;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private String name;

    private Long price;

    private List<String> images;
}
