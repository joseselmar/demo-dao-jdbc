package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
	void inset(Seller obj);
	void update(Seller obj);
	void delete( Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();

}
