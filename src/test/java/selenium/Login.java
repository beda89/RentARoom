package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Christian on 31.12.2014.
 */
public class Login {

    //private static final String IP = "localhost";
    private static final String IP = "178.62.141.202";
    private static final int PORT = 8080;
    //private static final String BASE = "";
    private static final String BASE = "RentARoom";

    private final String[] secPaths = {"rooms", "customer/5486d46b60b22029798babab"};
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new HtmlUnitDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    private String baseUrl() {
        return "http://" + IP + ":" + PORT + (BASE.isEmpty() ? "" : "/" + BASE);
    }

    private void login(String user, String pass) {
        driver.get(baseUrl() + "/login");
        WebElement inputUser = driver.findElement(By.id("inputUsername"));
        driver.findElement(By.id("inputPassword")).sendKeys(pass);
        inputUser.sendKeys(user);
        inputUser.submit();
    }

    private WebElement findButtonByCaption(String caption) {
        try {
            return driver.findElement(By.xpath("//button[text()='" + caption + "']"));
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    private WebElement loginBtn() {
        return findButtonByCaption("Einloggen");
    }

    private WebElement logoutBtn() {
        return findButtonByCaption("Ausloggen");
    }

    @Test
    public void testRedirectToLogin() {
        for (String secPath : secPaths) {
            driver.get(baseUrl() + "/" + secPath);
            assertNotNull(loginBtn());
            assertNull(logoutBtn());
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        login("admin", "admin");
        assertEquals(baseUrl() + "/rooms", driver.getCurrentUrl());
        assertNotNull(logoutBtn());
        assertNull(loginBtn());
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        login("max", "mustermann");
        assertEquals(baseUrl() + "/login?error", driver.getCurrentUrl());
        assertNotNull(driver.findElement(By.className("alert-danger")));
    }

    @Test
    public void testLoginThenLogout() {
        login("admin", "admin");
        assertEquals(baseUrl() + "/rooms", driver.getCurrentUrl());
        assertNotNull(logoutBtn());
        assertNull(loginBtn());

        logoutBtn().submit();
        assertEquals(baseUrl() + "/login?logout", driver.getCurrentUrl());
        WebElement infoAlert = driver.findElement(By.className("alert-info"));
        assertNotNull(infoAlert);
        assertTrue(infoAlert.getText().contains("Erfolgreich"));

        testRedirectToLogin();
    }

    @Test
    public void testLoginThenAccessRoot() {
        login("admin", "admin");
        driver.get(baseUrl() + "/");
        assertEquals(baseUrl() + "/rooms", driver.getCurrentUrl());
        assertNotNull(logoutBtn());
        assertNull(loginBtn());

    }

    @Test
    public void testLoginPageInaccessibleAfterLogin() {
        login("admin", "admin");
        driver.get(baseUrl() + "/login");
        assertEquals(baseUrl() + "/rooms", driver.getCurrentUrl());
    }

}
