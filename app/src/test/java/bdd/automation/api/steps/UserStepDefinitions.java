package bdd.automation.api.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.Entao;
//import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class UserStepDefinitions {

    private Map<String, String> expectedUser = new HashMap<>();

    @When("I do a POST to {word} with the following values:")
    @Quando("eu faço um POST para {word} com os seguintes valores:")
    public void iDoAPOSTToVUserWithTheFollowingValues(String endpoint, Map<String, String> user) {
        expectedUser = user;

        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post("http://localhost:12345/api" + endpoint).
                then().
//                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK);
    }

    @Then("I receive the created user when I do a GET to {word}")
    @Entao("quando faço um GET para {word}, o usuário criado é retornado")
    public void iReceiveTheCreatedUserWhenIDoAGETToVUserRafael(String endpoint) {
        when().
                get("http://localhost:12345/api" + endpoint).
//                get(endpoint). // com a classe config
        then().
//                contentType(ContentType.JSON). // não precisa porque já foi setado na classe config
                statusCode(HttpStatus.SC_OK).
                body("username", CoreMatchers.is( expectedUser.get("username")));
    }

    @Quando("eu faço um POST para {word} com a seguinte docString:")
    public void euFaçoUmPOSTParaVUserComASeguinteDocString(String endpoint, DocString docString) {
        expectedUser.put("username", "thenUser");

        given()
                .contentType(ContentType.JSON)
                .body(docString.getContent()).
        when()
                .post("http://localhost:12345/api" + endpoint).
        then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

    }

}
