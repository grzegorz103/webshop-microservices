package price.service.mappers;

import org.mapstruct.Mapper;
import price.service.events.PriceCreateRecvInfo;
import price.service.persistence.price.Price;
import price.service.services.price.PriceDTO;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDTO toDTO(Price price);

    Price toModel(PriceDTO priceDTO);

    PriceDTO toDTO(PriceCreateRecvInfo priceCreateRecvInfo);

}
