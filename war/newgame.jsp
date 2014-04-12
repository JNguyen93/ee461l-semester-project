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
<select>
  <option value="football" name="sport">Football</option>
  <option value="soccer" name="sport">Soccer</option>
  <option value="basketball" name="sport">Basketball</option>
</select>
 </header> 

<body>
Time:
<select>
<option name="beginTime" value="1:00">1:00</option>
<option name="beginTime" value="2:00">2:00</option>
<option name="beginTime" value="3:00">3:00</option>
<option name="beginTime" value="4:00">4:00</option>
<option name="beginTime" value="5:00">5:00</option>
<option name="beginTime" value="6:00">6:00</option>
<option name="beginTime" value="7:00">7:00</option>
<option name="beginTime" value="8:00">8:00</option>
<option name="beginTime" value="9:00">9:00</option>
<option name="beginTime" value="10:00">10:00</option>
<option name="beginTime" value="11:00">11:00</option>
<option name="beginTime" value="12:00">12:00</option>

</select>
<select>
<option name="beginAMorPM" value="pm">pm</option>
<option name="beginAMorPM" value="am">am</option>
</select>
 to <select>
<option name="endTime" value="1:00">1:00</option>
<option name="endTime" value="2:00">2:00</option>
<option name="endTime" value="3:00">3:00</option>
<option name="endTime" value="4:00">4:00</option>
<option name="endTime" value="5:00">5:00</option>
<option name="endTime" value="6:00">6:00</option>
<option name="endTime" value="7:00">7:00</option>
<option name="endTime" value="8:00">8:00</option>
<option name="endTime" value="9:00">9:00</option>
<option name="endTime" value="10:00">10:00</option>
<option name="endTime" value="11:00">11:00</option>
<option name="endTime" value="12:00">12:00</option>

</select>
<select>
<option name="endAMorPM"value="pm">pm</option>
<option name="endAMorPM" value="am">am</option>
</select>
<br>
<input type="checkbox" name="email" value="emailNotification">send e-mail notifications<br>
<input type="checkbox" name="sms" value="smsNotification">send sms notifications<br>

<p>Click and drag to select a location to play!</p>
<div id="map-canvas"></div>

<br>
<input type="submit">
</form>

<br><br><br>

<a href="/home.jsp">Home</a>


<%  }
    else{ %>

      <p>Hello! Please 
      <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to create a new game! </p>
      <br><br><br><a href="/home.jsp">Home</a>
      
<%  } %>


    

</center>
  </body>
</html>