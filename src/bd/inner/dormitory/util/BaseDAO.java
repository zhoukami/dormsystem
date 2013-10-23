package bd.inner.dormitory.util;

import java.sql.SQLException;


public interface BaseDAO<T> {
	public long add(T t) throws SQLException;
	public boolean update(T t) throws SQLException;
	public T get(long condition) throws SQLException;
	public boolean delete(Object id) throws SQLException;
}

