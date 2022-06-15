import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage; 
public class Sketch extends PApplet {
//initialize images, variables and arrays
  PImage SmallAsteroid;
  PImage LargeAsteroid;
  PImage Player;
  PImage menuBackground;
  PImage gameBackground;
  PImage Title;
  PImage Start;
  PImage Change;
  PImage one;
  PImage two;
  PImage three;
  PImage ship1;
  PImage ship2;
  PImage ship3;
  PImage explosionSprite;
  PImage explosion1;
  PImage explosion2;
  PImage explosion3;
  PImage explosion4;
  PImage bullet;
  PImage boost1;
  PImage boost2;
  PImage boost3;
  PImage boost4;
  PFont points;
  PImage lives;
   PImage [] boostAnimation = new PImage[4];
  
   int boostFrames = 4;
  int lAsteroidTotal = 10;
  PImage [] explosion = new PImage[4];
  int explosionframes = 4;
  float[] lAsteroidX = new float[lAsteroidTotal];
  float[] lAsteroidY = new float[lAsteroidTotal];
  
  float[] sAsteroidX = new float[15];
  float[] sAsteroidY = new float[15];
  
  boolean[] LargeAsteroidHide = new boolean[lAsteroidTotal];
  boolean[] SmallAsteroidHide = new boolean[15];
  
  float [] bulletX = new float [20];  
  float [] bulletY = new float [20];
  float [] bullet2Y = new float [20];
  float [] bullet2X = new float [20];

  boolean [] bulletHide = new boolean [20];
  boolean [] bulletHide2 = new boolean [20];

  int LargeAsteroidSpeed = 2;
  int SmallAsteroidSpeed = 4;
  int bulletIndex = 0;

  float fltPlayerX = 400;
  float fltPlayerY = 800;

  boolean boolUpPressed = false;
  boolean boolDownPressed = false;
  boolean boolLeftPressed = false;
  boolean boolRightPressed = false;

  boolean boolSpaceHeld = false;

  
int bulletDestroy = 0;
  boolean boolPlayerStatus = false;
  int intLives = 3;

  boolean boolMouseClicked = false;
  int playerPointsDisplayed = 0;
  int playerPoints = 0;
  int playerShip = 1;
  int Counter = 0;

  int BulletSpeed = 3;
  boolean hideBullet = false;

