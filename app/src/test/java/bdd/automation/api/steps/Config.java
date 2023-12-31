package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.UserApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
//import org.junit.Before;

public class Config {

    private UserApi userApi;
    private PetApi petApi;

    public Config() {
        userApi= new UserApi();
        petApi = new PetApi();
    }

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:12345";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", getToken())
                .setContentType(ContentType.JSON)
                .build();

//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .expectContentType(ContentType.JSON)
//                .build();

    }

    private String getToken() {
        return "grant access";
    }

    @After("@deleteAllUsers")
    public void deleteAllUsers() {
        System.out.println("delete users");
    }

    @After("@DeleteExtraPets")
    public void deleteExtraPets() {
        petApi.deleteExtraPets("available");
    }


//    @Before(order = 1)
//    public void doSomething() {
//        System.out.println("hook before");
//    }
//
//    @Before(value = "@primeira", order = 3)
//    public void doFirst() {
//        System.out.println("before primeiro");
//    }
//
//    @Before(value = "@segundo", order = 2)
//    public void doSecond() {
//        System.out.println("before segundo");
//    }
//
//    @Before(value = "@terceiro", order = 1)
//    public void doThird() {
//        System.out.println("before terceira");
//    }
//
//    // after
//    @After(value = "@primeira", order = 3)
//    public void doLast() {
//        System.out.println("after primeiro");
//    }
//
//    @After(value = "@segundo", order = 2)
//    public void doLast1() {
//        System.out.println("after segundo");
//    }
//
//    @After(value = "@terceiro", order = 1)
//    public void doLast3() {
//        System.out.println("after terceira");
//    }

}
