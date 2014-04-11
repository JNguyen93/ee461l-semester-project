package gamefinder;

import javax.servlet.http.HttpServletRequest;

public class GameServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)

                throws IOException {

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

        String sportName = req.getParameter("sport");
        String beginTime = req.getParameter("beginTime");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        String endTime = req.getParameter("endTime");
      

       Game game = new Game();
       game.setSport(sportName);
       game.setLocation();
       if(beginAMorPM=="am"){
    	//   game.setDate(beginTime);
       }
       else if(beginAMorPM=="pm"){
    	 //  game.setDate(beginTime+12);
       }
       if(endAMorPM=="am"){
       	//   game.setDate(endTime);
          }
          else if(endAMorPM=="pm"){
       	 //  game.setDate(endTime+12);
          }
  
      

 

        resp.sendRedirect("/home.jsp");

    }

}