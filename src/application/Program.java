package application;

import java.util.Date;
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
		
		System.out.println("\n ### Test 3: seller findAll ");
		List<Seller> alist = sd.findAll();
		
		for(Seller l : alist) {
			System.out.println(l);
		}
		
		System.out.println("\n ### Test 4: insert seller ");
		 
		Seller se = new Seller(null,"João","Ja@email.com",new Date(),4000.00,dp);
		sd.insert(se);
		System.out.println("novo ID "+se.getId());

	}

}
