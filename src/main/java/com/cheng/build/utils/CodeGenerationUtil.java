package com.cheng.build.utils;

import com.google.common.collect.ImmutableSet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class CodeGenerationUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Shanghai&useTimezone=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String PACKAGE_NAME = "com.cheng.build";
    private static final String TABLE_SCHEMA = "demo";
    private static final Set<String> TABLE_NAMES = ImmutableSet.of(
            "demo"
    );

    public static void main(String[] args) throws SQLException, IOException, TemplateException {
        autoGenerateCode();
    }

    /**
     * 自动生成代码
     */
    public static void autoGenerateCode() throws SQLException, IOException, TemplateException{

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            for (String tableName : TABLE_NAMES) {
                PreparedStatement preparedStatement = conn.prepareStatement(getSql(TABLE_SCHEMA, tableName));
                ResultSet resultSet = preparedStatement.executeQuery();
                String className = toPascalCase(tableName);
                List<Map<String, Object>> columns = getColumns(resultSet);

                generateCode(cfg, tableName, className, columns);
            }
        }
    }

    /**
     * 生成代码
     * @param cfg 配置
     * @param tableName 表名
     * @param className 类名
     * @param columns 字段信息
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateCode(Configuration cfg, String tableName, String className, List<Map<String, Object>> columns) throws IOException, TemplateException {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("packageName", PACKAGE_NAME);
        dataModel.put("tableName", tableName);
        dataModel.put("className", className);
        dataModel.put("lowerClassName", className.toLowerCase());
        dataModel.put("camelClassName", StringUtils.uncapitalize(className));
        dataModel.put("columns", columns);

        String columnNames = columns.stream().map(item -> item.get("name").toString()).collect(Collectors.joining(", "));
        String placeholders = columns.stream().map(col -> "#{" + col.get("camelCaseName") + "}").collect(Collectors.joining(", "));
        dataModel.put("columnNames", columnNames);
        dataModel.put("placeholders", placeholders);

        generateFile(cfg, dataModel, "entity.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/entity/" + className + ".java");
        generateFile(cfg, dataModel, "dto.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/dto/" + className + "DTO.java");
        generateFile(cfg, dataModel, "vo.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/vo/" + className + "VO.java");
        generateFile(cfg, dataModel, "controller.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/controller/" + className + "Controller.java");
        generateFile(cfg, dataModel, "mapper.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/mapper/" + className + "Mapper.java");
        generateFile(cfg, dataModel, "mapper_xml.ftl", "src/main/resources/mapper/" + className + "Mapper.xml");
        generateFile(cfg, dataModel, "service.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/service/" + className + "Service.java");
        generateFile(cfg, dataModel, "service_impl.ftl", "src/main/java/" + packageNamePath(PACKAGE_NAME) + "/service/impl/" + className + "ServiceImpl.java");
    }

    /**
     * 生成文件
     * @param cfg 配置
     * @param dataModel 参数
     * @param templateName 模板名称
     * @param outputPath 输出路径
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateFile(Configuration cfg, Map<String, Object> dataModel, String templateName, String outputPath) throws IOException, TemplateException {
        Template template = cfg.getTemplate(templateName);
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        try (FileWriter out = new FileWriter(outputFile)) {
            template.process(dataModel, out);
        }
    }

    /**
     * 路径替换
     * @param packageName
     * @return
     */
    private static String packageNamePath(String packageName) {
        return packageName.replace(".", "/");
    }

    /**
     * 获取字段值
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static List<Map<String, Object>> getColumns(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> columns = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> column = new HashMap<>();
            String columnName = resultSet.getString("COLUMN_NAME");
            String typeName = resultSet.getString("DATA_TYPE");
            String columnComment = resultSet.getString("COLUMN_COMMENT");
            String tableComment = resultSet.getString("TABLE_COMMENT");
            column.put("tableComment", tableComment);
            column.put("name", columnName);
            column.put("type", typeName);
            column.put("comment", columnComment);
            column.put("javaType", mapSqlTypeToJavaType(typeName));
            column.put("camelCaseName", toCamelCase(columnName, false));
            column.put("pascalCaseName", toCamelCase(columnName, true));
            columns.add(column);
        }
        return columns;
    }

    /**
     * mysql类型转java类型
     * @param sqlType
     * @return
     */
    private static String mapSqlTypeToJavaType(String sqlType) {
        switch (sqlType.toLowerCase()) {
            case "int":
            case "integer":
                return "Integer";
            case "bigint":
                return "Long";
            case "smallint":
                return "Short";
            case "decimal":
            case "numeric":
                return "BigDecimal";
            case "float":
                return "Float";
            case "double":
            case "real":
                return "Double";
            case "varchar":
            case "char":
            case "text":
            case "longtext":
            case "mediumtext":
            case "json":
            case "tinytext":
                return "String";
            case "date":
                return "LocalDate";
            case "datetime":
            case "timestamp":
                return "LocalDateTime";
            case "boolean":
            case "bit":
            case "tinyint":
                return "Boolean";
            default:
                return "Object";
        }
    }

    /**
     * 首字母大写驼峰
     * @param input 输入值
     * @return 首字母大写驼峰格式
     */
    private static String toPascalCase(String input) {
        return toCamelCase(input, true);
    }

    /**
     * 转驼峰
     * @param input 输入值
     * @param capitalizeFirstLetter 首字母是否大写
     * @return 驼峰格式
     */
    private static String toCamelCase(String input, boolean capitalizeFirstLetter) {
        StringBuilder result = new StringBuilder();
        boolean nextCapitalized = capitalizeFirstLetter;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                nextCapitalized = true;
            } else {
                if (nextCapitalized) {
                    result.append(Character.toUpperCase(c));
                    nextCapitalized = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }

    /**
     * 获取表结构、字段等信息
     * @param tableSchema 数据库名
     * @param tableName 表名
     * @return 表结构、字段等信息
     */
    private static String getSql(String tableSchema, String tableName) {
        return "SELECT\n" +
                "  t.table_name,\n" +
                "  t.table_comment,\n" +
                "  c.column_name,\n" +
                "  c.data_type,\n" +
                "  c.character_maximum_length,\n" +
                "  c.column_comment \n" +
                "FROM\n" +
                "  information_schema.columns c\n" +
                "INNER JOIN information_schema.tables t ON t.table_name = c.table_name \n" +
                "WHERE\n" +
                "  c.table_schema = '" + tableSchema + "' \n" +
                "  and t.table_schema = '" + tableSchema + "' \n" +
                "  and c.table_name = '" + tableName + "'";
    }
}
