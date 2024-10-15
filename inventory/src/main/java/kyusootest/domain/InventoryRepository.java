package kyusootest.domain;

import java.util.Date;
import java.util.List;
import kyusootest.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "inventories",
    path = "inventories"
)
public interface InventoryRepository
    extends PagingAndSortingRepository<Inventory, Long> {
    @Query(
        value = "select inventory " +
        "from Inventory inventory " +
        "where(:productName is null or inventory.productName like %:productName%)"
    )
    List<Inventory> getStock(String productName, Pageable pageable);
}
