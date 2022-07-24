import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * A class representing an object that is placed onto the screen.
 * This is the top level class for all objects that are drawn on the screen,
 * including Tiles, Towers, Enemies, and Projectiles.
 */
abstract class MapObject {
  
  /**
   * The x position of the MapObject
   */
  protected int posX;
  
  /**
   * The y position of the MapObject
   */
  protected int posY;
  
  /**
   * An image of the MapObject
   */
  protected BufferedImage image;
  
  /**
   * The name of the file we are going to use as the image of the MapObject
   */
  protected String imageName;
  
  /**
   * Creates a new MapObject
   * @param posX The x position where we want to create the MapObject
   * @param posY The y position where we want to create the MapObject
   */
  public MapObject(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }
  
  /**
   * Gets the current x position
   * @return posX
   */
  public int getX() {return posX;}
  
  /**
   * Gets the current y position
   * @return posY
   */
  public int getY() {return posY;}
  
  /**
   * Changes the x position of the MapObject
   * @param x the new x position we want to display the MapObject
   */
  public void setX(int x) {this.posX = x;}
  
  /**
   * Changes the y position of the MapObject
   * @param y the new y position we want to display the MapObject
   */
  public void setY(int y) {this.posY = y;}
  
  /**
   * Draws the MapObject onto the screen
   * @param g A graphics object
   */
  public void draw(Graphics g) {
    try {
      image = ImageIO.read(new File(imageName)); //Setting BufferedImage image using the image name
    } catch (Exception e) {
      System.out.println("error loading image: "+imageName);
    };
    g.drawImage(image, posX, posY, null); //This line actually draws the image
  }
}  