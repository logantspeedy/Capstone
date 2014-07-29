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
        Game instance = null;
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoard method, of class Game.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        Board board = null;
        Game instance = null;
        instance.setBoard(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = null;
        String expResult = "";
        String result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPlayer method, of class Game.
     */
    @Test
    public void testSetCurrentPlayer() {
        System.out.println("setCurrentPlayer");
        String currentPlayer = "";
        Game instance = null;
        instance.setCurrentPlayer(currentPlayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextPlayer method, of class Game.
     */
    @Test
    public void testNextPlayer() {        
        System.out.println("nextPlayer"); 
        String expstartPlayer = "Adam";
        String startPlayer = testgame.getCurrentPlayer();
        String expResult = "Steve";
        String result = testgame.nextPlayer();
        assertEquals(expResult, result);
        assertEquals(expstartPlayer,startPlayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        System.out.println("reinforce");
        String territory = "";
        int troops = 0;
        Game instance = null;
        Board expResult = null;
        Board result = instance.reinforce(territory, troops);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fortify method, of class Game.
     */
    @Test
    public void testFortify() {
        System.out.println("fortify");
        String startTerritory = "";
        String targetTerritory = "";
        int troops = 0;
        Game instance = null;
        Board expResult = null;
        Board result = instance.fortify(startTerritory, targetTerritory, troops);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
