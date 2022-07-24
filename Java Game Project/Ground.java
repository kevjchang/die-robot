/**
 * A class that represents the standard terrain of the playable map
 */
public class Ground extends Tile {
  
  /**
   * Creates a new Ground tile object
   * @param posX The x position that we are placing the tile on
   * @param posY The y position that we are placing the tile on
   */
  public Ground(int posX, int posY) {
    super (posX, posY);
    this.imageName = "groundTile.png"; //Setting the name of the image
  }
}