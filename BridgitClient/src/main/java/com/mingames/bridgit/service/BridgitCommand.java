package com.mingames.bridgit.service;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class BridgitCommand {
    public static final String FIND = "FIND";
    public static final String GETTITLE = "GETTITLE";
    public static final String GETURL = "GETURL";
    public static final String GETSOURCE = "GETSOURCE";
    private String function;
    private String param;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
