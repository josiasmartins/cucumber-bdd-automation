package bdd.automation.api.steps;

import bdd.automation.api.support.domain.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;

public class PetStepDefinitions {

    @Dado("que eu possuo animais disponíveis")
    public void queEuPossuoAnimaisDisponíveis() throws JsonProcessingException {

        Pet pet = Pet.builder().build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pet); // transforma o objeto java em json
        System.out.println(json);

    }

}
