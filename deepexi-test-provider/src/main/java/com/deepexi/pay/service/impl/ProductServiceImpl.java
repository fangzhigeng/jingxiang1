/**
 * ProductServiceImpl  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.dubbo.rpc.RpcContext;
import com.deepexi.pay.domain.ProductDO;
import com.deepexi.pay.domain.ProductCreateVO;
import com.deepexi.pay.domain.ProductDTO;
import com.deepexi.pay.enums.ResultEnum;
import com.deepexi.pay.manager.service.ProductBaseService;
import com.deepexi.pay.mapper.ProductMapper;
import com.deepexi.pay.service.ProductService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @program: ProductServiceImpl
 * @author: donh
 * @mail: hudong@deepexi.com
 * @create: 2019/3/27 下午2:13
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductBaseService productBaseService;

    @Autowired
    private ProductMapper productMapper;

    /**
     * @param page :
     * @param size :
     * @param price  :
     * @Description:
     * @return: com.deepexi.util.pageHelper.PageBean
     * @Author: donh
     * @Date: 2019/3/27 下午1:55
     */
    @Override
    public PageBean getProductList(Integer page, Integer size, Integer price) {
        PageHelper.startPage(page, size);
        List<ProductDTO> userTasks = productMapper.selectPageVo(price);
        return new PageBean<>(userTasks);
    }

    /**
     * @param productCreateVO :
     * @Description:
     * @return: java.lang.Integer
     * @Author: donh
     * @Date: 2019/3/27 下午1:55
     */
    @Override
    public Boolean createProduct(ProductCreateVO productCreateVO) {
        ProductDO product = new ProductDO();
//        BeanHelper.copyProperties(productCreateQuery, product);
        product.setName(productCreateVO.getName());
        product.setPrice(productCreateVO.getPrice());
        product.setTenantId("10001");
        productBaseService.createProduct(product);
        return true;
    }

    /**
     * @param id :
     * @Description:
     * @return: java.lang.Boolean
     * @Author: donh
     * @Date: 2019/3/27 下午1:55
     */
    @Override
    public Boolean deleteProductById(Long id) {
        productBaseService.deleteProductById(id);
        return true;
    }

    /**
     * @param id :
     * @Description:
     * @return: com.deepexi.demo.domain.eo.Product
     * @Author: donh
     * @Date: 2019/3/27 下午2:12
     */
    @Override
    @SentinelResource(value = "testSentinel", fallback = "doFallback", blockHandler = "exceptionHandler")
    public ProductDTO getProductById(Long id) {
        // dubbo生产者被消费者调用时，客户端隐式传入的参数
        String tenantId = RpcContext.getContext().getAttachment("tenantId");
        logger.info("获取客户端隐式参数，tenantId：{}", tenantId);
        ProductDO product = productBaseService.getProductById(id);
        return new ProductDTO(product.getName(), 1);
    }

    /**
     * @param i :
     * @Description: 描述
     * @return: java.lang.String
     * @Author: donh
     * @Date: 2019/3/27 下午2:10
     */
    public String doFallback(long i) {
        // Return fallback value.
        return "Oops, degraded";
    }

    /**
     * @param s  :
     * @param ex :
     * @Description: 熔断降及处理逻辑
     * @return: void
     * @Author: donh
     * @Date: 2019/3/27 下午2:09
     */
    public void exceptionHandler(long s, Exception ex) {
        // Do some log here.
        logger.info("-------------熔断降级处理逻辑---------\n");
        t