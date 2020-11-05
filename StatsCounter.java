import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class StatsCounter {
    // private int successCount_;
    private final AtomicInteger successCount_ = new AtomicInteger(0);
    //removed the synchronized and introduced reentrant lock
    // private final Lock lock = new ReentrantLock();
    public final int getSuccessCount() {
        return successCount_.get();
    }

    //introduce the synchronized keyword to make it more stable
    // public synchronized final void increaseSuccessCount(int delta) {
    //     successCount_ += delta;
    // }    

    // public final void increaseSuccessCount(int delta) {
    //     lock.lock();
    //         successCount_ += delta;   
    //     lock.unlock();
    // } 

    //working without locks
    public final void increaseSuccessCount(int delta) {
            // successCount_ += delta;   
            successCount_.getAndAdd(delta);
    } 
}