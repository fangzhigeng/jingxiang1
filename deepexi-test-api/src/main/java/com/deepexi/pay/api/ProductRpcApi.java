/**
 * ProductRpcApi  2019/4/2
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.api;

import com.deepexi.pay.domain.ProductDTO;

/**
 * @description: 商品rpc调用接口
 * @program: ProductRpcApi
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/4/2 下午2:35
 **/
public interface ProductRpcApi {

    /**
     * @description: 根据商品id获取商品部分信息
     * @param id : 
     * @return: com.deepexi.demo.dto.ProductDTO
     * @author: donh
     * @date: 2019/3/28 上午11:23
     */
    ProductDTO getProductById(Long id);

    /**
     * 添加商品
     * @param productDTO
     * @return
     */
    Boolean createProduct(ProductDTO productDTO);
}