package com.mindgames.bridgit.driver;

import com.mingames.bridgit.driver.BridgitDriver;
import com.mingames.bridgit.driver.BridgitWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 27.02.13
 * Time: 9:02
 * To change this template use File | Settings | File Templates.
 */
public class DriverFindTest {

    @Test
    public void shouldFindById() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.id("logOutput"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByClassName() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.className("testClass"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByTagName() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.tagName("input"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByXpath() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.xpath("//*[contains(@id, 'logOutput')]"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByCssSelector() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.cssSelector("#logOutput"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByName() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.name("testName"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByLinkText() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.linkText("someLink"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByPartialLinkText() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.partialLinkText("Link"));
        assertNotNull(element.getFullPath());
    }

    @Test
    public void shouldFindByFromElement() {
        BridgitDriver driver = new BridgitDriver();
        BridgitWebElement element = (BridgitWebElement) driver.findElement(By.tagName("body"));
        BridgitWebElement logOutput = (BridgitWebElement) element.findElement(By.cssSelector("#logOutput"));
        assertNotNull(logOutput.getFullPath());
    }
}
