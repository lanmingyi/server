package com.bright.autopoi.dict.service;


/**
 * 描述：
 */
public interface AutoPoiDictServiceI{
	/**
 	 * 方法描述:  查询数据字典
 	 * @param dicTable
 	 * @param dicCode
 	 * @param dicText
 	 * @return 
 	 * 返回类型： List<DictEntity>
 	 */
 	public String[] queryDict(String dicTable,String dicCode, String dicText);

}
