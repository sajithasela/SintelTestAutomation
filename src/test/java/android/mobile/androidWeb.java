package android.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class androidWeb {
    public static void main(String[] args) throws MalformedURLException {

        /* Prerequisites
           ## Appium Server should be started
                == > Open the terminal and type "appium"

           ## Android Simulator should be started
           ==> How to run the emulator?
                # open the terminal > /Library/Android/sdk/emulator
                # Run command Emulator -avd <Device Name>
                # <Device Name> Nexus_5X_API_27
               */

        //File file = new File("src");
        //File fs = new File(file,"ApiDemos-debug.apk");
        WebDriver driver;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Nexus_5X_API_27");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(MobileCapabilityType.VERSION, "5.1");


        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.get("https://expo.io");
    }
}

