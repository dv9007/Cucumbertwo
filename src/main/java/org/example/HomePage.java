package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends Utils {

    private By _registerButton = By.xpath("//a[@class='ico-register']");
    private By _verifyDollarSign = By.className("actual-price");
    private By _verifyEuroSign = By.xpath("//*[@id=\"customerCurrency\"]");
    private By _changeDollarToEuro = By.xpath("//*[@id=\"customerCurrency\"]/option[2]");
    private By _buildYourOwnComputer = By.xpath("//h2/a[@href=\"/build-your-own-computer\"]");
    private By _goodButtonUnderCommunityPoll = By.xpath("//input[@id=\"pollanswers-2\"]");
    private By _voteButton = By.xpath("//button[@class=\"button-2 vote-poll-button\"]");
    private By _errorMessageForVote = By.xpath("//div[@id=\"block-poll-vote-error-1\"]");
    private By _successMessageForVote = By.xpath("//span[@class=\"poll-total-votes\"]");

    LoadProperty loadProperty = new LoadProperty();
    SoftAssert softAssert = new SoftAssert();

    private By _productTitles = By.xpath("//div[contains(@class,'product-grid')]//div[@class='item-box']//h2");
    private By _loginButton = By.className("ico-login");
    private By _wishlistButton = By.className("wishlist-label");
    private By _welcomeToOurStore = By.xpath("//div[@class='topic-block-title']/h2");
    private By _computerButton = By.linkText("Computers");
    private By _facebookIcon = By.className("facebook");
    private By _searchBar = By.id("small-searchterms");
    private By _searchButton = By.className("search-box-button");
    private By _detailsButton = By.xpath("/html/body/div[6]/div[3]/div/div/div/div/div[5]/div[2]/div[2]/div[3]/a");

    // private By _FBnopCommerce = By.xpath("//h3[normalize-space()='NopCommerce - Home - Facebook']");

    public void clickOnRegistrationButton() {
        //Click on register button
        clickButton(_registerButton);
        //driver.findElement(By.xpath("//a[@class='ico-register']")).click();
    }

    public void verifyDollarSignOnHomePage() {
        //Verify if build your own computer price is display in Dollar (Assert Point)
        String actual_Message = driver.findElement(_verifyDollarSign).getText();
        Assert.assertTrue(actual_Message.startsWith("$"), "US Dollar is not the currency selected");
    }

    public void changeDollarIntoEuroSign() {
        //Change the Dollar into Euro sign
        clickButton(_changeDollarToEuro);
        //driver.findElement(By.xpath("//*[@id=\"customerCurrency\"]/option[2]")).click();
    }

    public void verifyEuroSignOnHomePage() {
        //Verify if build your own computer price is display in Euro (Assert Point)
        String actualMessage = driver.findElement(_verifyEuroSign).getText();
        Assert.assertTrue(actualMessage.contains("Euro"), "Euro is not the currency selected");
    }

    public void buildYourOwnComputerOnHomePage() {
        //Click on the Build your own computer
        clickButton(_buildYourOwnComputer);
    }

    public void verifyIfNonRegisteredUserIsAbleToVote() {
        //Select on good button on homepage under community poll
        clickButton(_goodButtonUnderCommunityPoll);

        //Click on vote button
        clickButton(_voteButton);

        //Verify that user is not able to vote without being registered
        driverWaitUntilVisibilityOfAllElementsLocatedBy(3, _errorMessageForVote);
        String actualMessage = driver.findElement(_errorMessageForVote).getText();
        Assert.assertTrue(actualMessage.contains("Only registered users can vote."), "Non registered user is able to vote");
        //Vote Error Message = "Only registered users can vote."
    }

    public void verifyRegisteredUserIsAbleToVote() {
        //Select on good button on homepage under community poll
        clickButton(_goodButtonUnderCommunityPoll);

        //Click on vote button
        clickButton(_voteButton);

        //Verify registered user is able to vote
        String actualMessage = driver.findElement(_successMessageForVote).getText();
        Assert.assertTrue(actualMessage.contains("vote(s)..."), "Registered user is not able to vote");
        //Vote Success Message = "20 vote(s)..."
    }
  //  public void userShouldBeAbleToClickOnAlert(){




    public void getProductTitles() {
        //Verify the product titles on the home page
        List<WebElement> productTitles = driver.findElements(_productTitles);
        System.out.println(productTitles.size());
        for (WebElement titles : productTitles) {
            System.out.println(titles.getText());
        }
    }

    public void verifyHomePageContains() {
        softAssert.assertEquals(getText(_registerButton), loadProperty.getProperty("RegisterButton"));
        softAssert.assertEquals(getText(_loginButton), loadProperty.getProperty("LoginButton"));
        softAssert.assertEquals(getText(_wishlistButton), loadProperty.getProperty("WishlistButton"));
        softAssert.assertEquals(getText(_welcomeToOurStore), loadProperty.getProperty("WelcomeToOurStoreTitle"));
        softAssert.assertAll();
    }

    public void voteButton() {
        //Click on the Vote Button on the Home Page
        clickButton(_voteButton);
    }

    public void handleVoteAlert(){
        //Switch to Alert
        Alert alert = driver.switchTo().alert();

        //Get text for Alert
        String alertPopUp = driver.switchTo().alert().getText();
        String actualErrorMessage = loadProperty.getProperty("AlertErrorMessage");
        Assert.assertEquals(actualErrorMessage, alertPopUp);

        //Click on Ok Button to close the Alert
        driver.switchTo().alert().accept();
    }

    public void computerButton(){
        //Click on computer on Home Page
        clickButton(_computerButton);
    }

    public void facebookPageWindowHandle(){
        //Declare that driver is on Home Page, which will be the Main Page
        String MainWindow = driver.getWindowHandle();

        //Click on Facebook Button
        clickButton(_facebookIcon);

        //Handle all new opened windows
        Set<String> screenOne = driver.getWindowHandles();
        Iterator<String> i1 = screenOne.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                //Switching to child window
                driver.switchTo().window(ChildWindow);
                //Verify user is on correct Facebook page
                String expectedFacebookUrl = loadProperty.getProperty("FacebookPageURL");
                String actualFacebookUrl = driver.getCurrentUrl();
                Assert.assertEquals(actualFacebookUrl, expectedFacebookUrl);
                driver.close();
            }
            //Switching to Parent window
            driver.switchTo().window(MainWindow);
        }
    }

    public void verifyUserIsOnHomePage(){
        // verify user is on Home Page
        String expectedHomeURL = loadProperty.getProperty("HomePageURL");
        String actualHomeURL = driver.getCurrentUrl();
        Assert.assertEquals(actualHomeURL, expectedHomeURL);
    }

    public void enterProductNameToSearch(String product){
        //On Home Page click on search bar and enter Nike or Apple (Parameterised  Method)
        typeText(_searchBar, product);

        //Click on Search Button on Home Page
        clickButton(_searchButton);

        //Verify that user is on the searched product page (Assert Point)
        String expectedPageURL = loadProperty.getProperty("SearchProductPageURL")+product;
        String actualPageURL = driver.getCurrentUrl();
        Assert.assertEquals(actualPageURL,expectedPageURL);
    }

    public void verifyProductListedContainsProductNameRequested(){
        //Verify that all the product listed contains either Nike or Apple (Same as URL) (Assert Point)
        List<WebElement> productTitles = driver.findElements(_productTitles);
        for (WebElement e: productTitles){
            System.out.println(e.getText());
        }
    }

    public void clickOnDetailsButton(){
        //On Home Page click under News and nopCommerce new release!, click on Details Button
        clickButton(_detailsButton);
    }

}




   // public void FBnopCommerce(){
   //     clickButton(_FBnopCommerce);


