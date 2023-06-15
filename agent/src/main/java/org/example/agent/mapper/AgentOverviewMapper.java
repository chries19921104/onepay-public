package org.example.agent.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.agent.po.SuccessfulPo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface AgentOverviewMapper {
    public SuccessfulPo getSuccessfulFi(@Param("modelList") List<Integer> modeList,
                                          @Param("currency") String currency,
                                          @Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end,
                                          @Param("agent_id")Long agent_id);


    public SuccessfulPo getSuccessFulTPIQr(@Param("currency") String currency,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end,
                                           @Param("agent_id")Long agent_id);

    public SuccessfulPo getSuccessfulFo(@Param("modelList") List<Integer> modeList,
                                        @Param("currency") String currency,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end,
                                        @Param("agent_id")Long agent_id);

    public Integer countAllSuccessfulFo(@Param("currency") String currency,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end,
                                        @Param("agent_id")Long agent_id);

//    public Integer getSuccessfulFoAmount(@Param("modelList") List<Integer> modeList,
//                                         @Param("currency") String currency,
//                                         @Param("start") LocalDateTime start,
//                                         @Param("end") LocalDateTime end,
//                                         @Param("agent_id")Long agent_id);
}
