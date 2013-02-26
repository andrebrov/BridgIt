package com.mindgames.bridgit.driver;

import com.mingames.bridgit.driver.BridgitDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 26.02.13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class DriverAuthTest {

    @Test
    public void shouldAuthorizeDuringStart() {
        BridgitDriver driver = new BridgitDriver();
        String actualTitle = driver.getTitle();
        assertEquals("Test", actualTitle);
    }
}
