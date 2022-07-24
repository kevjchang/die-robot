import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.LinkedList;

/**
 * A class representing a building
 */
public abstract class Tower extends MapObject {
  
  /**
   * The cost of the Tower
   */
  protected int cost;
  
  /**
   * The shooting range of the Tower
   */
  protected int range;
  
  /**
   * The time since the Tower has last fired
   */
  protected double timeSinceShot;
  
  /**
   * The time a Tower must wait before taking another shot
   */
  protected double fireRate;
  
  /**
   * Creates a new Tower object
   * @param posX The x position of the Tower
   * @param posY The y position of the Tower
   */
  public Tower(int posX, int posY) {
    super(posX, posY);
  }
  
  /**
   * Gets the cost of the Tower
   * @return Cost
   */
  public int getCost() {return cost;}
  
  /**
   * Shows the effective targeting range of the Tower by drawing a green circle
   * @param g A graphics object
   */
  public void showRange(Graphics g) {
    g.setColor(Color.GREEN);
    g.drawOval(posX-range+50, posY-range+50, range*2, range*2);
  }
  
  /**
   * A method that makes the Tower object shoot a Projectile object
   * @param enemyList The list of enemies that are on the screen/are alive
   * @param changeInTime The time in seconds since the last frame
   */
  public Projectile shoot(LinkedList<Enemy> enemyList, double changeInTime) {
    Projectile p;
    timeSinceShot+=changeInTime;
    for (int i=0;i<enemyList.size();i++) {
      if (timeSinceShot<fireRate) { //Moved to inside the loop, so that tower no longer continues shooting after one time
        return null;
      }
      Enemy e = enemyList.get(i);
      double distance = Math.sqrt(Math.pow(((e.posX+50)-(this.posX+50)),2) + Math.pow(((e.posY+50)-(this.posY+50)),2)); //Distance between Enemy and Tower
      
      if (distance<range) {
        p = new Projectile(posX+50, posY+50, e.getX()+50, e.getY()+50); //Aims for center mass of enemy for increased accuracy
        timeSinceShot = 0;                                              //Projectile also starts at the center of the tile its tower is placed on
        return p;
      }
    }
    return null;
  }
  
}