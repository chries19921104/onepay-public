package org.example.agent.conf;

import lombok.extern.slf4j.Slf4j;
import org.example.common.base.CommResp;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class WebErrorController implements ErrorController {

    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping({"/error"})
    public CommResp handleError(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        String contextPath = request.getRequestURI();
        log.info(contextPath);
        return CommResp.FAIL("error > " + code);
    }


}
