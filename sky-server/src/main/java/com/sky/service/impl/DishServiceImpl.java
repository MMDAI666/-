package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    /**
     * 新增菜品信息
     * @param dto
     */
    @Override
    @Transactional
    public void saveWithDishFlavor(DishDTO dto) {
        Dish dish =new Dish();
        BeanUtils.copyProperties(dto,dish);
        //将菜品信息保存到数据库
        dishMapper.insert(dish);
        //获得菜品主键值
        Long id=dish.getId();

        //将口味信息保存道数据库
        List<DishFlavor> flavors = dto.getFlavors();
        flavors.forEach(dishFlavor -> dishFlavor.setDishId(id));
        dishFlavorMapper.insertBeach(flavors);

    }
}
