package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class PetStepDefinitions {

    private PetApi petApi;
    private List<Pet> actualPets;
    private Response actualPetsResponse;

    public PetStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que eu possua animais {word}")
    public void thatIHavePetsAvailable(String status) {}

    @Quando("eu pesquiso por todos os animais {word}")
    public void iSearchForAllPetsAvailable(String status) {
        actualPets = petApi.getPetsByStatus(status);
    }

    @Entao("eu recebo a lista de animais available")
    public void iReceiveAListOfPetsAvailable() {
        assertThat(actualPets, Matchers.is(Matchers.not(Matchers.empty())));
    }

    @E("eu recebo uma outra lista de animais {word}")
    public void iReceiveAnotherListOfPetsAvailable(String status) {
        Response actualPetsResponse = petApi.getPetsResponseByStatus(status);

        actualPets = actualPetsResponse.body().jsonPath().getList("", Pet.class);

        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "size()", Matchers.is(actualPets.size()),
                        "findAll { it.status == '"+status+"' }.size()", Matchers.is(actualPets.size())
                );

    }

    @Então("eu recebo a lista com {} animal/animais")
    public void iReceiveAListOfPets(int petsQuantity) {
        assertThat(actualPets.size(), Matchers.is(petsQuantity));
    }

    @Dado("que eu não possua animais {word}")
    public void thatIdontHavePets(String status) {
        petApi.deletePetsByStatus(status);
    }

    @Quando("pesquiso por todos os animais {word}")
    public void iDoASearchForAllPetsAvailable(String status) {
        actualPetsResponse = petApi.getPetsResponseByStatus(status);
    }

    @Entao("recebo a lista com {int} animais {word}")
    public void iReceiveAListOfPetsAvailable(int petsQuantity, String status) {
        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "size()", Matchers.is(petsQuantity),
                        "findAll { it.status == '" + status + "' }.size()", is(petsQuantity)
                );

    }

    @E("{int} animais possuem o nome {word}")
    public void petsHasTheNameLion(int petsQuantity, String petName) {
        actualPetsResponse.
                then().
                body(
                        "findAll { it.name.contains('"+petName+"') }.size()", Matchers.is(petsQuantity)
                );
    }
}