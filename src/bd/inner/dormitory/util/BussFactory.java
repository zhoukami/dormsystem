package bd.inner.dormitory.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;




public class BussFactory {
	private static final Map<String, Object> classMap = new HashMap<String, Object>();
	private static BussFactory instance;
	private BussFactory(){
		
	}
	public synchronized static BussFactory getInstance(){
		if(instance == null){
			instance = new BussFactory();
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	public <T extends BaseBuss<?>> T getBUSS(Class<T> daoInterfaceClass){
		BaseBuss<?> buss = null;
		final String key = daoInterfaceClass.getSimpleName();
		System.out.println("BUSSFACTORY "+key);
		if(!classMap.containsKey(key)){
			try {
				Constructor<?> c = Class.forName(daoInterfaceClass.getCanonicalName()).getDeclaredConstructor(new Class[]{});
				c.setAccessible(true);
				buss = (BaseBuss<?>) c.newInstance(new Object[]{});
				classMap.put(key, buss);
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
		return (T) buss;
	}
}
