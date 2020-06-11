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
        priceRepository.save(new Price(1L, BigDecimal.TEN));
        priceRepository.save(new Price(2L, BigDecimal.ONE));
        priceRepository.save(new Price(3L, BigDecimal.TEN));
    }

}
