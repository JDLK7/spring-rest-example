Feature: person CRUD

Scenario: Create person
  Given a person name "Jose"
    And lastName "Domenech"
    And email "jose.domenech@bq.com"
    And age 22
  When I request to create a person
    Then response code must be 201

Scenario: Retrieve one person
  Given a person name "Jose"
    And lastName "Domenech"
    And email "jose.domenech@bq.com"
    And age 22
  When I request to create a person
    Then response code must be 201
    Then person response body must contain an ID not empty
  When I request to retrieve the previous person
    Then response code must be 200
    Then check email is "jose.domenech@bq.com"

Scenario: Update a person
  Given a person name "Jose"
    And lastName "Domenech"
    And email "jose.domenech@bq.com"
    And age 22
  When I request to create a person
    Then response code must be 201
    Then person response body must contain an ID not empty
  When I request to update a user name to "New Name"
    Then response code must be 200
    Then returned user has name set to "New Name"

Scenario: Delete a person
  Given a person name "Jose"
    And lastName "Domenech"
    And email "jose.domenech@bq.com"
    And age 22
  When I request to create a person
    Then response code must be 201
    Then person response body must contain an ID not empty
  When I request to delete the previous person
    Then response code must be 204
