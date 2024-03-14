package utilities;

/**
 * StopWatch class
 * The time is measured in seconds
 * Can be started, stopped, and reset
 * Start, stop, and reset all return this
 * Can check if the StopWatch is currently running
 * Time is stored in the object in nanoseconds
 */
public class StopWatch {

    private boolean running;
    private double startTime;//used when the stopwatch is running
    private double currentTime;//used when the stopwatch is stopped

    /**
     * sets the time to 0 and does not start
     */
    public StopWatch() {
        running = false;
        startTime = 0;
        currentTime = 0;
    }

    /**
     *
     * @return the time counted since start
     */
    public double elapsed(){
        if (isRunning()) {
            return (System.nanoTime() - startTime)/Math.pow(10, 9);
        }else{
            return currentTime/Math.pow(10, 9);
        }
    }
    public boolean isRunning(){return running;}

    /**
     * starts the clock
     *
     * @return this
     */
    public StopWatch start() {
        if (!isRunning()) {
            running = true;
            startTime = currentTime + System.nanoTime();//last so other tasks don't affect the time
        }
        return this;
    }

    /**
     * stops the clock
     *
     * @return this
     */
    public StopWatch stop() {
        if (isRunning()) {
            currentTime = elapsed();//first so other tasks don't affect the time
            running = false;
        }
        return this;
    }

    /**
     * resets the clock to 0
     *
     * @return this
     */
    public StopWatch reset(){
        if (isRunning()) {
            startTime = System.nanoTime();
        }else{
            currentTime = 0;
        }
        return this;
    }
}
