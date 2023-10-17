package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PetStepDefinitions {

    private PetApi petApi;

    private List<Pet> actualPets;

    public PetStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que eu possua animais {word}")
    public void queEuPossuoAnimaisAvailable() throws JsonProcessingException {

//        Pet pet = Pet.builder().build();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(pet); // transforma o objeto java em json
//        System.out.println(json);

        // seed

    }

    @Quando("eu pesquiso por todos os animais {word}")
    public void euPesquisoPorTodosOsAnimaisAvailable(String status) {
        actualPets = petApi.getPetsByStatus(status);
        System.out.println("ibag");
    }

    @Entao("eu recebo a lista de animais available")
    public void euReceboAListaDeAnimaisAvailable() {
        MatcherAssert.assertThat(actualPets, Matchers.is(Matchers.not(Matchers.empty())));
    }

    @E("eu recebo uma outra lista de animais {word}")
    public void euReceboUmaOutraListaDeAnimaisAvailable(String status) {
        Response actualAvailableResponse = petApi.getPetsResponseByStatus(status);

        actualPets = actualAvailableResponse.body().jsonPath().getList("", Pet.class);

        actualAvailableResponse
                .then()
                    .statusCode(HttpStatus.SC_OK)
                .body(
                        "size()", Matchers.is(actualPets.size()),
                        "findAll { it.status == 'available' }.size()", Matchers.is(actualPets.size())
                );
    }

    @Entao("eu recebo a lista com {int} animal/animais")
    public void euReceboAListaComAnimais(int petQuantity) {
        MatcherAssert.assertThat(actualPets.size(), Matchers.is(petQuantity));
    }

    @Dado("que eu não possua animais {word}")
    public void queEuNãoPossuaAnimaisSold(String status) {
        petApi.deletePetsByStatus(status);
    }
}
