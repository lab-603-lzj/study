
import java.util.Comparator;

/**
 * 按照数组的顺序正序排列，数组的元素位置决定了对象的排序先后<br>
 * 如果参与排序的元素并不在数组中，则排序在前
 *
 * @author looly
 *
 * @param <T> 被排序元素类型
 * @since 4.1.5
 */
public class IndexedComparator<T> implements Comparator<T> {

	private final T[] array;

	/**
	 * 构造
	 *
	 * @param objs 参与排序的数组，数组的元素位置决定了对象的排序先后
	 */
	@SuppressWarnings("unchecked")
	public IndexedComparator(T... objs) {
		this.array = objs;
	}

	@Override
	public int compare(T o1, T o2) {
		if (o1 instanceof String && o2 instanceof String){
			return ((String) o1).compareTo((String) o2);
		}
		return -1;
	}

}
