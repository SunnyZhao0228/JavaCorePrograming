package reentrantlock.action;

import reentrantlock.action.CircularSeqGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoqw
 * @date 2024/3/13
 */
public class LuckBasedSeqGenerator implements CircularSeqGenerator {
    private int sequence = -1;

    private final Lock lock = new ReentrantLock();

    @Override
    public int nextSequence() {
        lock.lock();
        try {
            if (sequence > 999) {
                sequence = 0;
            } else {
                sequence ++;
            }
            return sequence;
        } finally {
            lock.unlock();
        }
    }
}
