Feature:  Get All the products from API

  Scenario: Get all the products from API
    Given I have the Products API endpoint
    When I send a GET request to the endpoint
    Then I should receive a response with status code 200


  Scenario: Get all the products from API and Validate the response
    Given I have the Products API endpoint
    When I send a GET request to the endpoint
    Then I should receive a response with status code 200
    And I should see a list of products in the response body
    And each product should have a "title", "price", and "description" field