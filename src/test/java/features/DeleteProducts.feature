Feature: Delete a product via API

  Scenario Outline: Delete an existing product
    Given I have the Products API endpoint for product with ID "<id>"
    When I send a DELETE request to the endpoint
    Then I should receive a response with status code 204
    And the response body should be empty

    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |
