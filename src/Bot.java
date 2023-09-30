public class Bot {

    public MovePlayers bot;
    public Rectangle ball;


    public Bot(MovePlayers bot, Rectangle ball){
        this.bot = bot;
        this.ball = ball;
    }


    public void update (double frame){
        bot.updatePlayers(frame);

        if(ball.getY() < bot.rectangle.getY()){
            bot.up(frame);
        }else if(ball.getY() + ball.getHeight() > bot.rectangle.getY() + bot.rectangle.getHeight()){
        bot.down(frame);
        }

    }

}
