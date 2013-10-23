package bd.inner.dormitory.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;



public class DAOFactory {
	private static final Map<String, Object> classMap = new HashMap<String, Object>();
	private static DAOFactory instance;
	private DAOFactory(){
		
	}
	public synchronized static DAOFactory getInstance(){
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	public <T extends BaseDAO<?>> T getDAO(Class<T> daoInterfaceClass, Connection conn){
		BaseDAO<?> dao = null;
		final String key = daoInterfaceClass.getSimpleName();
		if(!classMap.containsKey(key)){
			try {
				Constructor<?> c = Class.forName(daoInterfaceClass.getCanonicalName()).getDeclaredConstructor(new Class[]{Connection.class});
				c.setAccessible(true);
				dao = (BaseDAO<?>) c.newInstance(new Object[]{conn});
				classMap.put(key, dao);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return (T) dao;
	}
}
