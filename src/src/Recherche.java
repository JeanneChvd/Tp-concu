import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Recherche {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static AtomicReference<Recherche> inst = new AtomicReference<>();


    /*Main Method
    /*Le main se lance grâce aux arguments renseignés dans le Run Configuration / Arguments
    *args[1] correspond au mot recherché
    *args[0] correspond à l'URL dans laquelle on recherche le mot
     */
	public static void main(String[] args) {
		Recherche recherche =  Recherche.getInst().get();
		recherche.start(args[1], args[0]);
	}

    public static AtomicReference<Recherche> getInst() {
	    inst.compareAndSet(null, new Recherche());
	    return inst;
    }

    /*
    Permet d'instancier les threads (ici 10)
     */
	public void start(String u, String m) {
        try {
            this.executorService.submit(new PageThread(new URL(u), m));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
}

    /*
    Arrête les threads
     */
public void finish() {
        executorService.shutdown();
}
}
