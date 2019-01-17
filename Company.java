

public class Company implements Comparable<Company>{

	private String name;
	private double currentPrice;
	
	public Company(String name,String price) {
		this.name = name;
		this.currentPrice = Double.parseDouble(price);

	}

	@Override
	public int compareTo(Company other) {
		return this.name.compareTo(other.name);
	}
	
	@Override
	public String toString() {
		return this.name + "   " + this.currentPrice;
	}
	
}
