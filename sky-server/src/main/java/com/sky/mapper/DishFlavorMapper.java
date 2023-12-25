package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 口味相关接口
 * @author 萌萌哒AI
 * @date 2023/12/25
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量添加口味信息
     * @param flavors
     */
    void insertBeach(List<DishFlavor> flavors);

    /**
     * 根据dish-id删除口味数据
     * @param dish_ids
     */
    void deleteByDishIds(List<Long> dish_ids);
}
