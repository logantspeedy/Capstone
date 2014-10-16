/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerClasses;

public class PresentationCases {
    private Game game;
    
    public PresentationCases(Game game){
        this.game = game;
    }
    
    public Game autoClaim(){
        Player currentPlayer;
        Node currentNode;
        for(int i = 0; i < 42; i++){
            currentNode = game.getBoard().nodes.get(i);
            currentPlayer = game.currentPlayer;
            if(currentNode.getControllingPlayer().equals(" ")){                
                game.claimTerritory(currentNode.getTerritoy(), currentPlayer.getName());
                game.currentPlayer = currentPlayer;
                game.currentPhase = "reinforce";
                game.reinforce(currentNode.getTerritoy(), 2);
                game.currentPhase = "claim";
            }
        }
        game.gameHistory = new ArrayList<String>();
        return game;
    }
    
    public Game playerAboutToDie(){
        int playerSize = game.getPlayerList().size();
        Player toDie = game.randomPlayer(playerSize);
        Player toKill = game.randomPlayer(playerSize);
        while(toDie.getName().equals(toKill.getName())){
            toKill = game.randomPlayer(playerSize);
        }
        Node currentNode;
        for(int i = 0; i < 42; i++){
            currentNode = game.getBoard().nodes.get(i);
            if(currentNode.getControllingPlayer().equals(toDie.getName())){
                if(toDie.territoriesControlled != 1){
                    toDie.territoriesControlled--;
                    toKill.territoriesControlled++;
                    currentNode.setControllingPlayer(toKill.getName());
                }
                else{
                    game.currentPhase = "reinforce";
                    game.currentStage = "game";
                    game.currentPlayer = toKill;
                }
            }
        }
        return game;
    }
}
