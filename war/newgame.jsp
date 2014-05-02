<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.googlecode.objectify.*" %>

<%@ page import="gamefinder.*" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
%> 

<html>

<head>
   <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />


       <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    <style type="text/css">
      #map-canvas { height: 75%; width:80%;}
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXMMCXLxQqjdY-Y3T3oEQwkxc8bC_NYag&sensor=false"/>
    </script>
    <script type="text/javascript" src="/js/map-newgame.js">
    </script>

 
</head>

<center>
 <header>
  <center><h1>Welcome to the Game Finder</h1></center>
  </header>
<body>

  

<%
    String websiteName = request.getParameter("websiteName");
    if (websiteName == null) {
        websiteName = "Game Finder";
    }
    pageContext.setAttribute("websiteName", websiteName);
    if (user != null) {
      pageContext.setAttribute("user", user);
       Date date = new Date();
   	out.print( "<h2 align=\"center\">" + date.toString() + "</h2>");
  
%>

  <p>Hello, ${fn:escapeXml(user.nickname)}! (You can sign out
  <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">here</a>.)</p>

</body>



<form action="/makegame" method="post">
<header>What kind of game would you like to play?
<select name = "sport">
  <option value="football">Football</option>
  <option value="soccer">Soccer</option>
  <option value="basketball">Basketball</option>
  <option value="ultimate">Ultimate</option>
  <option value="tennis">Tennis</option>
  <option value="volleyball">Volleyball</option>
  <option value="dodgeball">Dodgeball</option>
  <option value="baseball">Baseball</option>
  <option value="kickball">Kickball</option>
</select>
 </header> 

<body>
  Minimum Amount of Players: 
<select name ="numOfPlayers">
 <% for (int i=2; i<=20; i++){%>
    <option value="<%=i%>"><%=i%></option>
<%}%>
</select><br>
Time:  
<select name="beginTimeHour">
  <option value="12">12</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
</select>:
<select name="beginTimeMin">
  <option value="00">00</option>
  <option value="30">30</option>
</select>
<select name="beginAMPM">
  <option value="PM">pm</option>
  <option value="AM">am</option>
</select>
 to <select name="endTimeHour">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
  <option value="11">11</option>
  <option value="12">12</option>
</select>:
<select name="endTimeMin">
  <option value="00">00</option>
  <option value="30">30</option>
</select>
<select name="endAMPM">
  <option value="PM">pm</option>
  <option value="AM">am</option>
</select>
<br>
Date:
<select name="Month">
  <option value="1">January</option>
  <option value="2">February</option>
  <option value="3">March</option>
  <option value="4">April</option>
  <option value="5">May</option>
  <option value="6">June</option>
  <option value="7">July</option>
  <option value="8">August</option>
  <option value="9">September</option>
  <option value="10">October</option>
  <option value="11">November</option>
  <option value="12">December</option>
</select>
<select name="Day">
  <% for (int i=1; i<32; i++){%>
    <option value="<%=i%>"><%=i%></option>
<%}%>
</select>
<select name="Year">
  <option value="2014">2014</option>
</select><br>

<input type="checkbox" name="emailNotification" value="emailNotification">send me an e-mail notification when there's enough players!<br>
<input type="checkbox" name="smsNotifcation" value="smsNotification">send sms notifications<br>

<input type="hidden" name="longitude" id="longitude">
<input type="hidden" name="latitude" id="latitude">
<input type="text" name="locationName" id="locationName" size="50" value="Enter a name for the location">
<br>


<p>Click and drag to select a location to play!</p>
<div id="map-canvas"></div>

<br>
<input type="submit" value="Create new game"/>
</form>

<br><br><br><a href="/">Home</a>


<%  }
    else{ %>

      <p>Hello! Please 
      <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to create a new game! </p>
      <br><br><br><a href="/">Home</a>
      
<%  } %>


    

</center>
  </body>
</html>