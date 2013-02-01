package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgitCommand;
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

    private ServerService serverService;

    public void get(String s) {
    }

    public String getCurrentUrl() {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.GETURL);
        Object object = serverService.getObject(command);
        return object != null ? object.toString() : null;
    }

    public String getTitle() {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.GETTITLE);
        Object object = serverService.getObject(command);
        return object != null ? object.toString() : null;
    }

    public List<WebElement> findElements(By by) {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.FIND);
        command.setParam(by.toString());
        return serverService.sendFindElementCommand(command);
    }

    public WebElement findElement(By by) {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.FIND);
        command.setParam(by.toString());
        return serverService.sendFindElementCommand(command).get(0);
    }

    public String getPageSource() {
        BridgitCommand command = new BridgitCommand();
        command.setFunction(BridgitCommand.GETSOURCE);
        Object object = serverService.getObject(command);
        return object != null ? object.toString() : null;
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
}
