package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int[] array;

    private static int max;

    public MaxThread(int[] arr) {
        this.array = arr;
    }

    public static int getMax() {
        return max;
    }

    @Override
    public void run() {
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            temp = Math.max(temp, array[i]);
        }
        max = temp;
    }
}
// END
