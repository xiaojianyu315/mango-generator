package org.jfaster.generator.generators;

import org.jfaster.generator.dataobject.TableField;
import org.jfaster.generator.util.FieldUtil;
import org.jfaster.generator.util.FileUtil;
import org.jfaster.generator.util.TemplateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaojianyu on 17/5/22.
 */
public class EntityGenerator {

    public static void generator(String outputPath, String tableName, List<TableField> tableFields) throws Exception {
        //使用模板生成类
        Map paramMap = new HashMap();
        paramMap.put("className", FieldUtil.getClassName(tableName));
        paramMap.put("tableName", tableName);
        paramMap.put("fieldList", tableFields);
        String doStr = TemplateUtil.getTemplateStr("/entity.ftl", paramMap);
//        System.out.println(doStr);
        String classFile = outputPath + FieldUtil.getClassName(tableName) + ".java";
        FileUtil.mkdirFile(classFile);
        FileUtil.writer(classFile, doStr, false);
    }
}
