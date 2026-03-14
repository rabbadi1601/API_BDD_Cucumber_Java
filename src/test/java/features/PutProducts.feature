Feature: Update a product via API

  Scenario Outline: Update an existing product with valid data
    Given I have the Products API endpoint for product with ID "<id>"
    When I send a PUT request to the endpoint with title "<title>", price "<price>", and description "<description>"
    Then I should receive a response with status code 200
    And the response body should contain the updated product with title "<title>", price "<price>", and description "<description>"

    Examples:
      | id | title          | price | description          |
      | 1  | Updated Laptop | 1099  | Updated high-performance laptop |
      | 2  | Updated Smartphone | 699 | Updated latest model smartphone |
      | 3  | Updated Headphones | 249 | Updated noise-cancelling headphones |
