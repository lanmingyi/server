package com.brightcomplete.modules.system.vo;

import java.util.Date;

public class TaleDetailVo {

    //表名
    private String tableName;
    //表engine
    private String engine;
    //表注释
    private String tableComment;
    //表createTime
    private Date createTime;
    //库名
    private String tableSchema;
    //列名
    private String columnName;
    //列的排列顺序
    private String ordinalPosition;
    //默认值
    private String columnDefault;
    //是否为空
    private String isNullable;
    //数据类型
    private String dataType;
    //字符最大长度
    private String characterMaximumLength;
    //数值精度(最大位数)
    private String numericPrecision;
    //小数精度
    private String numericScale;
    //列类型
    private String columnType;
    //KEY
    private String columnKey;
    //额外说明
    private String extar;
    //注释
    private String columnComment;


    private String columnNameColumnComment;

    public String getColumnNameColumnComment() {
        return columnNameColumnComment;
    }

    public void setColumnNameColumnComment(String columnNameColumnComment) {
        this.columnNameColumnComment = columnNameColumnComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(String ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(String characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(String numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public String getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(String numericScale) {
        this.numericScale = numericScale;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtar() {
        return extar;
    }

    public void setExtar(String extar) {
        this.extar = extar;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}

