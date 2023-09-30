import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public boolean isGameRunning = true;
    Graphics2D g2;

    Rectangle playerOne;
    Rectangle playerTwo;
    Rectangle ballRectangle;
    KeyListener keyListener = new KeyListener();

    public Ball ball;

    public MovePlayers playerMove;

    public MovePlayers playerMove2;
    public Bot playerMoveBot;
    public Text leftPlayerScore;
    public Text rightPLayerScore;

    public Window () {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);//Here we access the class that has the static variables and we can easily use that class to change stuff and
        //keep things more organized

        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.UPPER_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        g2 = (Graphics2D) this.getGraphics();

        int total = Constants.SCREEN_WIDTH + Constants.SCREEN_HEIGHT;
        playerOne = new Rectangle(Constants.BOARD_PADDING, 40, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, Color.yellow);
        playerMove = new MovePlayers(playerOne, this.keyListener);
        playerTwo = new Rectangle(Constants.SCREEN_WIDTH - Constants.BOARD_PADDING - Constants.BOARD_WIDTH, 40, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, Color.RED);
        playerMove2 = new MovePlayers(playerTwo, this.keyListener);
        ballRectangle = new Rectangle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_SIZE, Constants.BALL_SIZE, Color.BLUE);
        leftPlayerScore = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE),10,Constants.TEXT_Y_POSITION);
        rightPLayerScore = new Text(0, new Font("Times New Roman",Font.PLAIN, Constants.TEXT_SIZE),Constants.SCREEN_WIDTH - 10 - 16,Constants.TEXT_Y_POSITION);
        ball = new Ball(ballRectangle, playerOne, playerTwo,leftPlayerScore,rightPLayerScore);
        playerMoveBot = new Bot(new MovePlayers(playerTwo), ballRectangle);






    }

    public void stopGame(){
        isGameRunning = false;
    }


    public void updateGame (double dt) {
        Image bufferImage = createImage(getWidth(), getHeight());
        Graphics bufferGraphics = bufferImage.getGraphics();
        this.draw(bufferGraphics);
        g2.drawImage(bufferImage, 0, 0, this);


        playerMove.updatePlayers(dt);
        if (MainMenu.isBotTraining){
            playerMoveBot.update(dt);
        }else{
            playerMove2.updatePlayer2(dt);
        }


//        rect.draw(g2);//pass it the graphics object so that the rectangle can draw itself
        playerOne.draw(g2);

        playerTwo.draw(g2);
        ball.update2(dt);
    }

    public void draw (Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
Font font = new Font("Times New Roman", Font.BOLD,14);



        leftPlayerScore.draw(graphics);
        rightPLayerScore.draw(graphics);
        playerOne.draw(graphics);
        playerTwo.draw(graphics);
        ballRectangle.draw(graphics);

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
}

