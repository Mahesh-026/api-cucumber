package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class APISteps {
    Response response;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request(String url) {
        response = get(url);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }
    @Given("I send a POST request to {string} with body:")
public void i_send_a_post_request(String url, String body) {
    response = given()
                  .header("Content-Type", "application/json")
                  .body(body)
                  .post(url);
}
}
