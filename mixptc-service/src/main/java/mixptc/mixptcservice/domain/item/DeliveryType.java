package mixptc.mixptcservice.domain.item;

public enum DeliveryType {
    //배송방식

    Proxy("대리배송"),SELF("직접배송"),ETC("기타");

    private final String description;


    DeliveryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
