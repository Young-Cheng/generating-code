# generating-code

#### 简介
本项目提供了一个自动生成 Java 代码的工具，可以根据配置自动生成以下组件：
- Entity
- DTO
- VO
- Controller
- Service
- ServiceImpl
- Mapper
- Mapper.xml

此外，还提供了基本的 CRUD 操作（分页查询、列表查询、保存、更新、删除）及其实现。

#### 功能

- 自动生成 Entity 类
- 自动生成 DTO 类
- 自动生成 VO 类
- 自动生成 Controller 类
- 自动生成 Service 接口
- 自动生成 Service 实现类
- 自动生成 Mapper 接口
- 自动生成 Mapper XML 文件
- 自动生成分页查询方法
- 自动生成列表查询方法
- 自动生成保存方法
- 自动生成更新方法
- 自动生成删除方法


#### 技术栈
Java 8+
Maven
MyBatis-Plus
Spring Boot
Lombok


#### 使用说明

1.  更改com.cheng.build.utils.CodeGenerationUtil中的数据库配置与表名，表名即为要生成代码的表
![输入图片说明](https://foruda.gitee.com/images/1733136205899536812/999ac9e3_11536060.png "屏幕截图")

#### 效果
- Entity
![Entity](https://foruda.gitee.com/images/1733136390316531925/e6db42fc_11536060.png "屏幕截图")
- DTO
![DTO](https://foruda.gitee.com/images/1733136416819627237/9719e41c_11536060.png "屏幕截图")
- VO
![VO](https://foruda.gitee.com/images/1733136465175896743/af9152ec_11536060.png "屏幕截图")
- Controller
![Controller](https://foruda.gitee.com/images/1733136511346446381/fd0275d4_11536060.png "屏幕截图")
- Service
![Service](https://foruda.gitee.com/images/1733136549000006532/0e1c1d44_11536060.png "屏幕截图")
- impl
![impl](https://foruda.gitee.com/images/1733136586346007824/c38fece5_11536060.png "屏幕截图")
- Mapper
![Mapper](https://foruda.gitee.com/images/1733136655327024458/2142511e_11536060.png "屏幕截图")
- mapper.xml
![mapper.xml](https://foruda.gitee.com/images/1733136707893509046/3cdd6b2b_11536060.png "屏幕截图")

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  Gitee  [Gitee:https://gitee.com/young-cheng/generating-code](https://gitee.com/young-cheng/generating-code)
2.  GitHub [GitHub:https://github.com/Young-Cheng/generating-code](https://github.com/Young-Cheng/generating-code)
