package com.mindgames.bridgit.driver;

import com.mingames.bridgit.driver.BridgitDriver;
import com.mingames.bridgit.driver.BridgitWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 26.02.13
 * Time: 17:37
 */
public class DriverCommandsTest {

    @Test
    public void shouldFillFullPathWhenFindObject() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("logOutput"));
        assertNotNull(element);
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldClick() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("Test"));
        element.click();
    }

    @Test
    public void shouldGetTagName() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("Test"));
        assertEquals("DIV", element.getTagName());
    }


    @Test
    public void shouldGetSize() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("Test"));
        assertEquals(new Dimension(50, 100), element.getSize());
    }

    @Test
    public void shouldGetPosition() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("Test"));
        assertEquals(new Point(8, 412), element.getLocation());
    }

    @Test
    public void shouldSendKeys() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("textInput"));
        element.clear();
        element.sendKeys("Test");
        assertEquals("Test", element.getText());
    }

    @Test
    public void shouldGetAttributeValue() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("logOutput"));
        assertEquals("30", element.getAttribute("rows"));
    }

    @Test
    public void shouldGetCssValue() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("Test"));
        assertEquals("50px", element.getCssValue("width"));
    }

}