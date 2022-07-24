import java.util.LinkedList;
import java.util.Queue;
/**
 * A class describing an inherently evil robot enemy
 */
public class Robot extends Enemy implements Moveable {
  
  /**
   * Creates a new enemy object
   * @param posX The starting x position of the enemy
   * @param posY The starting y position of the enemy
   * @param tileList A Queue containing all tiles the enemy must travel to
   */
  public Robot(int posX, int posY, Queue<Tile> tileList) {
    super(posX, posY, tileList); //Calling upon the superclass to create the route list and set positions
    this.health = 100;
    this.damage = 50;
    this.value = 20;
    this.speed = 5; //Speeds must be multiples/divisible by 10
    this.imageName = "robot.png";
  }
  
}