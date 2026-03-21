package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entity.Product;
import Entity.ProductCategory;

public class mainLab2_2 {
	public static void main(String[] args) {
		List<Product> listProduct = new ArrayList<Product>();
		listProduct.add(new Product("P01", "Mì gói", 5000, ProductCategory.FOOD));
		listProduct.add(new Product("P02", "Bánh mì", 5000, ProductCategory.FOOD));
		listProduct.add(new Product("P03", "Bánh Snack", 10000, ProductCategory.FOOD));
		listProduct.add(new Product("P04", "Áo thun", 49000, ProductCategory.CLOTHING));
		listProduct.add(new Product("P01", "Quần thun", 89000, ProductCategory.CLOTHING));
		listProduct.add(new Product("P01", "Giày", 119000, ProductCategory.CLOTHING));
		listProduct.add(new Product("P01", "Điện thoại", 35000000, ProductCategory.ELECTRONIC));
		listProduct.add(new Product("P01", "Máy tính xách tay", 25000000, ProductCategory.ELECTRONIC));
		listProduct.add(new Product("P01", "Máy tính bàn", 24900000, ProductCategory.ELECTRONIC));

		// Thống kê bằng map
		Map<ProductCategory, Integer> countMap = new HashMap<>();
		Map<ProductCategory, Double> totalMap = new HashMap<>();

		// Hiện thị thông tin sản phẩm
		for (Product product : listProduct) {
			ProductCategory category = product.getCategory();
			// Đếm số lượng sản phẩm
			countMap.put(category, countMap.getOrDefault(category, 0) + 1);

			// Tổng giá trị sản phẩm từng loại
			totalMap.put(category, totalMap.getOrDefault(totalMap, 0.0) + product.getPrice());
		}

		// Nếu dùng entrySet
		for (Map.Entry<ProductCategory, Integer> entry : countMap.entrySet()) {
			ProductCategory category = entry.getKey();
			int count = entry.getValue();
			double total = totalMap.get(category);
			System.out.println("Loại sản phẩm: " + category);
			System.out.println("Số lượng: " + count);
			System.out.println("Tổng giá trị: " + total);
		}
		// Nếu dùng keySet
//		for (ProductCategory category : countMap.keySet()) {
//			int count = countMap.get(category);
//			double total = totalMap.get(category);
//			System.out.println("Loại sản phẩm: " + category);
//			System.out.println("Số lượng: " + count);
//			System.out.println("Tổng giá trị: " + total);
//		}
	}
}
