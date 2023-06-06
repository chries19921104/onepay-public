package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.SystemWithdrawalOrderDto;
import org.example.common.entity.SystemWithdrawalOrder;
import org.example.admin.vo.SystemWithdrawalOrderVo;

public interface SystemWithdrawalOrderService extends IService<SystemWithdrawalOrder> {

    /**
     * 通过条件搜索代付记录
     * @param systemWithdrawalOrderDto
     * @return
     */
    Page<SystemWithdrawalOrderVo> searchByCondition(SystemWithdrawalOrderDto systemWithdrawalOrderDto);

    /**
     * 汇出报表文件下载，并返回文件名
     * @param systemWithdrawalOrderDto
     * @return
     */
    String download(SystemWithdrawalOrderDto systemWithdrawalOrderDto);
}
