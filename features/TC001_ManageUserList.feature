Feature: Manage the todo list

  Background:
    Given User open the browser
    And User navigate to todo web page

  Scenario: As a MVC user,I should be able to perform user actions in items list
    When User add items to todo list
          |Add items|
          |Item 1|
          |Item 2|
          |Item 3|
          |Item 4|
          |Item 5|
          |Item 6|
          |Item 7|
          |Item 8|
          |Item 9|
          |Item 10|
    Then User can see 10 items has get added to the list

    And User delete items in todo list
          |Deleted value|
          |Item 1|
          |Item 2|
          |Item 3|
    Then User should be able to see to do items count reduced to 7 in todo list

    And User marks item in todo list as completed
          |completed Value|
          |Item 4|
          |Item 5|
          |Item 6|
          |Item 7|

    And User edit the items in todo list
      |Add Value|Edit Value|
      |Item 8|Item 11|



Scenario Outline: As a MVC user,I should be able to navigate from different todo item status tabs
    When User add "<All>" items to the todo list
    And User mark as "<Completed>" items
    Then User able to see "<Completed>"  todo items in "Completed" tab
      And User able to see "<Active>"  todo items in "Active" tab
      And User able to see "<All>"  todo items in "All" tab
  Examples:
  |All                                   | Active                | Completed      |
  |Item 1, Item 2, Item 3 ,Item 4, Item 5| Item 1, Item 2,Item 5 | Item 3, Item 4 |






