import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Recherche {
	
	private AtomicInteger threadsCounter = new AtomicInteger(0);;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    /*Main Method
    /*Le main se lance grâce aux arguments renseignés dans le Run Configuration / Arguments
    *args[1] correspond au mot recherché
    *args[0] correspond à l'URL dans laquelle on recherche le mot
     */
	public static void main(String[] args) {
		Recherche Recherche = new Recherche();
		Recherche.counter(args[1], args[0]);
	}

	/*
	Permet d'instancier les threads (ici 10)
	 */
	public void counter(String u, String m) {
        try {
            this.threadsCounter.incrementAndGet();
            this.executorService.submit(new PageThread(new URL(u), m));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
}

    /*
    Arrête les threads
     */
public void endCounter() {
    if (threadsCounter.decrementAndGet() < 1) {
        executorService.shutdown();
    }
}
}
