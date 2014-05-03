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
    <script type="text/javascript">
      function change(ref){
        if (ref.value === "Subscribe" ){
          ref.value = "Unsubscribe";
          return alert("You have been subscribed!");
        }
        else{
            ref.value = "Subscribe";
            return alert("You have been unsubscribed!");
        }
      }
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

  <a href="/newgame.jsp">Create new game!</a><br>

<%  }
    else{%>
      <p>Hello!
      <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to create a new game!</p><br><br>
<%  } 
%>
<%    ObjectifyService.register(Game.class);
      List<Game> games = ObjectifyService.ofy().load().type(Game.class).list();
%>

<div style="font-size:12px;color:#222222;height:492px;width:20%;border:3px solid #A0A0A0;overflow:auto;float:left;text-align:center;">

<%  
      int i=0;
      for(Game game: games){
        if(game.isExpiredGame()){
          continue;
        } 
        pageContext.setAttribute("sport", game.getSport());
        pageContext.setAttribute("start", game.getStartTime());
        pageContext.setAttribute("end", game.getEndTime());
        pageContext.setAttribute("locationName", game.getLocationName());
        pageContext.setAttribute("latitude", game.getLat());
        pageContext.setAttribute("longitude", game.getLng()); 
        pageContext.setAttribute("players", game.getNumPlayers());
        pageContext.setAttribute("Year", game.getYear());
        pageContext.setAttribute("Month", game.getMonthText());
        pageContext.setAttribute("Day", game.getDay());
        pageContext.setAttribute("maxPlayers", game.getMaxPlayers());
%>
              <p><b>Sport: </b>${fn:escapeXml(sport)}</p>
              <p><b>Location: </b>${fn:escapeXml(locationName)}</p>
              <p><b>Time: </b>${fn:escapeXml(start)} -  ${fn:escapeXml(end)}</p>
              <p><b>Date: </b>${fn:escapeXml(Month)} ${fn:escapeXml(Day)}, ${fn:escapeXml(Year)}</p>
              <p><b>Players: </b>${fn:escapeXml(players)} &#x2F ${fn:escapeXml(maxPlayers)}</p>

              <form action="/makegame" method="get">
                <input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
                <input type="submit" value="Join Game"/>
              </form> 

<%
  if (game.isSubscribed(user.getEmail())){    
%>
      <form action="/subscribe" method="post">
        <input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
            <input type="submit" value="Unsubscribe" onclick="return change(this)"/>
      </form>
<%
  }
  else{
%>
      <form action="/subscribe" method="post">
        <input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
            <input type="submit" value="Subscribe" onclick="return change(this)"/>
      </form>
<%
  }
%>


              <input type="hidden" id="hiddenYear<%=i%>" value="<%=game.getYear()%>">
              <input type="hidden" id="hiddenMonth<%=i%>" value="<%=game.getMonthText()%>">
              <input type="hidden" id="hiddenDay<%=i%>" value="<%=game.getDay()%>">
              <input type="hidden" id="hiddenLocationName<%=i%>" value="<%=game.getLocationName()%>">
              <input type="hidden" id="hiddenStartTime<%=i%>" value="<%=game.getStartTime()%>">
              <input type="hidden" id="hiddenEndTime<%=i%>" value="<%=game.getEndTime()%>">
              <input type="hidden" id="hiddenSport<%=i%>" value="<%=game.getSport()%>">
              <input type="hidden" id="hiddenPlayers<%=i%>" value="<%=game.getNumPlayers()%>">
              <input type="hidden" id="hiddenMaxPlayers<%=i%>" value="<%=game.getMaxPlayers()%>">
              <input type="hidden" id="hiddenLat<%=i%>" value="<%=game.getLat()%>">
              <input type="hidden" id="hiddenLng<%=i%>" value="<%=game.getLng()%>">
              <hr>
<% 
    i++;}
    if(i > 0){
    i = i-1;
  } %>
  </div>

<input type="hidden" id="hiddenGameSize" value="<%=i%>">
<br>
        <div style="float:right;overflow:auto;width:78%;border:3px solid #A0A0A0;" id="map-canvas">
<br><br>
  </center>
  </body>
</html>