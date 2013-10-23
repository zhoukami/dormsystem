package bd.inner.dormitory.util;

public interface BaseBuss<T> {
	public boolean add(T t);
	public boolean update(T t);
	public boolean delete(Object id);
	public T load(Object id);
}
