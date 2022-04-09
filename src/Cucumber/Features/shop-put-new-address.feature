Feature: New address input

  Scenario Outline: Logged user can put a new address details to his account
    Given user has created account
    And shops authentication page is displayed
    When user logs in
    And user enters his account page
    And user goes to Your addresses page
    And user clicks Create new address button
    And user enters Alias <alias>, Address <address>, City <city>, Postal code <zip>, Country <country>, Phone <phone>
    And user clicks Save
    Then user sees success message with text Address successfully added!
    And on the first place of address list is visible alias <alias>
    And close shop page
    Examples:
      | alias  | address         | city             | zip    | country        | phone         |
      | bronek | Pizzowa 27      | Szerokowek Dolny | 00-000 | United Kingdom | +480000000000 |
      | Raging | Ziemniaczana 77 | Pole pole        | 71-317 | United Kingdom | 7131723423    |
      | bronek | Pizzowa 27      | Szerokowek Dolny | 00-000 | United Kingdom | +480000000000 |
      | Raging | Ziemniaczana 77 | Pole pole        | 71-317 | United Kingdom | 7131723423    |
      | bronek | Pizzowa 27      | Szerokowek Dolny | 00-000 | United Kingdom | +480000000000 |
      | Raging | Ziemniaczana 77 | Pole pole        | 71-317 | United Kingdom | 7131723423    |




