public class Ball {

    public Rectangle rectangle;
    public Rectangle leftPLayer;
    public Rectangle rightPlayer;
//    public Text leftSideText;
//    public Text rightSideText;

//    private double velocityY = 500.0;
//    private double velocityX = -300.0;

  private double vy = Constants.INITAL_Y_SPEED;//vy
   private double vx = Constants.INITAL_X_SPEED;//vx

    public Text leftSideText;
    public Text rightSideText;

    public Ball (Rectangle rectangle, Rectangle leftPLayer, Rectangle rightPlayer,Text leftSideText,Text rightSideText) {
        this.rectangle = rectangle;
        this.leftPLayer = leftPLayer;
        this.rightPlayer = rightPlayer;
        this.rightSideText = rightSideText;
        this.leftSideText = leftSideText;

    }






    public double calculateNewVelocityAngle(Rectangle paddle){

        double relativeIntersectY = (paddle.getY() + (paddle.getHeight() / 2.0)) - (this.rectangle.getY() + (this.rectangle.getHeight() / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.getHeight() / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update2(double frame) {

        // Check for collisions with the top and bottom of the screen
        if (vy >= 0.0) {
            if (this.rectangle.getY() + (vy  * frame) + this.rectangle.getHeight() > Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                vy *= -1.0;
            }
        } else if (vy < 0.0) {
            if (this.rectangle.getY() + (vy  * frame) < Constants.UPPER_HEIGHT) {
                vy *= -1.0;
            }
        }

        // Check for collisions with the players
        if (vx < 0.0) {
            // Check for collision with left player
            if (this.rectangle.getX() + (vx  * frame) < leftPLayer.getX() + leftPLayer.getWidth()) {
                if (this.rectangle.getY() + (vy  * frame) > leftPLayer.getY() && this.rectangle.getY() + (vy  * frame) + this.rectangle.getHeight() < leftPLayer.getY() + leftPLayer.getHeight()) {
                   double theta = calculateNewVelocityAngle(leftPLayer);
                   double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                   double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                   double oldSign = Math.signum(vx);
                   this.vx = newVx * (-1.0 * oldSign);
                    this.vy = newVy;
//                    vx *= -1.0;
                }
            }
        } else if (vx >= 0.0) {
            // Check for collision with right player
            if (this.rectangle.getX() + (vx  * frame) + rectangle.getWidth() > rightPlayer.getX()) {
                if (this.rectangle.getY() + (vy  * frame) > rightPlayer.getY() && this.rectangle.getY() + (vy  * frame) + this.rectangle.getHeight() < rightPlayer.getY() + rightPlayer.getHeight()) {

                    double theta = calculateNewVelocityAngle(rightPlayer);
                    double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                    double oldSign = Math.signum(vx);
                    this.vx = newVx * (-1.0 * oldSign);
                    this.vy = newVy;

//                    vx *= -1.0;
                }
            }
        }

       this.rectangle.y += vy * frame;
        this.rectangle.x += vx*frame;

        // Check if ball passed a player
        if (this.rectangle.getX() + this.rectangle.getWidth() < leftPLayer.getX()) {
            // Ball passed left player
            int rightSide = Integer.parseInt(rightSideText.text);
            rightSide++;
            rightSideText.text = rightSide + "";
//            System.out.println("Player 2 lost");
            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = 220;
            this.vy = 30.0;

            if(rightSide >= Constants.MAX_SCORE){
                Main.killState(2);
            }

        }else if(this.rectangle.x > rightPlayer.x + rightPlayer.width){
            int leftScore = Integer.parseInt(leftSideText.text);
            leftScore++;
            leftSideText.text = leftScore + "";
            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = -220;
            this.vy = 30.0;
            if(leftScore >= Constants.MAX_SCORE){
                Main.killState(2);
            }
        }


    }


}
