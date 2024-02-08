package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ReportService {
    /**
     * 获得指定时间内的营业额数据
     * @param begin
     * @param end
     * @return {@link TurnoverReportVO}
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 获得指定时间内的用户数据
     * @param begin
     * @param end
     * @return {@link UserReportVO}
     */
    UserReportVO getUSerStatistics(LocalDate begin, LocalDate end);

    /**
     * 订单统计
     * @param begin
     * @param end
     * @return {@link OrderReportVO}
     */
    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);

    /**
     * 销量排名Top10
     * @param begin
     * @param end
     * @return {@link SalesTop10ReportVO}
     */
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);
}
