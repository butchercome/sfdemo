package com.cjw.demo.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.InitializingBean;


public class CacheStore implements ICacheStore, InitializingBean {
	
	
	
	private Cache cache;

	private Integer maxElementsInMemory;
	private MemoryStoreEvictionPolicy memoryStoreEvictionPolicy;
	private Boolean overflowToDisk;
	private String diskStorePath;
	private Boolean eternal;
	private Long timeToLiveSeconds;
	private Long timeToIdleSeconds;
	private Boolean diskPersistent;
	private Long diskExpiryThreadIntervalSeconds;

	/**
	 * 当Bean初始化之后，初始化缓存
	 */
	public void afterPropertiesSet() throws Exception {
		initCacheStore(this.toString());
	}

	/**
	 * 初始化缓存
	 */
	public void initCacheStore(String cacheName) {
		cache = new Cache(cacheName, //
				getMaxElementsInMemory(),//
				getMemoryStoreEvictionPolicy(), //
				isOverflowToDisk(), //
				getDiskStorePath(), //
				isEternal(), //
				getTimeToLiveSeconds(), //
				getTimeToIdleSeconds(), //
				isDiskPersistent(), //
				getDiskExpiryThreadIntervalSeconds(), //
				null);
		CacheManager.getInstance().addCache(cache);
	}

	public Object getCacheData(Object key) {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return element.getObjectValue();
	}

	public boolean isDataInCache(Object key) {
		return cache.isKeyInCache(key);
	}

	public void putCacheData(Object key, Object data) {
		cache.put(new Element(key, data));
	}

	public void removeCacheData(Object key) {
		cache.remove(key);
	}

	public void shutdown() {
		cache.dispose();
	}

	public int getMaxElementsInMemory() {
		if (maxElementsInMemory == null) {
			return DefaultConfig.getInstance().getMaxElementsInMemory();
		}
		return maxElementsInMemory;
	}

	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}

	public MemoryStoreEvictionPolicy getMemoryStoreEvictionPolicy() {
		if (memoryStoreEvictionPolicy == null) {
			return DefaultConfig.getInstance().getMemoryStoreEvictionPolicy();
		}
		return memoryStoreEvictionPolicy;
	}

	public void setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy memoryStoreEvictionPolicy) {
		this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
	}

	public boolean isOverflowToDisk() {
		if (overflowToDisk == null) {
			return DefaultConfig.getInstance().isOverflowToDisk();
		}
		return overflowToDisk;
	}

	public void setOverflowToDisk(boolean overflowToDisk) {
		this.overflowToDisk = overflowToDisk;
	}

	public String getDiskStorePath() {
		if (diskStorePath == null) {
			return DefaultConfig.getInstance().getDiskStorePath();
		}
		return diskStorePath;
	}

	public void setDiskStorePath(String diskStorePath) {
		this.diskStorePath = diskStorePath;
	}

	public boolean isEternal() {
		if (eternal == null) {
			return DefaultConfig.getInstance().isEternal();
		}
		return eternal;
	}

	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}

	public long getTimeToLiveSeconds() {
		if (timeToLiveSeconds == null) {
			return DefaultConfig.getInstance().getTimeToLiveSeconds();
		}
		return timeToLiveSeconds;
	}

	public void setTimeToLiveSeconds(long timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

	public long getTimeToIdleSeconds() {
		if (timeToIdleSeconds == null) {
			return DefaultConfig.getInstance().getTimeToIdleSeconds();
		}
		return timeToIdleSeconds;
	}

	public void setTimeToIdleSeconds(long timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}

	public boolean isDiskPersistent() {
		if (diskPersistent == null) {
			return DefaultConfig.getInstance().isDiskPersistent();
		}
		return diskPersistent;
	}

	public void setDiskPersistent(boolean diskPersistent) {
		this.diskPersistent = diskPersistent;
	}

	public long getDiskExpiryThreadIntervalSeconds() {
		if (diskExpiryThreadIntervalSeconds == null) {
			return DefaultConfig.getInstance().getDiskExpiryThreadIntervalSeconds();
		}
		return diskExpiryThreadIntervalSeconds;
	}

	public void setDiskExpiryThreadIntervalSeconds(long diskExpiryThreadIntervalSeconds) {
		this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
	}

	
}
