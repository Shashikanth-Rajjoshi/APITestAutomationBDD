@Regression @GetProducts
  Feature: Get the Products from Test API Response
    Validate all the values from Response

  @TestId_GP_001
  Scenario: Verify Successful Response from Get Products API
    When I hit the url of get "products"
    Then I should receive http response code as "200"

    @TestId_GP_002
    Scenario: Verify Product Values From Get Products API Response
      When I hit the url of get "products"
      Then I should see the following values in response
        | APIName  | ResponseFileName | prod_0_ID | prod_0_Title                                          | prod_0_Price | prod_0_Category |
        | products | getProducts      | 1         | Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops | 109.95       | men's clothing  |
