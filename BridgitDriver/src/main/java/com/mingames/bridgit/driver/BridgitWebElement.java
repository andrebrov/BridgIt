package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgItCommand;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.mingames.bridgit.service.BridgItCommand.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public class BridgitWebElement implements WebElement {

    private By findBy;
    private BridgitDriver driver;
    private String fullPath;

    public void click() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(CLICK);
        command.setParam(findBy.toString());
        driver.execute(command);
    }

    public void submit() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(SUBMIT);
        command.setParam(findBy.toString());
        driver.execute(command);
    }

    public void sendKeys(CharSequence... charSequences) {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(SEND_KEYS);
        command.setParam(fillParamas(charSequences));
        driver.execute(command);
    }


    public void clear() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(CLEAR);
        command.setParam(findBy.toString());
        driver.execute(command);
    }

    public String getTagName() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_TAG_NAME);
        command.setParam(findBy.toString());
        return driver.execute(command);
    }

    public String getAttribute(String s) {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_ATTRIBUTE);
        command.setParam(fillParamas(s));
        return driver.execute(command);
    }

    public boolean isSelected() {
        String selected = getAttribute("selected");
        return selected != null;
    }

    public boolean isEnabled() {
        return Boolean.parseBoolean(getAttribute("enabled"));
    }

    public String getText() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_TEXT);
        command.setParam(findBy.toString());
        return driver.execute(command);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(this.getFullPath(), by);
    }

    public WebElement findElement(By by) {
        List<WebElement> elements = findElements(by);
        return elements.isEmpty() ? null : elements.get(0);
    }

    public boolean isDisplayed() {
        return Boolean.parseBoolean(getAttribute("displayed"));
    }

    public Point getLocation() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_LOCATION);
        command.setParam(findBy.toString());
        String result = driver.execute(command);
        String[] parse = result.split(";");
        return new Point(Integer.parseInt(parse[0]), Integer.parseInt(parse[1]));
    }

    public Dimension getSize() {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_SIZE);
        command.setParam(findBy.toString());
        String result = driver.execute(command);
        String[] parse = result.split(";");
        return new Dimension(Integer.parseInt(parse[0]), Integer.parseInt(parse[1]));
    }

    public String getCssValue(String s) {
        BridgItCommand command = new BridgItCommand();
        command.setFunction(GET_CSS_VALUE);
        command.setParam(fillParamas(s));
        return driver.execute(command);
    }

    public void setFindBy(By findBy) {
        this.findBy = findBy;
    }

    public By getFindBy() {
        return findBy;
    }

    public void setDriver(BridgitDriver driver) {
        this.driver = driver;
    }

    private String[] fillParamas(CharSequence... charSequences) {
        String[] additionalParams = new String[charSequences.length + 1];
        additionalParams[0] = findBy.toString();
        for (int i = 1; i <= charSequences.length; i++) {
            additionalParams[i] = charSequences[i - 1].toString();
        }
        return additionalParams;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFullPath() {
        return fullPath;
    }
}
