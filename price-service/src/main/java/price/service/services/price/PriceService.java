package price.service.services.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PriceService {

    PriceDTO getById(Long id);

    Page<PriceDTO> getAll(Pageable pageable);

    PriceDTO create(PriceDTO priceDTO);

    PriceDTO update(PriceDTO priceDTO);

    void delete(Long id);

}
