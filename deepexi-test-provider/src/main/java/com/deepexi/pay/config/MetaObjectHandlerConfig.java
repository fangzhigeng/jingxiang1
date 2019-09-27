/**
 * MetaObjectHandlerConfig  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: mybatis-plus公共字段自动填充，https://baomidou.oschina.io/mybatis-plus-doc/#/auto-fill
 * @program: MetaObjectHandlerConfig
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/27 下午3:55
 **/
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    /**
     * @description: 插入方法实体填充
     * @param metaObject :
     * @return: void
     * @author: donh
     * @date: 2019/3/27 下午3:56
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdAt", new Date(), metaObject);
    }

    /**
     * @description: 更新方法实体填充
     * @param metaObject :
     * @return: void
     * @author: donh
     * @date: 2019/3/27 下午3:57
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedAt", new Date(), metaObject);
    }
}