package com.sky.mapper;

import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜品-套餐关系表
 * @author 萌萌哒AI
 * @date 2023/12/25
 */
@Mapper
public interface SetmealDishMapper {


    /**
     * 批量查询
     * @param ids
     */
    public List<Long> getByIds(List<Long> ids);

    /**
     * 批量插入套餐-菜品信息
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
