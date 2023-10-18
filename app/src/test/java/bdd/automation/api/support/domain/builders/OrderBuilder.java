package bdd.automation.api.support.domain.builders;

import bdd.automation.api.support.domain.Order;

public class OrderBuilder {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public OrderBuilder() {
        this.reset();
    }

    public OrderBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public OrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public Order builder() {
        return new Order(
                id,
                petId,
                quantity,
                shipDate,
                status,
                complete
        );
    }

    public void reset() {
        id = 1;
        petId = 30;
        quantity = 1;
        shipDate = "20/02/21";
        status = "approvade";
        complete = true;
    }

}
