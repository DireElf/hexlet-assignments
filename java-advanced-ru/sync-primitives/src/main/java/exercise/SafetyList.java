package exercise;

class SafetyList {
    // BEGIN
    private final int START_SIZE = 10;
    private int[] array = new int[START_SIZE];
    private int size = 0;

    public synchronized void add(int x) {
        if (size == array.length - 1) {
            int[] arrCopy = new int[1 + (array.length * 3 / 2)];
            System.arraycopy(array, 0, arrCopy, 0, array.length);
            array = arrCopy;
        }
        array[size] = x;
        size++;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
