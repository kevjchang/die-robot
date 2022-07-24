import java.util.LinkedList;
/**
 * A class representing a laser cannon defense
 */
public class LaserCannon extends Tower {
  
  /**
   * Creates a new LaserCannon object
   * @param posX The x position we want to place it on
   * @param posY The y position we want to place it on
   */
  public LaserCannon(int posX, int posY) {
    super(posX, posY);
    this.cost = 600;
    this.range = 400;
    this.fireRate = 1.5;
    this.imageName = "laserCannon.png";
  }
  
  /**
   * Method that makes the LaserCannon shoot a LaserBeam object
   * @param enemyList The current wave of enemies that are alive
   * @param changeInTime The elapsed time from the last frame
   * @return A LaserBeam object that is directed towards the enemy selected to be shot at
   */
  public LaserBeam shoot(LinkedList<Enemy> enemyList, double changeInTime) {
    LaserBeam p; //Method overidden to return a laserbeam instead of a generic projectile
    timeSinceShot+=changeInTime;
    for (int i=0;i<enemyList.size();i++) {
      if (timeSinceShot<fireRate) { //Moved to inside the loop, so that tower no longer continues shooting after one time
        return null;
      }
      Enemy e = enemyList.get(i);
      double distance = Math.sqrt(Math.pow((e.posX-this.posX),2) + Math.pow((e.posY-this.posY),2));
      
      if (distance<range) {
        p = new LaserBeam(posX+50, posY+50, e.getX()+50, e.getY()+50); //Aims for center mass of enemy for increased accuracy
        timeSinceShot = 0;
        return p;
      }
    }
    return null;
  }
  
}