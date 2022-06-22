package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends Utils{

    private By _maleOrFemaleField = By.xpath("//input[@id=\"gender-male\"]");
    private By _firstNameField = By.xpath("//input[@name='FirstName']");
    private By _lastNameField = By.id("LastName");
    private By _emailField = By.id("Email");
    private By _passwordField = By.id("Password");
    private By _confirmPasswordField = By.id("ConfirmPassword");
    private By _registerButton = By.id("register-button");
    private By _dateOfBirthField = By.name("DateOfBirthDay");
    private By _monthOfBirthField = By.name("DateOfBirthMonth");
    private By _yearOfBirthField = By.name("DateOfBirthYear");

    public void verifyUserIsOnRegistrationPage(){

        driverWaitUntilURLToBe(10, "https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    public void userEntersRegistrationDetails(){

        //Select Male or Female
        clickButton(_maleOrFemaleField); //radio button
        //driver.findElement(By.id("gender-male")).click();

        //Enter firstname
        typeText(_firstNameField,"Rajesh");
        //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Testing");

        //Enter lastname
        typeText(_lastNameField,"Patel");
        //driver.findElement(By.id("LastName")).sendKeys("Patel");

        //Fill in the date of birth
        Select birthDay = new Select(driver.findElement(_dateOfBirthField));
        birthDay.selectByIndex(10);
        Select birthMonth = new Select(driver.findElement(_monthOfBirthField));
        birthMonth.selectByValue("5");
        Select birthYear = new Select(driver.findElement(_yearOfBirthField));
        birthYear.selectByVisibleText("1998");

        //Enter email
        typeText(_emailField,"testingpatel22"+randomDate()+"@gmail.com");
        //driver.findElement(By.id("Email")).sendKeys("testingpatel22@gmail.com");

        //Enter password
        typeText(_passwordField,"as23.twoone");
        //driver.findElement(By.id("Password")).sendKeys("as23.twoone");

        //Enter confirm password
        typeText(_confirmPasswordField,"as23.twoone");
        //driver.findElement(By.id("ConfirmPassword")).sendKeys("as23.twoone");

        //Click on register button
        clickButton(_registerButton);
        //driver.findElement(By.id("register-button")).click();
    }
}
