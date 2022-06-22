package org.example;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends Utils {


   DriveManager driveManager= new DriveManager();

   @BeforeMethod
   public void setUp(){
       driveManager.openURL();
    }


    @AfterMethod
    public void teardown(ITestResult result) {
        if (!result.isSuccess()) {
            takeSnapShot(result.getName());
        }
    }
}