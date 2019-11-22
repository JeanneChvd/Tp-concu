import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageThread implements Runnable {

		private URL u;
		public static List<String> l1 = new LinkedList<>();
		public static List<String> l2 = new LinkedList<>();
		public static List<String> l3 = new LinkedList<>();
		private String s;


		public PageThread(URL u, String s) {
			this.u = u;
			this.s = s;
		}

		/*
		Methode Run du thread
		 */
		@Override
		public void run() {
			try {

				BufferedReader in = null;
				in = new BufferedReader(new InputStreamReader(u.openStream()));

				String inputLine;
				Boolean b = false;

				while ((inputLine = in.readLine()) != null) {
					Pattern p = Pattern.compile("https://([^\"]*)\"", Pattern.DOTALL);
					Pattern p2 = Pattern.compile("Nantes");
					Matcher m = p.matcher(inputLine);
					Matcher m2 = p2.matcher(inputLine);
					if(m2.find()) {
						b = true;
					}
					if (m.find()) {
						for (int i = 0; i <= m.groupCount(); i = i + 2) {
							String groupe = m.group(i);
							l3.add(groupe);
						}
					}
				}
				if(b=true) {
					for(String urlFound : l3) {
						ThreadAddHisto.addHist(urlFound);
					}
					System.out.println(u);
				}
			} catch (IOException e) {
			}

			for (String urlFound : l1) {
				l1.add(urlFound);
				String utl = ThreadTake.take(l2);
				
				Recherche p = new Recherche();
				p.counter(utl, s);
			}
			Recherche p = new Recherche();
			p.endCounter();
			notify();
		}
	}