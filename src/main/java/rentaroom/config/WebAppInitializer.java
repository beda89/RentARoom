package rentaroom.config;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 28/11/14
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import rentaroom.config.security.SecurityConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class, MongoConfig.class};
    }

    //    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
