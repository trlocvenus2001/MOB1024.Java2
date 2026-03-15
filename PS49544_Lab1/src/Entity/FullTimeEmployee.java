package Entity;

public class FullTimeEmployee extends Employee {
	private double bonus;
    private double penalty;

    public FullTimeEmployee() {
    	
    }
    public FullTimeEmployee(String id, String name, double basicSalary, double bonus, double penalty) {
        super(id, name, basicSalary);
        this.setBonus(bonus);     
        this.setPenalty(penalty);
    }
    
    public double getBonus() {
		return bonus;
	}
	public boolean setBonus(double bonus) {
		if (bonus >= 0) {
			this.bonus = bonus;
			return true;
		}
		return false;
	}
	public double getPenalty() {
		return penalty;
	}
	public boolean setPenalty(double penalty) {
		if (penalty >= 0) {
			this.penalty = penalty;
			return true;
		}
		return false;
	}
	
	@Override
    public double income() {
        return getBasicSalary() + bonus - penalty;
    }
	@Override
	public String toString() {
		return super.toString() + "FullTimeEmployee [bonus=" + bonus + ", penalty=" + penalty + "]";
	}
	
}
