import java.awt.event.KeyEvent;

public class MovePlayers {
    public Rectangle rectangle;
    public KeyListener keyListener;

    public MovePlayers (Rectangle rectangle, KeyListener keyInput) {
        this.rectangle = rectangle;
        this.keyListener = keyInput;
        // System.out.println("Screen height:"+Constants.SCREEN_HEIGHT);
    }

    public MovePlayers (Rectangle rectangle){
        this.rectangle = rectangle;
        this.keyListener = null;
    }


    public void updatePlayer2(double frame){
        if (keyListener != null && !MainMenu.isBotTraining ){
            if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
                up(frame);
            }else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
                down(frame);
            }
        }
    }

    public void updatePlayers (double frame) {

        if(keyListener != null){
            if (keyListener.isKeyPressed(KeyEvent.VK_S)) {

                down(frame);
                // System.out.println("Board height:"+rectangle.getY());

            } else if (keyListener.isKeyPressed(KeyEvent.VK_W)) {
//            System.out.println(rectangle.getY());
//            System.out.println(rectangle.getX());
//            if (rectangle.getY() - Constants.BOARD_PADDING - 100 * frame > 0){//this line I added now can be deeeted
//
//                rectangle.setY((rectangle.getY() - Constants.PLAYER_SPEED * frame));
//            }
                up(frame);

            }
        }




    }


    public void up (double frame) {


        if (rectangle.getY() - Constants.BOARD_PADDING - 100 * frame > 0) {

            rectangle.setY((rectangle.getY() - Constants.PLAYER_SPEED * frame));
        }
    }

    public void down (double frame) {
        if (rectangle.getY() + Constants.BOARD_HEIGHT + Constants.BOARD_WIDTH < Constants.SCREEN_HEIGHT) {

            rectangle.setY((rectangle.getY() + Constants.PLAYER_SPEED * frame));
        }
    }

}
