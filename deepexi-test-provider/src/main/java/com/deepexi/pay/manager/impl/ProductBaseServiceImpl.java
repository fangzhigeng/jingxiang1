/**
 * ProductBaseServiceImpl  2019/3/28
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.manager.impl;

import com.deepexi.pay.domain.ProductDO;
import com.deepexi.pay.manager.service.ProductBaseService;
import com.deepexi.pay.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 商品通用基础接口实现类
 * @program: dubbo-demo
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/28 上午11:16
 **/
@Service
public class ProductBaseServiceImpl implements ProductBaseService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void createProduct(ProductDO productDO) {
        // 一些新增处理逻辑
        productMapper.insert(productDO);
    }

    @Override
    public void deleteProductById(Long id) {
        // 一些删除前处理逻辑
        productMapper.deleteById(id);
    }

    @Override
    public ProductDO getProductById(Long id) {
        // 一些处理逻辑
        return productMapper.selectById(id);
    }
}
