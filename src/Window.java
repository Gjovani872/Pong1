import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable{

    Graphics2D g2;

    Rectangle playerOne,ai,ball;
    KL keyListener = new KL();
    public Window(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);//Here we access the class that has the static variables and we can easily use that class to change stuff and
                                                                        //keep things more organized
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D) this.getGraphics();

        int total = Constants.SCREEN_WIDTH + Constants.SCREEN_HEIGHT;

        playerOne = new Rectangle(0,Constants.SCREEN_HEIGHT / 2,40,60,Color.WHITE);
        ai = new Rectangle(Constants.SCREEN_WIDTH - 40,Constants.SCREEN_HEIGHT / 2,40,60,Color.RED);
        ball = new Rectangle(Constants.SCREEN_WIDTH / 2,Constants.SCREEN_HEIGHT / 2,10,20,Color.WHITE);


    }


    public void update(double dt){
      //  System.out.println("" + dt + " seconds passed since the last frame");
        //System.out.println(1 / dt + " fps");
        //g2.fillArc(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,50,100);
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

//        if(keyListener.isKeyPressed(KeyEvent.VK_UP)){
//            System.out.println("THe user is pressing the up arrow");
//        }else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
//            System.out.println("The user is pressing the down arrow");
//        }
        Rectangle rect = new Rectangle(50,Constants.SCREEN_HEIGHT - 100,40,80,Color.BLUE);
        //if we wanted to draw the rectangle down we would need to get the height and then - something so it still
        //remains a positive number because y starts from 0 and goes to how long the height is
        //ex : Constants.HEIGHT - 100
        /*
        One line above we create a reference variable to the rectangle object in the heap
        and now we have a controller that we can use to controll the TV ie its behaviors
        */
//        rect.draw(g2);//pass it the graphics object so that the rectangle can draw itself
        playerOne.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
    }


    @Override
    public void run () {

            double lastFrameTime = 0.0;
            while(true){
                double time = Time.getTIme();//time passed since starting our program
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);

                try {
                    Thread.sleep(30);
                }catch(Exception e){
                    System.out.println("Not sleeping");
                }

            }
        }
    }

