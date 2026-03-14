Feature: Partially update a product via API

  Scenario Outline: Partially update an existing product
    Given I have the Products API endpoint for product with ID "<id>"
    When I send a PATCH request to the endpoint with field "<field>" and value "<value>"
    Then I should receive a response with status code 200
    And the response body should contain the product with "<field>" updated to "<value>"

    Examples:
      | id | field       | value              |
      | 1  | price       | 1199               |
      | 2  | description | Updated description for smartphone |
      | 3  | title       | Premium Headphones |
