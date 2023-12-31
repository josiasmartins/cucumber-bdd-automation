package bdd.automation.api.support.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private int petId;
    private int quantity = 2;
    private String shipDate;
    private String status;
    private boolean complete;

}
