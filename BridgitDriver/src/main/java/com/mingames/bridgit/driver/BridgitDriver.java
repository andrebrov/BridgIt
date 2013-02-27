package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgItCommand;
import com.mingames.bridgit.service.ServerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class BridgitDriver implements WebDriver {

    private ServerService serverService = new ServerService();


    public void get(String url) {

    }

    public String getCurrentUrl() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.GET_URL);
        return execute(command);
    }

    public String getTitle() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.GET_TITLE);
        return execute(command);
    }

    public List<WebElement> findElements(By by) {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.FIND);
        command.setParam(by.toString());
        List<WebElement> webElements = serverService.sendFindElementCommand(command);
        for (WebElement webElement : webElements) {
            BridgitWebElement bridgitWebElement = (BridgitWebElement) webElement;
            bridgitWebElement.setFindBy(by);
            bridgitWebElement.setDriver(this);
        }
        return webElements;
    }

    public WebElement findElement(By by) {
        return findElements(by).get(0);
    }

    public String getPageSource() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(BridgItCommand.GET_SOURCE);
        return execute(command);
    }

    public void close() {
    }

    public void quit() {
    }

    public Set<String> getWindowHandles() {
        return null;
    }

    public String getWindowHandle() {
        return null;
    }

    public TargetLocator switchTo() {
        return null;
    }

    public Navigation navigate() {
        return null;
    }

    public Options manage() {
        return null;
    }

    public String execute(BridgItCommand command) {
        Object object = serverService.getObject(command);
        return object != null ? object.toString() : null;
    }
}
