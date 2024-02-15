package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 数据统计相关接口
 * @author 萌萌哒AI
 * @date 2024/02/07
 */
@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;
    /**
     * 获得指定时间内的营业额数据
     * @param begin
     * @param end
     * @return {@link Result}<{@link TurnoverReportVO}>
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    )
    {
        log.info("统计从{}到{}的营业额",begin,end);
        TurnoverReportVO turnoverReportVO = reportService.getTurnoverStatistics(begin, end);
        return Result.success(turnoverReportVO);
    }

    /**
     * 用户统计
     * @param begin
     * @param end
     * @return {@link Result}<{@link UserReportVO}>
     */
    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    )
    {
        log.info("统计从{}到{}的用户数",begin,end);
        UserReportVO userReportVO= reportService.getUSerStatistics(begin, end);
        return Result.success(userReportVO);
    }


    /**
     * 订单统计
     * @param begin
     * @param end
     * @return {@link Result}<{@link UserReportVO}>
     */
    @GetMapping("/ordersStatistics")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    )
    {
        log.info("统计从{}到{}的订单数",begin,end);
        OrderReportVO orderReportVO= reportService.getOrdersStatistics(begin, end);
        return Result.success(orderReportVO);
    }

    /**
     * 销量排名Top10
     * @param begin
     * @param end
     * @return {@link Result}<{@link UserReportVO}>
     */
    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> SalesTop10(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    )
    {
        log.info("统计从{}到{}的销量排名Top10",begin,end);
        SalesTop10ReportVO salesTop10ReportVO= reportService.getSalesTop10(begin, end);
        return Result.success(salesTop10ReportVO);
    }

    /**
     * 导出数据报表
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response)
    {
        reportService.exportBusinessData(response);
    }
}
