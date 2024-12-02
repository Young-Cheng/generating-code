package ${packageName}.service;

import ${packageName}.entity.${className};
import ${packageName}.dto.${className}DTO;
import ${packageName}.vo.${className}VO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ${className}Service extends IService<${className}> {

    /**
     * 分页查询
     * @param ${camelClassName}DTO 参数
     * @return Page<${className}VO>
     */
    Page<${className}VO> getPage(${className}DTO ${camelClassName}DTO);

    /**
     * 列表查询
     * @param ${camelClassName}DTO 参数
     * @return List<${className}VO>
     */
    List<${className}VO> getList(${className}DTO ${camelClassName}DTO);

     /**
     * 保存
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    ${className}VO save(${className}DTO ${camelClassName}DTO);

    /**
     * 更新
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    ${className}VO update(${className}DTO ${camelClassName}DTO);

    /**
     * 删除
     * @param id 主键
     * @return 标识
     */
    Integer delete(Integer id);
}