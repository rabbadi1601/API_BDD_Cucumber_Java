package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import utlites.ConfigReader;

public class Products {
   public  int responseCode;
   public RequestSpecification httpRequest;
   public  Response response;
   private String productId;

    @Before
    public void setUp() {
        // Hook: Runs before each scenario
        RestAssured.baseURI = ConfigReader.getBaseURL();
        System.out.println("Setting up base URI for the scenario.");
    }

    @After
    public void tearDown() {
        // Hook: Runs after each scenario
        System.out.println("Tearing down after the scenario. Response code: " + (response != null ? response.getStatusCode() : "N/A"));
        // Reset or clean up if needed
    }

    @Given("I have the Products API endpoint")
    public void i_have_the_products_api_endpoint() {
        // Base URI is already set in @Before hook
        System.out.println("Products API endpoint is set up.");
    }

    @Given("I have the Products API endpoint for product with ID {string}")
    public void i_have_the_products_api_endpoint_for_product_with_id(String id) {
        // Base URI is already set in @Before hook
        productId = id;
        System.out.println("Products API endpoint for ID " + id + " is set up.");
    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("/products");
    }

    @When("I send a POST request to the endpoint with title {string}, price {string}, and description {string}")
    public void i_send_a_post_request_to_the_endpoint_with_title_price_and_description(String title, String price, String description) {
        String json = "{\"title\":\"" + title + "\",\"price\":" + price + ",\"description\":\"" + description + "\"}";
        httpRequest = RestAssured.given().header("Content-Type", "application/json");
        response = httpRequest.body(json).post("/products");
    }

    @When("I send a PUT request to the endpoint with title {string}, price {string}, and description {string}")
    public void i_send_a_put_request_to_the_endpoint_with_title_price_and_description(String title, String price, String description) {
        String json = "{\"title\":\"" + title + "\",\"price\":" + price + ",\"description\":\"" + description + "\"}";
        httpRequest = RestAssured.given().header("Content-Type", "application/json");
        response = httpRequest.body(json).put("/products/" + productId);
    }

    @When("I send a PATCH request to the endpoint with field {string} and value {string}")
    public void i_send_a_patch_request_to_the_endpoint_with_field_and_value(String field, String value) {
        String json;
        if (field.equals("price")) {
            json = "{\"" + field + "\":" + value + "}";
        } else {
            json = "{\"" + field + "\":\"" + value + "\"}";
        }
        httpRequest = RestAssured.given().header("Content-Type", "application/json");
        response = httpRequest.body(json).patch("/products/" + productId);
    }

    @When("I send a DELETE request to the endpoint")
    public void i_send_a_delete_request_to_the_endpoint() {
        httpRequest = RestAssured.given();
        response = httpRequest.delete("/products/" + productId);
    }
    @Then("I should receive a response with status code {int}")
    public void i_should_receive_a_response_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        responseCode = response.getStatusCode();
        assertEquals(responseCode, int1.intValue());
    }
    @Then("I should see a list of products in the response body")
    public void i_should_see_a_list_of_products_in_the_response_body() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Products API endpoint is set up.");
    }
    @Then("each product should have a {string}, {string}, and {string} field")
    public void each_product_should_have_a_and_field(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Products API endpoint is set up.");
    }

    @Then("the response body should contain the created product with title {string}, price {string}, and description {string}")
    public void the_response_body_should_contain_the_created_product_with_title_price_and_description(String title, String price, String description) {
        assertEquals(response.jsonPath().getString("title"), title);
        assertEquals(response.jsonPath().getInt("price"), Integer.parseInt(price));
        assertEquals(response.jsonPath().getString("description"), description);
    }

    @Then("the response body should contain the product with {string} updated to {string}")
    public void the_response_body_should_contain_the_product_with_updated_to(String field, String value) {
        if (field.equals("price")) {
            assertEquals(response.jsonPath().getInt(field), Integer.parseInt(value));
        } else {
            assertEquals(response.jsonPath().getString(field), value);
        }
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        assertTrue(response.getBody().asString().trim().isEmpty());
    }
}
