package com.mingames.bridgit.service;

import com.mingames.bridgit.driver.BridgitWebElement;
import org.openqa.selenium.WebElement;
import org.svenson.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class ServerService {

    private BridgitServerConnector server = BridgitServerConnector.getInstance();

    public List<WebElement> sendFindElementCommand(BridgItCommand command) {
        Object object = server.sendRequest(command);
        return parseObjectAsWebElements(object);
    }

    private List<WebElement> parseObjectAsWebElements(Object object) {
        List<WebElement> result = new ArrayList<WebElement>();
        JSONParser parser = new JSONParser();
        if (object != null) {
            parser.addTypeHint("BridgitWebElement[]", BridgitWebElement.class);
            List<Map> result1 = parser.parse(List.class, object.toString());
            for (Map path : result1) {
                BridgitWebElement element = new BridgitWebElement();
                element.setFullPath(path.get("fullpath").toString());
                result.add(element);
            }
        }
        return result;
    }

    public Object getObject(BridgItCommand command) {
        return server.sendRequest(command);
    }
}
