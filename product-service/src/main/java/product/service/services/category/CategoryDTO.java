package product.service.services.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import product.service.services.product.ProductDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CategoryDTO {

    private Long id;

    private String name;

    private List<ProductDTO> products;

}
