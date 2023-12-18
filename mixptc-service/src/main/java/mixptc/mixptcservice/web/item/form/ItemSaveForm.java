package mixptc.mixptcservice.web.item.form;

import lombok.Data;
import mixptc.mixptcservice.domain.item.DeliveryType;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    private Boolean open;//판매여부(체크박스)

    private DeliveryType deliveryType;//배송방식(라디오타입)

    private String genreCode; // 장르(셀렉트박스)
}
