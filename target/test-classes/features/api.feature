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

   Scenario: Create a new post
  Given I set the base API endpoint to "https://jsonplaceholder.typicode.com"
  When I send a POST request to "/posts" with body:
    """
    {
      "title": "API Automation",
      "body": "Testing complex POST request",
      "userId": 99
    }
    """
  Then the response status should be 201
  And the response should contain "id"
