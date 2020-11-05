import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class latch_lab {
public static void main(String[] args) {
    int threadcount = 5; 

    //introducting the countdown latch and cyclicbarrier
    CountDownLatch gate = new CountDownLatch(threadcount);
    CyclicBarrier barrier = new CyclicBarrier(threadcount, new Runnable(){

        @Override
        public void run(){
        System.out.println("It tripped");

        }
    });

    for(int i=1; i<= threadcount; i++){
        Thread thread = new Thread( new myRunnable(i,gate, barrier) );
        thread.start();
    }
        try {
            gate.await();
        } catch (Exception e) { } 
        System.out.println("Hell from the main thread");
  }  
    
}

class myRunnable implements Runnable {
    int index;
    private CountDownLatch gate;
    private CyclicBarrier barrier;
    public myRunnable(int index, CountDownLatch gate, CyclicBarrier barrier){
        this.index = index;
        this.gate = gate;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            Thread.sleep(index  * 1000);
        } catch (InterruptedException | BrokenBarrierException b){
            // e.printStackTrace();
        } finally{
            System.out.println("Thread "+ index + " done " + " Thread ID: "+ Thread.currentThread().getId());
        }
        gate.countDown();
    }
  }



