package ${packageName}.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@TableName("${tableName}")
public class ${className} {
<#list columns as column>
    /**
     * ${column.comment}
     */
<#if column.camelCaseName == "id">
    @TableId(value = "${column.name}", type = IdType.AUTO)
    private ${column.javaType} ${column.camelCaseName};
<#else>
    @TableField("${column.name}")
    private ${column.javaType} ${column.camelCaseName};
</#if>
</#list>

}