package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
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

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return {@link PageResult}
     */
    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page=dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        //判断菜品是否处于停售状态
        for (Long id : ids) {
            Dish dish = dishMapper.getBydId(id);
            if(Objects.equals(dish.getStatus(), StatusConstant.ENABLE))
            {
                throw  new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //判断能否删除---setmeal-dish中是否有该dish
        List<Long> byIds = setmealDishMapper.getByIds(ids);
        if (byIds ==null || !byIds.isEmpty())
        {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品表数据
        dishMapper.deleteByIds(ids);
        //删除口味表数据
        dishFlavorMapper.deleteByDishIds(ids);
    }
}
