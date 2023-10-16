package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.Pet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String FIND_PETS_BY_STATUS_ENPOINT = "http://localhost:12345/api/v3/pet/findByStatus?status={status}";

    public List<Pet> getPetByStatus(String status) {
        return given()
                .pathParam("status", status).
        when() // quando faço um get
                .get(FIND_PETS_BY_STATUS_ENPOINT)
        .then()
                .extract().body().jsonPath().getList("", Pet.class);
    }

    public List<Pet> getPetsByStatus2(String status) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.pathParam("status", status);
        Response response = httpRequest.get(FIND_PETS_BY_STATUS_ENPOINT);
        JsonPath jsonPath = response.body().jsonPath();
        return jsonPath.getList("", Pet.class);
    }

}
