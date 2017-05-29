package com.cjw.demo.ehcache.provider;

import com.cjw.demo.ehcache.ICache;
import com.cjw.demo.ehcache.ICacheDataProvider;
import com.cjw.demo.ehcache.ICacheStore;

public class BaseCache implements ICache {

	String				name;
	ICacheStore cacheStore;
	ICacheDataProvider cacheProvider;

	//protected final Log logger = LogFactory.getLog(BaseCache.class);

	public String getCacheName() {
		return name;
	}

	public void setCacheName( String name ) {
		this.name = name;
	}

	public ICacheStore getCacheStore() {
		return cacheStore;
	}

	public void setCacheStore( ICacheStore cacheStore ) {
		this.cacheStore = cacheStore;
	}

	public ICacheDataProvider getCacheDataProvider() {
		return cacheProvider;
	}

	public void setCacheProvider( ICacheDataProvider dataProvider ) {
		this.cacheProvider = dataProvider;
	}

	public Object getData( Object key ) {
		// 若provider未加载完成，此方法无效
		if ( cacheProvider == null )
			return null;

		// 判断缓存数据是否在缓存存储中存在
		Object data = cacheStore.getCacheData ( key );
		if ( data == null ) {
			data = cacheProvider.getData ( key );
			if (data == null) {
				return null;
			}
			cacheStore.putCacheData ( key, data );
		}
		return data;
	}

	public void refresh() {

	}

	public void shutdown() {
		if ( cacheStore != null )
			cacheStore.shutdown ();
	}

	public void removeData( Object key ) {
		if(this.cacheStore!=null){
			this.cacheStore.removeCacheData ( key );
		}
	}

	public boolean containsKey(Object key) {
		if(this.cacheStore!=null){
			Object data = cacheStore.getCacheData ( key );
			return data!=null;
		}
		return false;
	}

}
