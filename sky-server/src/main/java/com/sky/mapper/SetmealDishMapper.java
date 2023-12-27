package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 批量删除套餐-菜品信息
     *
     * @param setmealids
     */
    void deleteBarch(List<Long> setmealids);

    /**
     * 根据套餐id查询套餐-菜品信息
     *
     * @param SetmealId
     * @return {@link List}<{@link SetmealDish}>
     */
    @Select("select * from setmeal_dish where setmeal_id=#{SetmealId}")
    List<SetmealDish> getBySetmealId(Long SetmealId);

    /**
     * 根据setmealId删除信息
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);
}
