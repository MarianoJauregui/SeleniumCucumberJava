package steps;

import org.junit.Assert;

import cucumber.api.java.en.*;
import pages.GooglePage;

public class GoogleSteps {
    
    public GoogleSteps() {
        
    }
    GooglePage googlePage = new GooglePage();



    @Given("^I am on Google search page$")
    public void navigateToGoogle(){
        googlePage.navigateToGoogle();
        
    } 

    @When("^I enter a search (.+)$")
    public void search(String criteria){
        googlePage.enterSearchCriteria(criteria);
    } 

    @And("^click on the search button$")
    public void clickSearchButton(){
        googlePage.clickGoogleSearch();
    } 

    @Then("^The results match the (.+)$")
    public void validateResults(){
        Assert.assertEquals("Expected text", googlePage.firstResult());
    } 

}


