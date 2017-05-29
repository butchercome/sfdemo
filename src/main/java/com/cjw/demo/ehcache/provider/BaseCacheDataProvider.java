package com.cjw.demo.ehcache.provider;

import com.cjw.demo.ehcache.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public abstract class BaseCacheDataProvider implements ICacheDataProvider, InitializingBean,DisposableBean {

	//protected final Log logger = LogFactory.getLog(BaseCacheDataProvider.class);

	String cacheName;

	/**
	 * @return the cacheName
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * @param cacheName the cacheName to set
	 */
	public void setCacheName( String cacheName ) {
		this.cacheName = cacheName;
	}



	public void afterPropertiesSet() throws Exception {
		ICache cache = initializeCache();
		if(cache!=null)
			CacheManager.getInstance().regCache ( cache.getCacheName (), cache );
	}


	protected ICache cache =null;

	protected ICache initializeCache(){
		ICache cache = getCache ();
		if(cache==null){
			cache = this.createCache();
			cache.setCacheProvider(this);
			setCache ( cache );
		}
		return cache;
	}

	/**
	 * 不同的子类可能使用的CACHE类型不同。
	 */
	protected ICache createCache(){
		ICache cache = new BaseCache();
		cache.setCacheName ( cacheName );
		CacheStore store=new CacheStore(cacheName);
		cache.setCacheStore ( store );
		return cache;
	}



	/**
	 * @return the cache
	 */
	protected ICache getCache() {
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	protected void setCache( ICache cache ) {
		this.cache = cache;
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		ICache cache = getCache ();
		if(cache!=null) cache.shutdown ();
	}

}
