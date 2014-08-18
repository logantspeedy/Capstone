/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package API;


import listener.MainServletListener;
import serverClasses.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Taylor
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MainServletListener listener = new MainServletListener(); 
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");  
            out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            
            if(request.getParameter("submitcreate") != null){
                HttpSession session = request.getSession();
                if(request.getParameter("gamename") != null){
                    session.setAttribute("name", request.getParameter("gamename"));
                }
                if(request.getParameter("username") != null){
                    session.setAttribute("username", request.getParameter("username"));
                    ArrayList<String> players = new ArrayList<String>();
                    players.add(request.getParameter("username"));
                    session.setAttribute("players", players);
                    session.setAttribute("lobby", true);
                }
                out.println("Session created");
                out.println(listener.find(session.getId()).toString());
            }
            
            if (request.getParameter("submitjoin") != null){
                HttpSession session = listener.find(request.getParameter("join"));
                ArrayList<String> players = (ArrayList<String>) session.getAttribute("players");
                players.add(request.getParameter("username"));
                session.setAttribute("players", players);
            }
            
            
            
            //show current game (game name)
            
            out.println("<h1>Lobby</h1>");
            
             
            
 
 
            Set<String> listenerSessions = listener.getSessions();

            if (listenerSessions.size() > 0 && listener.find(listenerSessions.iterator().next()).getAttribute("name") != null){
                out.println("<h3>JOIN</h3>");
                out.println("Current number of games in lobby: " + listenerSessions.size());    
                out.println("<form action='MainServlet' method='POST'>");
                out.println("username: <input type='text' name='username'/><br/>");    
                for (String listenerSessionid : listenerSessions){
                    HttpSession listenerSession = listener.find(listenerSessionid);
                    String gameName = listenerSession.getId();
                    if (listenerSession.getAttribute("name") != null){
                        gameName = (String) listenerSession.getAttribute("name");
                    }

                    out.println("<div id='" + listenerSession.getId() + "' ><strong>" + gameName + "</strong>" + ": <input type='radio' name='join' value='" + listenerSession.getId() + "'/>");

                    out.println("Current Players Joined: " + listenerSession.getAttribute("players").toString() );
                    //out.println("<a href='javascript:void(0)' class='join-game'>Join</a></div>" );
                    if (request.getSession(false) != null){
                        if (listenerSessionid.equals(request.getSession(false).getId())){
                            out.println(" <a href='javascript:void(0)'> Start Game </a>");
                        }
                    }
                    
                    out.println("</div>");
                }
                out.println("<input type='submit' name='submitjoin' value='Join'/>");
                out.println("</form>");
            }
            
            //create a new game
            out.println("<h3>CREATE</h3>");
            out.println("<form action='MainServlet' method='POST'>");
            out.println("username: <input type='text' name='username'/><br/>");
            out.println("game name: <input type='text' name='gamename'/><br/>");
            out.println("<input type='submit' name='submitcreate' value='Create'/>");
            out.println("</form>");
            //HttpSession listenerSessionObject  = listener.find(listenerSession.toString());
            //out.println(listenerSessionObject.getAttribute("game"));
            out.println("</body>");
            out.println("</html>");            
            
            
            /*
            MainServletListener listener = new MainServletListener();  
            
            if(request.getParameter("join") != null){
                session.setAttribute("join", request.getParameter("join"));
            }
            
            
            if (session.getAttribute("join") != null){
                out.println("from session");
                String sessionId = (String) session.getAttribute("join");
                out.println("You are in the session: " + sessionId + "<br/>");
                session = listener.find(sessionId);
                if(request.getParameter("game") != null ){
                    session.setAttribute("game", request.getParameter("game"));
                }
                out.println("Currently stored in session: " + session.getAttribute("game") + "<br/>");
                out.println("<form action='MainServlet' method='POST'>");
                out.println("Tamper with data: <input type='text' name='game' value='" + session.getAttribute("game") + "'/><br/>");
                out.println("<input type='submit' name='Submit' value='Submit'/>");
                out.println("<a href='javascript:location.reload();'>Refresh</a>");
                
                return;
            }
            

            
            
            out.println("Join one of the following open sessions:<br/>");
            
            out.println("<form action='MainServlet' method='POST'>");
            
 
            Set listenerSessions = listener.getSessions();
            
            for (Object listenerSession : listenerSessions){
                
                out.println(listenerSession.toString() + ": <input type='radio' name='join' value='" + listenerSession.toString() + "'/>");
                
                if (listenerSession.toString().equals(session.getId())){
                    out.println("(yours)");
                }
                
                out.println("<br/>");
            }
            out.println("<input type='submit' name='Submit'/>");
            //HttpSession listenerSessionObject  = listener.find(listenerSession.toString());
            //out.println(listenerSessionObject.getAttribute("game"));
            out.println("</body>");
            out.println("</html>");
            */
        }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        response.setContentType("application/json");
       
        PrintWriter out = response.getWriter();   
      
        //create new session or get current session
        HttpSession session = request.getSession();
        

        if (request.getParameter("command").isEmpty() || request.getParameter("command") == null){
            processRequest(request, response);
            return;
        }
        
        switch (request.getParameter("command")) {
            
            case "startgame":
                {
                    String JSON = startGame(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                }        
            case "claimterritory":
                {
                    String JSON = claimTerritory(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                }
            case "reinforce":
                {
                    String JSON = reinforce(request, session);
                    if (JSON == null){return;}       
                    out.println(JSON);
                    break;
                }
            case "attack":
                {
                    String JSON = attack(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                }
            case "fortify":
                {
                    String JSON = fortify(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                }
            case "nextphase":
                String JSON = nextPhase(request, session); 
                if (JSON == null){return;}
                out.println(JSON);
                break;
                
                
                
        }       


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     * @return API for risk game
     */
    @Override
    public String getServletInfo() {
        return "API for our risk";
    }// </editor-fold>


    public String claimTerritory(HttpServletRequest request,HttpSession session){
        if (request.getParameter("playername") == null || request.getParameter("territory") == null){
            return null;
        }  
        //set variables
        String playerName = request.getParameter("playername");
        String territory = request.getParameter("territory");
        Game game;
        Gson gson = new Gson();
        //check if this is the first call for the game
        if (session.getAttribute("game") == null){
            //create the game object
            System.out.println("entered new game in claimTer");
            game = new Game(new String[]{"Player 1", "Player 2", "Player 3", "Player 4"});
        }
        else{
            //if it's not the first call, get the game JSON data
            String gameJSON  = (String) session.getAttribute("game");
            //get the json string back into an object              
            game = gson.fromJson(gameJSON, Game.class); 
        }
        //claim territory
        game.claimTerritory(territory, playerName);        
        //convert game to JSON
        String gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);


        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        
        return boardJSON;
    }
    
    public String startGame(HttpServletRequest request,HttpSession session){
        System.out.println("entered starting a new game!!!!!!!!!!");
        //set variables
        String playerName1 = request.getParameter("playername1");
        String playerName2 = request.getParameter("playername2");        
        Game game;
        
        Gson gson = new Gson();
        //check if this is the first call for the game

        game = new Game(new String[]{playerName1,playerName2});

        
        //convert game to JSON
        String gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);


        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        return boardJSON;
    }
    
    
    
    public String reinforce(HttpServletRequest request,HttpSession session){
       if(session.getAttribute("game") == null || request.getParameter("territory") == null || request.getParameter("troops") == null){
            return null;
        }
       //set variables
        String territory = request.getParameter("territory");
        int troops = Integer.parseInt(request.getParameter("troops"));     
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
 
        //reinforce the territory
        game.reinforce(territory, troops);
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());    
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        return boardJSON;

    }
    
    public String attack(HttpServletRequest request,HttpSession session){
       if(session.getAttribute("game") == null || request.getParameter("attackingterritory") == null || request.getParameter("defendingterritory") == null){
            return null;
        }
       //set variables
        String attackingTerritory = request.getParameter("attackingterritory");
        String defendingTerritory = request.getParameter("defendingterritory");      
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
 
        //attack the territory
        game.attack(attackingTerritory, defendingTerritory);
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());    
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        return boardJSON;

    }
    
    
    
    public String fortify(HttpServletRequest request,HttpSession session){
       
       if(session.getAttribute("game") == null || request.getParameter("startterritory") == null || request.getParameter("targetterritory") == null || request.getParameter("troops") == null){
            return null;
        }
        
       //set variables
        String startTerritory = request.getParameter("startterritory");
        String targetTerritory = request.getParameter("targetterritory");
        
        int troops = Integer.parseInt(request.getParameter("troops"));       
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
 
        //fortify
        game.fortify(startTerritory, targetTerritory, troops);
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());    
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        return boardJSON;

    } 
     public String nextPhase(HttpServletRequest request,HttpSession session){
       if(session.getAttribute("game") == null){
            return null;
        }
       //set variables     
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
 
        //go to the next phase in the game
        game.nextPhase();
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
        session.setAttribute("currentplayer", game.getCurrentPlayer());
        session.setAttribute("currentphase", game.getPhase());
        session.setAttribute("currentstage", game.getStage());    
        session.setAttribute("army", Integer.toString(game.currentPlayer.getArmy()));
        return boardJSON;
     }
     
     public String getGameData(HttpServletRequest request,HttpSession session){
       if(session.getAttribute("game") == null){
            return null;
        }
       //set variables     
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
 
        //go to the next phase in the game
        String currentPlayer = game.getCurrentPlayer();
        String currentPhase = game.getPhase();
        String currentstage = game.getStage();
        
        List<String>  gameData = Arrays.asList(currentPlayer, currentPhase, currentstage);
        
        //convert back to json
        String gameDataJSON = gson.toJson(gameData);
        
        return gameDataJSON;
     }    
}




