import java.awt.Rectangle;

/**
 * A class describing a bullet object fired by the machine gun tower
 */
public class Bullet extends Projectile {
  
  /**
   * Creates a new Bullet object with some pre-determined values
   * @param posX the x position of the tower the bullet is fired from
   * @param posY the y position of the tower the bullet is fired from
   * @param enemyX the x position of the enemy being targeted
   * @param enemyY the y position of the enemy being targeted
   */
  public Bullet(int posX, int posY, double enemyX, double enemyY) {
    super(posX, posY, enemyX, enemyY);
    this.speed = 10;
    this.sideLength = 15;
    this.damage = 15;
    boundingBox = new Rectangle(posX, posY, sideLength, sideLength);
    this.imageName = "bullet.png"; //Setting the image name
  }
}