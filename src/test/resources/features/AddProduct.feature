@Regression @AddProduct
Feature: Add a New Product
  Validate all the values from Response

  @TestId_AP_001
  Scenario: Verify New Product is Added from API Response
    When I Add a New Product with Following Details
      | APIName     | RequestFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image        |
      | Add_product | addProduct      | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com |
    Then I should receive http response code as "201"
    And I should see the following values in response
      | APIName     | ResponseFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image        |
      | Add_product | addProduct       | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com |

  @TestId_AP_002
  Scenario: Verify extra parameter added in request which is not part of default Json request
    When I Add a New Product with Following Details
      | APIName     | RequestFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image        | prod_extraParam |
      | Add_product | addProduct      | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com | extraValue      |
    Then I should receive http response code as "201"
    And I should see the following values in response
      | APIName     | ResponseFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image        |
      | Add_product | addProduct       | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com |

  @TestId_AP_003
  Scenario: Verify Existing Parameter is Removed from default Json request
    When I Add a New Product with Following Details
      | APIName     | RequestFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image |
      | Add_product | addProduct      | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | REMOVE     |
    Then I should receive http response code as "201"
    And I should see the following values in response
      | APIName     | ResponseFileName | prod_id | prod_title   | prod_price | prod_description                                     | prod_category  | prod_image |
      | Add_product | addProduct       | 21      | Test Product | 100.5      | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | null       |

  @TestId_AP_004
  Scenario Outline: Verify New Product is Added from API Response with Parametrized values
    When I Add a New Product with Following Details
      | APIName     | RequestFileName | prod_id | prod_title | prod_price | prod_description                                     | prod_category  | prod_image        |
      | Add_product | addProduct      | 21      | <Title>    | <Price>    | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com |
    Then I should receive http response code as "201"
    And I should see the following values in response
      | APIName     | ResponseFileName | prod_id | prod_title | prod_price | prod_description                                     | prod_category  | prod_image        |
      | Add_product | addProduct       | 21      | <Title>    | <Price>    | Test Product - Foldsack No. 1 Backpack, Fits Tshirts | men's clothing | https://image.com |
    Examples:
      | Title        | Price |
      | Test Product | 100.5 |
      | New Product  | 220.8 |