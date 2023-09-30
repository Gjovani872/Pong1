import javax.swing.JFrame;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {
    public boolean isGameRunning = true;
    Graphics2D g2;
    public MouseL  mouseListener = new MouseL();
    KeyListener keyListener = new KeyListener();

    public Text startGame;
    public Text exit;

    public Text botTraining;

    public static boolean isBotTraining = false;


    public MainMenu () {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);//Here we access the class that has the static variables and we can easily use that class to change stuff and
        //keep things more organized

        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.startGame = new Text("1v1", new Font("Times New Roman", Font.BOLD, 40), Constants.SCREEN_WIDTH / 2.0 - 128.0, Constants.SCREEN_HEIGHT / 2.0,Color.WHITE);
        this.exit = new Text("Exit Game", new Font("Times New Roman", Font.BOLD, 40), Constants.SCREEN_WIDTH / 2.0 - 120, Constants.SCREEN_HEIGHT / 2.0 + 60.0,Color.white);
        this.botTraining = new Text("Bot Training", new Font("Times New Roman", Font.BOLD, 40), Constants.SCREEN_WIDTH / 2.0 - 128.0, Constants.SCREEN_HEIGHT / 2.0 - 50, Color.WHITE);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        g2 = (Graphics2D)getGraphics();
    }


    public void updateGame (double dt) {
        Image bufferImage = createImage(getWidth(), getHeight());
        Graphics bufferGraphics = bufferImage.getGraphics();
        this.draw(bufferGraphics);
        g2.drawImage(bufferImage, 0, 0, this);

        System.out.println(mouseListener.getMouseX());
        System.out.println(mouseListener.getMouseY());

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width
        && mouseListener.getMouseY() > startGame.y - startGame.height / 2.0 && mouseListener.getMouseY() < startGame.y + startGame.height / 2.0){
            startGame.color = new Color(64, 19, 19);
            if (mouseListener.isMousePressed()){
                Main.killState(1);
            }
        }else{
            startGame.color = Color.WHITE;
        }

        //bot menu
        if (mouseListener.getMouseX() > botTraining.x && mouseListener.getMouseX() < botTraining.x + botTraining.width
                && mouseListener.getMouseY() > botTraining.y - botTraining.height / 2.0 && mouseListener.getMouseY() < botTraining.y + botTraining.height / 2.0){
            botTraining.color = new Color(64, 19, 19);
            if (mouseListener.isMousePressed()){
                Main.killState(1);
                isBotTraining = true;
            }
        }else{
            botTraining.color = Color.WHITE;
        }


        if (mouseListener.getMouseX() > exit.x && mouseListener.getMouseX() < exit.x + exit.width
                && mouseListener.getMouseY() > exit.y - exit.height / 2.0 && mouseListener.getMouseY() < exit.y + exit.height / 2.0){
            exit.color = new Color(66, 44, 56);
            if (mouseListener.isMousePressed())
                Main.killState(2);

        }else{
            exit.color = Color.WHITE;
        }

    }

    public void draw (Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);




        startGame.draw(graphics);
        exit.draw(graphics);
        botTraining.draw(graphics);

    }


    @Override
    public void run () {

        double lastFrameTime = 0.0;
        while (isGameRunning) {
            double time = Time.getTIme();//time passed since starting our program
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            updateGame(deltaTime);


        }
        this.dispose();
        return;
    }
    public void stopGame(){
        isGameRunning = false;

    }

}

