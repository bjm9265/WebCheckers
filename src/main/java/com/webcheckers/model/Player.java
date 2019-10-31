package com.webcheckers.model;



/**
 * This is the model for a single player entity.
 *
 * @author Justin Yau @ RIT CS Student
 */
public class Player {

  // Field where the player username will be stored
  private String username;

  /**
   * Create a new player entity with a reserved username
   *
   * @param username
   *          Username for the player entity
   */
  public Player(String username) {
    this.username = username;
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

  @Override
  public boolean equals(Object object){
    if (object instanceof Player){
        Player temp = (Player) object ;
        return this.username.equals(temp.username) ;
    }
    return false ;
  }

}