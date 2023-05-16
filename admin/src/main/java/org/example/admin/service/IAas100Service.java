package org.example.admin.service;

import org.example.common.base.CommResp;
import org.example.common.entity.Aas100;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.req.Aas100List;

/**
 * <p>
 * 爬虫服务器配置 服务类
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
public interface IAas100Service extends IService<Aas100> {
    CommResp pageData(Aas100List req);
}
