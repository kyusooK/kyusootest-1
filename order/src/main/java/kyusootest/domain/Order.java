package kyusootest.domain;

import kyusootest.domain.OrderPlaced;
import kyusootest.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Order_table")
@Data

//<<< DDD / Aggregate Root
public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String productName;
    
    
    
    
    private String productId;
    
    
    
    
    private Integer qty;

    @PostPersist
    public void onPostPersist(){


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }



//<<< Clean Arch / Port Method
    public void placeorder(PlaceorderCommand placeorderCommand){
        
        //implement business logic here:
        

        // multi default
        List<Inventory> inventory = OrderApplication.applicationContext
            .getBean(kyusootest.external.InventoryService.class)
            .getStock(// getProductName(), getColor());
    }
//>>> Clean Arch / Port Method



}
//>>> DDD / Aggregate Root
