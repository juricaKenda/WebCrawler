import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeSet;

//Note that this crawler works only for the url given in the main file

public class Crawler {

	private URL url;
	private URLConnection connection;
	private InputStreamReader inputStreamReader;
	private BufferedReader buffer;
	private TreeSet<Company> allCompanies;
	
	
	/**
	 * Creates a web crawler that starts crawling through a given web page immediately
	 * @param url is a hyperlink to the target page
	 */
	public Crawler(String url) {
		
		//Initialize a storage for companies
		this.allCompanies = new TreeSet<>();
		
		
		try {
			//Try making a connection to the given web page 
			this.url = new URL(url);
			this.connection = this.url.openConnection();
			
			//If opening a connection was successful get a stream of data
			this.inputStreamReader = new InputStreamReader(this.connection.getInputStream());
			this.buffer = new BufferedReader(this.inputStreamReader);
			
			//Read through all of the data that was passed into the stream 
			String eachLine ="";
			while(eachLine != null) {
				eachLine = this.buffer.readLine();			
				
				//This is the pattern that we are looking for
				if(eachLine.contains("streamFeed=")) {
					//Split the entire line at stream Feed section and grab the company name and analytics
					 String[] firstSeparation = eachLine.split("streamFeed=");
					 String mergedData = firstSeparation[1];
					 
					 //Extract the name 
					 String[] secondSeparation = mergedData.split(">");
					 String companyName = secondSeparation[0];
					 
					 //Further separate to extract the analytics
					 String[] thirdSeparation = secondSeparation[1].split("<");
					 String companyData = thirdSeparation[0];
					 
					 //After the extraction of data, skip three lines
					 eachLine = this.buffer.readLine();
					 eachLine = this.buffer.readLine();
					 eachLine = this.buffer.readLine();
					 
					 //Store the given company in the internal storage
					 this.allCompanies.add(new Company(companyName,companyData));
					 
				}

				
			}
			
			this.buffer.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			//ignore
		}
		
	}
	
	public TreeSet<Company> getCompanies(){
		return this.allCompanies;
	}
}
