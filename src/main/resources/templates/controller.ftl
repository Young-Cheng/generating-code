package ${packageName}.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageName}.dto.${className}DTO;
import ${packageName}.vo.${className}VO;
import ${packageName}.service.${className}Service;
import com.cheng.build.utils.R;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/${lowerClassName}")
public class ${className}Controller {

    @Resource
    private ${className}Service ${camelClassName}Service;

    /**
     * 分页查询
     * @param ${camelClassName}DTO 参数
     * @return Page<${className}VO>
     */
    @PostMapping("/getPage")
    public R<Page<${className}VO>> getPage(@RequestBody ${className}DTO ${camelClassName}DTO) {
        return R.ok(${camelClassName}Service.getPage(${camelClassName}DTO));
    }

    /**
     * 列表查询
     * @param ${camelClassName}DTO 参数
     * @return List<${className}VO>
     */
    @PostMapping("/getList")
    public R<List<${className}VO>> getList(@RequestBody ${className}DTO ${camelClassName}DTO) {
        return R.ok(${camelClassName}Service.getList(${camelClassName}DTO));
    }

    /**
     * 保存
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    @PostMapping("/save")
    public R<${className}VO> save(@RequestBody ${className}DTO ${camelClassName}DTO) {
        return R.ok(${camelClassName}Service.save(${camelClassName}DTO));
    }

    /**
     * 更新
     * @param ${camelClassName}DTO 参数
     * @return ${className}VO
     */
    @PostMapping("/update")
    public R<${className}VO> update(@RequestBody ${className}DTO ${camelClassName}DTO) {
        return R.ok(${camelClassName}Service.update(${camelClassName}DTO));
    }

    /**
     * 删除
     * @param id 主键
     * @return 标识
     */
    @PostMapping("/delete/{id}")
    public R<Integer> delete(@PathVariable("id") Integer id) {
        return R.ok(${camelClassName}Service.delete(id));
    }
}