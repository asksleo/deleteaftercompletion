@homepage4
Feature: I have to launch an application
  @toplinksk4
  Scenario: Given the url , launch an application
    Given I have the url and launch the browser
    When compare the url
    Then print the statement