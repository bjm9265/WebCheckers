package com.webcheckers.appl;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;

/**
 * Class for handling the creation of a game and management of who is in a game and what game specifically they are in
 * @author Brian Mirabito
 */

public class GameCenter {

    // map of active games
    private HashMap<Player, CheckersGame> activeGames;
    // map of players in a game with keys being a player and the value being opponent
    private HashMap<Player, Player> playersInGame;
    // map of players that will may have inverted views
    private HashMap<Player, Boolean> playersInverted;


    public GameCenter() {
        playersInGame = new HashMap<>() ;
        activeGames = new HashMap<>();
        playersInverted = new HashMap<>();
    }

    public CheckersGame startGame(Player one, Player two){
        CheckersGame game = new CheckersGame(one, two);
        addInGamePlayers(one, two);
        addToGame(one, game);
        playersInverted.put(one, false);
        playersInverted.put(two, true);
        addToGame(two, game); // Pass inverted game view here
        return game;
    }

    /**
     * adds players to the map of players who are currently in a game in opponent pairing
     * @param one = one player instance (key value)
     * @param two = one player instance (opponent of key)
     */
    public void addInGamePlayers(Player one, Player two){
        playersInGame.put(one, two);
        playersInGame.put(two, one);
    }

    public void playerLeftGame(Player one) {
        CheckersGame exitGame = getCheckersGame(one);
        this.activeGames.remove(one);
        this.playersInGame.remove(one);
        this.playersInverted.remove(one);
        Player opponent = null;
        for(Player p: this.activeGames.keySet()) {
            if(getCheckersGame(p) == exitGame) {
                opponent = p;
                break;
            }
        }
        if(opponent != null) {
            this.activeGames.remove(opponent);
            this.playersInGame.remove(opponent);
            this.playersInverted.remove(opponent);
        }
    }

    /**
     * adds player to active games map
     * @param p = player
     * @param g = game instance
     */
    public void addToGame(Player p, CheckersGame g){
        activeGames.put(p, g);
    }

    /**
     *
     * @param p player in the game that you want to find
     * @return returns specific game instance called
     */
    public CheckersGame getCheckersGame(Player p){
        return activeGames.get(p);
    }

    /**
     * Returns whether or not the player view is inverted
     * @param p - The player
     * @return
     */
    public boolean isPlayerViewInverted(Player p) {
        if(playersInverted.containsKey(p)) {
            return playersInverted.get(p);
        }
        return false;
    }

    /**
     * Returns the player instance of the opponent player
     * @param p player you want to find opponent of
     * @return the opponent player or null if no opponent
     */
    public Player getOpponent(Player p){
        return  playersInGame.containsKey(p) ? playersInGame.get(p) : null;
    }
}
