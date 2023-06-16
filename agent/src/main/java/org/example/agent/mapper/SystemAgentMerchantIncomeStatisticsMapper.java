package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.agent.po.DailyReportPo;
import org.example.agent.po.SummaryReportDataPo;
import org.example.agent.vo.DailyReportVo;
import org.example.common.entity.SystemAgentMerchantIncomeStatistics;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SystemAgentMerchantIncomeStatisticsMapper extends BaseMapper<SystemAgentMerchantIncomeStatistics> {

    public List<DailyReportPo> getDailyReportList(@Param("miList")List<Long> merchantIdList,
                                                  @Param("start") Timestamp start,
                                                  @Param("end") Timestamp end);

    public List<SummaryReportDataPo> getSummaryReportDataPoList(@Param("miList")List<Long> merchantIdList ,
                                                                @Param("year") Integer year,
                                                                @Param("month") Integer month,
                                                                @Param("start") Integer start_record,
                                                                @Param("size") Integer size,
                                                                @Param("currency") String currency);

    public Integer getTotalNumberOfPages(@Param("miList")List<Long> merchantIdList ,
                                         @Param("year") Integer year,
                                         @Param("month") Integer month,
                                         @Param("currency") String currency);
}
