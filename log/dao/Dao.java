package log.dao;

import java.util.List;

import log.entity.Employee;

public interface Dao {
	public List<Employee> list() throws Exception;
	
	public Employee find(int id) throws Exception;
	
	public boolean insert(Employee employee) throws Exception;
	
	public boolean update(Employee employee) throws Exception;
	
	public boolean remove(int id) throws Exception;
	
}
