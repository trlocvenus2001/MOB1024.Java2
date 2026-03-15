package Entity;

public class Car extends Vehicle {
	private double taxRate;
    private double registrationFee;
    
    public Car() {
    	
    }
    
    public Car(String id, String brand, double basePrice, double taxRate, double registrationFee) {
        super(id, brand, basePrice);
        this.setTaxRate(taxRate);
        this.setRegistrationFee(registrationFee);
    }
    
    public double getTaxRate() {
		return taxRate;
	}

    public boolean setTaxRate(double taxRate) {
        if (taxRate >= 0 && taxRate <= 1) {
            this.taxRate = taxRate;
            return true;
        }
        return false;
    }

	public double getRegistrationFee() {
		return registrationFee;
	}

	public boolean setRegistrationFee(double registrationFee) {
        if (registrationFee >= 0) {
            this.registrationFee = registrationFee;
            return true;
        }
        return false;
    }

	@Override
    public double finalPrice() {
        return getBasePrice() + (getBasePrice() * taxRate) + registrationFee;
    }
}
