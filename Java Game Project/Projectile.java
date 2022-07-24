import java.lang.Math;
import java.awt.Rectangle;
/**
 * A class representing a projectile shot from a tower
 */
public class Projectile extends MapObject implements Moveable {
  
  /**
   * The speed of the Projectile over one frame
   */
  protected double speed;
  
  /**
   * The adjusted speed of the Projectile in the x direction
   */
  protected double vX;
  
  /**
   * The adjusted speed of the Projectile in the y direction
   */
  protected double vY;
  
  /**
   * The dimensions of the projectile
   */
  protected int sideLength;
  
  /**
   * A rectangle object used to detect collisions with enemies
   */
  protected Rectangle boundingBox;
  
  /**
   * The damage dealt by the Projectile upon collision with an enemy object
   */
  protected int damage;
  
  /**
   * Gets the damage dealt by the projectile
   * @return The damage value
   */
  public int getDamage() {return damage;}
  
  /**
   * Gets the speed of the Projectile
   * @return speed
   */
  public double getSpeed() {return speed;}
  
  /**
   * Gets the x velocity of the Projectile
   * @return vX
   */
  public double getVX() {return vX;}
  
  /**
   * Gets the y velocity of the Projectile
   * @return vY
   */
  public double getVY() {return vY;}
  
  /**
   * Gets the rectangle used as the boundingBox of the Projectile
   * @return The boundingBox
   */
  public Rectangle getBoundingBox() {return boundingBox;}
  
  /**
   * Creates a new Projectile object with some pre-determined values
   * @param posX the x position of the tower the Projectile is fired from
   * @param posY the y position of the tower the Projectile is fired from
   * @param enemyX the x position of the enemy being targeted
   * @param enemyY the y position of the enemy being targeted
   */
  public Projectile(int posX, int posY, double enemyX, double enemyY) {
    super(posX, posY);
    double angle = Math.atan2(enemyY-posY, enemyX-posX);
    vX = Math.cos(angle);
    vY = Math.sin(angle);
    //boundingBox = new Rectangle(posX, posY, sideLength, sideLength);
  }
  
  /**
   * Moves the Projectile for the duration of one frame
   */
  public void move() {
    posX+=vX*speed;
    posY+=vY*speed;
    boundingBox.setLocation(posX, posY); //Moving the boundingBox along with the image
  }
  
}