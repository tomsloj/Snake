import java.util.TimerTask;

public class MyTimer extends TimerTask {

    int time;
    Thread thread;

    MyTimer(int time)
    {
        this.time = time;
        thread = new Thread();
    }
    @Override
    public void run() {

    }
}
