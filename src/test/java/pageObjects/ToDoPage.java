package pageObjects;

import net.bytebuddy.asm.Advice;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToDoPage {


    //static AndroidDriver androiddriver;
    static Logger log = Logger.getLogger(ToDoPage.class.getName());
    WebDriver driver;

    public ToDoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@class='new-todo']")
    WebElement AddElement;

    @FindBy(linkText = "Completed")
    WebElement Completed;

    @FindBy(linkText = "All")
    WebElement All;

    @FindBy(linkText = "Active")
    WebElement Active;

    @FindBy(xpath = "//ul[@class='todo-list']/li")
    List<WebElement> TotalRecords;

    public void clickOnItemRow(int row){
        driver.findElement(By.xpath("//ul[@class='todo-list']/li[" + row + "]//label")).click();
    }

    public void deleteItem(int row){
        driver.findElement(By.xpath("//ul[@class='todo-list']/li[" + row + "]//button[@class='destroy']")).click();
    }

    public String toDoItemRowGetText(int row){
        String rowItemGetText = driver.findElement(By.xpath("//ul[@class='todo-list']/li[" + row + "]//label")).getText();
        return rowItemGetText;
    }

    public void enterItemsToDoList(String addItems){
        AddElement.sendKeys(addItems);
        AddElement.sendKeys(Keys.RETURN);
    }

    public int getTotalRecords(){
        int TableSize =  TotalRecords.size();
        return TableSize;

    }

    public void clickOnInprogressItems(int row){
        driver.findElement(By.xpath("//ul[@class='todo-list']/li["+row+"]//div/input[@type='checkbox']")).click();
    }

    public void clickOnTab(String tabName){
        if(tabName.equalsIgnoreCase("Completed")){
            Completed.click();
        }
        if(tabName.equalsIgnoreCase("Active")){
            Active.click();
        }
        if(tabName.equalsIgnoreCase("All")){
            All.click();
        }
    }
    public void editTodoItems(int row, String editValue){
        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(By.xpath("//li["+row+"]/div/label"));
        actions.doubleClick(elementLocator).perform();
        try
        {
            driver.findElement(By.cssSelector("input.edit")).sendKeys(editValue);
        } catch (Exception e) {
                System.out.println("Unexpected Behaviour ==> "+e.getMessage());
        }


    }
}
