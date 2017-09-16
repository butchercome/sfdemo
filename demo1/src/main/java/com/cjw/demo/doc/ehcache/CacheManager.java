package com.cjw.demo.doc.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {

	static CacheManager singleton =new CacheManager();

	public static CacheManager getInstance(){
		return singleton;
	}

	private  Map<String, ICache>	storages	= new ConcurrentHashMap<String, ICache> ();

	
	public static void main(String args[]){
		
		CacheManager.getInstance().getCache("rangCache");
	}
	
	/**
	 * @return the storages
	 */
	public  ICache getCache(String name) {
		return storages.get ( name );
	}

	/**
	 * @param storages the storages to set
	 */
	public  void regCache(String name, ICache icache ) {
		storages.put ( name, icache );
	}
}
