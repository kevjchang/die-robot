import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class that combines the other elements of the game into one object, from which we can run the game we've created
 * January 22, 2021
 * @author Kevin Chang
 */
public class Game extends JFrame implements ActionListener {
  
  /**
   * A list of projectiles shot by towers
   */
  static LinkedList<Projectile> projectileList = new LinkedList<Projectile>();
  
  /**
   * A map object to be drawn later
   */
  static Map map = new Map(); //Already made to avoid nullPointerException
  
  /**
   * A player object to keep track of stats such as health and money
   */
  static Player player = new Player(); //Creating player object to store values like health and money
  
  /**
   * Wave object that creates new waves based on the waveNum and stores enemies
   */
  static Wave robotWave = new Wave(); //Already made to avoid nullPointerException
  
  /**
   * Wave number
   */
  static int waveNum = 0;
  
  /**
   * A list storing towers created by the player
   */
  static LinkedList<Tower> towerList = new LinkedList<Tower>(); //Already made to avoid nullPointerException
  
  /**
   * Keeps track of the time from the previous frame
   */
  double pastTime; //Keeps track of time
  /**
   * Keeps track of the current time on the current frame
   */
  double currentTime;
  /**
   * The difference in time between the current and previous frames
   */
  double elapsedTime;
  
  static myGraphicsPanel graphicsPanel; //panel that is being animated
  JPanel gameMenu;
  JPanel instructionPanel;
  JPanel sidePanel;
  
  JPanel buyPanel;
  JPanel tilePanel;
  
  static MyMouseListener mouseListener; //Mouse listener
  
  enum GameState { //GameState enums for convienience
    MENU, BUILD, WAVE, PAUSE, END
  }
  /**
   * Stores the current GameState of the game object
   */
  static GameState gameState;
  
  enum BuildState { //enums for allowing structures to be built. This way only one type of object can be placed at any time
    NONE, REMOVETOWER, PLACELASERCANNON, PLACEMACHINEGUN, PLACEPATH, REMOVEPATH;
  }
  /**
   * Stores the current BuildState of the object
   */
  static BuildState buildState;
  
  /**
   * Determines whether the effective areas of the towers will be shown on the screen
   */
  static boolean showRange = true;
  
  //*****************MAIN METHOD**************************//
  public static void main(String[]args) {
    
    new Game(); //Creating a game object, which will run once constructed
    
  }
  
  //**********************CONSTRUCTOR*****************************//
  
