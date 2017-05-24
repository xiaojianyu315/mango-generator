package org.jfaster.generator.generators;

import org.jfaster.generator.dao.DbDao;
import org.jfaster.generator.dataobject.TableField;
import org.jfaster.generator.db.DbUtil;
import org.jfaster.generator.util.FieldUtil;
import org.jfaster.generator.util.FileUtil;
import org.jfaster.generator.util.TemplateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class DaoGenerator {
    public static void generator(String outputPath, String tableName,String pk, List<TableField> tableFields) throws Exception {
        Map paramMap = new HashMap();

        paramMap.put("className", FieldUtil.getClassName(tableName));
        paramMap.put("tableName", tableName);
        paramMap.put("fieldList", tableFields);
        paramMap.put("pk", pk);
        paramMap.put("pkFieldName", FieldUtil.columnToField(pk));
        paramMap.put("pkFieldType", getPkType(pk,tableFields));
        String daoStr = TemplateUtil.getTemplateStr("/dao.flt", paramMap);
        System.out.println(daoStr);
        String daoFile = outputPath + FieldUtil.getClassName(tableName) + "Dao.java";
        FileUtil.mkdirFile(daoFile);
        FileUtil.writer(daoFile, daoStr, false);
    }


    private static String getPkType(String pk, List<TableField> tableFields){
        String type = "";
        for(TableField tf  : tableFields){
            if(tf.getColumnName().equals(pk)){
                return tf.getFieldType();
            }
        }
        return null;
    }
}
