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
    <script type="text/javascript" src="/js/map.js">
    </script>
    <script type="text/javascript">
	function alert1(){
		alert("You have been subscribed!");
		return true;
		}
	</script>
	<script type="text/javascript">
	function alert2(){
		alert("You have been unsubscribed!");
		return true;
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

<%  }
    else{ %>

      <p>Hello!
      <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to create a new game!</p>
      
<%  } %>

    <% if (user != null){ %>
 
    <a href="/newgame.jsp">Create new game</a>
 	 <br>
 	 <br>    
    
    <div style="font-size:12px;color:#222222;height:800px;width:20%;border:3px solid #A0A0A0;overflow:auto;float:center;text-align:center;">
   	
<%  

	ObjectifyService.register(Game.class);
	List<Game> games = ObjectifyService.ofy().load().type(Game.class).list();   
	//games.clear();
	for(Game game: games){
		  pageContext.setAttribute("sport", game.getSport());
		  pageContext.setAttribute("start", game.getStartTime());
		  pageContext.setAttribute("end", game.getEndTime());

		  pageContext.setAttribute("numPlayers", game.getNumPlayers());
	

		  pageContext.setAttribute("numOfPlayers", game.getNumPlayers());
		  pageContext.setAttribute("id", game.getID());
		  %>
		  <hr>
		  

		    <p> Sport: <b>${fn:escapeXml(sport)}</b> </p>
		  	  <p> Start Time: <b>${fn:escapeXml(start)}</b> </p>
		  	  <p> End Time: <b>${fn:escapeXml(end)}</b> </p> 
		  	   <p> Numbers of players: <b>${fn:escapeXml(numPlayers)}</b> </p>
		  	  <p> End Time: <b>${fn:escapeXml(end)}</b> </p>
		  	  <p> Players: <b>${fn:escapeXml(numOfPlayers)}</b> </p>
		  <form action="/makegame" method="get">
		  <input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
		  <input type="submit" value="Join Game"/>
		  </form> 
		  
		    <div><form action="/subscribe" method="post">
		    	<input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
      			<div><input type="submit" value="Subscribe!" onclick="return alert1()"/></div>
			</form>
			<form action="/subscribe" method="get">
				<input type="hidden" name="gameId" id="gameId" value="<%=game.getID()%>" />
	      		<div><input type="submit" value="Unsubscribe" onclick="return alert2()"/></div>
			</form>
			</div>
			
			
		  <%
	}
    
	
    
    } %>
</div>
<br><a href="/">Home</a>
  </center>
  </body>
</html>