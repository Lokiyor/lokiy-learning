package com.lokiy.learning.spring.security.config;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author lokiy
 * @date 2022/8/30
 * @description TODO
 */
@Slf4j
@Component
public class SystemMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private Snowflake snowflake;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Object id = metaObject.getValue("id");
        if(id == null){
            this.strictInsertFill(metaObject, "id", String.class, snowflake.nextIdStr());
        }
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", String.class, "1");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateBy", String.class, "1");

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", String.class, "1");

    }
}
