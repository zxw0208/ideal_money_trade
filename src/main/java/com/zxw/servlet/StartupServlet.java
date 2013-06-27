package com.zxw.servlet;

import com.zxw.service.CryptsyService;
import com.zxw.service.impl.CryptsyServiceImpl;
import com.zxw.utils.RuntimeProperties;
import com.zxw.utils.SpringContext;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 昕炜
 * Date: 13-4-17
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class StartupServlet extends DispatcherServlet {

    Logger logger = Logger.getLogger(this.getClass());

    protected WebApplicationContext initWebApplicationContext() {
        WebApplicationContext ac = super.initWebApplicationContext();
        SpringContext.setApplicationContext(ac);

        /*CryptsyService cs = ac.getBean(CryptsyServiceImpl.class);
        cs.updateMarket();*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                already();
            }
        });
        thread.start();

        return ac;
    }

    public void already(){
        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet post = new HttpGet("http://ip138.com/");

        try {
            Runtime.getRuntime().exec("cmd.exe /c taskkill /F /IM PhDDNS.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            HttpResponse resp = null;
            try {
                resp = httpClient.execute(post);
                int status = resp.getStatusLine().getStatusCode();
                if(status == 200){
                    logger.info("已联网。。。");
                    Process child = Runtime.getRuntime().exec("cmd.exe /c " + RuntimeProperties.getProperty("_HSK_LOCATION"));
                    Thread.sleep(30000);
                    try{
                        isOpenHsk();
                        logger.info("花生壳打开成功。。。。");
                    }catch(IOException ex){
                        ex.printStackTrace();
                        Runtime.getRuntime().exec("cmd.exe /c taskkill /F /IM PhDDNS.exe");
                        logger.info("花生壳未连接成功。。。。");
                        throw new Exception(ex);
                    }

                    //child.destroy();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(resp != null){
                        resp.getEntity().getContent().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void isOpenHsk() throws IOException {
        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet post = new HttpGet("http://zxw02081.vicp.cc:10082/trade");
        HttpResponse resp = httpClient.execute(post);
        int status = resp.getStatusLine().getStatusCode();
        if(status == 200){
            System.out.println("...");
        }else{
            System.out.println(status);
        }
    }

}
