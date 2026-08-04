package threadcore.study.threadnosafe.example;

public class NoSafePlus {
    private static int count = 0; // Count variable to be incremented

    public static int getCount() { // Method to get the current value of count
        return count;
    }

    public static void selfIncrement() {
        count++;
    }

    public static void selfSafeIncrement() {
        synchronized(NoSafePlus.class) {
            count++;
        }
    }
}
