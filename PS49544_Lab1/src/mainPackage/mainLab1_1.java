package mainPackage;

import java.util.ArrayList;

import Entity.*;

public class mainLab1_1 {
	public static void main(String[] args) {
		ArrayList<Product> listProduct = new ArrayList <Product>();
		Product p1 = new Product("Ao01", "Áo thun", 10);
		listProduct.add(p1);
		listProduct.add(new Product("Ao02", "Áo sơ mi", 12));
		listProduct.add(new ImportedProduct("001S", "Quần tây", 15.0, 0.1, 1));
		listProduct.add(new ImportedProduct("Q02","Quần Jean",20.0,0.15,1));
		listProduct.add(new ImportedProduct("Q03","Quần đùi", 3.0 ,0.15,1));
		for (Product p : listProduct) {
			System.out.println(p.toString());
		}
		Product maxProduct = listProduct.get(0);
		for (Product p : listProduct) {
		    if (p.finalPrice() > maxProduct.finalPrice()) {
		        maxProduct = p;
		    }
		}
		System.out.println("Sản phẩm có giá cao nhất là: " + maxProduct.toString());
	}
}
 	