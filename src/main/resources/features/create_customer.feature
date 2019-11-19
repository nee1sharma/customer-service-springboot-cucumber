Feature: Create new customer
 As customer
 I want to create new customer
 So that new customer is added

  @create
    Scenario: Should be able to create new customer
      Given an authenticated user
      When user adds new customer
      Then customer should be created
      And status code is 'HTTP CREATED'
