package org.jfaster.generator.dataobject;

/**
 * Created by xiaojianyu on 16/5/4.
 */
public class TableField {
    private String tableName;
    private String fieldName;
    private String columnName;
    private String firstUppercaseFieldName;
    private String fieldType;
    private String columnType;
    private String columnComment;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFirstUppercaseFieldName() {
        return firstUppercaseFieldName;
    }

    public void setFirstUppercaseFieldName(String firstUppercaseFieldName) {
        this.firstUppercaseFieldName = firstUppercaseFieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

}
