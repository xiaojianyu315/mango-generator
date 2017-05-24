package org.jfaster.generator.dataobject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class DbTableName implements Serializable{

    private List<String> tableNames;

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }
}
