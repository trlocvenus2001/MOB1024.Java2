package Entity;

public class ImportedProduct extends Product {
	private double importTaxRate;
	private double shippingFee;
	
	public ImportedProduct() {
	}
	
	public ImportedProduct(String id, String name, double basePrice, double importTaxRate, double shippingFee) {
		super(id, name, basePrice);
		this.setImportTaxRate(importTaxRate);
		this.setShippingFee(shippingFee);
	}

	public double getImportTaxRate() {
		return importTaxRate;
	}
	public boolean setImportTaxRate(double importTaxRate){
		if (importTaxRate >= 0 && importTaxRate <= 1) {
			this.importTaxRate = importTaxRate;
			return true;
		}
		return false;
	}
	public double getShippingFee() {
		return shippingFee;
	}
	public boolean setShippingFee(double shippingFee) {
		if (shippingFee >= 0) {
			this.shippingFee = shippingFee;
			return true;
		}
		return false;
	}

	@Override
	public double finalPrice() {
		// TODO Auto-generated method stub
		return getBasePrice() + (getBasePrice() * importTaxRate) + shippingFee;	}

	@Override
	public String toString() {
		return super.toString() + "ImportedProduct [importTaxRate=" + importTaxRate + ", shippingFee=" + shippingFee + "]";
	}
	
}
