package com.bright.autopoi.cache;

import java.io.InputStream;

import com.bright.autopoi.cache.manager.POICacheManager;
import com.bright.autopoi.word.entity.MyXWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * word 缓存中心

 */
public class WordCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordCache.class);

	public static MyXWPFDocument getXWPFDocumen(String url) {
		InputStream is = null;
		try {
			is = POICacheManager.getFile(url);
			MyXWPFDocument doc = new MyXWPFDocument(is);
			return doc;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return null;
	}

}
