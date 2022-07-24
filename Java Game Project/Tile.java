/**
 * A class representing a tile on the map
 */
public abstract class Tile extends MapObject {
  
  /**
   * The width of the Tile
   */
  protected int width = 100;
  
  /**
   * The height of the Tile
   */
  protected int height = 100;
  
  /**
   * Creates a new Tile object
   * @param posX The x position where we want to create the Tile
   * @param posY The y position where we want to create the Tile
   */
  public Tile(int posX, int posY) {
    super(posX, posY);
  }
  
  /**
   * Gets the width of the Tile
   * @return The Tile width
   */
  public int getWidth() {return width;}
  
  /**
   * Gets the height of the Tile
   * @return The Tile height
   */
  public int getHeight() {return height;}
}