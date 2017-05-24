package org.jfaster.generator.dataobject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class Table implements Serializable{

    private List<String> columnName;
    private List<String> fieldType;
    private List<String> columnComment;

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public List<String> getFieldType() {
        return fieldType;
    }

    public void setFieldType(List<String> fieldType) {
        this.fieldType = fieldType;
    }

    public List<String> getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(List<String> columnComment) {
        this.columnComment = columnComment;
    }
}
