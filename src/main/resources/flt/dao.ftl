import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ReturnGeneratedId;

@DB(
        table = "${tableName}"
)
public interface ${className}Dao {

    <#assign BASE_COLUMNS = ""/>
    <#assign VALUES_COLUMNS = " "/>
    <#list fieldList as o>
         <#assign BASE_COLUMNS = BASE_COLUMNS + ", " + o.columnName/>
         <#assign VALUES_COLUMNS = VALUES_COLUMNS + ":" + o.fieldName + ", "/>
    </#list>
    String BASE_COLUMNS = "${BASE_COLUMNS?substring(1,BASE_COLUMNS?length)} ";
    String VALUES_COLUMNS = "${VALUES_COLUMNS?substring(0,VALUES_COLUMNS?length-2)} ";

    @ReturnGeneratedId
    @SQL("insert into #table(" + BASE_COLUMNS + ") values(" + VALUES_COLUMNS + ")")
    long insert(${className} object);

    @SQL("select " + BASE_COLUMNS + " from #table where ${pk} = :1")
    ${className} selectByPrimaryKey(${pkFieldType} ${pkFieldName});

    @SQL(
          " update #table set "
    <#list fieldList as o>
    <#if  o.columnName != pk >
        <#if  o_index == fieldList?size - 1 >
        + " ${o.columnName} = :${o.fieldName} "
        <#else>
        + " ${o.columnName} = :${o.fieldName}, "
        </#if>
    </#if>
    </#list>
        + " where ${pk} = :${pkFieldName}"
    )
    boolean updateByPrimaryKey(${className} object);

    @SQL("delete from #table where ${pk} = :1")
    boolean deletesByPrimaryKey(${pkFieldType} ${pkFieldName});
}