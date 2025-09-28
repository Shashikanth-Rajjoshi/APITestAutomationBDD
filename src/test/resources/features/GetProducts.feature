@Regression @GetProducts
  Feature: Get the Products from Test API Response
    Validate all the values from Response

  @TestId_GP_001
  Scenario: Verify Successful Response from Get Products API
    When I hit the url of get "products"
    Then I should receive http response code as "200"
