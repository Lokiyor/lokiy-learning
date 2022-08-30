package com.lokiy.learning.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @author lokiy
 * @date 2022/8/30
 * @description TODO
 */
public class CodeGenerator {


    public static void main(String[] args) {
        FastAutoGenerator.create(
                "jdbc:postgresql://127.0.0.1:5432/learning?useUnicode=true&characterEncoding=utf8&serverTimeZone=CTT&stringtype=unspecified",
                "postgres",
                "123456")
                .globalConfig(builder -> {
                    builder.author("Lokiy")
//                            .enableSwagger()
                            .fileOverride()
                            .outputDir("/Users/lokiy/temp/");
                })
                .packageConfig(builder -> {
                    builder.parent("com.lokiy.learning.spring.security")
                            .moduleName("sys")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/lokiy/temp/"));
                })
                .strategyConfig( builder -> {
                    builder.addInclude("sys_user", "sys_role", "sys_permission")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("del_flag")
                            .logicDeletePropertyName("delFlag")
                            .addTableFills(
                                    new Column("id", FieldFill.INSERT),
                                    new Column("del_flag", FieldFill.INSERT),
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("create_by", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE),
                                    new Column("update_by", FieldFill.INSERT_UPDATE)
                                    )
                            .idType(IdType.INPUT)
//                            .addTablePrefix()
                            ;
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
