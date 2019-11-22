import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTake {

    private static ReentrantReadWriteLock Lock = new ReentrantReadWriteLock();

    static String take(List<String> lst) {
        Lock.writeLock().lock();
        String url = null;
        if (!lst.isEmpty()) {
            url = lst.get(0);
            lst.remove(0);
        }
        Lock.writeLock().unlock();
        return url;
    }
}
