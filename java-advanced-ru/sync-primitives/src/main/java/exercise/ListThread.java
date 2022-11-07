package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList safetyList;

    public ListThread(SafetyList list) {
        this.safetyList = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            safetyList.add(i);
        }
    }
}
// END
