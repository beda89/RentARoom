package ControllerTests;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.config.MongoConfig;
import rentaroom.controller.LoginController;

import java.security.Principal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 12/01/15
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, LoginController.class})
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void testLoginNoError() {
        ModelAndView model = loginController.login("", "", new Principal() {
            public String getName() {
                return "admin";
            }
        });
        assertEquals("redirect:/rooms", model.getViewName());
    }

    @Test
    public void testLoginErrorNoPrincipal() {
        ModelAndView model = loginController.login("", "error", null);
        assertTrue(model.getModel().containsKey("error"));
        assertEquals("Invalid username and password!", model.getModel().get("error"));
    }

    @Test
    public void testLoginLogoutNoErrorNoPrincipal() {
        ModelAndView model = loginController.login("logout", "", null);
        assertTrue(model.getModel().containsKey("msg"));
        assertEquals("You've been logged out successfully.", model.getModel().get("msg"));
    }
}
