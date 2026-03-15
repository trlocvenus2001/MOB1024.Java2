package mainPackage;

import java.util.ArrayList;

import Entity.*;
public class mainLab1_4 {
	public static void main(String[] args) {
        ArrayList<Vehicle> listVehicle = new ArrayList<>();
        listVehicle.add(new Car("C01", "Toyota", 500.0, 0.1, 20.0));
        listVehicle.add(new Car("C02", "Honda", 400.0, 0.05, 15.0));
        listVehicle.add(new Motorbike("M01", "Yamaha", 50.0, 5.0));
        listVehicle.add(new Motorbike("M02", "Suzuki", 45.0, 3.0));
        Vehicle v3 = new Vehicle("", "Xe Tự Chế", 20.0);
        listVehicle.add(v3);
        for (Vehicle v : listVehicle) {
            System.out.println(v.toString());
        }
        Vehicle cheapest = listVehicle.get(0);
        for (Vehicle v : listVehicle) {
            if (v.finalPrice() < cheapest.finalPrice()) {
               cheapest = v;
            }
        }
        System.out.println("Phương tiện có giá thấp nhất" + cheapest.toString());
	}   
}
