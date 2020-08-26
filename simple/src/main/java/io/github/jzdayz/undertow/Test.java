package io.github.jzdayz.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import lombok.extern.apachecommons.CommonsLog;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@CommonsLog
public class Test {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1, 1, 1L, TimeUnit.HOURS, new ArrayBlockingQueue<>(100)
    );

    private static Undertow undertow = null;

    public static class MyServlet implements Servlet {

        @Override
        public void init(ServletConfig config) throws ServletException {

        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        private AtomicInteger i = new AtomicInteger(0);

        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            AsyncContext asyncContext = null;
            if (i.get() == 0) {
                asyncContext = req.startAsync();
            }
            log.info("进入" + i.get() + " " + req.getRemotePort());
            i.addAndGet(1);
            if (i.get() == 1) {
                AsyncContext finalAsyncContext = asyncContext;
                THREAD_POOL_EXECUTOR.submit(() -> {
                    try {
                        log.info("开始睡眠");
                        TimeUnit.SECONDS.sleep(2L);
                        log.info("结束睡眠");
                        finalAsyncContext.dispatch();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            {
                log.info("1111");
                res.getWriter().println("ME");
                i.set(0);
                res.flushBuffer();
                req.getAsyncContext().complete();
                undertow.stop();
            }

        }

        @Override
        public String getServletInfo() {
            return null;
        }

        @Override
        public void destroy() {

        }
    }

    public static void main(final String[] args) throws Exception {
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(Test.class.getClassLoader())
                .setContextPath("/myapp")
                .setDeploymentName("test.war")
                .addServlets(
                        Servlets.servlet("MyServlet", MyServlet.class)
                                .addInitParam("message", "Hello World")
                                .addMapping("/*").setAsyncSupported(true));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
                .addPrefixPath("/myapp", manager.start());

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .build();
        server.start();
        undertow = server;
    }
}
