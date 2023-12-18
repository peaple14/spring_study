package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
/*fast:빠른거
* normal:평범한것
* slow:느린거*/
@Data
@AllArgsConstructor
public class DeliveryCode {
    private String code; //시스템상 쓰는것
    private String displayName; //고객에게 보여주기용
}
