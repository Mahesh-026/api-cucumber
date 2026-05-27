package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class APISteps {
    Response response;
    Map<String, String> scenarioContext = new HashMap<>();

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request(String url) {
        response = get(url);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Given("I set the base API endpoint to {string}")
    public void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    @When("I send a POST request to {string} with body:")
    public void sendPostRequest(String path, String body) {
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(path);
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain {string}")
    public void verifyResponseContains(String key) {
        assertTrue(response.getBody().asString().contains(key));
    }

    @Then("I save the {string} from the response as {string}")
    public void saveValue(String jsonKey, String alias) {
        String value = response.jsonPath().getString(jsonKey);
        scenarioContext.put(alias, value);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String path) {
        String resolvedPath = resolvePath(path);
        response = RestAssured.get(resolvedPath);
    }

    @When("I send a DELETE request to {string}")
    public void sendDeleteRequest(String path) {
        String resolvedPath = resolvePath(path);
        response = RestAssured.delete(resolvedPath);
    }

    // Helper method to replace placeholders like {userId} with stored values
    private String resolvePath(String path) {
        for (Map.Entry<String, String> entry : scenarioContext.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            if (path.contains(placeholder)) {
                path = path.replace(placeholder, entry.getValue());
            }
        }
        return path;
    }
}
