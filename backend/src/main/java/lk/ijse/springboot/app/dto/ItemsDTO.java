package lk.ijse.springboot.app.dto;

public class ItemsDTO {

    private String code;
    private String description;
    private Double unicPrice;
    private int QtyOnHand;

    public ItemsDTO() {
    }

    public ItemsDTO(String code, String description, Double unicPrice, int qtyOnHand) {
        this.setCode(code);
        this.setDescription(description);
        this.setUnicPrice(unicPrice);
        setQtyOnHand(qtyOnHand);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnicPrice() {
        return unicPrice;
    }

    public void setUnicPrice(Double unicPrice) {
        this.unicPrice = unicPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemsDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unicPrice=" + unicPrice +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}
