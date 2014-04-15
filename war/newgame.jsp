<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

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
    <style type="text/css">
      #map-canvas { height: 75%; width:80%;}
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXMMCXLxQqjdY-Y3T3oEQwkxc8bC_NYag&sensor=false"/>
    </script>
    <script type="text/javascript" src="/js/map-draggable.js">
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
%>

  <p>Hello, ${fn:escapeXml(user.nickname)}! (You can sign out
  <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">here</a>.)</p><br><br><br>

</body>



<form action="/makegame" method="post">
<header>What kind of game would you like to play?
<select name = "sport">
  <option value="football">Football</option>
  <option value="soccer">Soccer</option>
  <option value="basketball">Basketball</option>
</select>
 </header> 
Players: 
<select name ="numOfPlayers">
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
</select>
<body>
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

</select>
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
<input type="checkbox" name="email" value="emailNotification">send e-mail notifications<br>
<input type="checkbox" name="sms" value="smsNotification">send sms notifications<br>

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