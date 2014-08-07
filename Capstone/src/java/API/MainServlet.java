/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package API;


import serverClasses.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("{response:error}");

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
        response.setContentType("application/json");
       
        PrintWriter out = response.getWriter();   
      
        //create new session or get current session
        HttpSession session = request.getSession();

        switch (request.getParameter("command")) {
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
      
        //create new session or get current session
        HttpSession session = request.getSession();

        switch (request.getParameter("command")) {
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
        
        return boardJSON;
     }
    
}




