package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品信息
     * @param dto
     */
    void saveWithDishFlavor(DishDTO dto);

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return {@link PageResult}
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void delete(List<Long> ids);
}
