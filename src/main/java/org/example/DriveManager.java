package org.example;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DriveManager extends Utils {

    LoadProperty loadProperty = new LoadProperty();
    DesiredCapabilities caps = new DesiredCapabilities();

    public final String AUTOMATE_USERNAME = loadProperty.getProperty("darshakgoyani_jo3JzD");
    public final String AUTOMATE_ACCESS_KEY = loadProperty.getProperty("gyi3fqmf6rQFosa52XkJ");
    public final String BrowserStackURL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    boolean cloud = Boolean.parseBoolean(System.getProperty("cloud"));

      String browserName ="chrome";

   //  String browserName = System.getProperty("browser");

    public void openURL() {


        if (cloud) {
            System.out.println("running cloud");

            //applying conditional loop for different browser options

            if (browserName.equalsIgnoreCase("Chrome")) {

                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "11");
                caps.setCapability("browser","Chrome");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("browserstack.local", "false");
                caps.setCapability("browserstack.selenium_version", "4.2.0");

            } else {
                System.out.println(" your browser name is wrong");
            }

            try {
                driver = new RemoteWebDriver(new URL(BrowserStackURL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else
        {

            //this will be run if cloud false(locally)

            System.out.println(" Running Locally ");

                if (browserName.equalsIgnoreCase("Chrome")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");

                    //Open Chrome
                    driver = new ChromeDriver();


                } else if (browserName.equalsIgnoreCase("Firefox")) {
                    System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                } else if (browserName.equalsIgnoreCase("edge")) {
                    System.setProperty("webdriver.edge.driver", "src/test/java/drivers/msedgedriver.exe");
                    driver = new EdgeDriver();
                } else {

                    System.out.println("Either your name is wrong or not provided  : " + browserName);
                }

                //Duration to be wait

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();

                driver.get("https://demo.nopcommerce.com/");

            }
        }
        public void closeURL () {
            driver.quit();
        }
    }

