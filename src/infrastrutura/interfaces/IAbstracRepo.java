package infrastrutura.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.EModel;

public interface IAbstracRepo<T extends EModel> {

	public T get(int id) throws SQLException;
	public ArrayList<T> getAll() throws SQLException;
	public boolean insert(T model) throws SQLException;
	public boolean delete(int id) throws SQLException;
	public boolean update(int id, T nuevo) throws SQLException;
	
	public boolean querySimple(String query) throws SQLException;
	public boolean queryParam(String query, Object ...param) throws SQLException;
	public ResultSet queryReturn(String query) throws SQLException;
	public ResultSet queryReturnParam(String query, Object ...param) throws SQLException;

}




