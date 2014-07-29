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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

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
        
        //new board: get given command + players. create new board using node and board class, create new session with id and new board, pass back the session id
        //return: json of new board       
        if(request.getParameter("command").equals("newboard")){

                
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

                Board board = game.getBoard();
                Gson gson = new Gson();
                String json = gson.toJson(board);
                //converting an object that has the attribute which is a list of nodes. nodes are objects with attributes: String territory, String player, String continent, int troops, String[] adjacentNodes
                //returned should be {nodes: 
                
                
                //store it in the session
                session.setAttribute("board", json);
                //might as well store the number of players too for later use
                session.setAttribute("players", playerNumber);
                
                //respond with the generated board as a json array
                out.println(json);
            }
        
        else if(request.getParameter("command").equals("reinforce")){
            if(session.getAttribute("board") == null){
                out.println("Error: No board found in session");
                return;
            }
            Gson gson = new Gson();
            //gson.fromJson(session.getAttribute("board"), Board.class);
            out.println(session.getAttribute("board"));
            out.println("reinforce");
        }

       else if(request.getParameter("command").equals("attack")){
            if(session.getAttribute("board") == null){
                out.println("Error: No board found in session");
                return;
            }
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
        //reinforce: get given command, the board id, player, area to place troops
        //return: json of new board
        
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

}
