<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">
    <resultMap id="BaseResultMap" type="${packageName}.entity.${className}">
    <#list columns as column>
        <result column="${column.name}" property="${column.camelCaseName}" />
    </#list>
    </resultMap>

</mapper>