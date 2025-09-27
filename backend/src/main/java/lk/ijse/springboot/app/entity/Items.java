package lk.ijse.springboot.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Items {

    @Id
    private String code;
    private String description;


    private Double unicPrice;
    private int QtyOnHand;

    public Items() {
    }

    public Items(String code, String description, Double unicPrice, int qtyOnHand) {
        this.setCode(code);
        this.setDescription(description);
        this.setUnicPrice(unicPrice);
        setQtyOnHand(qtyOnHand);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return "Items{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unicPrice=" + unicPrice +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}
