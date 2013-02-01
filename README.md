BridgIt Project
=======

This project is an implementation of a WebDriver that uses javascript client to interact with web page. 
BridgItDriver project has 3 parts:
  - WebDriver implementation (BridgItDriver)
  - server (BridgItServer)
  - javascript webdriver

Interaction works in next way. We use BridgItDriver as any other Selenium WebDriver implementation. BridgItDriver send 
commands to BridgItServer when we do any interaction with web page (find element, click on element and etc.). Then server 
via websocket send this commands to javascript webdriver which will interact with webpage and send result via BridgItServer
back to BridgItDriver.
