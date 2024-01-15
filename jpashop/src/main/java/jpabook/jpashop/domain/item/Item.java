package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//InheritanceType 1.Single Table 2.Table Per Class 3. Joined
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    //도메인 설계시 Entity 자체에 해결할 수 있는 것들은 Entity안에 비즈니스 로직을 넣는것이 좋음
    //===비즈니스 로직===//
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 감소시에는 0보다 작아서는 안된다는걸 유의하자
    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0)
        {
            throw  new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;

    }
    
}
