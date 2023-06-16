package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.mapper.SystemNoticeRecordMapper;
import org.example.admin.service.SystemNoticeRecordService;
import org.example.common.entity.SystemNoticeRecord;
import org.springframework.stereotype.Service;

@Service
public class SystemNoticeRecordServiceImpl extends ServiceImpl<SystemNoticeRecordMapper, SystemNoticeRecord> implements SystemNoticeRecordService {
}
