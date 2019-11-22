import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTake {

    private static ReentrantReadWriteLock Lock = new ReentrantReadWriteLock();

    /*
    Ajout du verrou Lock2
    si la liste lst n'est pas vide, l'url prend la valeur du premier élément, on le supprime de la liste et on l'affiche
     */
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
