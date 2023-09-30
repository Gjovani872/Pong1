public class Main {
    public static int zeroOne = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window window;
    public static void main (String[] args) {
       // Window window = new Window();
         menu = new MainMenu();
        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void killState(int newState){
        if (newState == 1 && zeroOne == 0){
            menu.stopGame();
            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();
        }else if(newState == 0 && zeroOne == 1){
            window.stopGame();
            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();

        }else if(newState == 2){
            if(window != null){

                window.stopGame();
            }
            if (menu != null){
                menu.stopGame();
            }

        }

        zeroOne = newState;
    }

}