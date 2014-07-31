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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        //board format: ArrayList<Node> nodes
        //Node(String territory, String player, String continent, int troops, String[] adjacentNodes)
        
        response.setContentType("application/json");
       
        PrintWriter out = response.getWriter();
        //possible requests: reinforce, attack, fortify, new board, end turn
        

        
        //create new session or get current session
        HttpSession session = request.getSession();
        
        //new board: get given command + players
        //return: json of new board            
        if(request.getParameter("command").equals("newboard")){
                //get the json and set the session
                String json = newBoard(request, session);              
                //respond with the generated board as a json array
                out.println(json);
            }
        
        //reinforce: get given command, currentplayer, area to place troops, number of troops
        //return: json of new board        
        else if(request.getParameter("command").equals("reinforce")){
            String json = reinforce(request, session);
            if (json == null){
                out.println("Error");
                return;
            }
            out.println(json);
        }
        
       else if(request.getParameter("command").equals("attack")){
            if(session.getAttribute("board") == null){
                out.println("Error: No board found in session");
                return;
            }
            
            //attack(String attackingTerritory, String defendingTerritory, int aArmy, int dArmy
            out.println("attack");
        }
        else if(request.getParameter("command").equals("fortify")){
            if(session.getAttribute("board") == null){
                out.println("Error: No board found in session");
                return;
            }
            out.println("fortify");
        }
        else if(request.getParameter("command").equals("end")){
            if(session.getAttribute("board") == null){
                out.println("Error: No board found in session");
                return;
            }
            out.println("end turn");
        }

        
        //attack: get given command, board id, player attacking, player to attack, territory
        //return: json of new board
        
        //fortify: get given command, board id, player, area to place troops
        //return: json of new board
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "API for our risk";
    }// </editor-fold>


    public String newBoard(HttpServletRequest request,HttpSession session){
                
        //create a list of players 
        int playerNumber;

        if (request.getParameter("players") == null){
            playerNumber = 2;
        }              
        else{
            playerNumber = Integer.parseInt(request.getParameter("players"));
        }

        String[] playerArray = new String[6];
        while(playerNumber !=0){
            String player = "Player " + playerNumber;
            playerArray[playerNumber -1] = player;
            playerNumber -=1;
        }

        //create the game object
        Game game = new Game(playerArray);
        game.nextPlayer();
        Board board = game.getBoard();
        Gson gson = new Gson();
        String boardjson = gson.toJson(board);
        //converting an object that has the attribute which is a list of nodes. nodes are objects with attributes: String territory, String player, String continent, int troops, String[] adjacentNodes
        //returned should be {nodes: 
        //create json of game object to store in session
        String gamejson = gson.toJson(game);
        //store it in the session
        session.setAttribute("game", gamejson);
        session.setAttribute("board", boardjson);
        //might as well store the number of players too for later use
        session.setAttribute("players", playerNumber);

        
        
        return boardjson;
    }
    public String reinforce(HttpServletRequest request,HttpSession session){
       //check the session exsists
       if(session.getAttribute("game") == null){
            return null;
        }
       //if it does, take it out 
        String gamejson  = (String) session.getAttribute("game");
        //get the json string back into an object
        Gson gson = new Gson();
        Game game = gson.fromJson(gamejson, Game.class);


        //check all parameters are set
        if(request.getParameter("territory") == null || request.getParameter("troops") == null){
            return null;
        }
        //if they are, get them
        String territory = request.getParameter("territory");
        int troops = Integer.parseInt(request.getParameter("troops"));
        
        //reinforce the territory
        game.reinforce(territory, troops);
        
        //convert back to json
        String newgamejson = gson.toJson(game);
        //reset the session with new game
        session.setAttribute("game", newgamejson);        
        //return the  new json string
        return newgamejson;

    }
}




