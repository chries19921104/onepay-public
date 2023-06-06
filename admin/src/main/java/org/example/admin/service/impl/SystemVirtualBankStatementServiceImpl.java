package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.mapper.SystemVirtualBankStatementMapper;
import org.example.admin.service.SystemVirtualBankStatementService;
import org.example.common.entity.SystemVirtualBankStatement;
import org.springframework.stereotype.Service;

@Service
public class SystemVirtualBankStatementServiceImpl extends ServiceImpl<SystemVirtualBankStatementMapper, SystemVirtualBankStatement> implements SystemVirtualBankStatementService {
}
