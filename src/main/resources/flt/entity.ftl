<#assign javaUtilCount=0/>
<#assign javaMathCount=0/>
import java.io.Serializable;
<#list fieldList as o>
    <#if o.fieldType == 'Date' && javaUtilCount == 0>
import java.util.*;
    <#assign javaUtilCount=javaUtilCount + 1/>
    </#if>
    <#if o.fieldType == 'Date' && javaMathCount == 0>
import java.math.BigDecimal;
    <#assign javaMathCount=javaMathCount + 1/>
    </#if>
</#list>

/**
* 对应数据库表名: ${tableName}
*/
public class ${className} implements Serializable{

<#list fieldList as o>
    /**
    * 数据库字段: ${o.columnName}
    * 字段描述: ${o.columnComment}
    */
    private ${o.fieldType} ${o.fieldName};

</#list>
<#list fieldList as o>

    public void set${o.firstUppercaseFieldName}(${o.fieldType} ${o.fieldName}) {
        this.${o.fieldName} = ${o.fieldName};
    }

    public ${o.fieldType} get${o.firstUppercaseFieldName}() {
        return ${o.fieldName};
    }
</#list>
}