package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshots {


    public WebDriver driver;

    public TakeScreenshots(WebDriver driver){
        this.driver = driver;
    }

    private static Object TakesScreenshot;

    public void capture() throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File trg=new File(".//screenshots/image");
        FileUtils.copyFile(src, trg);
    }
}
