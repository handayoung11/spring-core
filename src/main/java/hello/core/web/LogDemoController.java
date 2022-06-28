package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @ResponseBody
    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        myLogger.setRequestURL(url);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
