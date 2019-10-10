package com.webcheckers.model;

import com.sun.tools.javac.comp.Check;

/**
 * This is the model for a single player entity.
 *
 * @author Justin Yau @ RIT CS Student
 */
public class Player {

  // Field where the player username will be stored
  private String username;

  // Field where whether or not the player is in-game or not will be stored
  private boolean inGame;

  /**
   * Create a new player entity with a reserved username
   *
   * @param username
   *          Username for the player entity
   */
  public Player(String username) {
    this.username = username;
    this.inGame = false;
  }

  /**
   * Returns whether or not the player is in-game
   * @return
   *      Whether or not the player is in-game
   */
  public boolean isInGame() {
    return this.inGame;
  }

  /**
   * Sets whether or not the player is in-game
   * @param inGame - The new state of whether or not the player is in-game
   */
  public void toggleGame(boolean inGame) {
    this.inGame = inGame;
  }

  /**
   * Returns the username of the player
   * @return
   *      The username of the player
   */
  public String getName() {
    return this.username;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized String toString() {
    return this.username;
  }

}