package product.service.services.category;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import product.service.services.product.ProductDTO;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CategoryDTO {

    private Long id;

    @NotBlank
    @Length(max = 250)
    private String name;

    private List<ProductDTO> products;

}
