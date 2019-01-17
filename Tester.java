import java.util.TreeSet;


public class Tester {

	
	public static void main(String[] args) {
		Crawler webCrawler = new Crawler("https://money.cnn.com/data/hotstocks/index.html");
		TreeSet<Company> companies = webCrawler.getCompanies();
		
		companies.stream().forEach(company -> System.out.println(company));
	}
}
