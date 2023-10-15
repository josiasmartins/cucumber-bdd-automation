package bdd.automation.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
//import org.junit.Before;

public class config {

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:12345";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", getToken())
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

    }

    private String getToken() {
        return "grant access";
    }

    @Before(order = 1)
    public void doSomething() {
        System.out.println("hook before");
    }

    @Before(value = "@primeira", order = 3)
    public void doFirst() {
        System.out.println("before primeiro");
    }

    @Before(value = "@segundo", order = 2)
    public void doSecond() {
        System.out.println("before segundo");
    }

    @Before(value = "@terceiro", order = 1)
    public void doThird() {
        System.out.println("before terceira");
    }

    // after
    @After(value = "@primeira", order = 3)
    public void doLast() {
        System.out.println("after primeiro");
    }

    @After(value = "@segundo", order = 2)
    public void doLast1() {
        System.out.println("after segundo");
    }

    @After(value = "@terceiro", order = 1)
    public void doLast3() {
        System.out.println("after terceira");
    }

}
