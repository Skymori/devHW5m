package ua.goit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete = false;

}
