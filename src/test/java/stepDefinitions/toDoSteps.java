package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ToDoPage;
import utilities.TestUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class toDoSteps extends BaseClass {
    public WebDriver driver;
    ToDoPage todopage;

    @Before
    public void setup() throws IOException {
        log = Logger.getLogger(toDoSteps.class.getName());
        PropertyConfigurator.configure("log4j.properties");

        // Initialize the Driver
        configProp = new Properties();

        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);

        System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath_mac"));
        driver = new ChromeDriver();
        log.info("End of before method");
    }
    @Given("User open the browser")
    public void user_open_the_browser() throws IOException {
        todopage = new ToDoPage(driver);
        log.info("User Open the browser...");
    }

    @Given("User navigate to todo web page")
    public void user_navigate_to_todo_web_page() {
        driver.get(configProp.getProperty("url"));
        driver.manage().window().maximize();
        log.info("User navigate to url");
    }

    @When("User add items to todo list")
    public void user_add_items_to_todo_list(DataTable addItems) {
        List<List<String>> items = addItems.asLists(String.class);
        for (int i = 1; i < items.size(); i++) {
            String addtoList = items.get(i).toString();
            todopage.enterItemsToDoList(TestUtil.removeBrackets(addtoList));
            log.info("User add items to the todo list");
        }
    }

    @Then("User can see {int} items has get added to the list")
    public void user_can_see_items_has_get_added_to_the_list(Integer count) {
        int expectedValue = count;
        int ActualValue = todopage.getTotalRecords();
       Assert.assertEquals(expectedValue,ActualValue);
        log.info("User verify add items in the todo list");
    }

    @When("User delete items in todo list")
    public void user_delete_items_in_todo_list(DataTable deleteItems) {
        List<List<String>> items = deleteItems.asLists(String.class);
        int toDoListSize = todopage.getTotalRecords();

        for (int i = 1; i < items.size(); i++) {
            String deleteItem = items.get(i).toString();

            for (int j = 1; j < toDoListSize; j++) {
                int item = j;
                todopage.toDoItemRowGetText(item);
                if (TestUtil.removeBrackets(deleteItem).equalsIgnoreCase(todopage.toDoItemRowGetText(item))) {
                    todopage.clickOnItemRow(item);
                    todopage.deleteItem(item);
                    // New List count
                     toDoListSize = todopage.getTotalRecords();
                    log.info("User delete the items in the todo list");
                }
            }
        }

    }

    @Then("User should be able to see to do items count reduced to {int} in todo list")
    public void user_should_be_able_to_see_to_do_items_count_reduced_to_in_todo_list(Integer count) {
        int expectedCount = count;
        int ActualCount = todopage.getTotalRecords();
       Assert.assertEquals(expectedCount,ActualCount);
        log.info("User verify the records after delete the items in the todo list");
    }

    @When("User marks item in todo list as completed")
    public void user_marks_item_in_todo_list_as_completed(DataTable completedList) {
        List<List<String>> complatedItems = completedList.asLists(String.class);

        for (int i = 1; i < complatedItems.size(); i++) {
            String itemsToCompated = complatedItems.get(i).toString();
            TestUtil.removeBrackets(itemsToCompated);

            for (int j = 1; j < todopage.getTotalRecords(); j++) {
                int item = j;
                if (TestUtil.removeBrackets(itemsToCompated).equalsIgnoreCase(todopage.toDoItemRowGetText(item))) {
                    todopage.clickOnInprogressItems(item);
                    log.info("User set items as the competed items in the todo list");
                }
            }
        }

    }

    @When("User edit the items in todo list")
    public void user_edit_the_items_in_todo_list(DataTable edititems) {
        List<Map<String,String>> editItems = edititems.asMaps(String.class,String.class);

        for (int i=0; i<editItems.size(); i++){
            int item = i;
                String editValue = editItems.get(i).get("Edit Value");

            for (int j=1; j<=todopage.getTotalRecords(); j++){
                int row = j;
                String UIText = todopage.toDoItemRowGetText(j);
                if(editItems.get(item).get("Add Value").equalsIgnoreCase(UIText)){
                    todopage.editTodoItems(row,editValue);
                    log.info("User edit the items in the todo list");
                }
            }

        }
    }

    @When("User add {string} items to the todo list")
    public void user_add_items_to_the_todo_list(String addItems) {
        ArrayList<String> addlist = new ArrayList<>(Arrays.asList(addItems.split(",")));
        for (int i=0; i<addlist.size();i++){
            todopage.enterItemsToDoList(TestUtil.removeBrackets(addlist.get(i)));
        }
    }

    @When("User mark as {string} items")
    public void user_mark_as_items(String completedItems) {
        ArrayList<String> itemsToComplate = new ArrayList<>(Arrays.asList(completedItems.split(",")));
        for (int i=0; i<itemsToComplate.size();i++){

            for (int j = 1; j < todopage.getTotalRecords(); j++) {
                int item = j;

                if (TestUtil.removeBrackets(itemsToComplate.get(i).trim()).equalsIgnoreCase(todopage.toDoItemRowGetText(item))) {
                    todopage.clickOnInprogressItems(item);
                }
            }
        }
    }

    @Then("User able to see {string}  todo items in {string} tab")
    public void user_able_to_see_todo_items_in_tab(String items, String tab) {
        ArrayList<String> ItemType = new ArrayList<>(Arrays.asList(items.split(",")));
        if (tab.equalsIgnoreCase("Completed")){
            todopage.clickOnTab(tab);
           Assert.assertSame(ItemType.size(),todopage.getTotalRecords());
            log.info("User verify the items in the Completed todo list");
        }
        else if (tab.equalsIgnoreCase("Active")){
            todopage.clickOnTab(tab);
           Assert.assertSame(ItemType.size(),todopage.getTotalRecords());
            log.info("User verify the items in the Active todo list");
        }
        else {
            todopage.clickOnTab(tab);
           Assert.assertSame(ItemType.size(),todopage.getTotalRecords());
            log.info("User verify the items in the All todo list");
        }

    }
}
