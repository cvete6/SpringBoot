package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Order {

    private String pizzaType;

    private String clientName;

    private String clientAddress;

    private String pizzaSize;

    private Long orderId;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {

        return orderId;
    }

    public Order() {

    }

    public Order(String pizzaType, String clientName, String clientAddress, Long OrderId) {
        this.pizzaType = pizzaType;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = OrderId;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType=pizzaType;
    }

    public String getPizzaType() {
        return pizzaType;
    }
}
