package ${packageName}.vo;

import java.math.BigDecimal;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ${className}VO {
<#list columns as column>

    /**
     * ${column.comment}
     */
    private ${column.javaType} ${column.camelCaseName};
</#list>

}