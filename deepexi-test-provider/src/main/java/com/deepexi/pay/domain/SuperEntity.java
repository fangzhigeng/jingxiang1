/**
 * SuperEntity  2019/4/2
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.config.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 数据库实体基类
 * @program: SuperEntity
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/4/2 下午2:34
 **/
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = -3023221879862316801L;

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    //多租户标识
    private String tenantId;

    /**
     * 版本号，乐观锁
     */
    private Integer  version;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(value="is_deleted")
    private Boolean deleted;

    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date createdTime;

    private String updatedBy;

    @TableField(fill = FieldFill.UPDATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}