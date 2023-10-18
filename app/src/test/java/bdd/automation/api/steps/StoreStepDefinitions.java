package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.domain.Order;
import bdd.automation.api.support.domain.Pet;
import bdd.automation.api.support.domain.builders.OrderBuilder;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.runner.manipulation.Orderable;

public class StoreStepDefinitions {

    PetApi petApi;
    StoreApi storeApi;
    Pet expectedPet;
    Order expectedOrder;

    public StoreStepDefinitions() {
        petApi = new PetApi();
        storeApi = new StoreApi();
    }

//    @Given("something...")
//    public void something() {
//        Store store1 = new StoreBuilder().builder();
//        Store store2 = new StoreBuilder()
//                .withId(1)
//                .withPetId(23)
//                .withQuantity(23)
//                .withComplete(true)
//                .withShipDate("11/01/2021")
//                .withStatus("pending")
//                .builder();
//
//        Store store3 = new StoreBuilder()
//                .withQuantity(40)
//                .builder();
//
//        Store store4 = new StoreBuilder().builder();
//
//        System.out.println("builder");
//    }

    @Dado("que eu possua animal {word}")
    public void queEuPossuaAnimalAvailable(String status) {
        Pet pet = Pet.builder()
                .id(77)
                .status(status).build();

        expectedPet = petApi.createPet(pet);

    }

    @Quando("faço o pedido desse animal")
    public void façoOPedidoDesseAnimal() {
        Order order = new OrderBuilder()
                .withId(222)
                .withPetId(expectedPet.getId())
                .builder();

        expectedOrder = storeApi.createOrder(order);
    }

    @Entao("o pedido é aprovado")
    public void oPedidoÉAprovado() {
        Response actualOrderResponse = storeApi.getOrder(expectedOrder.getId());
        actualOrderResponse
                .then()
                .body(
                        "id", CoreMatchers.is(expectedOrder.getId()),
                        "petId", CoreMatchers.is(expectedPet.getId()),
                        "quantity", CoreMatchers.is(expectedOrder.getQuantity()),
                        "status", CoreMatchers.is("approved")
                );
    }
}
