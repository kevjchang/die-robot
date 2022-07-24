import java.util.LinkedList;
import java.util.Queue;
/**
 * A class representing a tank type enemy
 */
public class Tank extends Enemy {
  
  /**
   * Creates a new enemy object
   * @param posX The starting x position of the enemy
   * @param posY The starting y position of the enemy
   * @param tileList A Queue containing all tiles the enemy must travel to
   */
  public Tank(int posX, int posY, Queue<Tile> tileList) {
    super(posX, posY, tileList); //Calling upon the superclass to create the route list and set positions
    this.health = 2500;
    this.damage = 200;
    this.value = 300;
    this.speed = 2; //Speeds must be multiples/divisible by 10
    this.imageName = "tank.png";
  }
}