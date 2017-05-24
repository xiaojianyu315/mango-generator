package org.jfaster.generator.dao;

import org.jfaster.generator.dataobject.DbTableName;
import org.jfaster.generator.dataobject.TableField;
import org.jfaster.generator.util.DbUtil;
import org.jfaster.generator.util.FieldUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class DbDao {

    public String queryPK(Connection conn, String dbName, String tableName) throws IllegalAccessException, SQLException, InstantiationException {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ")
                .append("       COLUMN_NAME as pk ")
                .append(" FROM ")
                .append("       INFORMATION_SCHEMA.`KEY_COLUMN_USAGE` ")
                .append(" WHERE ")
                .append("       CONSTRAINT_SCHEMA= ? ")
                .append("       AND TABLE_NAME=? ")
                .append("       AND CONSTRAINT_NAME='PRIMARY' ");
        List<Map<String, Object>> maps = DbUtil.queryMapList(conn, sql.toString(), dbName, tableName);
        if (maps != null && maps.size() > 0) {
            return String.valueOf(maps.get(0).get("pk"));
        } else {
            System.out.println("=========================================================");
            System.out.println("= 表名 = " + tableName + "，无主键，请设置主键，已设置默认主键为id =");
            System.out.println("=========================================================");
            return "id";
        }
    }

    public DbTableName queryTableName(Connection conn, String dbName) throws IllegalAccessException, SQLException, InstantiationException {
        DbTableName dtn = new DbTableName();
        String sql = "select table_name as tableName from information_schema.tables where table_schema = ?";
        List<Map<String, Object>> maps = DbUtil.queryMapList(conn, sql, dbName);
        if (maps != null && maps.size() > 0) {
            List<String> tableNameList = new ArrayList<String>();
            for (Map m : maps) {
                tableNameList.add(m.get("tableName") + "");
            }
            dtn.setTableNames(tableNameList);
        }
        return dtn;
    }

    /**
     * 根据数据库和表名获取字段
     *
     * @param dbName    数据库名
     * @param tableName 表名
     * @return
     */
    public List<TableField> queryFieldList(Connection conn, String dbName, String tableName) throws IllegalAccessException, SQLException, InstantiationException {
        List<TableField> tableList = new ArrayList<TableField>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select  ")
                .append("         column_name as columnName, ")
                .append("         data_type as dataType, ")
                .append("         case when data_type in ('tinyint','smallint','mediumint','int')  then 'Integer'  ")
                .append("         when data_type in ('bigint')  then 'Long' ")
                .append("         when data_type in ('float','double','decimal') then 'BigDecimal' ")
                .append("         when data_type in ('char','varchar','tinytext','text','mediumtext','longtext') then 'String' ")
                .append("         when data_type in ('BLOB') then 'Blob' ")
                .append("         when data_type in ('date','time','datetime','timestamp') then 'Date' ")
                .append("         else '' ")
                .append("         end as fieldType,  ")
                .append("         column_comment as columnComment ")
                .append(" from information_schema.COLUMNS  ")
                .append(" where table_schema = ? and table_name = ? ");
        List<Map<String, Object>> maps = DbUtil.queryMapList(conn, sql.toString(), dbName, tableName);
        List<TableField> tfList = new ArrayList<TableField>();
        if (maps != null && maps.size() > 0) {
            for (Map m : maps) {
                TableField tf = new TableField();
                tf.setFieldName(FieldUtil.columnToField(m.get("columnName") + ""));
                tf.setColumnName(m.get("columnName") + "");
                tf.setFieldType(m.get("fieldType") + "");
                tf.setFirstUppercaseFieldName(FieldUtil.capitalize(FieldUtil.columnToField(m.get("columnName") + "")));
                tf.setColumnComment(m.get("columnComment") + "");
                tfList.add(tf);
            }
        }
        return tfList;
    }
}
