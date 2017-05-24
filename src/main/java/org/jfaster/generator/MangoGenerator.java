package org.jfaster.generator;

import org.jfaster.generator.dao.DbDao;
import org.jfaster.generator.dataobject.DbTableName;
import org.jfaster.generator.dataobject.TableField;
import org.jfaster.generator.db.DbUtil;
import org.jfaster.generator.generators.DaoGenerator;
import org.jfaster.generator.generators.EntityGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class MangoGenerator {
    private String url;
    private String username;
    private String password;
    private String dbName;
    private String tableName;
    private String outputPath;

    public MangoGenerator(String url, String username, String password, String dbName, String outputPath) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.outputPath = outputPath;
    }

    public MangoGenerator(String url, String username, String password, String dbName, String tableName, String outputPath) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
        this.outputPath = outputPath;
    }

    public void generator() {
        try {
            Connection conn = DbUtil.getConn(getUrl(), getUsername(), getPassword());
            DbDao dbDao = new DbDao();
            DbTableName dbTableName = dbDao.queryTableName(conn, getDbName());
            if (dbTableName != null && dbTableName.getTableNames() != null) {
                List<String> tableNames = dbTableName.getTableNames();
                for (String tableName : tableNames) {
                    if (getTableName() != null && getTableName().length() > 0) {
                        if (!tableName.equals(getTableName())) {
                            continue;
                        }
                    }
                    List<TableField> tableFields = dbDao.queryFieldList(conn, dbName, tableName);
                    EntityGenerator.generator(outputPath, tableName, tableFields);

                    String pk = dbDao.queryPK(conn, dbName, tableName);
                    DaoGenerator.generator(outputPath, tableName, pk, tableFields);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