  public void settings() {
    size(800,800);
  }

 
  public void setup() {
   //create small and big asteroids; fill the elements for the array
    for (int i = 0; i < lAsteroidX.length; i++) {
      lAsteroidY[i] = random(height);
      lAsteroidX[i] = random(width);
      LargeAsteroidHide[i] = false;
    }

    for(int i = 0; i < sAsteroidY.length; i ++){
      sAsteroidY[i]=  random(height);
      sAsteroidX[i] = random(width);
      SmallAsteroidHide[i] = false;
    }

    
   //create images and apply the changes that are needed 
    LargeAsteroid = loadImage("asteroid.png");
    LargeAsteroid.resize(45, 45);

    SmallAsteroid = loadImage("smalasteroid.png");
    SmallAsteroid.resize(30, 30);

    menuBackground = loadImage("mbackground.jpg");
    menuBackground.resize(800, 800);

    gameBackground= loadImage("gbackground.jpg");
    gameBackground.resize(800, 800);
    
    Title = loadImage("text.png");
    Start = loadImage("START.png");
    Change = loadImage("change.png");
    one = loadImage("1.png");
    two = loadImage("2.png");
    three = loadImage("3.png");

    ship1 = loadImage("ship1.png");
    ship2 = loadImage("ship2.png");
    ship3 = loadImage("ship3.png");

    explosion[0] =  loadImage("explosion1.png");
    explosion[1] = loadImage("explosion2.png");
    explosion[2] = loadImage("explosion3.png");
    explosion[3] = loadImage("explosion_4.png");

    bullet = loadImage("Bullet.png");
    bullet.resize(5, 5);

    boostAnimation[0] =  loadImage("boost1.png");
    boostAnimation[1] = loadImage("boost2.png");
    boostAnimation[2] = loadImage("boost3.png");
    boostAnimation[3] = loadImage("boost4.png");

    points =  createFont("Courier New", 16, true);
    lives= loadImage("heart2.png");
    lives.resize(50, 50);
    textFont(points, 32);
   
   
  }
  public void draw() {
  //check if game has started, if it hasnt show menu screen
    if (boolPlayerStatus ==  false){
      menuScreen();
      intLives = 3;
    }
  //if game started run game
    else if (boolPlayerStatus == true) {
    background(gameBackground);
    
   

  //draw the large asteroids and move them
    for (int i = 0; i < lAsteroidY.length; i++) {
      if (LargeAsteroidHide[i] == false) {
        image(LargeAsteroid, lAsteroidX[i], lAsteroidY[i]);

        lAsteroidY[i] += LargeAsteroidSpeed;
      }
  
   //reset large asteroids when they go off screen  
      if (lAsteroidY[i] > height - 25) {
        lAsteroidY[i] = 0;
      }

    //collision with player  
      if (dist(fltPlayerX, fltPlayerY, lAsteroidX[i], lAsteroidY[i]) <= 30 && LargeAsteroidHide[i] == false) {
        image(explosion[(frameCount/8)% explosionframes], lAsteroidX[i], lAsteroidY[i]);
        Counter++;
        intLives--;
        if (Counter == 8){
        LargeAsteroidHide[i] = true;
          Counter = 0;
        }
       //reset asteroid after colision
        if( LargeAsteroidHide[i] = true){
          lAsteroidX[i] = random(width);
          lAsteroidY [i] = 0;
          LargeAsteroidHide[i] = false;
        }
      }
      
    
      
    }
//draw and move small asteroids
    for(int b = 0; b < sAsteroidX.length; b++){
      if(SmallAsteroidHide[b] ==  false){
        image(SmallAsteroid, sAsteroidX[b], sAsteroidY[b]);
        sAsteroidX[b] += SmallAsteroidSpeed;
      }
//reset if they go off screen
      if(sAsteroidX[b] > width - 20){
        sAsteroidX[b] = 0;
      }
//collision
      if (dist(fltPlayerX, fltPlayerY, sAsteroidX[b], sAsteroidY[b]) <= 25 && SmallAsteroidHide[b] == false) {
        image(explosion[(frameCount/8)% explosionframes], sAsteroidX[b], sAsteroidY[b]);
        Counter++;
        intLives--;
        if (Counter == 8){
        SmallAsteroidHide[b] = true;
        Counter = 0;
        }
        if( SmallAsteroidHide[b] = true){
          sAsteroidX[b] = 0;
          sAsteroidY [b] = random(height);
          SmallAsteroidHide[b] = false;
        }
      }

    }
//draw bullets, move bullets
    for(int i = 0; i < bulletX.length; i++){
      if (bulletHide[i] == false){
        image(bullet, bulletX[i], bulletY[i]);

        bulletY[i] -= BulletSpeed;
      
      }
    //use a for to check all the possible positions of the bullet and the large asteroids and check if there is a collision
      for(int j = 0; j < lAsteroidX.length; j++){
        if(dist(bulletX[i], bulletY[i], lAsteroidX[j], lAsteroidY[j]) <= 45 && LargeAsteroidHide[j] == false && bulletHide[i] == false){
          image(explosion[(frameCount/8)% explosionframes], lAsteroidX[j], lAsteroidY[j]);
          Counter++;
          bulletDestroy = i;
          playerPoints += 10;
          playerPointsDisplayed +=10;
          if (Counter == 2000){
          LargeAsteroidHide[j] = true;
          Counter = 0;
          }
         //reset large asteroids after collision
          if( LargeAsteroidHide[j] = true){
            lAsteroidX[j] = random(width);
            lAsteroidY [j] = 0;
            LargeAsteroidHide[j] = false;
          }
        }
      }
      bulletHide[bulletDestroy] = true;
     //use a loop to chekc all positions of the bullets and the small asteroids and check for collision
      for(int c = 0; c < sAsteroidX.length; c++){
        if(dist(bulletX[i], bulletY[i], sAsteroidX[c], sAsteroidY[c]) <= 30 && SmallAsteroidHide[c] == false && bulletHide[i] == false){
          image(explosion[(frameCount/8)% explosionframes], sAsteroidX[c], sAsteroidY[c]);
          Counter++;
          bulletDestroy = i;
          playerPoints += 10;
          playerPointsDisplayed += 10;
          if (Counter == 20000){
          SmallAsteroidHide[c] = true;
          Counter = 0;
          }
          //reset small asteroids if there is a collision
          if(SmallAsteroidHide[c] = true){
            sAsteroidX[c] = 0;
            sAsteroidY [c] = random(height);
            SmallAsteroidHide[c] = false;
          }
        }
      }
      bulletHide[bulletDestroy] = true;
    }
//create second bullet, the collision loops are the same
    for(int i = 0; i < bullet2X.length; i++){
      if (bulletHide2[i] == false){
        image(bullet, bullet2X[i], bullet2Y[i]);

        bullet2Y[i] -= BulletSpeed;
      
      }
      for(int j = 0; j < lAsteroidX.length; j++){
        if(dist(bullet2X[i], bullet2Y[i], lAsteroidX[j], lAsteroidY[j]) <= 45 && LargeAsteroidHide[j] == false && bulletHide2[i] == false){
          image(explosion[(frameCount/8)% explosionframes], lAsteroidX[j], lAsteroidY[j]);
          Counter++;
          bulletDestroy = i;
          playerPoints += 10;
          playerPointsDisplayed +=10;
          if (Counter == 2000){
          LargeAsteroidHide[j] = true;
          Counter = 0;
          }
          if( LargeAsteroidHide[j] = true){
            lAsteroidX[j] = random(width);
            lAsteroidY [j] = 0;
            LargeAsteroidHide[j] = false;
          }
        }
      }
      bulletHide[bulletDestroy] = true;
      for(int t = 0; t < sAsteroidX.length; t++) { 
        if(dist(bullet2X[i], bullet2Y[i], sAsteroidX[t], sAsteroidY[t]) <= 30 && SmallAsteroidHide[t] == false && bulletHide2[t] == false){
          image(explosion[(frameCount/8)% explosionframes], sAsteroidX[t], sAsteroidY[t]);
          Counter++;
          bulletDestroy = i;
          playerPoints += 10;
          playerPointsDisplayed +=10;
          if (Counter == 20000){
          SmallAsteroidHide[t] = true;
          Counter = 0;
          }
          if(SmallAsteroidHide[t] = true){
            sAsteroidX[t] = 0;
            sAsteroidY [t] = random(height);
            SmallAsteroidHide[t] = false;
          }
        }
      }
      bulletHide[bulletDestroy] = true;
    }
 
    fill(0, 0, 255);

//see what ship the player chooses and draw it accordingly  
    if(playerShip == 1){
      image(ship1, fltPlayerX, fltPlayerY);
    }
    else if(playerShip == 2){
      image(ship2, fltPlayerX, fltPlayerY);
    }
    else if(playerShip == 3){
      image(ship3, fltPlayerX, fltPlayerY);
    }
    
  //move player when the controls are pressed  
    if (boolLeftPressed) {
      fltPlayerX += -5;
    }
    if (boolRightPressed) {
      fltPlayerX += 5;
    }
   //draw boost animation when u go forward
    if (boolUpPressed) {
      fltPlayerY += -5;
      image(boostAnimation[(frameCount/4)%4], (fltPlayerX + 7), (fltPlayerY + 32));
    }
    if (boolDownPressed) {
      fltPlayerY += 5;
    }

    fill(246, 7, 17);

//draw lives    
    for (int i = 1; i <= intLives; i++) {
      image(lives, 50 * i, 50 );
    }
   //display score 
    text("Score: " + playerPointsDisplayed, 550,50);
//get extra lives after every 100 points
    if (playerPoints == 100){
      intLives ++;
      playerPoints = 0;
    }
//increases difficulty as you get more points
    if(playerPointsDisplayed == 200){
      LargeAsteroidSpeed = 5;
      SmallAsteroidSpeed = 7;
    }
    else if(playerPointsDisplayed == 800){
      LargeAsteroidSpeed = 8;
      SmallAsteroidSpeed = 10;
    }

    if(playerPointsDisplayed == 500){
      lAsteroidTotal += 13;
    }
//end game if the lives hit 0
    if (intLives == 0) {
      boolPlayerStatus = false;
    }
  }

  //failsafe
  else {
    boolPlayerStatus = false;
  }
  
  }
  public void keyPressed() {
  //set movement booleans
    if (key == 'a')  {
      boolLeftPressed = true;
    }
    else if (key == 'd') {
      boolRightPressed = true;
    }
    else if (key == 'w') {
      boolUpPressed = true;
    }
    else if (key == 's') {
      boolDownPressed = true;
    }

    

  }

  
  public void keyReleased() {
  //set movement booleans
    if (key == 'a')  {
      boolLeftPressed = false;
    }
    else if (key == 'd') {
      boolRightPressed = false;
    }
    else if (key == 'w') {
      boolUpPressed = false;
    }
    else if (key == 's') {
      boolDownPressed = false;
    }
  }

