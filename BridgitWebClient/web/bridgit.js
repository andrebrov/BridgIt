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
    if (command.function == "GET_TITLE") {
        return document.title;
    }
}
