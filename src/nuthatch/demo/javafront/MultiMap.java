package nuthatch.demo.javafront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class MultiMap<K, V> {
	Map<K, List<V>> resultMap = new HashMap<K, List<V>>();


	public void add(K key, V value) {
		List<V> list = resultMap.get(key);
		if(list == null) {
			list = new ArrayList<V>();
			resultMap.put(key, list);
		}
		list.add(value);
	}


	public Set<Entry<K, List<V>>> entrySet() {
		return resultMap.entrySet();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		MultiMap<?, ?> other = (MultiMap<?, ?>) obj;
		if(resultMap == null) {
			if(other.resultMap != null) {
				return false;
			}
		}
		else if(!resultMap.equals(other.resultMap)) {
			return false;
		}
		return true;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (resultMap == null ? 0 : resultMap.hashCode());
		return result;
	}
}
