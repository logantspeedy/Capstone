/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests.ServerClasses;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import serverClasses.Board;
import serverClasses.Game;
import serverClasses.Player;
import serverClasses.Node;

/**
 *
 * @author ltspeedy
 */
public class GameTest {
    
    public Game testgame;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String[] players = new String[]{"Adam", "Steve", "John"};
        testgame = new Game(players);
        testgame.setCurrentPlayer("Adam");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBoard method, of class Game.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");        
		
		ArrayList<Node> nodes = new ArrayList();
		Node castleBlack = new Node("Castle Black", "", "The North", 0, new String[]{"Winterfell", "Crasters Keep"});
		Node winterfell = new Node("Winterfell", "", "The North", 0, new String[]{"The Twins", "Crasters Keep"});
		Node theTwins = new Node("The Twins", "", "Riverlands", 0, new String[]{"Winterfell", "The Eyrie", "Iron Islands"});
		Node theEyrie = new Node("The Eyrie", "", "The Vale", 0, new String[]{"The Twins", "Kings Landing"});
		nodes.add(castleBlack);
		nodes.add(winterfell);
		nodes.add(theTwins);
		nodes.add(theEyrie);
		Board expBoard = new(board);
		expBoard.setBoard(nodes);		
        
        Board result = testgame.getBoard();
        assertEquals(expBoard, result);
      
    } 

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        testgame.setCurrentPlayer("Steve");
        String expResult = "Steve";
        String result = testgame.getCurrentPlayer();        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCurrentPlayer method, of class Game.
     */
    @Test
    public void testSetCurrentPlayer() {
        System.out.println("setCurrentPlayer");
        String currentPlayer = "John";
        String expResult = "John";
        testgame.setCurrentPlayer(currentPlayer);
        String result = testgame.getCurrentPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of nextPlayer method, of class Game.
     */
    @Test
    public void testNextPlayer() {        
        System.out.println("nextPlayer"); 
        testgame.setCurrentPlayer("Adam");        
        String expResult = "Steve";
        String result = testgame.nextPlayer();
        assertEquals(expResult, result);       
        
    }

    /**
     * Test of NextStage method, of class Game.
     */
    @Test
    public void testNextStage() {
        System.out.println("NextStage");
        Game instance = null;
        instance.NextStage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reinforce method, of class Game.
     */
    @Test
    public void testReinforce() {
        //board is declared with empty countries so need to add a controlling player (who is the current player) to get past checks
        //then if reinforce for 1 we should get a result of 1 army in the country after the reinforce.
        System.out.println("reinforce");
        String territory = "Castle Black";
        int troops = 1;        
        Board expResult = testgame.getBoard();
        testgame.setControllingPlayer(territory,"Adam");
        Board resultBoard = testgame.reinforce(territory, troops);
        int expTroops=1;
        int resultTroops = resultBoard.getTroops(territory);
        assertEquals(expTroops, resultTroops);
        
    }

    /**
     * Test of fortify method, of class Game.
     */
    @Test
    public void testFortify() {
        //set up board with 2 armies in start territory and 1 in target territory
        // set both territories to belong to current player to get around checks
        System.out.println("fortify");
        String startTerritory = "Castle Black";
        String targetTerritory = "Winterfell";
        int troops = 1;
        Board testBoard = testgame.getBoard();
        testgame.setControllingPlayer(startTerritory,"Adam");
        testgame.setControllingPlayer(targetTerritory,"Adam");
        testBoard.changeTroops(startTerritory,2);
        testBoard.changeTroops(targetTerritory,1);         
        Board resultBoard = testgame.fortify(startTerritory, targetTerritory, troops);
        int expTroops=2;
        int resultTroops = resultBoard.getTroops(targetTerritory);
        assertEquals(expTroops, resultTroops);
        
    }

    /**
     * Test of attack method, of class Game.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        String attackingTerritory = "";
        String defendingTerritory = "";
        Game instance = null;
        Board expResult = null;
        Board result = instance.attack(attackingTerritory, defendingTerritory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
