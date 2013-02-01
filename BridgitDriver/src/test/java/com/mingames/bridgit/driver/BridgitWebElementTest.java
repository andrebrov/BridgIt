package com.mingames.bridgit.driver;

import com.mingames.bridgit.service.BridgItCommand;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.mingames.bridgit.service.BridgItCommand.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 01.02.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class BridgitWebElementTest {

    public static final By SOME_ID_LOCATOR = By.id("someId");
    private BridgitDriver driver;
    private BridgItCommand[] commands;
    private BridgitWebElement element;

    @BeforeMethod
    public void setup() {
        element = new BridgitWebElement();
        element.setFindBy(SOME_ID_LOCATOR);
        driver = mock(BridgitDriver.class);
        element.setDriver(driver);
        commands = new BridgItCommand[]{null};
        prepareDriverForExecute(null);
    }


    private void prepareDriverForExecute(final String resultValue) {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                commands[0] = (BridgItCommand) arguments[0];
                return resultValue;
            }
        }).when(driver).execute((BridgItCommand) any());
    }

    @Test
    public void shouldClick() {
        element.click();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", CLICK, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
    }

    @Test
    public void shouldSubmit() {
        element.submit();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", SUBMIT, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
    }

    @Test
    public void shouldSendKeys() {
        element.sendKeys("keys");
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", SEND_KEYS, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong locator to click", "keys", command.getParam()[1]);
    }

    @Test
    public void clear() {
        element.clear();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", CLEAR, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
    }

    @Test
    public void shouldGetTagName() {
        prepareDriverForExecute("tagname");
        String tagName = element.getTagName();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_TAG_NAME, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong tag name", "tagname", tagName);
    }

    @Test
    public void shouldGetAttribute() {
        prepareDriverForExecute("attributeValue");
        String attribute = element.getAttribute("attributeName");
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_ATTRIBUTE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong attribute value", "attributeValue", attribute);
    }

    @Test
    public void shouldGetIsSelected() {
        prepareDriverForExecute("true");
        Boolean attribute = element.isSelected();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_ATTRIBUTE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertTrue("Wrong selected value", attribute);
    }

    @Test
    public void shouldGetIsEnabled() {
        prepareDriverForExecute("true");
        Boolean attribute = element.isEnabled();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_ATTRIBUTE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertTrue("Wrong selected value", attribute);
    }

    @Test
    public void shouldGetText() {
        prepareDriverForExecute("text");
        String text = element.getText();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_TEXT, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong text", "text", text);
    }

    public void shouldFindElements() {
        fail();
    }

    public void shouldFindElement() {
        fail();
    }

    @Test
    public void shouldGetIsDisplayed() {
        prepareDriverForExecute("true");
        Boolean attribute = element.isEnabled();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_ATTRIBUTE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertTrue("Wrong selected value", attribute);
    }

    @Test
    public void shouldGetLocation() {
        prepareDriverForExecute("15;60");
        Point location = element.getLocation();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_LOCATION, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong selected value", 15, location.getX());
        assertEquals("Wrong selected value", 60, location.getY());
    }


    @Test
    public void shouldGetSize() {
        prepareDriverForExecute("15;60");
        Dimension size = element.getSize();
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_SIZE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong selected value", 15, size.getWidth());
        assertEquals("Wrong selected value", 60, size.getHeight());
    }

    @Test
    public void shouldGetCssValue() {
        prepareDriverForExecute("cssValue");
        String cssValue = element.getCssValue("cssAttributeName");
        BridgItCommand command = commands[0];
        verify(driver).execute((BridgItCommand) any());
        assertNotNull("Command was not generated", command);
        assertEquals("Wrong command was sent", GET_CSS_VALUE, command.getFunction());
        assertEquals("Wrong locator to click", SOME_ID_LOCATOR.toString(), command.getParam()[0]);
        assertEquals("Wrong locator to click", "cssAttributeName", command.getParam()[1]);
        assertEquals("Wrong selected value", "cssValue", cssValue);
    }

}
