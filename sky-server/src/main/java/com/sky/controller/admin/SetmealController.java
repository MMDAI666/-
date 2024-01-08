package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐相关接口
 * @author 萌萌哒AI
 * @date 2023/12/27
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    /**
     * 新增套餐
     * @param setmealDTO
     * @return {@link Result}
     */
    @PostMapping
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO)
    {
        log.info("新增套餐：{}",setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }


    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO)
    {
        log.info("查询第{}页，每页{}条",setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        PageResult pageResult=setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return {@link Result}
     */
    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam List<Long> ids)
    {
        log.info("删除{}",ids);
        setmealService.deleteBarch(ids);
        return Result.success();
    }

    /**
     * 修改套餐信息
     * @return {@link Result}
     */
    @PutMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO)
    {
        log.info("修改套餐信息{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 根据id查询套餐，用于页面回显
     * @param id
     * @return {@link Result}<{@link SetmealVO}>
     */
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id)
    {
        log.info("查询id为{}的套餐",id);
        SetmealVO setmealVO= setmealService.getById(id);
        return Result.success(setmealVO);
    }

    /**
     * 套餐停售启售
     * @param status
     * @param id
     * @return {@link Result}
     */
    @PostMapping("/status/{status}")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result startOrStop(@PathVariable Integer status, Long id) {
        setmealService.startOrStop(status, id);
        return Result.success();
    }
}
