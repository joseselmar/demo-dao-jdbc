package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		
		SellerDao sd = DaoFactory.createSellerDao();
		
		System.out.println("### Test 1: seller findById ");
		Seller s = sd.findById(3);
		System.out.println(s);
		
		System.out.println("\n ### Test 2: seller findByDepartment ");
		Department dp = new Department(2,null);
		List<Seller> list = sd.findByDepartment(dp);
		
		for(Seller l : list) {
			System.out.println(l);
		}

	}

}
