package bdd.automation.api.steps;

import io.cucumber.java.fr.Quand;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.junit.CucumberOptions;
import io.restassured.http.ContentType;

import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;

//@CucumberOptions(publish = true)
public class UserStepDefinitions {

    private Map<String, String> expectedUser = new HashMap<>();

    @Quando("faço um POST para {word} com os seguintes valores:")
    public void euFacoUmPOSTParaUserComOsSeguintesValores(String endpoint, Map<String, String> user) {
        expectedUser = user;

        given()
                .contentType(ContentType.JSON)
                .body(user);
        when()
                .post("http://localhost:12345/api/v3/user" + endpoint)
        .then()
            .contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_OK);

    }

    @Entao("quando faço um GET para {word}, o usuario criado é retornado")
    public void quandoFacoUmGETParaVUserRafaelOUsuarioCriadoERetornado(String endpoint) {
        when()
                .get("http://localhost:12345/api" + endpoint).
        then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("username", CoreMatchers.is(expectedUser.get("username")));
    }
}
