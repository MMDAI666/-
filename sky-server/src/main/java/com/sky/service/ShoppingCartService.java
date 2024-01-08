package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     *添加购物车商品
     */
    public void add(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询购物车
     * @return {@link List}<{@link ShoppingCart}>
     */
    List<ShoppingCart> show();
}
