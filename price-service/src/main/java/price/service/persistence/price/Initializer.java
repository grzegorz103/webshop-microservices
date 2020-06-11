package price.service.persistence.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class Initializer {

    private final PriceRepository priceRepository;

    public Initializer(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @PostConstruct
    public void init(){
        priceRepository.save(new Price(1L, BigDecimal.TEN, 4L));
        priceRepository.save(new Price(2L, BigDecimal.ONE, 5L));
        priceRepository.save(new Price(3L, BigDecimal.TEN, 6L));
    }

}
