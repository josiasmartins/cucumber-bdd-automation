package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
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

    @Dado("que eu possuo animais available")
    public void queEuPossuoAnimaisAvailable() throws JsonProcessingException {

//        Pet pet = Pet.builder().build();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(pet); // transforma o objeto java em json
//        System.out.println(json);

        // seed

    }

    @Quando("eu pesquiso por todos os animais {word}")
    public void euPesquisoPorTodosOsAnimaisAvailable(String status) {
        List<Pet> actualPets = petApi.getPetByStatus(status);
        System.out.println("ibag");
    }

    @Entao("eu recebo a lista de animais available")
    public void euReceboAListaDeAnimaisAvailable() {
        MatcherAssert.assertThat(actualPets, Matchers.is(Matchers.not(Matchers.empty())));
    }
}
