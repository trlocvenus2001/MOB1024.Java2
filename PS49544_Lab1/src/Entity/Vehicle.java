package Entity;

public class Vehicle {
	private String id;
    private String brand;
    private double basePrice;
    
    public Vehicle() {
	}

	public Vehicle(String id, String brand, double basePrice) {
        this.setId(id);
        this.brand = brand;
        this.setBasePrice(basePrice);
    }

	public String getId() {
		return id;
	}

	public boolean setId(String id) {
		if (id != "") {
			this.id = id;
			return true;
		}
		return false;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public boolean setBasePrice(double basePrice) {
		if (basePrice >= 0) {
			this.basePrice = basePrice;
			return true;
		}
		return false;
	}
	
	public double finalPrice() {
        return basePrice;
    }
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", brand=" + brand + ", basePrice=" + basePrice + "]";
	}
    
    
}
