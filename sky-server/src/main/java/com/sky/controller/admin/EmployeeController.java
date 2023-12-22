package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeEditPasswordDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
//@Tag(name = "员工相关接口")
//@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
//    @Operation(summary = "登录接口")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
//    @Operation(summary = "退出接口")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return {@link Result}
     */
    @PostMapping
//    @Operation(summary = "新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO)
    {
        log.info("增加员工：{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/page")
//    @Operation(summary = "分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO)
    {
        log.info("分页查询：{}",employeePageQueryDTO);
        PageResult pageResult=employeeService.page(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     * @return {@link Result}
     */
    @PostMapping("status/{status}")
//    @Operation(summary = "启用禁用员工账号")
    public Result startOrStop(@PathVariable Integer status,Long id)
    {
        log.info("启用启用禁用员工账号：{}，{}",status,id);
        employeeService.startOrStop(status,id);
        return  Result.success();
    }

    /**
     * 根据Id查询
     * @param id
     * @return {@link Result}<{@link Employee}>
     */
    @GetMapping("/{id}")
    public Result<Employee> getbyId(@PathVariable Long id)
    {
        log.info("查询id为{}的员工信息",id);
        return Result.success(employeeService.getById(id));
    }

    /**
     * 编辑员工信息
     *
     * @return {@link Result}
     */
    @PutMapping
    public Result update(@RequestBody EmployeeDTO employeeDTO)
    {
        log.info("修改员工信息为：{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     * 修改密码
     * @return {@link Result}
     */
    @PutMapping("/editPassword")
    public Result editPassword(@RequestBody EmployeeEditPasswordDTO editPasswordDTO)
    {
        editPasswordDTO.setId(BaseContext.getCurrentId());
        log.info("修改{}的密码，旧密码：{}，新密码{}",editPasswordDTO.getId(),editPasswordDTO.getOldPassword(),editPasswordDTO.getNewPassword());
        employeeService.editPassword(editPasswordDTO);
        return Result.success();
    }
}
