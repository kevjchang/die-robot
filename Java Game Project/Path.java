/**
 * A class representing a path tile that enemies can travel on
 */
public class Path extends Tile {
  
  /**
   * Creates a new Path object
   * @param posX The x position we want to create/display the Path
   * @param posY The y position we want to create/display the Path
   */
  public Path(int posX, int posY) {
    super(posX, posY);
    this.imageName = "pathTile.png"; //Setting the image name
  }
}