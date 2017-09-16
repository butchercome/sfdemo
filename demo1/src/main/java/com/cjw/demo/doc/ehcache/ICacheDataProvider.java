package com.cjw.demo.doc.ehcache;


public interface ICacheDataProvider {

	/**
	 * 根据键值从实际的数据源中获取数据。
	 * @param key
	 * @return
	 */
	public Object getData(Object key);
}
