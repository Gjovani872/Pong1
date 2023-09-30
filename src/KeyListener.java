import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    private boolean keypressed[] = new boolean [128]; //has 128 characters that we can use in ASCII key code

    @Override
    public void keyTyped (KeyEvent e) {

    }

    @Override
    public void keyPressed (KeyEvent e) {
        keypressed[e.getKeyCode()] = true; //when the key is pressed, int type
//        System.out.println("Presesd a key");
    }

    @Override
    public void keyReleased (KeyEvent e) {
        keypressed[e.getKeyCode()] = false;//when the key is not pressed
    }


    public boolean isKeyPressed(int keyCode){
        return keypressed[keyCode];
    }
}
