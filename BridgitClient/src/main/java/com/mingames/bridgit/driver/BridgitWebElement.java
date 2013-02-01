package com.mingames.bridgit.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public class BridgitWebElement implements WebElement {

    private Map<String, String> attributes = new HashMap<String, String>();

    public void click() {
    }

    public void submit() {
    }

    public void sendKeys(CharSequence... charSequences) {
    }

    public void clear() {
    }

    public String getTagName() {
        return getAttribute("tagName");
    }

    public String getAttribute(String s) {
        return attributes.get(s);
    }

    public boolean isSelected() {
        return Boolean.getBoolean(getAttribute("selected"));
    }

    public boolean isEnabled() {
        return Boolean.getBoolean(getAttribute("enabled"));
    }

    public String getText() {
        return getAttribute("text");
    }

    public List<WebElement> findElements(By by) {
        return null;
    }

    public WebElement findElement(By by) {
        return null;
    }

    public boolean isDisplayed() {
        return Boolean.getBoolean(getAttribute("displayed"));
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSize() {
        return null;
    }

    public String getCssValue(String s) {
        return null;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
