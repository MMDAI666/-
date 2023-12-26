package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品相关接口
 * @author 萌萌哒AI
 * @date 2023/12/26
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品信息
     * @param dto
     * @return {@link Result}
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dto)
    {
        log.info("新增菜品信息：{}。。",dto);
        dishService.saveWithDishFlavor(dto);
        return Result.success();
    }

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO)
    {
        log.info("查询第{}页，每页{}",dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        PageResult pageResult=dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除菜品数据
     * 要求菜品停售且不被套餐关联
     * @param ids
     * @return {@link Result}
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids)
    {
        log.info("删除{}",ids);
        dishService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询菜品数据及关联的口味数据
     * @param id
     * @return {@link Result}<{@link DishVO}>
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id)
    {
        log.info("查询id为{}的菜品信息",id);
        DishVO dishVO=dishService.getByIdwithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品信息
     * @param dto
     * @return {@link Result}
     */
    @PutMapping
    public Result update(@RequestBody DishDTO dto)
    {
        log.info("修改菜品信息为：{}",dto);
        dishService.updateWithFlavor(dto);
        return Result.success();
    }

    /**
     * 启售停售菜品
     * @param status
     * @param id
     * @return {@link Result}
     */
    @PostMapping("status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id)
    {
        log.info("修改{}状态为{}",id,status);
        dishService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据分类id查询启售中的菜品
     * @param categoryId
     * @return {@link Result}<{@link List}<{@link Dish}>>
     */
    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId)
    {
        log.info("查询分类id为{}的菜品",categoryId);
        List<Dish> list=dishService.list(categoryId);
        return  Result.success(list);
    }
}
