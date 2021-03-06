package Proyecto_2_infra;
import java.util.Vector;

class Producer extends Thread {  //implements Runnable {
    static final int MAXQUEUE = 5;
    private Vector messages = new Vector();

    @Override
    public void run() {
        try {
            while (true) {
                putMessage();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    private synchronized void putMessage() throws InterruptedException {

        while (messages.size() == MAXQUEUE)
            wait();
        messages.addElement(new java.util.Date().toString());
        notify();
    }

    // Called by Consumer
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0)
            wait();
        String message = (String) messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}

