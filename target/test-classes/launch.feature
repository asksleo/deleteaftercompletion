@homepage
Feature: I want to launch an application
@landingpage2
Scenario: Given the url , launch an application
Given I have the url and launch the browser
When compare the url
Then print the statement

@toplinks
Scenario:  user on landing page and hit the toplinks url
Given user visited the landing page
When user hit the toplinks menu button
Then user landed on toplinks page