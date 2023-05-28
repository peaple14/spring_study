package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
//JPA에서 관리하고있다는뜻.
@Entity
public class Item {

    //@ID=pk라는뜻.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//db에서 값이 증가하는것.id++
    private Long id;

    //db에서 이런것들과 매핑된다는뜻.,camel과 언더스코어 자동변환.
    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() { //JPA는 기본생성자 필수.
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
