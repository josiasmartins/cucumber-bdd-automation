package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.Pet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String FIND_PETS_BY_STATUS_ENPOINT = "http://localhost:12345/api/v3/pet/findByStatus?status={status}";
    private static final String ENDPOINT_PET = "http://localhost:12345/api/v3/pet/{id}";
    private static final String CREATE_ENDPOINT_PET = "http://localhost:12345/api/v3/pet";


    public List<Pet> getPetsByStatus(String status) {
        return given()
                .pathParam("status", status).
        when() // quando faço um get
                .get(FIND_PETS_BY_STATUS_ENPOINT)
        .then()
                .extract().body().jsonPath().getList("", Pet.class);
    }

    public Response getPetsResponseByStatus(String status) {
        return given()
                .pathParam("status", status)
            .when()
                .get(FIND_PETS_BY_STATUS_ENPOINT);
    }

    public void deletePetsByStatus(String status) {
        List<Integer> petsId = given()
                    .pathParam("status", status)
                .when()
                    .get(FIND_PETS_BY_STATUS_ENPOINT)
                .thenReturn()
                    .path("id");

        if (!petsId.isEmpty()) {
            for (Integer id : petsId) {
                given().pathParam("id", id).delete(ENDPOINT_PET);
            }
        }

    }

    public List<Pet> getPetsByStatus2(String status) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.pathParam("status", status);
        Response response = httpRequest.get(FIND_PETS_BY_STATUS_ENPOINT);
        JsonPath jsonPath = response.body().jsonPath();
        return jsonPath.getList("", Pet.class);
    }

    public Pet createPet(Pet pet) {
        return given()
                .body(pet).
           when()
                .post(CREATE_ENDPOINT_PET).
           then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(Pet.class); // transforma o json do retorno na classe Pet do java
    }

}
