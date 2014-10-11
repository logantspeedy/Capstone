/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
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
            if (gameSession.getAttribute("players") == null){
                return;
            }
            ArrayList<String> players = (ArrayList<String>) gameSession.getAttribute("players");
            players.remove((String) session.getAttribute("username")); 
            gameSession.setAttribute("players", players);
        }
        checkPlayers();
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
        if (event.getName().equals("joinedgame")){
            HttpSession gameSession = findGame((String) event.getValue());
            if (gameSession.getAttribute("players") == null){
                return;
            }
            ArrayList<String> players = (ArrayList<String>) gameSession.getAttribute("players");
            String username = (String) event.getSession().getAttribute("username");
            for (String player : players){
                if (player.equals(username)){
                    players.remove(player);
                }
            }
        }
        
        checkPlayers();
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        checkPlayers();
    }
    
    private void checkPlayers(){
        Set <String> playerSessions = getPlayerSessions();
        for (String sessionId : playerSessions){
            HttpSession playerSession = findPlayer(sessionId);
            if (playerSession.getAttribute("joinedgame") != null){
                String gameSessionId = (String) playerSession.getAttribute("joinedgame");
                if (findGame(gameSessionId) == null){
                    playerSession.removeAttribute("joinedgame");
                }
            }
        }
    }

    public HttpSession findPlayer(String sessionId) {
        return playerSessions.get(sessionId);
    }
    
    public Set getPlayerSessions(){
        return playerSessions.keySet();
    }
    
    public ArrayList<ArrayList<String>> getPlayerNames(){
        ArrayList<ArrayList<String>> players = new ArrayList<>();
        
        for (String session : playerSessions.keySet()){
            HttpSession playerSession = playerSessions.get(session);
            if (playerSession.getAttribute("username") != null){
                ArrayList<String> playerNameSession = new ArrayList<>();
                String username = (String) playerSession.getAttribute("username");
                playerNameSession.add(username);
                playerNameSession.add(session);
                players.add(playerNameSession);
            }    
        }
        return players;
    }
    
    public HttpSession findGame(String sessionId) {
        return gameSessions.get(sessionId);
    }
    
    public ArrayList<ArrayList<String>> getGameSessions(){
        ArrayList<ArrayList<String>> gamesSessionData = new ArrayList<>();
        for (String sessionId : gameSessions.keySet()){
            HttpSession gameSession = findGame(sessionId);
            ArrayList<String> singleSessionData = new ArrayList<String>();
            singleSessionData.add(gameSession.getId());
            singleSessionData.add((String) gameSession.getAttribute("gamename"));
            singleSessionData.add((String) gameSession.getAttribute("username"));
            String playersString = "";
            ArrayList<String> players = (ArrayList<String>) gameSession.getAttribute("players");
            ListIterator<String> iterator = players.listIterator();
            while(iterator.hasNext()){
                playersString = playersString + iterator.next();
                if(!iterator.hasNext()){
//                    playersString = playersString + ".";
                }
                else{
                    playersString = playersString + ", ";
                }
            }   
                             
            singleSessionData.add(playersString);
            singleSessionData.add((String) gameSession.getAttribute("private"));
            if (gameSession.getAttribute("game") != null){
                singleSessionData.add((String) "inprogress");
            }
            gamesSessionData.add(singleSessionData);
        }

        return gamesSessionData;
    }
    
}