  /**
   * Creates a new Game object with all graphical components
   */
  public Game() {
    //Graphics component of the game
    setTitle("Die, Robot!");
    setSize(1900,1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    FlowLayout gameLayout = new FlowLayout();
    setLayout(gameLayout); //Setting the layout of the JFrame
    
    //********************Menu panel (the menu on the side of the screen when we open the game)*********************
    gameMenu= new JPanel(); 
    gameMenu.setLayout(new BoxLayout(gameMenu, BoxLayout.Y_AXIS));
    
    JButton startButton = new JButton("Play");
    JButton instructionsButton = new JButton("Instructions");
    JButton quitButton = new JButton("Quit");
    gameMenu.add(startButton);
    gameMenu.add(instructionsButton);
    gameMenu.add(quitButton);
    startButton.addActionListener(this);
    instructionsButton.addActionListener(this);
    quitButton.addActionListener(this);
    
    gameMenu.setPreferredSize(new Dimension(400,1000));
    
    //*************************Instructions & story panel*******************************
    instructionPanel = new JPanel();
    instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
    
    JButton backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    JLabel story = new JLabel("You are the leader of a distant human colony on the planet");
    JLabel story2 = new JLabel("er-a23, 23 light years away from earth. You’ve set up a");
    JLabel story3 = new JLabel("base on this planet as an outpost near a wormhole to Earth’s");
    JLabel story4 = new JLabel("solar system, searching for rare materials to fuel industry");
    JLabel story5 = new JLabel("back on your home planet.");
                              
    JLabel instructions = new JLabel("First, create a path that leads to the headquarters building.");
    JLabel instructions2 = new JLabel("We need this for supply. Then, use the credits you have to");
    JLabel instructions3 = new JLabel("purchase buildings that defend the supply route.");
    JLabel instructions4 = new JLabel("Click play to start killing robots!");
    
    
    instructionPanel.add(story);
    instructionPanel.add(story2);
    instructionPanel.add(story3);
    instructionPanel.add(story4);
    instructionPanel.add(story5);
    
    instructionPanel.add(instructions);
    instructionPanel.add(instructions2);
    instructionPanel.add(instructions3);
    instructionPanel.add(instructions4);
    
    instructionPanel.add(backButton);
    instructionPanel.setPreferredSize(new Dimension(400,1000));
    
    //*******************Side panel during the game******************************
    sidePanel = new JPanel();
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
    
    buyPanel = new JPanel(); //JPanel for buying towers 
    tilePanel = new JPanel(); //JPanel for placing tiles
    
    JButton waveButton = new JButton("Start Wave");
    JButton pauseButton = new JButton("Pause");
    JButton resumeButton = new JButton("Resume");
    JButton exitButton = new JButton("Exit Game");
    
    JButton buyLaserCannon = new JButton("Buy Laser Cannon");
    JButton buyMachineGun = new JButton("Buy Machine Gun");
    JButton removeTowerButton = new JButton("Remove Tower");
    JButton showRangeButton = new JButton("Toggle Visible Range");
    JButton placePathButton = new JButton("Place Path");
    JButton removePathButton = new JButton("Remove Path");
    
    buyPanel.add(buyLaserCannon);
    buyPanel.add(buyMachineGun);
    buyPanel.add(removeTowerButton);
    buyPanel.add(showRangeButton);
    
    tilePanel.add(placePathButton);
    tilePanel.add(removePathButton);
    
    //Adding cards and major buttons to main sidePanel
    sidePanel.add(waveButton);
    sidePanel.add(pauseButton);
    sidePanel.add(resumeButton);
    sidePanel.add(exitButton);
    sidePanel.add(buyPanel);
    sidePanel.add(tilePanel);
    sidePanel.setPreferredSize(new Dimension(400,1000));
    
    waveButton.addActionListener(this);
    pauseButton.addActionListener(this);
    resumeButton.addActionListener(this);
    exitButton.addActionListener(this);
    buyLaserCannon.addActionListener(this);
    buyMachineGun.addActionListener(this);
    removeTowerButton.addActionListener(this);
    showRangeButton.addActionListener(this);
    placePathButton.addActionListener(this);
    removePathButton.addActionListener(this);
    
    //*****************Animated panel**********************
    graphicsPanel = new myGraphicsPanel();
    graphicsPanel.setPreferredSize(new Dimension(1500,1000));
    //Adding mouseListener to the graphicsPanel
    mouseListener = new MyMouseListener();
    graphicsPanel.addMouseListener(mouseListener);
    
    
    
    //*************Adding panels to JFrame and setting stuff visible*************
    add(graphicsPanel);
    add(gameMenu);
    add(instructionPanel);
    add(sidePanel);
    
    setVisible(true);
    graphicsPanel.setVisible(true);
    gameMenu.setVisible(true);
    instructionPanel.setVisible(false);
    sidePanel.setVisible(false);
    
    //Setting the GameState
    gameState = GameState.MENU;
    //Setting the BuildState
    buildState = BuildState.NONE;
    
    run(); //Running game from constructor
    
  } //*******************End of constructor***************************
   
  //******************Implementing actionListener***************************
  
  /**
   * A method which keeps track of GUI button interactions
   * @param event An event indicating the occurrence of an action
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    if (command.equals("Quit")) { //button which quits game
      System.exit(0);
      
    } else if (command.equals("Play")) { //Switching to the side panel
      gameMenu.setVisible(false);
      sidePanel.setVisible(true);
      gameState = GameState.BUILD; //Changing the gamestate to allow for building towers and tiles
      
    } else if (command.equals("Instructions")) { //switching to the instructions panel
      gameMenu.setVisible(false);
      instructionPanel.setVisible(true);
      
    } else if (command.equals("Back")) {
      instructionPanel.setVisible(false);
      gameMenu.setVisible(true);
      
    } else if (command.equals("Exit Game")) { //Exits the current game, clearing all game data
      sidePanel.setVisible(false);
      gameMenu.setVisible(true);
      //Changing gameState
      gameState = GameState.MENU;
      //Resetting map
      map = new Map();
      //Erasing all towers, enemies, and bullets
      towerList.clear();
      (robotWave.getEnemyList()).clear();
      projectileList.clear();
      //Resetting wave counter, and player stats
      waveNum = 0;
      player = new Player();
      
    } else if ((command.equals("Start Wave"))&&(gameState==GameState.BUILD)) { //Starts a new wave
      buildState = BuildState.NONE;
      //Check if all tiles are placed correctly, then create a new wave of robots using the newWave() method
      if (map.findRoute()!=null) {
        System.out.println("Path valid");
        System.out.println("Starting a new wave ");
        waveNum++;
        robotWave.newWave(waveNum, map.findRoute()); //Creating a new wave based on the current wave number
        gameState = GameState.WAVE; //Changing the game state after creating all the robots
      } else if (map.findRoute()==null) {
        System.out.println("The path is invalid");
      }
      
    } else if ((command.equals("Buy Laser Cannon"))&&(gameState==GameState.BUILD)) {
      buildState = BuildState.PLACELASERCANNON;
      
    } else if ((command.equals("Buy Machine Gun"))&&(gameState==GameState.BUILD)) {
      buildState = BuildState.PLACEMACHINEGUN;
      
    } else if ((command.equals("Remove Tower"))&&(gameState==GameState.BUILD)) { //Might combine this with the other remove button
      buildState = BuildState.REMOVETOWER;
      
    } else if (command.equals("Toggle Visible Range")&&(gameState!=GameState.END)) {  
      if (showRange==false) {
        showRange = true;
      } else {
        showRange = false;
      }
      
    } else if ((command.equals("Place Path"))&&(gameState==GameState.BUILD)) {
      buildState = BuildState.PLACEPATH;
      
    } else if ((command.equals("Remove Path"))&&(gameState==GameState.BUILD)) {
      buildState = BuildState.REMOVEPATH;
      
    } else if ((command.equals("Pause"))&&(gameState==GameState.WAVE)) {
      gameState = GameState.PAUSE;
    } else if (command.equals("Resume")&&(gameState==GameState.PAUSE)) {
      gameState = GameState.WAVE;
    }
  }
  
  //******************mouseListener***********************
  /**
   * A class which tracks mouse interactions
   */
  static class MyMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e){
      int mouseX = e.getX();
      int mouseY = e.getY();
      //Add new tower (or tile in other if statements) here based on what object was selected
      Tile t = map.getTile(mouseX, mouseY);
      
      
      if (buildState==BuildState.PLACEPATH) {
        if (Ground.class.isInstance(t)) { //If the area clicked is a Ground tile, we change to a new path tile
        Path p = new Path(t.posX,t.posY);
          map.changeTile(p); 
        }
        
        
      } else if (buildState==BuildState.REMOVEPATH) { //If the area clicked is on a Path tile, we change it to a ground tile
        if ((Path.class.isInstance(t))&&(t!=(map.getScreenMap())[0][0])&&(t!=(map.getScreenMap())[9][14])) {
          map.changeTile(new Ground(t.posX, t.posY));
        }
        
        
      } else if ((buildState==BuildState.PLACEMACHINEGUN)&&(player.getMoney()>=400)) { //Making sure the player has enough money
        //Iterating through the towerList to check if the tile clicked are already occupied by another tower
        for (int i=0; i<towerList.size(); i++) {
          Tower d = towerList.get(i);
          if ((d.getX()==t.getX())&&(d.getY()==t.getY())) { //Nothing is placed if the tile has another tower
            return;
          }
        }
        for (int i=0; i<((map.getScreenMap())).length; i++) {
          for (int j=0; j<((map.getScreenMap()))[0].length; j++) {
            Tile o = (map.getScreenMap())[i][j];
            if ((o.getX()==t.getX())&&(o.getY()==t.getY())&&(Path.class.isInstance(o))) { //Nothing is changed if the tile clicked is a path tile
              return;
            }
          }
        }
        towerList.add(new MachineGun(t.getX(), t.getY())); //Adding machine gun after passing the checks above
        player.setMoney(player.getMoney()-400); //Subtracting player credits
        buildState = BuildState.NONE;
        
        
      } else if ((buildState==BuildState.PLACELASERCANNON)&&(player.getMoney()>=600)) { //Same process with machine gun is applied to this statement
        for (int i=0; i<towerList.size(); i++) {
          Tower d = towerList.get(i);
          if ((d.getX()==t.getX())&&(d.getY()==t.getY())) {
            return;
          }
        }
        for (int i=0; i<((map.getScreenMap())).length; i++) {
          for (int j=0; j<((map.getScreenMap()))[0].length; j++) {
            Tile o = (map.getScreenMap())[i][j];
            if ((o.getX()==t.getX())&&(o.getY()==t.getY())&&(Path.class.isInstance(o))) {
              return;
            }
          }
        }
        towerList.add(new LaserCannon(t.getX(), t.getY()));
        player.setMoney(player.getMoney()-600);
        buildState = BuildState.NONE;
        
        
      } else if (buildState==BuildState.REMOVETOWER) {
        //Iterating through the towerList and tile list, checking if anything other than a ground tile is there
        for (int i=0; i<towerList.size(); i++) {
          Tower d = towerList.get(i);
          if ((d.getX()==t.getX())&&(d.getY()==t.getY())) {
            player.setMoney(player.getMoney()+d.getCost()); //Recompensating player credits
            towerList.remove(d);
          }
        }
        buildState = BuildState.NONE;
      }
    }
    public void mousePressed(MouseEvent e){   // MUST be implemented even if not used!
    }
    public void mouseReleased(MouseEvent e){
      mouseClicked(e); //Using this method here so that the player won't have to click so precisely
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
  }
  
