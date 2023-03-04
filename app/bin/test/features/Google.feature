Feature: Test Google search functionality

@Test
    Scenario Outline: Scenario Outline name: As a user I enter a search criteria in Google
        Given I am on Google search page
        When I enter a search <criteria>
        And click on the search button
        Then The results match the <word>

    Examples: 
        |   criteria   |   word        |
        |   Argentina  |   Argentina   |
        |   Brasil     |   Brasil      |
    