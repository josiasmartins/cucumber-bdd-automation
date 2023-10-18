package bdd.automation.api.steps;

import bdd.automation.api.support.domain.Store;
import bdd.automation.api.support.domain.builders.StoreBuilder;
import io.cucumber.java.en.Given;

public class StoreStepDefinitions {

    @Given("something...")
    public void something() {
        Store store1 = new StoreBuilder().builder();
        Store store2 = new StoreBuilder()
                .withId(1)
                .withPetId(23)
                .withQuantity(23)
                .withComplete(true)
                .withShipDate("11/01/2021")
                .withStatus("pending")
                .builder();

        Store store3 = new StoreBuilder()
                .withQuantity(40)
                .builder();

        Store store4 = new StoreBuilder().builder();

        System.out.println("builder");
    }

}
