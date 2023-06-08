package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.WithdrawalOrderDto;
import org.example.common.entity.SystemWithdrawalOrder;
import org.example.admin.vo.WithdrawalOrderVo;

import java.util.List;

public interface SystemWithdrawalOrderService extends IService<SystemWithdrawalOrder> {

    /**
     * 通过条件搜索代付记录
     * @param withdrawalOrderDto
     * @return
     */
    List<WithdrawalOrderVo> searchByCondition(WithdrawalOrderDto withdrawalOrderDto);

    /**
     * 汇出报表文件下载，并返回文件名
     * @param withdrawalOrderDto
     * @return
     */
    String download(WithdrawalOrderDto withdrawalOrderDto);

    /**
     * 获取条件搜索后的代付记录总条数
     * @param withdrawalOrderDto
     * @return
     */
    Integer getTotal(WithdrawalOrderDto withdrawalOrderDto);

    /**
     * 详情页面数据之一，通过代付id查询返回
     * @param foId
     * @return
     */
    List<WithdrawalOrderVo> getWithdrawalOrderVoByFoId(Long foId);

}
