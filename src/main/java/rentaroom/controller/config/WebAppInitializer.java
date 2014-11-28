package rentaroom.controller.config;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 28/11/14
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import sun.security.krb5.Config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class);

        ServletRegistration.Dynamic registration =
                container.addServlet("dispatcher", new DispatcherServlet(ctx));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

}
