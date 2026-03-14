Feature: Create a new product via API

  Scenario Outline: Create a new product with valid data
    Given I have the Products API endpoint
    When I send a POST request to the endpoint with title "<title>", price "<price>", and description "<description>"
    Then I should receive a response with status code 201
    And the response body should contain the created product with title "<title>", price "<price>", and description "<description>"

    Examples:
      | title          | price | description          |
      | Laptop         | 999   | High-performance laptop |
      | Smartphone     | 599   | Latest model smartphone |
      | Headphones     | 199   | Noise-cancelling headphones |
