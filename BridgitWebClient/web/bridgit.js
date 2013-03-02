$(document).ready(
    function () {
        var socket = new WebSocket("ws://localhost:8081");
        socket.onopen = function () {
            writeToLog("Connection opened");
        };
        socket.onclose = function () {
            writeToLog("Connection closed");
        };
        socket.onmessage = function (event) {
            writeToLog("Recieve msg: " + event.data);
            send(process(event.data));
        };
        var send = function (msg) {
            writeToLog("Send msg: " + msg);
            socket.send(msg);
        };
        var writeToLog = function (msg) {
            var current = document.getElementById("logOutput").value;
            document.getElementById("logOutput").value = current + "\n" + msg;
        };
    });

var process = function (data) {
    var command = JSON.parse(data);
    var result = "";
    switch (command.function) {
        case "GET_TITLE":
            result = document.title;
            break;
        case "GET_URL":
            result = document.location;
            break;
        case "GET_SOURCE":
            result = document.body.innerHTML;
            break;
        case "FIND":
            if (command.param.indexOf(",") < 0) {
                result = objToString(getObject(command.param));
            } else {
                var params = parseComplexParam(command.param);
                var obj = getObjectFromObject(params[1], params[0]);
                result = objToString(obj);
            }
            break;
        case "CLICK":
            result = getObject(command.param).trigger('click');
            break;
        case "SUBMIT":
            result = getObject(command.param).trigger('submit');
            break;
        case "SENDKEYS":
            var params = parseComplexParam(command.param);
            result = getObject(params[0]);
            result.val(params[1]);
            break;
        case "CLEAR":
            getObject(command.param).val("");
            break;
        case "GET_TAG_NAME":
            result = getObject(command.param).prop("tagName");
            break;
        case "GET_ATTRIBUTE":
            var params = parseComplexParam(command.param);
            var obj = getObject(params[0]);
            result = obj.attr(params[1]).toString();
            break;
        case "GET_TEXT":
            var object2 = getObject(command.param);
            result = $(object2[0]).text();
            break;
        case "GET_LOCATION":
            pos = getObject(command.param).position();
            result = pos.left + ";" + pos.top;
            break;
        case "GET_SIZE":
            var object = getObject(command.param);
            var h = object.height();
            var w = object.width();
            result = w + ";" + h;
            break;
        case "GET_CSS_VALUE":
            var params = parseComplexParam(command.param);
            var obj = getObject(params[0]);
            result = obj.css(params[1]);
            break;
    }
    return result;
}

var getObject = function (path) {
    return getObjectFromObject("html", path);
}

var getObjectFromObject = function (startObj, path) {
    var resultObject;
    switch (true) {
        case path.indexOf("By.id") > 0:
            resultObject = $(startObj).find("#" + extractLocator(path, "By.id"));
            break;
        case path.indexOf("By.className") > 0:
            resultObject = $(startObj).find("." + extractLocator(path, "By.className"));
            break;
        case path.indexOf("By.tagName") > 0:
            resultObject = $(startObj).find(extractLocator(path, "By.tagName"));
            break;
        case path.indexOf("By.xpath") > 0:
            resultObject = $(startObj).find(document.body).xpath(extractLocator(path, "By.xpath"));
            break;
        case path.indexOf("By.selector") > 0:
            resultObject = $(startObj).find(extractLocator(path, "By.selector"));
            break;
        case path.indexOf("By.name") > 0:
            resultObject = $(startObj).find("*[name=" + extractLocator(path, "By.name") + "]");
            break;
        case path.indexOf("By.linkText") > 0:
            resultObject = $(startObj).find('a').filter(function (index) {
                return $(this).text() === extractLocator(path, "By.linkText");
            });
            break;
        case path.indexOf("By.partialLinkText") > 0:
            resultObject = $(startObj).find('a').filter(function (index) {
                return $(this).text().indexOf(extractLocator(path, "By.partialLinkText")) > 0;
            });
            break;
    }
    return resultObject;
}

var extractLocator = function (path, by) {
    var locatorStartIndex = path.indexOf(by) + by.length + 2;
    var locatorEndIndex = path.lastIndexOf("]");
    return path.substr(locatorStartIndex, locatorEndIndex - locatorStartIndex);
}

var objToString = function (objects) {
    var array = [];
    objects.each(function () {
        var preResult = {};
        preResult.fullpath = $(this).getDomPath();
        array.push(JSON.stringify(preResult));
    });
    return "[" + array.join() + "]";
}

var parseComplexParam = function (data) {
    var array = [];
    var commaIndex = data.indexOf(",");
    var bracketIndex = data.indexOf("]");
    array.push(data.substring(0, commaIndex) + "]");
    array.push(data.substring(commaIndex + 2, bracketIndex));
    return array;
}
