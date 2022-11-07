package exercise;

// BEGIN
public class MinThread extends Thread {
    private int[] array;

    private static int min;

    public MinThread(int[] arr) {
        this.array = arr;
    }

    public static int getMin() {
        return min;
    }

    @Override
    public void run() {
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            temp = Math.min(temp, array[i]);
        }
        min = temp;
    }
}
// END