  //***************************RUN METHOD************************************
  
  /**
   * A method which begins the game loop
   */
  public void run() {
    //get player name
    pastTime = System.currentTimeMillis(); //Starting time counter, which will be continued in update()
    
    while (true) { //Loops forever until the game is closed
      
      if (gameState==GameState.BUILD) {} //We don't do anything here, but additional features in the build phase can be added later if need be
      
      else if (gameState==GameState.WAVE) {
        
        update(); //Updating enemies and towers
        
        if (robotWave.waveOver()) { //If the enemylist in the wave is empty, then the gameState is reset
          projectileList.clear(); //Clearing the projectile list once all enemies are destroyed
          gameState = GameState.BUILD;
        }
        
        if (player.getHealth()<=0) {gameState = GameState.END;} //change gamestate if the headQuarters is destroyed
      }
      
      else if (gameState==GameState.PAUSE) {} //Does nothing, nothing is updated
      
      else if (gameState==GameState.END) {} //Does nothing, nothing is updated and only the exit button can be pressed
      
      graphicsPanel.repaint(); //Repainting to show updates on screen
      
      try  {Thread.sleep(20);} catch(Exception e){} //Pausing between frames
      
    }
  } //****************************END OF RUN()*********************************
  
  
  //****************************UPDATE METHOD*********************************
  /**
   * A method that updates all positions, health, and other stats of objects on the screen during the wave
   */
  public void update() {
    currentTime = System.currentTimeMillis();
    elapsedTime = (currentTime - pastTime)/1000; //Getting the difference in time between frames and converting to seconds
    pastTime = currentTime;
    //****************************UPDATING TOWERS***********************
    for (int i=0; i<towerList.size(); i++) { //Going through each Tower object, and using shoot()
      Tower t = towerList.get(i);
      Projectile p = t.shoot(robotWave.getEnemyList(), elapsedTime);
      if (p!=null) {
        projectileList.add(p);
      }
    }
    //***************************UPDATING PROJECTILES******************
    for (int i=0; i<projectileList.size(); i++) {
      Projectile p = projectileList.get(i);
      for (int j=0;j<(robotWave.getEnemyList()).size();j++) {
        Enemy e = (robotWave.getEnemyList()).get(j);
        if ((p.getBoundingBox()).intersects(e.getBoundingBox())) {
          e.setHealth(e.getHealth()-p.getDamage()); //Removing projectile if it collides with an enemy object
          projectileList.remove(p);
        }
      }
      if ((p.getX()<0)||(p.getX()>1500)||(p.getY()<0)||(p.getY()>1000)) { //Removing projectile if they leave the screen
        projectileList.remove(p);
      } else {
        p.move(); //Moving the projectile if nothing changes
      }
    }
    //****************UPDATING ENEMIES**************************
    for (int i=0; i<(robotWave.getEnemyList()).size(); i++) { //Going through each Enemy object and updating their position,
      Enemy e = (robotWave.getEnemyList()).get(i);            //removing if they reach the end or are killed
      if ((e.route).isEmpty()) {
        player.setHealth(player.getHealth()-e.getDamage()); //Subtracting player health when robot reaches the end of the path
        (robotWave.getEnemyList()).remove(e);
      }
      if (e.getHealth()<=0) {
        player.setMoney(player.getMoney()+e.getValue()); //Adding player score and money if an enemy is killed
        player.setScore(player.getScore()+e.getValue());
        (robotWave.getEnemyList()).remove(e);
      } else {
        e.move(); //Moving the enemy if nothing changes
      }
    }
  } //*************************END OF UPDATE()************************
  
  
  //***********************GRAPHICS PANEL*******************************
  static class myGraphicsPanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      //Drawing all objects here
      //Drawing map
      map.drawMap(g);
      //Drawing all enemies
        for (int i=0; i<(robotWave.getEnemyList()).size(); i++) {
          ((robotWave.getEnemyList()).get(i)).draw(g);
        }
      //Drawing all towers
        for (int i=0; i<towerList.size(); i++) {
          (towerList.get(i)).draw(g);
          if (showRange==true) {
            (towerList.get(i)).showRange(g);
          }
        }
      //Drawing all projectiles
        for (int i=0; i<projectileList.size(); i++) {
          (projectileList.get(i)).draw(g);
        }
      //Drawing in-game stats such as score, health, money, and wave
        int largeSize = 30;
        Font largeFont = new Font("Agency FB", Font.BOLD, largeSize);
        Color textColor = new Color(50, 168, 166);
        g.setFont(largeFont);
        g.setColor(textColor);
        g.drawString("Current Wave: "+waveNum, 1250, 50);
        g.drawString("Health: "+player.getHealth(), 1250, 100);
        g.drawString("Legal Tender: "+player.getMoney(), 1250, 150);
        g.drawString("Current State: "+gameState, 1250, 200); //Drawing gamestate for player clarity
        
        if (gameState==GameState.END) { //Drawing game over text on screen if player has lost the game
          largeSize = 70;
          largeFont = new Font("Agency FB", Font.BOLD, largeSize);
          textColor = new Color(230, 15, 0);
          g.setFont(largeFont);
          g.setColor(textColor);
          g.drawString("THE INHERENTLY EVIL ROBOTS DESTROYED YOUR BASE!!!!!", 100, 500);
        }
      
    }
  }
  
} //End of Game class