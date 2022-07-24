import java.awt.Rectangle;

/**
 * A class representing the projectile shot by the laser cannon
 */
public class LaserBeam extends Projectile {

  /**
   * Creates a new LaserBeam object
   * @param posX the x position of the tower the bullet is fired from
   * @param posY the y position of the tower the bullet is fired from
   * @param enemyX the x position of the enemy being targeted
   * @param enemyY the y position of the enemy being targeted
   */
  public LaserBeam (int posX, int posY, double enemyX, double enemyY) {
    super(posX, posY, enemyX, enemyY);
    this.speed = 20;
    this.sideLength = 25;
    this.damage = 85;
    this.boundingBox = new Rectangle(posX, posY, sideLength, sideLength);
    this.imageName = "laserBeam.png";
  }
  
}