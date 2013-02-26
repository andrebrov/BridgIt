package com.mingames.bridgit.service;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 30.01.13
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class BridgItCommand {

    public static final String AUTH = "AUTH";
    public static final String FIND = "FIND";
    public static final String GET_TITLE = "GET_TITLE";
    public static final String GET_URL = "GET_URL";
    public static final String GET_SOURCE = "GET_SOURCE";
    public static final String CLICK = "CLICK";
    public static final String SUBMIT = "SUBMIT";
    public static final String SEND_KEYS = "SENDKEYS";
    public static final String CLEAR = "CLEAR";
    public static final String GET_TAG_NAME = "GET_TAG_NAME";
    public static final String GET_ATTRIBUTE = "GET_ATTRIBUTE";
    public static final String GET_TEXT = "GET_TEXT";
    public static final String GET_LOCATION = "GET_LOCATION";
    public static final String GET_SIZE = "GET_SIZE";
    public static final String GET_CSS_VALUE = "GET_CSS_VALUE";

    private String function;
    private String[] param;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String[] getParam() {
        return param;
    }

    public void setParam(String... param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BridgItCommand)) return false;

        BridgItCommand command = (BridgItCommand) o;

        if (!function.equals(command.function)) return false;
        if (!Arrays.equals(param, command.param)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = function.hashCode();
        result = 31 * result + (param != null ? Arrays.hashCode(param) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"function\":\"" + function + '"' +
                ", \"param\":\"" + (param == null ? null : Arrays.asList(param)) +
                "\"}";
    }
}
