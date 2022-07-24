import java.util.LinkedList;
/**
 * A class representing the machine gun tower
 */
public class MachineGun extends Tower {
  
  /**
   * Creates a new MachineGun object
   * @param posX The x position we want to place it on
   * @param posY the y position we want to place it on
   */
  public MachineGun(int posX, int posY) {
    super(posX, posY);
    this.cost = 400;
    this.range = 250;
    this.fireRate = 0.25;
    this.imageName = "machineGun.png";
  }
  
  /**
   * Method that makes the MachineGun shoot a Bullet object
   * @param enemyList The current wave of enemies that are alive
   * @param changeInTime The elapsed time from the last frame
   * @return A Bullet object that is directed towards the enemy selected to be shot at
   */
  public Bullet shoot(LinkedList<Enemy> enemyList, double changeInTime) {
    Bullet p;
    timeSinceShot+=changeInTime;
    for (int i=0;i<enemyList.size();i++) {
      if (timeSinceShot<fireRate) { //Moved to inside the loop, so that tower no longer continues shooting after one time
        return null;
      }
      Enemy e = enemyList.get(i);
      double distance = Math.sqrt(Math.pow((e.posX-this.posX),2) + Math.pow((e.posY-this.posY),2));
      
      if (distance<range) {
        p = new Bullet(posX+50, posY+50, e.getX()+50, e.getY()+50); //Aims for center mass of enemy for increased accuracy
        timeSinceShot = 0;
        return p;
      }
    }
    return null;
  }
}