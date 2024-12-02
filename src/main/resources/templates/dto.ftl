package ${packageName}.dto;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ${className}DTO extends Page {
<#list columns as column>

    /**
     * ${column.comment}
     */
    private ${column.javaType} ${column.camelCaseName};
</#list>
}