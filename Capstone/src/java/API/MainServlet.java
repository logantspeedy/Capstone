/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package API;


import ServerClasses.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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
import listener.MainServletListener;
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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");  
            out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js'></script>");
            out.println("</head>");
            out.println("<body>");

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
        //processRequest(request, response);
        
        response.setContentType("application/json");
       
        PrintWriter out = response.getWriter(); 
        
        MainServletListener listener = new MainServletListener(); 
        //create new session or get current session
        
        HttpSession session = request.getSession(false);
        Boolean joined = false;
        HttpSession oldSession = null;
        if (session != null && session.getAttribute("joinedgame") != null){
            String sessionId = (String) session.getAttribute("joinedgame");
            oldSession = session;
            session = listener.findGame(sessionId); 
            joined = true;
        }
        
        //join game, game name, player name
        
        //create game, game name, player name
        
        //get current games, game name, player names
        
            //game object is stored in the session of the player who created the game
            //other players have a reference to the game session in their own session
        
        

        
        switch (request.getParameter("command")) {   
            case "invite":
                {
                    if (request.getParameter("sessionid") == null || request.getParameter("sessionid").equals("")){
                        break;
                    }
                    
                    String userSessionId = request.getParameter("sessionid");
                    
                    
                    HttpSession userSession = listener.findPlayer(userSessionId);
                    
                    userSession.setAttribute("invited", session.getId());
                    
                    out.println("done inviting user");
                    
                    break;                    
                    
                    
                    
                }  
            
             case "checkinvite":
                {                
                    String result = "false";
                    if (session.getAttribute("invited") != null){
                        result  = session.getAttribute("invited").toString();
                    }
                    else{
                        result = "false";
                    }
                    
                    
                    Gson gson = new Gson();     

                    String resultJSON = gson.toJson(result);                   

                    out.println(resultJSON);
                    
                    break;                    
                    
                    
                    
                }           
             case "checkingame":
                {                
                    Boolean inGame = false;
                    if (joined == true){
                        inGame = true;
                    }
                    else if (session.getAttribute("gamename") != null){
                        inGame = true;
                    }
                    
                    Gson gson = new Gson();     

                    String inGameJSON = gson.toJson(inGame);                   

                    out.println(inGameJSON);
                    
                    break;                   
                    
                    
                    
                }
             case "checkuseringame":
                {
                    if (request.getParameter("sessionid") == null || request.getParameter("sessionid").equals("")){
                        break;
                    }                    
                    String userSessionId = (String) request.getParameter("sessionid");
                    
                    HttpSession userSession = listener.findPlayer(userSessionId);
                    
                    Boolean inGame = false;
                    if (userSession.getAttribute("joinedgame") != null || userSession.getAttribute("gamename") != null){
                        inGame = true;
                    }

                    
                    Gson gson = new Gson();     

                    String inGameJSON = gson.toJson(inGame);                   

                    out.println(inGameJSON);
                    
                    break;                   
                    
                    
                    
                }             
            case "gamestart":
                {
                    //checking if the game has been started by the game owner
                    Boolean gameStart = false;
                    if (session.getAttribute("game") != null){
                        gameStart = true;
                    }
                    Gson gson = new Gson();     

                    String gameStartJSON = gson.toJson(gameStart);                   

                    out.println(gameStartJSON);
                    break;                    
                    
                    
                    
                }               
            
            case "login":
                {
                    if (request.getParameter("username") == null || request.getParameter("username").equals("")){
                        break;
                    }  
                    //set variables
                    String username = request.getParameter("username");
                    
                    session.setAttribute("username", username);

                    out.println("done logging in");
                    break;
                    //given username, game session id
                    //set username in session, set joined attribute as the game session id
                    //update the game session with added user
                }            
            case "logout":
                {
                    if (joined == true){
                        oldSession.invalidate();
                    }
                    else{
                        session.invalidate();
                    }
                    out.println("done logging out");
                    break;
                    //given username, game session id
                    //set username in session, set joined attribute as the game session id
                    //update the game session with added user
                }              
            case "joingame":
                {
                    if (request.getParameter("username") == null || request.getParameter("gameid") == null){
                        break;
                    }  
                    //set variables
                    String username = request.getParameter("username");
                    String gameID = request.getParameter("gameid");  
                    
                    session.setAttribute("username", username);
                    session.setAttribute("joinedgame", gameID);
                    if (session.getAttribute("invited") != null){
                        session.removeAttribute("invited");
                    }
                    out.println("done joining game");
                    break;
                    //given username, game session id
                    //set username in session, set joined attribute as the game session id
                    //update the game session with added user
                }
            
            case "creategame":
                {
                    if (request.getParameter("private") == null || request.getParameter("username") == null || request.getParameter("gamename") == null || request.getParameter("username").equals("") || request.getParameter("gamename").equals("")){
                        break;
                    } 
                    
                    //set variables
                    String username = request.getParameter("username");
                    String gameName = request.getParameter("gamename");  
                    String privateGame = request.getParameter("private");
                    ArrayList<String> players = new ArrayList<>();
                    players.add(username);
                    
                    session.setAttribute("username", username);
                    session.setAttribute("gamename", gameName);                     
                    session.setAttribute("players", players); 
                    session.setAttribute("private", privateGame);
                    out.println("done creating game");
                    break;
                    //given username, gamename
                    //create sesssion with username, playerlist, gamename
                    
                }
            
            case "leavegame":
                {                    
                    if (joined == true){
                        if (oldSession.getAttribute("joinedgame") != null){
                            oldSession.removeAttribute("joinedgame");
                        }
                    }
                    else{
                        if (session.getAttribute("gamename") != null){
                        String username = (String) session.getAttribute("username");
                        session.invalidate();
                        session = request.getSession();
                        session.setAttribute("username", username);                       
                        }
                    }
                    
                    out.println("done leaving game");
                    break;

                    
                }
            case "getsessionidlobby":
                {
                    String sessionId = "0";
                    if (joined == true){
                        sessionId = oldSession.getId();
                    }
                    else{
                        if (session!=null){
                            sessionId = session.getId();
                        }
                    }

                    Gson gson = new Gson();     

                    String sessionIdJSON = gson.toJson(sessionId);                   

                    out.println(sessionIdJSON);
                    break;

                }            
            case "getgames":
                {
                   ArrayList<ArrayList<String>> gamesIDSet = listener.getGameSessions();
                   
        
                    Gson gson = new Gson();

        
                    //convert game to JSON
                    String gamesIDSetJSON = gson.toJson(gamesIDSet);                   

                    out.println(gamesIDSetJSON);
                    break;
                    //return a set list
                }
            
            case "usercheck":
                {
                    
                    
                    if (request.getParameter("gamesessionid") == null || request.getParameter("gamesessionid").equals("")){
                        break;
                    }   
                    Boolean inGame = false;
                    //checks if user is in a specified game
                    String gameSessionId = request.getParameter("gamesessionid");                     
                    
                   if (joined == true){
                       if (oldSession.getAttribute("joinedgame").equals(gameSessionId)){
                           inGame = true;
                       }
                   }
                   else if (session.getAttribute("gamename") != null){
                       if (session.getId().equals(gameSessionId)){
                          inGame = true;
                       }
                   }
                   
        
                    Gson gson = new Gson();

        
                    //convert game to JSON
                    String inGameJSON = gson.toJson(inGame);                   

                    out.println(inGameJSON);
                    break;
                    //return a set list
                }            
            
            case "getusers":
                {
                    ArrayList<ArrayList<String>> playerNames = listener.getPlayerNames();    
                    Gson gson = new Gson();

                    //convert game to JSON
                    String playerNamesJSON = gson.toJson(playerNames);                   

                    out.println(playerNamesJSON);
                    break;
                }            
            case "getgamedata":
                {
                    String JSON = getGameData(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                }  
            case "getgameowner":
                {
                    String JSON = getGameOwner(request, session);
                    if (JSON == null){return;}
                    out.println(JSON);
                    break;
                } 
            
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
            //set house added by jack
            case "sethouse":
                {
                String JSON = setHouse(request, session);
                if (JSON == null){return;}
                    out.println(JSON);
                break;
                }
            
            //added by jack
            case "getplayershouse":
                {
                String JSON = getPlayersHouse(request, session);
                if (JSON == null){return;}
                    out.println(JSON);
                break;
                }
            //added by jack
            case "getsessionid":
                {
                String JSON = getSessionId(request, session);
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
        
        System.out.println(" ");
        System.out.println("**********************In Claim Territory*********************");
        System.out.println("Target Player:" +playerName);
        System.out.println("Target territory:" +territory);
        System.out.println(" ");
        
        //check if this is the first call for the game
        if (session.getAttribute("game") == null){
            //create the game object
            System.out.println("entered new game in claimTer");
            ArrayList<String> players = (ArrayList<String>) session.getAttribute("players");
            String[] stringArrayPlayers = new String[players.size()];
            stringArrayPlayers = players.toArray(stringArrayPlayers);   
            game = new Game(stringArrayPlayers);
        }
        else{
            //if it's not the first call, get the game JSON data
            String gameJSON  = (String) session.getAttribute("game");
            //get the json string back into an object              
            game = gson.fromJson(gameJSON, Game.class); 
        }
        //claim territory
        game.claimTerritory(territory, playerName.trim());        
        
        //convert game to JSON
        String gameJSON = gson.toJson(game);
        
        //store session data
        session.setAttribute("game", gameJSON);
        return gameJSON;
    }
    
    public String startGame(HttpServletRequest request,HttpSession session){


        ArrayList<String> players = (ArrayList<String>) session.getAttribute("players");
        String[] stringArrayPlayers = new String[players.size()];
        stringArrayPlayers = players.toArray(stringArrayPlayers);       

        
        Gson gson = new Gson();
 
        Game game = new Game(stringArrayPlayers);

        
        //convert game to JSON
        String gameJSON = gson.toJson(game);
        

        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);

        //store session data
        session.setAttribute("game", gameJSON);
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
        
        System.out.println("*********In attack**********");
        System.out.println(attackingTerritory);
        System.out.println(defendingTerritory);
        //attack the territory
        game.attack(attackingTerritory, defendingTerritory);
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
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
        
        System.out.println("in next phase");
        //go to the next phase in the game
        game.nextPhase();
        
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        //store session data
        session.setAttribute("game", gameJSON);
        return boardJSON;
     }
    //set house added by jack
     public String setHouse(HttpServletRequest request,HttpSession session){
       
       if(session.getAttribute("game") == null || request.getParameter("house") == null
               ||request.getParameter("player") == null){
            return null;
        }
       
       //set variables
        String house = request.getParameter("house");
        String player = request.getParameter("player");
         
        //display variables
        System.out.println(" ");
        System.out.println("********************IN SET HOUSE***************");
        System.out.println("target house:"+ house);
        System.out.println("target player:"+ player);
        System.out.println(" ");
        
        //get game
        String gameJSON  = (String) session.getAttribute("game");
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);       
        
        //Get Players
        ArrayList<Player> players = game.getPlayerList();
        
        System.out.println("Displayer Players before Changes:");
        for (Player p : players){
            System.out.println(p);
        }
          
        for (Player p : players){
            if ((p.getName().trim()).equals(player.trim())){
                p.setHouse(house);}
        }
        
        System.out.println("Displayer Players after Changes:");
        for (Player p : players){
            System.out.println(p);
        }
        Boolean allHousesSet = true;
        for(Player p : players){
            if(p.getHouse().equals("")){
                allHousesSet = false;
            }
        }
        if(allHousesSet){
            game.setStartingHouses();
        }
        //convert back to json
        gameJSON = gson.toJson(game);
        
        //get the board and convert it to JSON
        Board board = game.getBoard();
        String boardJSON = gson.toJson(board);
        
        session.setAttribute("game", gameJSON);
        return gameJSON;

    } 
     
     public String getGameData(HttpServletRequest request,HttpSession session){
        
         if(session.getAttribute("game") == null){
            return null;
        }
         
       //set variables     
        String gameJSON  = (String) session.getAttribute("game");
        
//        System.out.println(" ");
//        System.out.println("********************In Get Game Data********************************");
//        System.out.println(gameJSON);
//        System.out.println(" ");
        return gameJSON;
     }    
     public String getGameOwner(HttpServletRequest request,HttpSession session){
        
         if(session.getAttribute("game") == null){
            return null;
        }
       //set variables     
        String username  = (String) request.getParameter("username");
        
        //NOT TESTED RETURNING JSON
        Gson gson = new Gson();
        String usernameJson = gson.toJson(username);
        return usernameJson;
        
     }  
    public String getPlayersHouse(HttpServletRequest request,HttpSession session){
        
         if(session.getAttribute("game") == null || request.getParameter("player") == null){
            return null;
        }
        
        String player = request.getParameter("player");
        
        //display in player house info
//        System.out.println(" ");
//        System.out.println("*******************In Get PlayersHouse***********************");
//        System.out.println("Player: "+player);
//        System.out.println(" ");
       
        String gameJSON  = (String) session.getAttribute("game");
        
        Gson gson = new Gson();
        Game game = gson.fromJson(gameJSON, Game.class);     
        ArrayList<Player> players = game.getPlayerList();


        
        String house = null;
//        System.out.println("Return Value BEFORE setting:" + house);
        
        for (Player p : players){
            if ((p.getName().trim()).equals(player.trim())){
                house = p.getHouse();}
       }
        
        
        String houseJson = gson.toJson(house);
        //        System.out.println("Return Value AFTER setting:" + houseJson);
        return houseJson;
     }
    
    public String getSessionId(HttpServletRequest request,HttpSession session){
        
        System.out.println("Session id is"+ session.getId());
        
        Enumeration e = session.getAttributeNames();
            while (e.hasMoreElements()) {
            String name = (String)e.nextElement();
    //        System.out.println(name);
            String value = session.getAttribute(name).toString();
    //        System.out.println(value);
        }
        return null;} 
}




