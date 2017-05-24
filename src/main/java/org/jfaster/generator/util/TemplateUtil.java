package org.jfaster.generator.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jfaster.generator.dataobject.TableField;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件内容模板生产工厂类
 *
 * @date 2
 * @author
 * @Description:
 * @project mailUtil
 */
public class TemplateUtil {

    // 模板文件路径
    private static String templatePath = "/flt";
    // 模板文件内容编码
    private static final String ENCODING = "utf-8";
    // 模板生成配置
    private static Configuration conf = new Configuration();
    // 邮件模板缓存池
    private static Map<String, Template> tempMap = new HashMap<String, Template>();
    static {
        // 设置模板文件路径
        conf.setClassForTemplateLoading(TemplateUtil.class, templatePath);
        conf.setTemplateUpdateDelay(0);
    }

    /**
     * 通过模板文件名称获取指定模板
     *
     * @Date:2014年4月26日 下午3:09:05
     * @author
     * @param name
     *            模板文件名称
     * @return Template 模板对象
     * @throws IOException
     * @Description:
     * @return Template
     */
    private static Template getTemplateByName(String name) throws IOException {
        if (tempMap.containsKey(name)) {
            // 缓存中有该模板直接返回
            return tempMap.get(name);
        }
        // 缓存中没有该模板时，生成新模板并放入缓存中
        Template temp = conf.getTemplate(name);
        tempMap.put(name, temp);
        return temp;
    }

    /**
     * 获取替换后的模板字符串
     *
     * @Date:2014年4月26日 下午3:14:18
     * @author
     * @param name
     *            模板文件名称,classpath/flt为根路径
     * @param map
     *            与模板内容转换对象
     * @Description:
     * @return void
     */
    public static String getTemplateStr(String name, Map<String, String> map) {
        Writer out = null;
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = getTemplateByName(name);
            temp.setEncoding(ENCODING);
            out = new StringWriter(2048);
            temp.process(map, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e.toString(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.toString(), e);
        }
        return out.toString();
    }

    /**
     * 根据指定模板将内容直接输出到文件
     *
     * @Date:2014年4月26日 下午3:20:15
     * @author
     * @param name
     *            模板文件名称
     * @param map
     *            与模板内容转换对象
     * @param outFile
     *            输出的文件绝对路径
     * @Description:
     * @return void
     */
    public static void outputToFile(String name, Map<String, String> map,
                                    String outFile) {
        FileWriter out = null;
        try {
            out = new FileWriter(new File(outFile));
            Template temp = getTemplateByName(name);
            temp.setEncoding(ENCODING);
            temp.process(map, out);
        } catch (IOException e) {
            throw new RuntimeException(e.toString(), e);
        } catch (TemplateException e) {
            throw new RuntimeException(e.toString(), e);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     *
     * @Date:2014年4月26日 下午3:24:37
     * @author
     * @param name
     *            模板文件的名称
     * @param map
     *            与模板内容转换对象
     * @return
     * @throws IOException
     * @throws TemplateException
     * @Description:
     * @return String
     */
    public static String generateHtmlFromFtl(String name,
                                             Map<String, String> map) throws IOException, TemplateException {
        Writer out = new StringWriter(2048);
        Template temp = getTemplateByName(name);
        temp.setEncoding(ENCODING);
        temp.process(map, out);
        return out.toString();
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        m.put("className", "WlcMember");
        m.put("tableName", "wlc_member");
        m.put("packageName", "com.welicai");
        m.put("primaryKeyName", "id");
        m.put("primaryKeyType", "java.lang.Long");
        m.put("jdbcType", "BIGINT");

        List<TableField> fieldList = new ArrayList<TableField>();
        TableField tf = new TableField();
        tf.setFieldName("realName");
        tf.setColumnName("real_name");
        tf.setFieldType("Date");
        tf.setFirstUppercaseFieldName("Id");
        fieldList.add(tf);
        m.put("fieldList",fieldList);
        System.out.println(TemplateUtil.getTemplateStr("/mybatis_mapper.flt",m));
    }
}