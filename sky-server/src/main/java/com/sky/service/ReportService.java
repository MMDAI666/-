package com.sky.service;

import com.sky.vo.TurnoverReportVO;
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
}
