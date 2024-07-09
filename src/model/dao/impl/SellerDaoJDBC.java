package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void inset(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement( "SELECT seller.*, department.Name as DepName  FROM seller "
					+"INNER JOIN department ON seller.DepartmentID = department.Id"
					+" WHERE seller.Id = ? ");
			st.setInt(1, id );
			rs = st.executeQuery();

			
			if(rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				return seller;
			}
			
			return null;
			
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	
	}

	private Seller instantiateSeller(ResultSet rs,Department d) throws SQLException {
		Seller s = new Seller(rs.getInt("Id"),rs.getString("Name"),rs.getString("Email"),rs.getDate("BirthDate"),rs.getDouble("baseSalary"),d);
		return s;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
		return department;
	}
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Seller> findByDepartment(Department dep) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName  FROM seller INNER JOIN department ON seller.DepartmentID = department.Id WHERE department.Id = ? ORDER BY seller.Name");
			
			st.setInt(1,dep.getId());
			rs = st.executeQuery();
			
			Map<Integer ,Department> map = new HashMap<>();
			List<Seller> seller = new ArrayList<>();
			
	
			
			while(rs.next()) {
				
				Department d = map.get(rs.getInt("DepartmentId"));
				if(d == null) {
					d = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), d);
				}
				
				seller.add(instantiateSeller(rs, d));				
			}
			
			return seller;
			
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
