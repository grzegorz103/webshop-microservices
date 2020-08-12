package order.service.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByProductIdsIsContaining(Long productId);

    Page<Order> findAllByUserId(String userId, Pageable pageable);

    @Query("select o from Order o " +
            "order by " +
            "case when cast(o.orderState as text) like 'CREATED' then 1 else 2 end, " +
            " o.creationDate")
    Page<Order> findAllByDateDescSorted(Pageable pageable);

}
