import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadAddHisto {

    private static ReentrantReadWriteLock Lock2 = new ReentrantReadWriteLock();

    static boolean addHist(String url) {
        boolean r = false;
        Lock2.writeLock().lock();
        if (!PageThread.l1.contains(url)) {
            PageThread.l1.add(url);
            PageThread.l2.add(url);
            r = true;
        }
        Lock2.writeLock().unlock();
        return r;
    }
}