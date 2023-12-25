package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
