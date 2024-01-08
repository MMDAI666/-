package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCarService;
    /**
     * 添加购物车
     * @param shoppingCartDTO
     * @return {@link Result}
     */
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO)
    {
        log.info("添加到购物车的商品：{}",shoppingCartDTO);
        shoppingCarService.add(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查看购物车
     * @return {@link Result}<{@link List}<{@link ShoppingCart}>>
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list()
    {
        return Result.success(shoppingCarService.show());
    }

    /**
     * 减少份数
     * @return {@link Result}
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO)
    {
        shoppingCarService.sub(shoppingCartDTO);
        return Result.success();
    }

    @DeleteMapping("/clean")
    public Result clean()
    {
        shoppingCarService.clean();
        return Result.success();
    }
}
