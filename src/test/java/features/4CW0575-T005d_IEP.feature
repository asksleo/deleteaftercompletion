@all
@web
@regression_T005d_IEP_Initial_Referral4
Feature:TX 005d:IEP_Initial_Referral

  Background: Login and create student
    Given open the page "Login page" by the link "{tx}{studentManager}"
    And the page "Login page" is opened
    When in the field "Login input" send encrypted value "login"
    And in the field "Password input" send encrypted value "password"
    And click on button "Login button"
#    And the page "Student manager page" is opened
#    And wait until loader disappeared
#    And click on element "Create student plus icon"
#    And wait until loader disappeared
#    And the block "Student details popup block" is opened
#    And set random value 5 to input "Student data input" by it's part "Student ID:" and save to variable "StudentID"
#    And set the random value 5 to input "Student data input" by it's part "First Name:"
#    And set value "DOTCOM5d" to input "Student data input" by it's part "Last Name:" without saving
#    And in the field "Birth date" send value "09/09/2019"
#    And click on element "School drop down"
#    And select first option from active dropdown by actions
#    And click on element "Gender drop down"
#    And select second option from active dropdown by actions
#    And scroll to the "bottom" of the page
#    And click on button "Save button"
#    And wait until loader disappeared
#    And the page "Student manager page" is opened
#    And scroll to the "top" of the page
#    And wait for 2 second
#    And wait until loader disappeared
#    And click on element "Student filter icon"
#    And wait until loader disappeared
#    And wait for 3 second
#    And the page "Student filter popup block" is opened
#    And click on element "Filter pop up window"
#    And in the field "Student data input" send value "{StudentID}"
#    And click on button "Filter button"
#    And wait until loader disappeared
#    And the page "Student manager page" is opened
#    And click on element "Search student icon"
#    And wait until loader disappeared
#    And click on button "Contacts tab"
#    And wait until loader disappeared
#    And click on element "Create contact icon"
#    And wait until loader disappeared
#    And the page "Select student contact pop up" is opened
#    And set random value 5 to input "Student contact id input" by it's part "Contact ID:" and save to variable "ContactID"
#    And set value "FAKE" to input "Student contact input field" by it's part "Last Name:" without saving
#    And set value "MOTHER" to input "Student contact input field" by it's part "First Name:" without saving
#    And click on button "Student relationship drop down"
#    And wait for 1 second
#    And wait until loader disappeared
#    And select value "Mother" on the list "List of option from dropdown"
#    And click on button "Save student contact"
#    And wait until loader disappeared
#    And the page "Student manager page" is opened

  Scenario: Validating IEP Evaluation Discontinuation event lock Answer chosen on Evaluation Discontinuation Form = "Student moved or transferred out of the district"
    Given open the page "All students page" by the link "{tx}{planManagerStudent}"
    And wait until loader disappeared
    And in the field "Student id input" send value "{StudentID}"
    And click on button "Filter button"
    And wait until loader disappeared

  Scenario: Validating IEP Evaluation Discontinuation event lock Answer chosen on Evaluation Discontinuation Form = "Student moved or transferred out of the district"
    Given open the page "All students page" by the link "{tx}{planManagerStudent}"
    And wait until loader disappeared
    And in the field "Student id input" send value "{StudentID}"
    And click on button "Filter button"
    And wait until loader disappeared

  Scenario: Validating IEP Evaluation Discontinuation event lock Answer chosen on Evaluation Discontinuation Form = "Student moved or transferred out of the district"
    Given open the page "All students page" by the link "{tx}{planManagerStudent}"
    And wait until loader disappeared
    And in the field "Student id input" send value "{StudentID}"
    And click on button "Filter button"
    And wait until loader disappeared
