package com.lokiy.learning.spring.security.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lokiy.learning.spring.security.util.SnowFlakeUtil;
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
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "id", String.class, String.valueOf(snowFlakeUtil.nextId()));
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", String.class, "0");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateBy", String.class, "0");

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", String.class, "0");

    }
}
