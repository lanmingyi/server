package com.bright.autopoi.excel.imports;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import com.bright.autopoi.excel.entity.params.ExcelImportEntity;
import com.bright.autopoi.excel.entity.sax.SaxReadCellEntity;
import com.bright.autopoi.exception.excel.ExcelImportException;
import com.bright.autopoi.exception.excel.enums.ExcelImportEnum;
import com.bright.autopoi.handler.inter.IExcelDataHandler;
import com.bright.autopoi.util.ExcelUtil;
import com.bright.autopoi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Cell 取值服务 判断类型处理数据 1.判断Excel中的类型 2.根据replace替换值 3.handler处理数据 4.判断返回类型转化数据返回
 */
public class CellValueServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CellValueServer.class);

	private List<String> hanlderList = null;

	/**
	 * 获取单元格内的值
	 * 
	 * @param xclass
	 * @param cell
	 * @param entity
	 * @return
	 */
	private Object getCellValue(String xclass, Cell cell, ExcelImportEntity entity) {
		if (cell == null) {
			return "";
		}
		Object result = null;
		// 日期格式比较特殊,和cell格式不一致
		if ("class java.util.Date".equals(xclass) || ("class java.sql.Time").equals(xclass)) {
			if ( CellType.NUMERIC == cell.getCellTypeEnum()) {
				// 日期格式
				result = cell.getDateCellValue();
			} else {
				cell.setCellType( CellType.STRING);
				result = getDateData(entity, cell.getStringCellValue());
			}
			if (("class java.sql.Time").equals(xclass)) {
				result = new Time(((Date) result).getTime());
			}
		} else if ( CellType.NUMERIC == cell.getCellTypeEnum()) {
			result = cell.getNumericCellValue();
		} else if ( CellType.BOOLEAN == cell.getCellTypeEnum()) {
			result = cell.getBooleanCellValue();
		} else if ( CellType.FORMULA == cell.getCellTypeEnum() && PoiPublicUtil.isNumber(xclass)) {
			//如果单元格是表达式 且 字段是数字类型
			result = cell.getNumericCellValue();
		} else {
			result = cell.getStringCellValue();
		}
		return result;
	}

	/**
	 * 获取日期类型数据
	 * @param entity
	 * @param value
	 * @return
	 */
	private Date getDateData(ExcelImportEntity entity, String value) {
		if (StringUtils.isNotEmpty(entity.getFormat()) && StringUtils.isNotEmpty(value)) {
			SimpleDateFormat format = new SimpleDateFormat(entity.getFormat());
			try {
				return format.parse(value);
			} catch (ParseException e) {
				LOGGER.error("时间格式化失败,格式化:{},值:{}", entity.getFormat(), value);
				throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
			}
		}
		return null;
	}

	/**
	 * 获取cell的值
	 * 
	 * @param object
	 * @param excelParams
	 * @param cell
	 * @param titleString
	 */
	public Object getValue(IExcelDataHandler dataHanlder, Object object, Cell cell, Map<String, ExcelImportEntity> excelParams, String titleString) throws Exception {
		ExcelImportEntity entity = excelParams.get(titleString);
		String xclass = "class java.lang.Object";
		if (!(object instanceof Map)) {
			Method setMethod = entity.getMethods() != null && entity.getMethods().size() > 0 ? entity.getMethods().get(entity.getMethods().size() - 1) : entity.getMethod();
			Type[] ts = setMethod.getGenericParameterTypes();
			xclass = ts[0].toString();
		}
		Object result = getCellValue(xclass, cell, entity);
		if (entity != null) {
			result = hanlderSuffix(entity.getSuffix(), result);
			// 多值替换
			result = replaceValue(entity.getReplace(), result,entity.isMultiReplace());
		}
		result = hanlderValue(dataHanlder, object, result, titleString);
		return getValueByType(xclass, result, entity);
	}

	/**
	 * 获取cell值
	 * 
	 * @param dataHanlder
	 * @param object
	 * @param cellEntity
	 * @param excelParams
	 * @param titleString
	 * @return
	 */
	public Object getValue(IExcelDataHandler dataHanlder, Object object, SaxReadCellEntity cellEntity, Map<String, ExcelImportEntity> excelParams, String titleString) {
		ExcelImportEntity entity = excelParams.get(titleString);
		Method setMethod = entity.getMethods() != null && entity.getMethods().size() > 0 ? entity.getMethods().get(entity.getMethods().size() - 1) : entity.getMethod();
		Type[] ts = setMethod.getGenericParameterTypes();
		String xclass = ts[0].toString();
		Object result = cellEntity.getValue();
		result = hanlderSuffix(entity.getSuffix(), result);
		//  多值替换
		result = replaceValue(entity.getReplace(), result,entity.isMultiReplace());
		result = hanlderValue(dataHanlder, object, result, titleString);
		return getValueByType(xclass, result, entity);
	}

	/**
	 * 把后缀删除掉
	 * 
	 * @param result
	 * @param suffix
	 * @return
	 */
	private Object hanlderSuffix(String suffix, Object result) {
		if (StringUtils.isNotEmpty(suffix) && result != null && result.toString().endsWith(suffix)) {
			String temp = result.toString();
			return temp.substring(0, temp.length() - suffix.length());
		}
		return result;
	}

	/**
	 * 根据返回类型获取返回值
	 * 
	 * @param xclass
	 * @param result
	 * @param entity
	 * @return
	 */
	private Object getValueByType(String xclass, Object result, ExcelImportEntity entity) {
		try {
			// excel 导入报错，空指针问题
			if(result==null || "".equals(String.valueOf(result))){
				return null;
			}
			if ("class java.util.Date".equals(xclass)) {
				return result;
			}
			if ("class java.lang.Boolean".equals(xclass) || "boolean".equals(xclass)) {
				// Excel注解的numFormat方法似乎未实现 #970
				Boolean temp = Boolean.valueOf(String.valueOf(result));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return Boolean.valueOf(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.lang.Double".equals(xclass) || "double".equals(xclass)) {
				// Excel注解的numFormat方法似乎未实现 #970
				Double temp = Double.valueOf(String.valueOf(result));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return Double.valueOf(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.lang.Long".equals(xclass) || "long".equals(xclass)) {
				// Excel注解的numFormat方法似乎未实现 #970
				Long temp = Long.valueOf(ExcelUtil.remove0Suffix(String.valueOf(result)));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return Long.valueOf(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.lang.Float".equals(xclass) || "float".equals(xclass)) {
				//:Excel注解的numFormat方法似乎未实现 #970
				Float temp = Float.valueOf(String.valueOf(result));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return Float.valueOf(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.lang.Integer".equals(xclass) || "int".equals(xclass)) {
				// Excel注解的numFormat方法似乎未实现 #970
				Integer temp = Integer.valueOf(ExcelUtil.remove0Suffix(String.valueOf(result)));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return Integer.valueOf(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.math.BigDecimal".equals(xclass)) {
				// Excel注解的numFormat方法似乎未实现 #970
				BigDecimal temp = new BigDecimal(String.valueOf(result));
				//if(StringUtils.isNotEmpty(entity.getNumFormat())){
				//	return new BigDecimal(new DecimalFormat(entity.getNumFormat()).format(temp));
				//}else{
					return temp;
				//}
			}
			if ("class java.lang.String".equals(xclass)) {
				// 针对String 类型,但是Excel获取的数据却不是String,比如Double类型,防止科学计数法
				if (result instanceof String) {
					// excel导入数字类型，去掉后缀.0
					return ExcelUtil.remove0Suffix(result);
				}
				// double类型防止科学计数法
				if (result instanceof Double) {
					return PoiPublicUtil.doubleToString((Double) result);
				}
				// excel导入数字类型，去掉后缀.0------
				return ExcelUtil.remove0Suffix(String.valueOf(result));
			}
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
		}
	}

	/**
	 * 调用处理接口处理值
	 * 
	 * @param dataHanlder
	 * @param object
	 * @param result
	 * @param titleString
	 * @return
	 */
	private Object hanlderValue(IExcelDataHandler dataHanlder, Object object, Object result, String titleString) {
		if (dataHanlder == null || dataHanlder.getNeedHandlerFields() == null || dataHanlder.getNeedHandlerFields().length == 0) {
			return result;
		}
		if (hanlderList == null) {
			hanlderList = Arrays.asList(dataHanlder.getNeedHandlerFields());
		}
		if (hanlderList.contains(titleString)) {
			return dataHanlder.importHandler(object, titleString, result);
		}
		return result;
	}

	// 导入多值替换--
	/**
	 * 导入支持多值替换
	 * @param replace 数据库中字典查询出来的数组
	 * @param result excel单元格获取的值
	 * @param multiReplace 是否支持多值替换
	 */
	private Object replaceValue(String[] replace, Object result,boolean multiReplace) {
		if(result == null){
			return "";
		}
		if(replace == null || replace.length<=0){
			return result;
		}
		String temp = String.valueOf(result);
		String backValue = "";
		if(temp.indexOf(",")>0 && multiReplace){
			//原值中带有逗号，认为他是多值的
			String multiReplaces[] = temp.split(",");
			for (String str : multiReplaces) {
				backValue = backValue.concat(replaceSingleValue(replace, str)+",");
			}
			if(backValue.equals("")){
				backValue = temp;
			}else{
				backValue = backValue.substring(0, backValue.length()-1);
			}
		}else{
			backValue = replaceSingleValue(replace, temp);
		}
		// 字典替换失败提示日志
		if(replace.length>0 && backValue.equals(temp)){
			LOGGER.warn("====================字典替换失败,字典值:{},要转换的导入值:{}====================", replace, temp);
		}
		return backValue;
	}
	/**
	 * 单值替换 ,若没找到则原值返回
	 */
	private String replaceSingleValue(String[] replace, String temp){
		String[] tempArr;
		for (int i = 0; i < replace.length; i++) {
			// @Excel dicText字段的值有下划线时，导入功能不能正确解析---
			//tempArr = replace[i].split("_");
			tempArr = getValueArr(replace[i]);
			if (temp.equals(tempArr[0]) || temp.replace("_","---").equals(tempArr[0])) {
                // 导入字典替换需要将---替换成_，不然数据库会存--- ------------
                if(tempArr[1].contains("---")){
                    return tempArr[1].replace("---","_");
                }
                return tempArr[1];
			}
		}
		return temp;
	}

	/**
	 * 字典文本中含多个下划线横岗，取最后一个（解决空值情况）
	 *
	 * @param val
	 * @return
	 */
	public String[] getValueArr(String val) {
		int i = val.lastIndexOf("_");//最后一个分隔符的位置
		String[] c = new String[2];
		c[0] = val.substring(0, i); //label
		c[1] = val.substring(i + 1); //key
		return c;
	}

}
