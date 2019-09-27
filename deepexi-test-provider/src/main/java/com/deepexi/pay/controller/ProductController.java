/**
 * ProductController  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.deepexi.pay.domain.ProductCreateVO;
import com.deepexi.pay.domain.ProductPageQuery;
import com.deepexi.pay.domain.ProductUpdateVO;
import com.deepexi.pay.service.ProductService;
import com.deepexi.util.config.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @description: 商品服务rest接口，中台中心层不需要rest接口可忽略
 * @program: ProductController
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/27 下午4:06
 **/
@Validated
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    /**
     * @description: 分页查询商品信息
     * @param page : 第几页
     * @param size : 每页查询数据条数
     * @param price : 价格作为筛选条件
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:28
     */
    @GetMapping
    public Payload getProductList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                  @RequestParam(name = "price", required = false, defaultValue = "0") Integer price) {
        return new Payload(productService.getProductList(page, size, price));
    }

    /**
     * @description: 根据商品id查询商品信息
     * @param id :
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:30
     */
    @GetMapping("/{id:[a-zA-Z0-9]+}")
    public Payload getProductById(@PathVariable("id") Long id) {
        return new Payload(productService.getProductById(id));
    }

    /**
     * @description: 创建商品
     * @param productCreateVO :
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:30
     */
    @PostMapping
    public Payload createProduct(@RequestBody ProductCreateVO productCreateVO) {
        return new Payload(productService.createProduct(productCreateVO));
    }

    /**
     * @description: 更新商品信息
     * @param id :
     * @param productUpdateVO :
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:30
     */
    @PutMapping("/{id:[a-zA-Z0-9]+}")
    public Payload updateProductById(@PathVariable("id") Long id, ProductUpdateVO productUpdateVO) {
        return new Payload(null);
    }

    /**
     * @description: 根据id删除商品
     * @param id :
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/4/4 下午3:11
     */
    @DeleteMapping("/{id:[a-zA-Z0-9]+}")
    public Payload deleteProductById(@PathVariable("id") Long id) {
        return new Payload(productService.deleteProductById(id));
    }

    /**
     * @description: 测试统一异常接口
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:31
     */
    @GetMapping("/testError")
    public Payload testError() {
        productService.testError();
        return new Payload(true);
    }

    /**
     * @description: 测试sentinel限流接口
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:32
     */
    @GetMapping("/testSentinel")
    @SentinelResource(value = "testSentinel", blockHandler = "exceptionHandler")
    public Payload testSentinel() {
        logger.info("远程Sentinel测试接口成功: Hello World!!");
        return new Payload(true);
    }

    /**
     * @description: 测试接口，绑定参数校验demo，测试post方式
     * @param id :
     * @param productCreateVO :
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:32
     */
    @PostMapping("/testPostValidate")
    public Payload testPostValidate(@Min(value = 5, message = "id必须大于等于5") @RequestParam Long id,
                                    @Valid @RequestBody ProductCreateVO productCreateVO) {
        return new Payload(true);
    }

    /**
     * @description:  测试接口，绑定参数校验demo2，测试get url传参方式
     *                参数少时可以直接列出来单独写
     *                参数多时可以选择封装成dto对象
     * @param id :
     * @param productPageQuery :
     * @return:
     * @author: donh
     * @date: 2019/3/28 下午2:33
     */
    @GetMapping("/testGetValidate")
    public Payload testGetValidate(@Min(value = 5, message = "id必须大于等于5") @RequestParam Long id,
                                   @Valid ProductPageQuery productPageQuery) {
        return new Payload(true);
    }

    /**
     * @description: 测试远程rpc调用时异常处理接口
     * @return: com.deepexi.util.config.Payload
     * @author: donh
     * @date: 2019/3/28 下午2:33
     */
    @GetMapping("/testRpcError")
    public Payload testRpcError() {
        productService.testRpcError();
        return new Payload(true);
    }

}
