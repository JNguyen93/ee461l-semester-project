<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.googlecode.objectify.Objectify" %>
<%@ page import="gamefinder.Game" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
  <%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <script type="text/javascript" src="/js/map-home.js">
    </script>
 </head>

 <header>
  <center><h1>Welcome to the Game Finder</h1></center>
  </header>

  <body>
<center>

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
  <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">here</a>.)</p>

<%  }
    else{%>
      <p>Hello!
      <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to create a new game!</p>
      
<%  } %>

<%  if (user != null){ %>
      <br><br>
      <a href="/newgame.jsp">Create new game</a><br><br>
       
<%    ObjectifyService.register(Game.class);
      List<Game> games = ObjectifyService.ofy().load().type(Game.class).list();
      %>
      <input type="hidden" id="hiddenGameSize" value="<%=games.size()%>">

      <div style="height:200px;width:80%;border:1px solid #000000;overflow:auto;">

<%  
      int i=0;
      for(Game game: games){  
        pageContext.setAttribute("sport", game.getSport());
        pageContext.setAttribute("start", game.getStartTime());
        pageContext.setAttribute("end", game.getEndTime());
        pageContext.setAttribute("locationName", game.getLocationName());
        pageContext.setAttribute("latitude", games.get(i).getLat());
        pageContext.setAttribute("longitude", games.get(i).getLng()); 
        pageContext.setAttribute("players", games.get(i).getNumPlayers());
        pageContext.setAttribute("maxPlayers", games.get(i).getMaxPlayers()); %>
              <p><b>Sport: </b>${fn:escapeXml(sport)}<input type="hidden" id="hiddenSport<%=i%>" value="<%=games.get(i).getSport()%>"></p>
              <p><b>Start Time: </b>${fn:escapeXml(start)}<input type="hidden" id="hiddenStartTime<%=i%>" value="<%=games.get(i).getStartTime()%>"></p>
              <p><b>End Time: </b>${fn:escapeXml(end)}<input type="hidden" id="hiddenEndTime<%=i%>" value="<%=games.get(i).getEndTime()%>"></p>
              <p><b>Location: </b>${fn:escapeXml(locationName)}<input type="hidden" id="hiddenLocationName<%=i%>" value="<%=games.get(i).getLocationName()%>"></p>
              <p><b>Players: </b>${fn:escapeXml(players)} &#x2F ${fn:escapeXml(maxPlayers)}</p>
              <input type="hidden" id="hiddenPlayers<%=i%>" value="<%=games.get(i).getNumPlayers()%>">
              <input type="hidden" id="hiddenMaxPlayers<%=i%>" value="<%=games.get(i).getMaxPlayers()%>">
              <input type="hidden" id="hiddenLat<%=i%>" value="<%=games.get(i).getLat()%>">
              <input type="hidden" id="hiddenLng<%=i%>" value="<%=games.get(i).getLng()%>">
              <hr>
<% 
    i++;}
  } %></div>

<br>
    <div id="map-canvas"></div><br><br>
  </center>
  </body>
</html>