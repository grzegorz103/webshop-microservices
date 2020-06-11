package price.service.persistence.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import price.service.mappers.PriceMapper;
import price.service.services.price.PriceDTO;

import java.math.BigDecimal;

@Service
public class PriceProviderImpl implements PriceProvider {

    private final PriceMapper priceMapper;

    private final PriceRepository priceRepository;

    public PriceProviderImpl(PriceMapper priceMapper,
                             PriceRepository priceRepository) {
        this.priceMapper = priceMapper;
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceDTO getById(Long id) {
        return priceMapper.toDTO(
                priceRepository.findById(id).orElseThrow(IllegalAccessError::new)
        );
    }

    @Override
    public Page<PriceDTO> getAll(Pageable pageable) {
        return priceRepository.findAll(pageable)
                .map(priceMapper::toDTO);
    }

    @Override
    public PriceDTO save(PriceDTO priceDTO) {
        return priceMapper.toDTO(
                priceRepository.save(
                        priceMapper.toModel(priceDTO)
                )
        );
    }

    @Override
    public void delete(Long id) {
        if (!priceRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }

        priceRepository.deleteById(id);
    }

    @Override
    public BigDecimal getProductPrice(Long productId) {
        BigDecimal productPrice = priceRepository.findProductPrice(productId);
        System.out.println(productPrice + " " + productId);
        return productPrice;
    }

}
