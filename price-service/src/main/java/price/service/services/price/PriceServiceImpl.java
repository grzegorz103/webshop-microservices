package price.service.services.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import price.service.persistence.price.PriceProvider;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceProvider priceProvider;

    public PriceServiceImpl(PriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

    @Override
    public PriceDTO getById(Long id) {
        return priceProvider.getById(id);
    }

    @Override
    public Page<PriceDTO> getAll(Pageable pageable) {
        if (pageable.isUnpaged()) {
            return priceProvider.getAll(PageRequest.of(0, 10));
        }

        return priceProvider.getAll(pageable);
    }

    @Override
    public PriceDTO create(PriceDTO priceDTO) {
        Objects.requireNonNull(priceDTO);
        return priceProvider.save(priceDTO);
    }

    @Override
    public PriceDTO update(PriceDTO priceDTO) {
        Objects.requireNonNull(priceDTO);
        return priceProvider.save(priceDTO);
    }

    @Override
    public void delete(Long id) {
        priceProvider.delete(id);
    }

    @Override
    public BigDecimal getProductPrice(Long productId) {
        return priceProvider.getProductPrice(productId);
    }

}
