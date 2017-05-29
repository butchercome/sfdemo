package com.cjw.demo.address.util;

import java.io.*;
import java.util.Iterator;

public class CityBasedataReader implements Iterable<String>, Iterator<String> {
	private final InputStream fis;
	private final BufferedReader br;
	private String line;

	public CityBasedataReader() {
		try {
//			System.out.println(DataCache.class.getClassLoader().getResource("../").getPath());
			System.out.println(DataCache.class.getClassLoader().getResource("./").getPath());
			fis = DataCache.class.getClassLoader().getResourceAsStream("./citybasedata_v3.config");
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		try {
			if (br != null)
				line = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean res = line != null;
		if (!res) {
			closeAll();
		}
		return res;
	}

	@Override
	public String next() {
		return line;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

	@Override
	protected void finalize() {
		closeAll();
	}

	private void closeAll() {
		if (br != null)
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (fis != null)
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
