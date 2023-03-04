package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;

public class BraveNewCoinAPISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I have a valid API key for the (.+) URI$")
    public void iSetTheRequestParams(String URI) {
        request = given().header("x-rapidapi-key", "945267f1ffmshd19f71c88bea502p1f2bd7jsn21a3ac15f8dc")
                .header("x-rapidapi-host", "bravenewcoin.p.rapidapi.com").contentType(ContentType.JSON).baseUri(URI)
                .log().all();
    }

    @When("^I send a POST request with a valid body to the (.+) endpoint$")
    public void sendPOSTRequest(String endpoint) {
        File requestBody = new File("app/src/test/resources/payloads/TokenRequestBody.json");

        response = request.when().body(requestBody).post(endpoint).prettyPeek();
    }

}
