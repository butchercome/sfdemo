package com.cjw.demo.doc.ehcache;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class BaseCache implements ICache, InitializingBean, DisposableBean {
	

	// 缓存名称
	private String name;
	// 缓存实现接口
	private ICacheStore cacheStore;
	// 缓存数据提供接口
	private ICacheDataProvider cacheProvider;
	

	public String getCacheName() {
		return name;
	}

	public void setCacheName(String name) {
		this.name = name;
	}

	public ICacheStore getCacheStore() {
		return cacheStore;
	}

	public void setCacheStore(ICacheStore cacheStore) {
		this.cacheStore = cacheStore;
	}

	public ICacheDataProvider getCacheDataProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(ICacheDataProvider dataProvider) {
		cacheProvider = dataProvider;
	}

	public Object getData(Object key) {
		Object data = cacheStore.getCacheData(key);
		if (data == null) {
			synchronized (this) {
				data = cacheStore.getCacheData(key);
				if (data == null) {
					data = cacheProvider.getData(key);
					cacheStore.putCacheData(key, data);
				}
			}
		}
		return data;
	}

	public void refresh() {
	}

	public void shutdown() {
		cacheStore.shutdown();
	}

	public void removeData(Object key) {
		cacheStore.removeCacheData(key);
	}

	public boolean containsKey(Object key) {
		Object data = cacheStore.getCacheData(key);
		return data != null;
	}

	/**
	 * 当Bean初始化之后，注册当前缓存到缓存管理器
	 */
	public void afterPropertiesSet() throws Exception {
		// 如果没有在Spring配置cacheStore，则创建一个缺省的cacheStore
		if (cacheStore == null) {

			CacheStore defaultCacheStore = new CacheStore();
			defaultCacheStore.initCacheStore(name);
			cacheStore = defaultCacheStore;
		}
		CacheManager.getInstance().regCache(this.name, this);
	}

	public void destroy() throws Exception {
		shutdown();
	}


}
