package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.User;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserApi {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    public void createUser(User user) {
        given()
                .body(user).
        when()
                .post(CREATE_USER_ENDPOINT).
        then()
                .statusCode(HttpStatus.SC_OK);
    }

    public String getUsername(User user) {
        return given()
                .pathParam("name", user.getUsername()).
        when()
                .get(USER_ENDPOINT).
        thenReturn()
                .path("username");
    }

}
