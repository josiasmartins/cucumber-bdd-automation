package bdd.automation.api.steps;

import bdd.automation.api.support.api.UserApi;
import bdd.automation.api.support.domain.User;
import io.cucumber.docstring.DocString;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Entao;
//import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserStepDefinitions {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    private User expectedUser;
    private UserApi userApi;

    public UserStepDefinitions() {
        userApi = new UserApi();
    }

//    private Map<String, String> expectedUser = new HashMap<>();

    @Before
    public void init() {
        Config config = new Config();

        config.setup();
//        config.doSomething();
//        config.doFirst();
//        config.doSecond();
//        config.doThird();
    }

    @After
    public void after_init() {
        new Config().deleteAllUsers();
    }

//    @When("I do a POST to {word} with the following values:")
//    @Quando("eu faço um POST para {word} com os seguintes valores:")
//    public void iDoAPOSTToVUserWithTheFollowingValues(String endpoint, Map<String, String> user) {
//        expectedUser = user;
//
//        given().
//                contentType(ContentType.JSON).
//                body(user).
//                when().
//                post("http://localhost:12345/api" + endpoint).
//                then().
////                contentType(ContentType.JSON).
//                statusCode(HttpStatus.SC_OK);
//    }

//    @Then("I receive the created user when I do a GET to {word}")
//    @Entao("quando faço um GET para {word}, o usuário criado é retornado")
//    public void iReceiveTheCreatedUserWhenIDoAGETToVUserRafael(String endpoint) {
//        when().
//                get("http://localhost:12345/api" + endpoint).
////                get(endpoint). // com a classe config
//        then().
////                contentType(ContentType.JSON). // não precisa porque já foi setado na classe config
//                statusCode(HttpStatus.SC_OK).
    //                body("username", is( expectedUser.get("username")));
//    }

    @Quando("eu faço um POST para {word} com a seguinte docString:")
    public void euFaçoUmPOSTParaVUserComASeguinteDocString(String endpoint, DocString docString) {
//        expectedUser.put("username", "thenUser");

        given()
                .contentType(ContentType.JSON)
                .body(docString.getContent()).
        when()
                .post("http://localhost:12345/api" + endpoint).
        then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

    }

    @Quando("crio um usuario")
    public void crioUmUsuario() {

        expectedUser = User.builder().email("rafael@gmail.com").build();

        userApi.createUser(expectedUser);

//        given()
//                .body(expectedUser).
//        when()
//                .post(CREATE_USER_ENDPOINT).
//        then()
//                .statusCode(HttpStatus.SC_OK);

    }

    @Entao("o usuário é salvo no sistema")
    public void oUsuárioÉSalvoNoSistema() {
        String actualUsername = userApi.getUsername(expectedUser);

        MatcherAssert.assertThat(actualUsername, CoreMatchers.is(expectedUser.getUsername()));
    }
}
