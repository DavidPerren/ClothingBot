package sample;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Purchaser {
    private static final String jsScrollFunction = "arguments[0].scrollIntoView(true);";

    private static PropertiesReader user = new PropertiesReader("user.properties");
    private static PropertiesReader adidasForm = new PropertiesReader("adidas.properties");

    private static String purchaseTxt = "Purchase History.txt";
    //private static ChromeOptions chromeOptions = new ChromeOptions();
    private static WebDriver driver;
    //new ChromeOptions().setExperimentalOption("profile.managed_default_content_settings.images",2)




    /**
     * TODO: selecting value from dropdown menu
     * @param url url of adidas item trying to buy
     */
    public static void buyAdidas(String url) {




        try {
            // To prevent buying the shoe again
            if (notCopped(url)) {
                // Go to webpage
                String model = "";

                //converts size to url
                double baseSize = (double)580;
                double ShoeSize = (parseInt(user.get("ShoeSize"))-6.5);
                ShoeSize*=20;
                Double RawSize = ShoeSize + baseSize;





                Pattern pattern = Pattern.compile("\\/(\\w{6})\\.");
                Matcher matcher = pattern.matcher(url);
                if (matcher.find())
                {
                    model = matcher.group(1);
                    System.out.println("Product code: "+matcher.group(1));
                }


                url = "http://www.adidas.com/us/" + model + ".html?forceSelSize=" + model + "_" + RawSize.intValue();
                System.out.println("URL w/size: "+url);


                /**
                 * disables images
                 */
                HashMap<String, Object> images = new HashMap<String, Object>();
                images.put("images", 2);
                HashMap<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values", images);

                ChromeOptions chOptions =new ChromeOptions();
                chOptions.setExperimentalOption("prefs", prefs);


                driver = new ChromeDriver(chOptions);
                driver.get(url);


                //executePossibleDropdown("QuantityInstructions", "Quantity", "QuantitySelectionIsDropdown");

                Thread.sleep(1000);
                //driver.findElement(Html).click();

                    //xpathClick(adidasForm.get("btn btn-bag gl-cta gl-cta--primary gl-cta--full-width"));
                    //WebElement temp = driver.findElement(By.partialLinkText("Bag"));
                //System.out.println("temp = " + temp);
                    System.out.println("Add to Bag clicked");

                /*Thread.sleep(1500);
                xpathClick(adidasForm.get("Checkout"));
                Thread.sleep(2000);*/
/*
                proceedAsGuest();

                xpathClick(adidasForm.get("Shipping"));

                xpathFill(adidasForm.get("FirstName"), user.get("FirstName"));
                xpathFill(adidasForm.get("LastName"), user.get("LastName"));
                xpathFill(adidasForm.get("Address"), user.get("Address"));
                xpathFill(adidasForm.get("PhoneNumber"), user.get("PhoneNumber"));
                xpathFill(adidasForm.get("Email"), user.get("Email"));
                xpathFill(adidasForm.get("City"), user.get("City"));
                xpathFill(adidasForm.get("ZipCode"), user.get("ZipCode"));

                executePossibleDropdown("StateInstructions", "State", "StateIsDropdown");


                // Billing info
                xpathClick("//*[@id=\"dwfrm_delivery\"]/div[2]/div[2]/div/fieldset/div/div[2]/div/div/span");
                xpathFill(adidasForm.get("BillingFirstName"), user.get("BillingFirstName"));
                xpathFill(adidasForm.get("BillingLastName"), user.get("BillingLastName"));
                xpathFill(adidasForm.get("BillingAddress"), user.get("BillingAddress"));
                xpathFill(adidasForm.get("BillingCity"), user.get("BillingCity"));
                xpathFill(adidasForm.get("BillingZipCode"), user.get("BillingZipCode"));
                xpathFill(adidasForm.get("BillingPhoneNumber"), user.get("BillingPhoneNumber"));

                executePossibleDropdown("BillingStateInstructions", "State", "BillingStateIsDropdown");


                xpathClick(adidasForm.get("ReviewAndPay"));
                Thread.sleep(3000);

                // checkout info
                xpathFill(adidasForm.get("CardNumber"), user.get("CardNumber"));
                xpathFill(adidasForm.get("SecurityCode"), user.get("CardSecurityCode"));

                executePossibleDropdown("CardMonthInstructions", "ExpirationMonthNumber", "CardMonthIsDropdown");

                executePossibleDropdown("CardYearInstructions", "ExpirationYearNumber", "CardYearIsDropdown");

                xpathClick(adidasForm.get("PlaceOrderButton"));
                System.out.println("Purchase complete\nLink: " + driver.getCurrentUrl());
                recordPurchase(url);*/


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Purchase Failed");
        }
    }

    public static void buySupreme(String keyword)
    {
        try
        {
            /**
             * disables images
             */
            HashMap<String, Object> images = new HashMap<String, Object>();
            images.put("images", 2);
            HashMap<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values", images);

            ChromeOptions chOptions =new ChromeOptions();
            chOptions.setExperimentalOption("prefs", prefs);


            driver = new ChromeDriver(chOptions);
            driver.get("http://www.supremenewyork.com/shop/all");
            driver.findElement(By.partialLinkText("Tagless"));
            if (notCopped(url)) {


            }

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Purchase Failed");
        }
    }

    /**
     *
     * @param url url of item trying to buy
     * @param stringDate release date and time in format "MMM dd, yyyy hh:mm:ss aa"
     */
    public static void timeTest(final String url, String stringDate) {

       /* Timer timer = new Timer();
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");

        try {
            Date date = formatter.parse(stringDate);
            timer.schedule(new dateTimeScheduler(url), date);
        } catch (Exception e) {
            System.out.println(e);
        }*/
       buyAdidas(url);


    }

    static class dateTimeScheduler extends TimerTask {
        String url;

        /**
         *
         * @param url url of item
         */
        public dateTimeScheduler(String url) {

            this.url = url;
        }

        @Override
        public void run() {
            if (url.contains("adidas.com")) buyAdidas(url);
            else System.out.println("unknown shop in url");
        }
    }

    /**
     * Fills in a form input on the webdriver based on xpath
     *
     * @param inputElement  The input element on the form
     * @param valueInputted The value going into the inputElement
     */
    private static void xpathFill(String inputElement, String valueInputted) {
        driver.findElement(By.xpath(inputElement)).sendKeys(valueInputted);
        System.out.println(valueInputted + " filled");
    }

    /**
     * Clicks on an element on the webpage based on .
     * If the element is not clickable because it's covered
     * up by something else, it'll wait until it's clickable
     * and timeout after 10 seconds
     *
     * @param elementToClick Element you want to click
     */
    private static void xpathClick(String elementToClick) {
        try {
            WebElement element = driver.findElement(By.className(elementToClick));

            // Scroll element into view if not clickable
            if (!element.isDisplayed() && !element.isEnabled()) {
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript(jsScrollFunction, element);
            }
            element.click();
            System.out.println(elementToClick + " clicked");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Click Failed");
        }
    }

    /**
     * Clicks on an element on the webpage based on .
     * If the element is not clickable because it's covered
     * up by something else, it'll wait until it's clickable
     * and timeout after 10 seconds
     *
     * @param elementToClick Element you want to click
     */
    private static void tagNameClick(String elementToClick) {
        WebElement element = driver.findElement(By.tagName(elementToClick));

        // Scroll element into view if not clickable
        if (!element.isDisplayed() && !element.isEnabled()) {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript(jsScrollFunction, element);
        }
        element.click();
        System.out.println(elementToClick + " clicked");
    }

    /**
     * Method made specifically for dropdown menus
     * <p>
     * The corresponding properties file of the website
     * contains a sequence of xpaths tailored for that
     * dropdown.
     * <p>
     * The method runs through each xpath,
     * however if the element is not in view,
     * a WebDriver exception will be caught and
     * it will scroll down until it's clickable
     *
     * @param instructionsProperty Sequence of xpaths for dropdown
     * @param innerTextToSelect    innerHTML to select (This is pulled from user.properties
     * @param isDropdownProperty   Later parsed as boolean to determine if a dropdown menu is present
     */
    private static void executePossibleDropdown(String instructionsProperty, String innerTextToSelect, String isDropdownProperty) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement element;

        // Dropdowns require an unpredictable amount of clicks (typically two)
        // This will loop through each xpath in the corresponding
        // properties file and click it
        System.out.println("Handling " + instructionsProperty + " dropdown");

        if (Boolean.parseBoolean(adidasForm.get(isDropdownProperty))) {
            // A dropdown is present
            // Every dropdown requires x amount of clicks to complete
            // and each click has its own xpath.
            // The xpaths are all stored in the same property and
            // and separated by a single '&'
            final String[] xpathInstructions = adidasForm.get(instructionsProperty).split("&");

            for (String instruction : xpathInstructions) {
        /*
        Replace 'REPLACE' keyword variable from user.properties

        This can be confusing :(
        In the adidas.properties file, some xpaths I have included
        'REPLACE'

        This line will get the edit the xpath by replacing the 'REPLACE'
        keyword with the innerHTML necessary.

        Ex:
          Your card expires in 2017 and the xpath of the dropdown element is:
          //span[text()[contains(.,'REPLACE')]]

          The 'REPLACE' will get replaced with '2017' which will evaluate
          into a real HTML element within the DOM.

        */
                element = driver.findElement(By.xpath(instruction.replace("REPLACE", user.get(innerTextToSelect))));

                try {
                    element.click();
                    System.out.println(instructionsProperty + " clicked");
                } catch (WebDriverException wde) {
                    // Occurs when the element must be scrolled into view before being clickable
                    je.executeScript(jsScrollFunction, element);
                    element.click();
                    System.out.println(instructionsProperty + " clicked");
                }
            }
        } else {
            // If the form element is not a dropdown we can just simply click the element
            driver.findElement(By.xpath(instructionsProperty)).click();
        }
    }

    /**
     * After purchasing the shoe,
     * the url will be written to this file
     * to exit the while() loop in the buyShoe()
     * method. This will prevent duplicates
     *
     * @param url url of shoe
     */
    private static void recordPurchase(String url) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(purchaseTxt, true)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Write url to file
        out.println(url);
        System.out.println("\nRecord saved " + url);
        out.close();
    }

    /**
     * This method will exit the while loop
     * (keep trying until the shoe is copped)
     *
     * @param url url of the shoe
     * @return whether the shoe url has been written to the file
     */
    private static Boolean notCopped(String url) {
        System.out.println("url = " + url);
        try {
            // Read in file and check for a match
            BufferedReader br = new BufferedReader(new FileReader(purchaseTxt));
            String inline;
            while ((inline = br.readLine()) != null) {
                System.out.println("inline = " + inline);

                if (inline.equals(url)) {
                    // A match was found meaning the user already purchased
                    System.out.println("Shoe owned");
                    return false;
                }
            }
            System.out.println("Shoe not owned");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a reCaptcha exists in the DOM
     * if there is a captcha it will wait until the
     * user to solve it and user input
     * <p>
     * TODO: Improve captcha identification
     */
    private static void captcha() {
        try {
            System.out.println("\nChecking for captcha...");

            // Captcha is very difficult to identify however I've had the most success with this xpath
            if (!driver.findElements(By.xpath(adidasForm.get("Captcha"))).isEmpty()) {
                // Element exists
                System.out.println("Captcha found!");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\nSolve the reCaptcha on the webpage" +
                        "\nType anything in the console once completed");

                // Wait for user to finish captcha and provide user input
                if (br.readLine() != null) {
                    System.out.println("Captcha solved!\nContinuing checkout...");
                }
            } else {
                System.out.println("\nNo reCaptcha found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adidas will occasionally prompt login if the user is a guest
     * this will click the button to proceed to checkout as a guest
     */
    private static void proceedAsGuest() {
        // If they prompt login
        if (driver.getCurrentUrl().equals("https://www.adidas.com/us/checkout-start")) {
            driver.findElement(By.xpath("//*[@id=\"frameContainer\"]/div[2]/form/div/button")).click(); // sketchy
        }
    }



}