#    And the list "List name" size is equal to 1
#    And click on 1 element from the list "List name"
#    And the page "Student page" is opened
#    And wait until loader disappeared
#    And click on link "Left menu item link" by it's part "Student Team"
#    And the page "Student team page" is opened
#    And wait until loader disappeared
#    And click on button "Create button"
#    And wait until loader disappeared
#    And click on button "Student team button" by it's part "Select Users"
#    And the popup "Select user popup" is opened
#    And wait until loader disappeared
#    And click on checkbox "Select user checkbox"
#    And click on button "Select users popup button" by it's part "Add"
#    And wait until loader disappeared
#    And the page "Student team page" is opened
#    And click on element "Relationship dropdown"
#    And wait for 1 second
#    And select first option from active dropdown by actions
#    And click on checkbox "Make case manager checkbox"
#    And click on button "Student team button" by it's part "Save"
#    And wait until loader disappeared
#    And click on button "Create button"
#    And wait until loader disappeared
#    And click on button "Student team button" by it's part "Student Contact"
#    And wait until loader disappeared
#    And click on element "Student contacts dropdown"
#    And wait for 1 second
#    And select first option from active dropdown by actions
#    And click on button "Student team button" by it's part "Save"
#    And the page "Student page" is opened
#    And wait until loader disappeared
#    And click on link "Left menu item link" by it's part "Events"
#    And wait until loader disappeared
#    And the page "Event page" is opened
#    And wait until loader disappeared
#    And click on button "Create event button"
#    And the page "Create event pop up" is opened
#    And wait until loader disappeared
#    And click on element "Event dropdown"
#    And wait until loader disappeared
#    And in the field "Event input" send value "IEP Initial Referral"
#    And select value "IEP Initial Referral" on the list "List of option from dropdown"
#    And click on button "Create event button" by it's part "Save"
#    And the page "Event page" is opened
#    And wait until loader disappeared
##    And the element "" by it's part "IEP Initial Referral" is displayed in 30 seconds
#
#    # Create event "IEP Evaluation Discontinuation"
#    And click on button "Create event button"
#    And the page "Create event pop up" is opened
#    And click on element "Event dropdown"
#    And wait until loader disappeared
#    And in the field "Event input" send value "IEP Evaluation Discontinuation"
#    And select value "IEP Evaluation Discontinuation" on the list "List of option from dropdown"
#    And click on button "Create event button" by it's part "Save"
#    And the page "Event page" is opened
#    And wait until loader disappeared
#    And click on link "Plan name link" by it's part "IEP Evaluation Discontinuation"
#    And the page "Core plan page" is opened
#    And wait until loader disappeared
#
#    #Fill required form "Evaluation discontinuation page"
#    And the page "Core plan page" is opened
#    And click on link "Left menu item link" by it's part "Evaluation Discontinuation"
#    And wait until loader disappeared
#    And the page "Evaluation discontinuation page" is opened
#    And wait until loader disappeared
#    And click on element "Date initial evaluation discontinued"
#    And send keyboard button "Enter"
#    And click on element "Reasons for discontinuing radio button" by it's part "Student moved or transferred out of the district"
#    And click on element "Person continuing discontinuation multiselect"
#    And wait for 1 second
#    And select first option from active dropdown by actions
#    And click on element "Save form button"
#    And wait until loader disappeared
#
#    ## Lock event
#    And the page "Core plan page" is opened
#    And scroll to the "top" of the page
#    And click on link "Breadcrumbs link" by it's part "IEP Events"
#    And wait until loader disappeared
#    And the page "Event page" is opened
#    And wait until loader disappeared
#    And scroll to the "bottom" of the page
#    And scroll into view of element with complex locator "Lock event link" by it's part "IEP Evaluation Discontinuation"
#    And the element "Lock event link" by it's part "IEP Evaluation Discontinuation" is displayed in 20 seconds
#    And click on element "Lock event link" by it's part "IEP Evaluation Discontinuation" with handel accept
#    And wait until loader disappeared
#    And the element "Locked event plan name link" by it's part "IEP Evaluation Discontinuation" is displayed in 20 seconds
#    And the element "Current and upcoming events have no records" is displayed in 2 seconds
#    And the element "Current and upcoming events have no records" has the text "No records available."
#
#    ## Validate Student program status text
#    And click on link "Plan name link" by it's part "Student Information"
#    And the block "Student information block" is opened
#    And User refreshes page
#    And wait until loader disappeared
#    And scroll to the "bottom" of the page
#    And wait until loader disappeared
#    Then the element "Student status" by it's part "Closed" is displayed in 40 seconds
#    And scroll to the "top" of the page
#
#    ## Validate unlocked event is removed
#    And the page "Student page" is opened
#    And wait until loader disappeared
#    And click on link "Left menu item link" by it's part "Events"
#    And wait until loader disappeared
#    And the page "Event page" is opened
#    And wait until loader disappeared
#    Then the element "Plan name link" by it's part "IEP Initial Referral" isn't displayed