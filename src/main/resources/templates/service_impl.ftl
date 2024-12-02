package ${packageName}.service.impl;

import ${packageName}.entity.${className};
import ${packageName}.dto.${className}DTO;
import ${packageName}.vo.${className}VO;
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.service.${className}Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import javax.annotation.Resource;
import java.util.Objects;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {

    @Resource
    private ${className}Mapper ${camelClassName}Mapper;

    /**
    * 获取Wrapper
    */
    private LambdaQueryWrapper<${className}> getWrapper(${className}DTO ${camelClassName}DTO) {

        return Wrappers.lambdaQuery(${className}.class)
                <#list columns as column>
                    <#if column.javaType == "Integer" || column.javaType == "Long" || column.javaType == "Double" || column.javaType == "Float" || column.javaType == "Boolean" || column.javaType == "Short">
                        .eq(Objects.nonNull(${camelClassName}DTO.get${column.pascalCaseName}()), ${className}::get${column.pascalCaseName}, ${camelClassName}DTO.get${column.pascalCaseName}())
                    <#elseif column.javaType == "String">
                        .like(CharSequenceUtil.isNotBlank(${camelClassName}DTO.get${column.pascalCaseName}()), ${className}::get${column.pascalCaseName}, ${camelClassName}DTO.get${column.pascalCaseName}())
                    <#elseif column.javaType == "LocalDate" || column.javaType == "LocalDateTime">
                        .eq(Objects.nonNull(${camelClassName}DTO.get${column.pascalCaseName}()), ${className}::get${column.pascalCaseName}, ${camelClassName}DTO.get${column.pascalCaseName}())
                    <#else>
                        .eq(Objects.nonNull(${camelClassName}DTO.get${column.pascalCaseName}()), ${className}::get${column.pascalCaseName}, ${camelClassName}DTO.get${column.pascalCaseName}())
                    </#if>
                </#list>;
    }

    /**
     * 分页查询
     * @param ${camelClassName}DTO 参数
     * @return Page<${className}VO>
     */
    @Override
    public Page<${className}VO> getPage(${className}DTO ${camelClassName}DTO) {
        LambdaQueryWrapper<${className}> wrapper = getWrapper(${camelClassName}DTO);
        Page<${className}> page = ${camelClassName}Mapper.selectPage(new Page<>(${camelClassName}DTO.getCurrent(), ${camelClassName}DTO.getSize()), wrapper);
        return this.getCopyBeanPage(page, ${className}VO.class);
    }

    /**
     * 列表查询
     * @param ${camelClassName}DTO 参数
     * @return List<${className}VO>
     */
    @Override
    public List<${className}VO> getList(${className}DTO ${camelClassName}DTO) {
        LambdaQueryWrapper<${className}> wrapper = getWrapper(${camelClassName}DTO);
        List<${className}> list = ${camelClassName}Mapper.selectList(wrapper);
        return BeanUtil.copyToList(list, ${className}VO.class);
    }


    /**
     * 保存
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    @Override
    public ${className}VO save(${className}DTO ${camelClassName}DTO) {
        ${className} ${camelClassName} = this.saveOrUpdate(${camelClassName}DTO);
        return convertToVo(${camelClassName});
    }


    /**
     * 更新
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    @Override
    public ${className}VO update(${className}DTO ${camelClassName}DTO) {
        ${className} ${camelClassName} = this.saveOrUpdate(${camelClassName}DTO);
        return convertToVo(${camelClassName});
    }

    /**
     * 删除
     * @param id 主键
     * @return 标识
     */
    @Override
    public Integer delete(Integer id) {
        return ${camelClassName}Mapper.deleteById(id);
    }

    /**
    * 实体转换
    */
    private ${className}VO convertToVo(${className} entity) {
        ${className}VO vo = new ${className}VO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
     * 新增或删除【id为空新增，id不为空修改】
     * @param ${camelClassName}DTO 参数
     * @return 【修改后的实体对象】
     */
    private ${className} saveOrUpdate(${className}DTO ${camelClassName}DTO) {

        ${className} ${camelClassName} = BeanUtil.copyProperties(${camelClassName}DTO, ${className}.class);
        if (Objects.isNull(${camelClassName}.getId())) {
            ${camelClassName}Mapper.insert(${camelClassName});
        } else {
            ${camelClassName}Mapper.updateById(${camelClassName});
        }
        return ${camelClassName};
    }

    /**
     * 拷贝对象
     * @param source 源对象
     * @param target 目标对象
     * @return Page<T>
     */
    private <T, S> Page<T> getCopyBeanPage(Page<S> source, Class<T> target) {
        if (Objects.isNull(source)) return null;
        Page<T> page = new Page<>();
        page.setRecords(BeanUtil.copyToList(source.getRecords(), target));
        page.setTotal(source.getTotal());
        page.setCurrent(source.getCurrent());
        page.setSize(source.getSize());
        return page;
    }

}