Feature: Get customer detail by customerId
  As customer
  I want to retrieve customer detail
  So that I can view customer detail


  Scenario Outline: Should be able to retrieve customer detail by customerId
    Given an authenticated user
    When user requests for customer details by customerId '<customer_id>'
    And status code is '<http_status>'
    Examples:
     | customer_id       | http_status       |
     | 122111            | OK                |
     | N12311            | NOT FOUND         |