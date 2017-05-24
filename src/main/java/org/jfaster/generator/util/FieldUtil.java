package org.jfaster.generator.util;


public class FieldUtil {

    /**
     * 根据数据库字段名获取pojo的字段名
     * 规则:先全部转换小写,如果存在下划线,去掉下划线,并把下划线后面首字母大写
     * @param column 数据库字段名称
     * @return
     */
    public static String columnToField(String column){
        String fieldStr = "";
        if(column != null && column.length() >0){
            column = column.toLowerCase();
            if(column.indexOf("_") > -1){
                String[] columnArr = column.split("_");
                for (int i = 0; i < columnArr.length; i++) {
                    fieldStr += capitalize(columnArr[i]);
                }
                return uncapitalize(fieldStr);
            }else{
                fieldStr = column;
            }
        }
        return fieldStr;
    }

    public static String capitalize(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;

    }

    public static String uncapitalize(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return  name;

    }

    public static String getClassName(String tableName){
        return capitalize(columnToField(tableName));
    }

    public static void main(String[] args) {
        System.out.println(FieldUtil.columnToField("asdfAdsfadsf"));
    }
}
