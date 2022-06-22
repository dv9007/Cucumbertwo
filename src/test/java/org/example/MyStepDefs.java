package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepDefs {

HomePage homePage = new HomePage();
RegistrationPage registrationPage = new RegistrationPage();
RegistrationSuccessPage registrationSuccessPage = new RegistrationSuccessPage();


    @Given("I am on registration page")
    public void i_am_on_registration_page() {
        // Write code here that turns the phrase above into concrete actions
        homePage.clickOnRegistrationButton();
    }
    @When("I enter required registration details")
    public void i_enter_required_registration_details() {
        // Write code here that turns the phrase above into concrete actions
        registrationPage.verifyUserIsOnRegistrationPage();
        registrationPage.userEntersRegistrationDetails();
    }
    @When("I click on register submit button")
    public void i_click_on_register_submit_button() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("I should able to registered successfully")
    public void i_should_able_to_registered_successfully() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        registrationSuccessPage.verifyUserRegisteredSuccessfully();
    }

}
