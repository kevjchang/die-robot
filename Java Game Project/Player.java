/**
 * A class representing a player and storing player data
 */
public class Player {
  
  /**
   * The name of the player
   */
  private String name;
  
  /**
   * The player's current score
   */
  private int score = 0;
  
  /**
   * The amount of monetary wealth the player has currently amassed
   */
  private int money = 1000;
  
  /**
   * The health of the player, or rather the health of the player's headquarters building
   */
  private int health = 1000;
  
  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
  
  /**
   * Gets the current score of the player
   * @return Player's score
   */
  public int getScore() {
    return score;
  }
  
  /**
   * Gets the current money the player has
   * @return Player's money
   */
  public int getMoney() {
    return money;
  }
  
  /**
   * Gets the player's health
   * @return The player's health
   */
  public int getHealth() {
    return health;
  }
  
  /**
   * Sets the score of the player
   * @param score The new score
   */
  public void setScore(int score) {
    this.score = score;
  }
  
  /**
   * Sets the money of the player
   * @param money The new money
   */
  public void setMoney(int money) {
    this.money = money;
  }
  
  /**
   * Sets the health of the player
   * @param health The new health
   */
  public void setHealth(int health) {
    this.health = health;
  }
}