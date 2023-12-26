package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

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

    /**
     * 根据id查询菜品信息及口味信息
     * @param id
     * @return {@link DishVO}
     */
    DishVO getByIdwithFlavor(Long id);

    /**
     * 修改菜品信息
     * @param dto
     */
    void updateWithFlavor(DishDTO dto);

    /**
     * 启售停售菜品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据分类id查询启售中的菜品
     * @param categoryId
     * @return {@link List}<{@link Dish}>
     */
    List<Dish> list(Long categoryId);
}
