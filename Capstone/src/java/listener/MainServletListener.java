/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Taylor
 */
public class MainServletListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final Map<String, HttpSession> playerSessions = new HashMap<String, HttpSession>();
    private static final Map<String, HttpSession> gameSessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        playerSessions.put(session.getId(), session);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        playerSessions.remove(session.getId());
        if (gameSessions.containsKey(session.getId())){
            gameSessions.remove(session.getId());
        }
        if (session.getAttribute("joinedgame") != null){
            HttpSession gameSession = findGame((String) session.getAttribute("joinedgame"));
            ArrayList<String> players = (ArrayList<String>) gameSession.getAttribute("players");
            players.remove((String) session.getAttribute("username")); 
            gameSession.setAttribute("players", players);
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        if (event.getName().equals("gamename")){
            gameSessions.put(session.getId(), session);          
        }
        if (event.getName().equals("joinedgame")){
            HttpSession gameSession = findGame((String) event.getValue());
            ArrayList<String> players = (ArrayList<String>) gameSession.getAttribute("players");
            players.add((String) session.getAttribute("username"));
            gameSession.setAttribute("players", players);
        }
    }
    
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("gamename")){
            gameSessions.remove(event.getSession().getId());         
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
    
    

    public HttpSession findPlayer(String sessionId) {
        return playerSessions.get(sessionId);
    }
    
    public Set getPlayerSessions(){
        return playerSessions.keySet();
    }
    
    public HttpSession findGame(String sessionId) {
        return gameSessions.get(sessionId);
    }
    
    public Set getGameSessions(){
        return gameSessions.keySet();
    }
    
}
