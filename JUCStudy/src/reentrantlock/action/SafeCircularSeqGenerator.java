package reentrantlock.action;

/**
 * @author zhaoqw
 * @date 2024/3/13
 */
public class SafeCircularSeqGenerator implements CircularSeqGenerator {
    private int sequence = -1;
    @Override
    public synchronized int nextSequence() {
        if (sequence > 999) {
            sequence = 0;
        } else {
            sequence ++;
        }
        return sequence;
    }

    // 等同于synchronized (this)
    public int nextSequence2() {
        synchronized (this) {
            if (sequence > 999) {
                sequence = 0;
            } else {
                sequence ++;
            }
            return sequence;
        }
    }
}
