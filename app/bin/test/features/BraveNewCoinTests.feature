@BraveNewCoin
Feature: BraveNewCoin API scenarios.

    Rule: When I send POST request to the endpoint, I recieve a token I can use to make further authenticated calls.

        Scenario: As a user I can retrieve a token when making a valid POST request
            Given I have a valid API key for the htpps://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with the valid body to the /ouath/token endpoint
            Then I can validate I recieved a valid token on the response

        Scenario: As a user, when I use an invalid API key, I get an HTTP code status 403
            Given I have an invalid API key for the htpps://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with the valid body to the /ouath/token endpoint
            Then I recieve an HTTP status code 403

        Scenario: As a user, when I send a POST request without "grant_type", I get an HTTP code status 400
            Given I have an valid API key for the htpps://bravenewcoin.p.rapidapi.com URI
            When I send a POST request without "grant_type" in it's body
            Then I recieve an HTTP status code 400
