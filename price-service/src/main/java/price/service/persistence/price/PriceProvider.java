package price.service.persistence.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import price.service.services.price.PriceDTO;

import java.math.BigDecimal;

public interface PriceProvider {

    PriceDTO getById(Long id);

    Page<PriceDTO> getAll(Pageable pageable);

    PriceDTO save(PriceDTO priceDTO);

    void delete(Long id);

    BigDecimal getProductPrice(Long productId);

}
