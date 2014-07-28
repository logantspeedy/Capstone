/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package API;

import ServerClasses.*;
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
        //possible requests: reinforce, attack, fortify, new board, 
        
        //new board: get given command + players. create new board using node and board class, create new session with id and new board, pass back the session id
        //return: json of new board
            
        if(request.getParameter("command")== "newboard"){
                //create new session
                HttpSession session = request.getSession();
                //create a new board class object
                Board board = new Board();
        
                int players = Integer.parseInt(request.getParameter("players"));
                
                //create the new board for the correct amount of players
                board.createNewBoard(players);
                
                //store it in the session
                session.setAttribute("board", board);
                //might as well store the number of players too for later use
                session.setAttribute("players", players);
                
                //respond with the generated board as a json array
                JSONArray boardJSON = new JSONArray(board.getBoard());
                out.println(boardJSON);
            }
        

        
        //reinforce: get given command, the board id, player, area to place troops
        //return: json of new board
        
        //attack: get given command, board id, player attacking, player to attack, territory
        //return: json of new board
        
        //fortify: get given command, board id, player, area to place troops
        //return: json of new board
        processRequest(request, response);
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
