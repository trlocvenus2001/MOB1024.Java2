package Entity;

public class PartTimeEmployee extends Employee {
	private double workingHours;
    private double hourlyRate;

    public PartTimeEmployee(String id, String name, double basicSalary, double workingHours, double hourlyRate) {
        super(id, name, basicSalary);
        this.setWorkingHours(workingHours);
        this.setHourlyRate(hourlyRate);
    }
    
    public double getWorkingHours() {
		return workingHours;
	}

	public boolean setWorkingHours(double workingHours) {
		if (workingHours >= 0) {
			this.workingHours = workingHours;
			return true;
		}
		return false;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public boolean setHourlyRate(double hourlyRate) {
		if (hourlyRate >= 0) {
			this.hourlyRate = hourlyRate;
			return true;
		}
		return false;
	}

	@Override
    public double income() {
        return workingHours * hourlyRate;
    }

	@Override
	public String toString() {
		return super.toString() + "PartTimeEmployee [workingHours=" + workingHours + ", hourlyRate=" + hourlyRate + "]";
	}
	
}
