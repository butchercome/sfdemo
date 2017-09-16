package com.cjw.demo.doc.ehcache.provider;

import com.cjw.demo.doc.ehcache.ICacheStore;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheStore implements ICacheStore {

	private final net.sf.ehcache.Cache cache;

	public CacheStore(String name){
		final CacheManager manager = CacheManager.getInstance();
		net.sf.ehcache.Cache c = manager.getCache(name);
		if (c == null) {
			manager.addCache(name);
			c = manager.getCache(name);
		}
		this.cache = c;
		if (this.cache == null)
			throw new IllegalStateException("Can not create ehCache entity! Name["+name+"]");
	}

	public Object getCacheData( Object key ) {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return element.getObjectValue();
	}

	public boolean isDataInCache( Object key ) {
		return cache.isKeyInCache ( key );
	}

	public void putCacheData( Object key, Object data ) {
		cache.put(new Element(key, data));
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		cache.dispose ();
	}

	public void removeCacheData( Object key ) {
		if(cache!=null){
			cache.remove ( key );
		}
	}



}
