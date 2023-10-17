package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;

public class PetStepDefinitions {

    private PetApi petApi;
    private List<Pet> actualPets;
    public PetStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que eu possua animais {word}")
    public void queEuPossuaAnimaisAvailable(String status) {
    }


    @Quando("eu pesquiso por todos os animais {word}")
    public void euPesquisoPorTodosOsAnimaisAvailable(String status) {
        actualPets = petApi.getPetsByStatus(status);
    }


    @Entao("eu recebo a lista de animais available")
    public void euReceboAListaDeAnimaisAvailable() {
        MatcherAssert.assertThat(actualPets, Matchers.is(Matchers.not(Matchers.empty())));
    }

    @E("eu recebo uma outra lista de animais {word}")
    public void euReceboUmaOutraListaDeAnimaisAvailable(String status) {
        Response actualPetsResponse = petApi.getPetsResponseByStatus(status);

        actualPets = actualPetsResponse.body().jsonPath().getList("", Pet.class);

        actualPetsResponse
                .then()
                    .statusCode(HttpStatus.SC_OK).
                body(
                    "size()", Matchers.is(actualPets.size()),
                        // findAll { it.status == 'available' }.size() faz iteracao e verifica se o status é igual a 'status' e retorne o tamanho
                        "findAll { it.status == 'available' }.size()", Matchers.is(actualPets.size())
                );
    }

    @Entao("eu recebo a lista com {} animal/animais")
    public void euReceboAListaComAnimais(int petsQuantity) {
        MatcherAssert.assertThat(actualPets.size(), Matchers.is(petsQuantity));
    }

    @Dado("que eu não possua animais {word}")
    public void queEuNãoPossuaAnimaisSold(String status) {
        petApi.deletePetsByStatus(status);
    }

}
