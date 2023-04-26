package com.score;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static com.score.Constants.*;

public class Base {
    //@Test
    private static  AndroidDriver driver;
    public void setDriver(AndroidDriver ad){   driver = ad;     }
    public AndroidDriver getDriver(){    return driver;     }
    String APP_PATH =  Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "build",
                           "android", "theScore.apk").toString();
    private static final String workingDir = System.getProperty("user.dir");
    //public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
     static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService() {
                return service;
            }
    public static void setService(AppiumDriverLocalService service1) {
                service = service1;
            }
    public static void startAppiumService() {
                try {
                       // Build the Appium service
                        AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("0.0.0.0")
                                .usingAnyFreePort().withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                        //set appium service
                        setService(AppiumDriverLocalService.buildService(builder));
                        getService().clearOutPutStreams();
                        //start service
                        getService().start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }


    public static  ExtentHtmlReporter htmlreporter;
    public static  ExtentReports extent =  new ExtentReports();
    public ExtentTest test;
    public  static String videoPath;
    @BeforeTest
    public void reportSetup(){
        String strDateTimeStamp = getDateTimeStamp();
        String strFileName = "extentReport_score_" +strDateTimeStamp +".html";
        htmlreporter = new ExtentHtmlReporter(EXTENT_REPORT_PATH +strFileName);
        extent.attachReporter(htmlreporter);
        videoPath =EXTENT_REPORT_PATH+ "recorded_video_score_" +strDateTimeStamp;
    }

    @AfterSuite
    public void reportTeardown(){
        extent.flush();
    }

    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
    public  String getDateTimeStamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
        return format.format(date);
    }
    public  void getScreenshot(String screenShotName) throws IOException {
        String screenshot_path = PROJECT_PATH +"/screenshots/"+screenShotName+getDateTimeStamp();
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        //String filename= UUID.randomUUID().toString();
        File targetFile=new File(screenshot_path  +".jpg");
        FileUtils.copyFile(srcFile,targetFile);
    }

    public  void InitiateDriver() throws IOException {

        //create capabilities

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICE_NAME);

        //options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\app\\theScore.apk");
        options.setApp(ANDROID_APK_PATH);

        /* create object for AndroridDriver */
        try {
           // driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
           driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            //driver.quit();
            System.out.println("Driver created !");
            setDriver(driver);

        } catch (MalformedURLException e) {
        }



    }

    public void runScenario() throws Exception {

        test = extent.createTest("scoreScenario1");
        test.info("test started !");
        test.log(Status.INFO, "Test created !");

        ((CanRecordScreen) getDriver()).startRecordingScreen(new AndroidStartScreenRecordingOptions());


        welcomeScreen();
        favLeaguesScreen();
        locationScreen();
        favTeamsScreen();
        neverMissGameScreen();
        teamsPlayersNewsScreen();
        playerStatsScreen();
        navigateBackFromTMlScreen();
        verifyLeaguesTeamsNewsScreen();
    }

    @AfterTest
    public void tearDown() throws IOException {
        String base64String = ((CanRecordScreen)getDriver()).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String  destinationPath=  videoPath+ ".mp4";  //""target/ScoreRecords1.mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, data);
        test.log(Status.PASS, "Execution video is available at "+ path);
    }
    public void reportFailure(String failMsg, String screenShotName) throws Exception {
        test.log(Status.FAIL,failMsg);
        getScreenshot(screenShotName);
        throw new Exception("Test failed !");
    }

    public void reportSuccess(String successMsg, String screenShotName) throws Exception {
        test.log(Status.PASS,successMsg);
        getScreenshot(screenShotName);
    }

    public void wait(int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    public void welcomeScreen() throws Exception {
        // Verify Welcome Page is displayed
        String strTxt = driver.findElement(By.id("com.fivemobile.thescore:id/txt_welcome")).getText();
        if (strTxt.toLowerCase().contains("welcome")) {
            System.out.println("Welcome page is displayed with text - " + strTxt);
            reportSuccess("Welcome page is displayed ", "Welcome_page_success");
        }
        else
            reportFailure("Continue button is not displayed!","Continue_button_failed");

        WebElement btnContinue = driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text"));
        if (btnContinue.isDisplayed()) {
            btnContinue.click();

        } else
            reportFailure("Continue button is not displayed!","Continue_button_failed");
    }


    public void favLeaguesScreen() throws Exception {
        // verify Favourite Leagues page
        wait(2);
        String strXpath_chooseFavLeagues = "com.fivemobile.thescore:id/title_onboarding";
        String strFavPageText = driver.findElement(By.id(strXpath_chooseFavLeagues)).getText();
        if (strFavPageText.contains("favorite leagues")) {
            System.out.println(" Onboarding : Choose your Favourite Leagues Page is displayed");
            reportSuccess("Favourite League page is displayed", "Fav_League_Page_success");
        }
        else
            reportFailure("Favourite League page is NOT  displayed", "Fav_League_Page_failed");

        // Continue button click
        String strXpath_btnContinue = "com.fivemobile.thescore:id/action_button_text";
        driver.findElement(By.id(strXpath_btnContinue)).click();
        System.out.println("Continue button clicked on Choose Favourite page !");

    }

    public void locationScreen() throws Exception {
        // Location Page ::
        wait(2);
        String xpath_location_title = "com.fivemobile.thescore:id/location_title";
        if (driver.findElement(By.id(xpath_location_title)).isDisplayed()) {
            System.out.println("Location page displayed : Tailored content");
            reportSuccess("Location page displayed","Location_page_success");
                    }
        else
            reportFailure("Location page NOT  displayed","Location_page_Failed");
        String strXpath_maybeLater = "com.fivemobile.thescore:id/btn_disallow";
        driver.findElement(By.id(strXpath_maybeLater)).click();
        System.out.println("location page : maybe later button clicked !");
        test.log(Status.PASS, "location page : maybe later button clicked !");
    }

        // Favourite Teams page ::
    public void favTeamsScreen() throws Exception {
            wait(2);
            String strXpath_chooseFavTeams = "com.fivemobile.thescore:id/title_onboarding";
            String strFavTeamPageText = driver.findElement(By.id(strXpath_chooseFavTeams)).getText();
            if (strFavTeamPageText.contains("favorite teams")) {
                System.out.println(" Onboarding : Choose your Favourite teams Page is displayed");
                reportSuccess("Favourite teams page displayed","Favourite_page_success");
            }
            else
                reportFailure("Favourite teams page NOT  displayed","Favourite_page_Failure");

            String strNHLtab_xpath = "//android.widget.LinearLayout[@content-desc=\"NHL\"]/android.widget.TextView";
            WebElement NHLTabElement = driver.findElement(By.xpath(strNHLtab_xpath));
            if (NHLTabElement.isDisplayed()) {
                NHLTabElement.click();
                System.out.println("NHL Tab is clicked !!");
                test.log(Status.PASS, "NHL Tab is clicked !!");
            }
            else
                reportFailure("NHL tab is not displayed", "NHL_TAB_FAILURE");
            //Select Toronto maple leafs
            String TML_id = "com.fivemobile.thescore:id/txt_name";
            List<WebElement> TML_Elements = driver.findElements(By.id(TML_id));
            // for each item in TML_Element
            System.out.println("the Webelements array size = " + TML_Elements.size());

            for (WebElement webElement : TML_Elements) {
                String team_text = webElement.getText();
                if (team_text.contains("Toronto Maple Leafs")) {
                    System.out.println(team_text);
                    test.log(Status.PASS, "team_text" + team_text);
                    webElement.click();
                    break;
                }

            }


            // verify the favourite team is displayed on top favourites section
            if (driver.findElement(By.id("com.fivemobile.thescore:id/icon_team_logo")).isDisplayed()) {
                System.out.println("Toronto Maple Leaf team is added to Favourites list!");
                //test.log(Status.PASS, "Toronto Maple Leaf team is added to Favourites list!");
                reportSuccess("Toronto Maple Leaf team is added to Favourites list!","Favourite_page_TML_success");
            }

            // Continue button click
            String strXpath_btnContinue = "com.fivemobile.thescore:id/action_button_text";
            driver.findElement(By.id(strXpath_btnContinue)).click();
            System.out.println("Continue button clicked on Choose Favourite Teams page !");
        }

    public void neverMissGameScreen() throws Exception {

        // click on done on Never miss a game
        wait(1);
        try {
        String strXpath_title_nevermissgame = "com.fivemobile.thescore:id/title_onboarding";
        WebElement webElement_nevermiss = driver.findElement(By.id(strXpath_title_nevermissgame));
        if (webElement_nevermiss.getText().contains("Never miss a game")) {
            System.out.println("the page Never miss a page is displayed");
            driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text")).click();
            System.out.println("the page Never miss a page is displayed");
            reportSuccess("Never miss a page is displayed","NeverMiss_screen_Success");
        }
        else
            reportFailure("Never miss a page is NOT displayed","NeverMiss_screen_Failure");

        // Dismiss a BET dialog

            WebElement dismissButton = driver.findElement(By.id("com.fivemobile.thescore:id/dismiss_modal"));
            if (dismissButton.isDisplayed()) {
                dismissButton.click();
                System.out.println("Dismissed the Bet dialog by pressing close button !");
            }
        }catch (SkipException e){
            test.log(Status.INFO,"The Bet screen is not displayed!");
        }
    }

        // TEams players and news page:
    public void teamsPlayersNewsScreen() {
            wait(2);
            WebElement webElement_fav_icon_tmleaf = driver.findElement(By.id("com.fivemobile.thescore:id/icon_team_logo"));
            if (webElement_fav_icon_tmleaf.isDisplayed())
                webElement_fav_icon_tmleaf.click();
        }

    public void playerStatsScreen() throws Exception {
        //Verify Toronto Maple Leaf  page is displayed
        wait(2);
        WebElement webElement_TMMLPage = driver.findElement(By.id("com.fivemobile.thescore:id/team_name"));
        if (webElement_TMMLPage.getText().contains("Toronto Maple Leafs"))
            System.out.println(" Landed in to Toronto Maple Leafs Team's page ! ");

        // select sub tab - Player statistics
        WebElement webElement_PlayerStat_tab = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Player Stats\"]/android.widget.TextView"));
        if (webElement_PlayerStat_tab.isDisplayed()) {
            webElement_PlayerStat_tab.click();
            System.out.println("Player statistics tab is clicked !");
        }

        // To get all text from Player stats page
        wait(2);
        List<WebElement> lstTextElements = driver.findElements(By.className("android.widget.TextView"));
        String strAllText = "";
        for (WebElement we : lstTextElements)
            strAllText = strAllText + ", " + we.getText();
        System.out.println(" all text elements  >>> " + strAllText);
        if (strAllText.toLowerCase().contains("samsonov"))
            reportSuccess("player stats screen is displayed!", "Player_stats_screen_success");
        else
            reportFailure("player stats screen is NOT  displayed!", "Player_stats_screen_Failed");
    }
    public void navigateBackFromTMlScreen() {
        //back button click  ; Navigate up - accessibility id
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        test.log(Status.PASS, "Navigate back button clicked !");
    }
    public void verifyLeaguesTeamsNewsScreen() throws Exception {
        //Verify the previous page is displayed correctly
        // bottom navigation
        wait(2);
        if (driver.findElement(By.id("com.fivemobile.thescore:id/bottom_navigation")).isDisplayed()) {
            System.out.println("Bottom Navigation bar displayed");
            // verify fav icon is displayed
            //android.widget.FrameLayout[@content-desc="Favorites"]/android.widget.FrameLayout/android.widget.ImageView
            if (driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Favorites\"]/android.widget.FrameLayout/android.widget.ImageView")).isDisplayed()) {
                System.out.println("Favourites icon in Bottom Navigation bar displayed");
                reportSuccess("League Teams News screen is displayed!", "Leagues_Teams_News_screen_success");
            }
        else
            reportFailure("League Teams News screen is NOT displayed!", "Leagues_Teams_News_screen_Failure");

        }

    }
}


