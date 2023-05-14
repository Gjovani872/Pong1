public class Time {

    //This will get the java system nanoTIme

    public static double timeStarted = System.nanoTime();

    public static double getTIme(){
       // System.out.println("time started  : "+timeStarted);
        return (System.nanoTime() - timeStarted) * 1E-9;}



}