  public void keyTyped(){
   //if the space bar is pressed create two bullets
    if (key == 32){
      boolSpaceHeld = true;
      bulletX[bulletIndex] = fltPlayerX + 7.5f;
      bulletY[bulletIndex] =  fltPlayerY;
      bulletHide[bulletIndex] = false;
      
      bullet2X[bulletIndex] = fltPlayerX + 22.5f;
      bullet2Y[bulletIndex] = fltPlayerY;
      bulletHide2[bulletIndex] =  false;

      bulletIndex++;
    //reset index when the max number of bullets have been created
      if(bulletIndex == 20){
        bulletIndex = 0;
      }
    }
  }
  public void mousePressed(){
    boolMouseClicked = true;
  }
  public void mouseReleased(){
    boolMouseClicked = false;
  }


/**
 * draws menu screen(includes all the buttons)
 */
  public void menuScreen(){
    background(menuBackground);
    image(Title, 25, 50);
    image(Start, 214.5f, 300);
    image(Change, 50.5f, 500);
    image(one, 92.3333f, 700);
    image(two, 359, 700);
    image(three, 625.6666f, 700);
    image(ship1, 118.333f, 650);
    image(ship2, 385, 650);
    image(ship3, 651.6666f, 650);
//start clicked, start game
    if (mouseX > 214.5f && mouseX < 585.5 && mouseY > 300 && mouseY < 400 && boolMouseClicked == true) {
      boolPlayerStatus = true;

  }
  // if number or ship 1 clicked change ship to 1  
  if (mouseX > 118.333f && mouseX < 148.33 && mouseY > 650 && mouseY < 680 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip = 1;
    }
    
    if (mouseX > 92.3333f && mouseX < 148.3333f && mouseY > 700 && mouseY < 782 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip = 1;
    }
  // if number 2 or ship 2 are clicked change to ship 2
    if (mouseX > 385 && mouseX < 415 && mouseY > 650 &&  mouseY < 680 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip = 2;
    }

    if (mouseX > 359  && mouseX < 441 && mouseY > 700 &&  mouseY < 782 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip = 2;
    }

  // if number 3 or ship 3 are clicked change to ship 3

    if (mouseX > 651.6666f && mouseX < 680.6666f && mouseY > 650 && mouseY < 680 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip =  3;
    }

    if(mouseX > 625.666f && mouseX < 707.666f && mouseY > 700 && mouseY < 782 && boolMouseClicked == true){
      boolPlayerStatus = true;
      playerShip = 3;
    }

}

}
