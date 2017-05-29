package com.cjw.demo.address.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DFAInstance {
	private static final Logger logger = LoggerFactory.getLogger("DFAInstance");

	public static final DFA dfa;

	static {
		long initStart = System.currentTimeMillis();
		dfa = DFA.create(DataCache.nameMap.keySet());
		logger.info("DFA init cost:" + (System.currentTimeMillis() - initStart));
	}

}
