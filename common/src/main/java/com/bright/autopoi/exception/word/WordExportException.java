package com.bright.autopoi.exception.word;

import com.bright.autopoi.exception.word.enmus.WordExportEnum;

/**
 * word导出异常
 */
public class WordExportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WordExportException() {
		super();
	}

	public WordExportException(String msg) {
		super(msg);
	}

	public WordExportException(WordExportEnum exception) {
		super(exception.getMsg());
	}

}
