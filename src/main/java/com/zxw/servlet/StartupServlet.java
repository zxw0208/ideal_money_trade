package com.zxw.servlet;

import com.zxw.utils.RuntimeProperties;
import com.zxw.utils.SpringContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created with IntelliJ IDEA.
 * User: 昕炜
 * Date: 13-4-17
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class StartupServlet extends DispatcherServlet {

    protected WebApplicationContext initWebApplicationContext() {
        WebApplicationContext ac = super.initWebApplicationContext();
        SpringContext.setApplicationContext(ac);

        RuntimeProperties.setProperty(RuntimeProperties.LOGIN_PAGE_NUM, "5");
        RuntimeProperties.setProperty(RuntimeProperties.PROCESS_PAGE_NUM, "3");
        return ac;
    }

}
