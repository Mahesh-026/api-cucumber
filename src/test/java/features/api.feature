Feature: API Testing with RestAssured

  Scenario: Verify GET request
    Given I send a GET request to "https://jsonplaceholder.typicode.com/posts/1"
    Then the response status code should be 200
  Scenario: Verify POST request
    Given I send a POST request to "https://jsonplaceholder.typicode.com/posts" with body:
      """
      {
        "title": "foo",
        "body": "bar",
        "userId": 1
      }
      """
    Then the response status code should be 201  

    Scenario: Create, retrieve, and delete a user
    Given I set the base API endpoint to "https://reqres.in/api"
    When I send a POST request to "/users" with body:
      """
      {
        "name": "Mahesh",
        "job": "Automation Tester"
      }
      """
    Then the response status should be 201
    And the response should contain "id"
    And I save the "id" from the response as "userId"

    When I send a GET request to "/users/{userId}"
    Then the response status should be 200
    And the response should contain "Mahesh"

    When I send a DELETE request to "/users/{userId}"
    Then the response status should be 204