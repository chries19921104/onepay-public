package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.utils.I18nUtil;
import org.example.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@Api(tags = "国际化测试")
@RestController
@RequestMapping(value = "/test/i18n")
public class I18nTest {

    @ApiOperation(value = "国际化测试")
    @GetMapping("/test")
    public R testInfo() {
        Map<String, String> map = new I18nUtil().i18n();
        return R.okHasData(map);
    }
}
