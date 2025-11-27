package infrastrutura;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import dominio.EFactory;
import dominio.EModel;
import infrastrutura.interfaces.IAbstracRepo;
import infrastrutura.interfaces.ISerBD;
import jakarta.persistence.Column;

public abstract class AbsRepo<T extends EModel> implements IAbstracRepo<T> {

	protected ISerBD bd;
	protected String tabla;
	protected HashMap<String, Object> mapa;
	protected EFactory<T> fac;
	
	public AbsRepo(ISerBD bd, EFactory<T> fac, String tabla) {
		this.bd = bd;
		this.fac = fac;
		this.tabla = tabla;
	}
	
	@Override
	public T get(int id) throws SQLException {
		String query = "SELECT * FROM " + tabla + " WHERE id = ? ;";
		return fac.crearDesdeBD(queryReturnParam(query, id));
	}

	@Override
	public ArrayList<T> getAll() throws SQLException {
		String query = "SELECT * FROM " + tabla + ";";
		ArrayList<T> result = new ArrayList<>();
		ResultSet rS = queryReturn(query);
		while (rS.next()) {
			result.add(fac.crearDesdeBD(rS));
		}
		return result;
	}

	private void mapeo(T model) {
		// Recupero la clase del modelo
	    Class<?> clazz = model.getClass();
	
	    // Creo parametros auxiliares
	    mapa = new LinkedHashMap<>();
	    boolean bAcesible = false;
	    
	    // Recorro todos los campos del modelo
	    for (Field campo : clazz.getDeclaredFields()) {
	        bAcesible = campo.isAccessible();
	    	campo.setAccessible(true); // Si son privados los abro
	        try {
	        	// Me salto los campos que son estaticos o transient
	        	if (Modifier.isStatic(campo.getModifiers()) || Modifier.isTransient(campo.getModifiers())) { continue; }
	            
	        	Object valor = campo.get(model); // Recupero el valor, si es un primitivo hace autoboxing
	            
	        	// Omito valores nulos
	        	if (valor != null) {
	        		
	        		// Creo la key o bien con el nombre del campo o con la anotacion pertinente
	                String nombreColumna = campo.isAnnotationPresent(Column.class)
				                            ? campo.getAnnotation(Column.class).name()
				                            : campo.getName();
					                
	                // Inserto en el mapa el nombre y el valor.
	                mapa.put(nombreColumna, valor);
	            }
	        } catch (IllegalAccessException e) {
	            throw new RuntimeException("Error accediendo a campo: " + campo.getName(), e);
	        
	        } finally {
	        	// Cierro el campo a su estado original
	        	campo.setAccessible(bAcesible);
	        }
	    }  
    }
	
	@Override
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM " + tabla + " WHERE id = ? ;";
		return queryParam(query, id);
	}
	
	@Override
	public boolean insert(T model) throws SQLException {
		mapeo(model); // Reflexion para rellenar el mapa de los atributos
		if (mapa.isEmpty()) return false;
		
		String query = "INSERT INTO " + tabla + "(";
		ArrayList<Object> param = new ArrayList<>();
		int j = 0;
		for (Entry<String, Object> campo: mapa.entrySet()) {
			if ("id".equals(campo.getKey())) continue;
			query += j > 0 ? "," : "";
			query += campo.getKey();
			param.add(campo.getValue());
			j++;
		}
		query += ") VALUES (";
		for (int i = 0; i < param.size(); i++) {
			query += i < param.size()-1 ? "?," : "?);";
		}
		return queryParam(query, param.toArray());
	}

	@Override
	public boolean update(int id, T nuevo) throws SQLException {
		mapeo(nuevo);
		if (mapa.isEmpty()) return false;
		String query = "UPDATE " + tabla + " SET ";
		ArrayList<Object> campos = new ArrayList<>();
		int i = 0;
		for (Entry<String, Object> campo: mapa.entrySet()) {
			if ("id".equals(campo.getKey())) continue;
			query += i > 0 ? ", " : " "; // Añade comas si no es el ultimo elemento
			campos.add(campo.getValue());
			query += campo.getKey()+" = ? ";
			i++;
		}
		query += " WHERE id = ?;";
		campos.add(id);
		return queryParam(query, campos.toArray());
	}
	
	@Override
	public boolean querySimple(String query) throws SQLException {
		PreparedStatement prepaStm = bd.getConn().prepareStatement(query);
		return prepaStm.execute();
	}
	
	
	@Override
	public boolean queryParam(String query, Object... param) throws SQLException {
		PreparedStatement prepaStm = bd.getConn().prepareStatement(query);
		return setParam(prepaStm, param).execute();
	}
	
	@Override
	public ResultSet queryReturn(String query) throws SQLException {
		PreparedStatement prepaStm = bd.getConn().prepareStatement(query);
		return prepaStm.executeQuery();
	}
	
	@Override
	public ResultSet queryReturnParam(String query, Object... param) throws SQLException {
		PreparedStatement prepaStm = bd.getConn().prepareStatement(query);
		return setParam(prepaStm, param).executeQuery();
	}

	private PreparedStatement setParam(PreparedStatement prepaStm, Object ...param) throws SQLException {
		for (int i = 0; i < param.length; i++) {
			
			if(param[i] instanceof String) {
				prepaStm.setString(i+1, (String) param[i]);
				
			} else if(param[i] instanceof Integer) {
				prepaStm.setInt(i+1, (Integer) param[i]);
			
			} else if(param[i] instanceof Boolean) {
				prepaStm.setBoolean(i+1, (Boolean) param[i]);
			
			} else if(param[i] instanceof Double) {
				prepaStm.setDouble(i+1, (Double) param[i]);
			}
		}
		
		return prepaStm;
	}
}
