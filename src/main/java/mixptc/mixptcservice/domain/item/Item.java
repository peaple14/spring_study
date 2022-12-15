package mixptc.mixptcservice.domain.item;

import lombok.Data;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private Boolean open;//판매여부(체크박스)
    private DeliveryType deliveryType;//배송방식(라디오타입)
    private String genreCode; // 장르(셀렉트박스)


    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
