package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {
    /**
     * 新增菜品信息
     * @param dto
     */
    void saveWithDishFlavor(DishDTO dto);

}
