package Entity;

public class Motorbike extends Vehicle {
	private double insuranceFee;
	
	public Motorbike() {
		
	}
	public Motorbike(String id, String brand, double basePrice, double insuranceFee) {
        super(id, brand, basePrice);
        this.setInsuranceFee(insuranceFee);
    }
		
	public double getInsuranceFee() {
		return insuranceFee;
	}
	public boolean setInsuranceFee(double insuranceFee) {
        if (insuranceFee >= 0) {
            this.insuranceFee = insuranceFee;
            return true;
        }
        return false;
    }
	@Override
    public double finalPrice() {
        return getBasePrice() + insuranceFee;
    }
}
