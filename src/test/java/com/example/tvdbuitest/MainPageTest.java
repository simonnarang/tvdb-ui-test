package com.example.tvdbuitest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {

  @BeforeAll
  public static void setUpAll() {
    Configuration.browserSize = "1280x800";
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  // TC: when visiting homepage, we should see a welcome message.
  @Test
  public void testHomepage() {
    open("http://thetvdb.com/");
    assertTrue($(By.xpath("/html/body/div[4]/div[2]/div/p")).text().equals("You've found the most accurate source for TV and film. Our information comes from fans like you, so create a free account and help your favorite shows and movies. Everything added is shared with many other sites, mobile apps, and devices."));
  }

  // TC: login with an empty credential. We should expect to see two popup error messages, one for each field.
  // Fault revealed: each error message is displayed twice, leading to a confusing user experience./
  @Test
  public void testLogin() {
    open("http://thetvdb.com/auth/login");
    $(By.xpath("/html/body/div[4]/div[4]/div/form/button")).click();
    assertEquals( 2, $((By.xpath("/html"))).findAll(By.className("alert-fade")).size());
  }

  // TC: when visiting contributions, prompt asking for contributions should be displayed
  @Test
  public void testContributions() {
    open("http://thetvdb.com/points");
    $(By.xpath("/html/body/div[5]/div/div/div/div[1]/div[3]/a/i")).click();
    $(By.xpath("/html/body/div[5]/div/div/div/div[2]/div[3]/a[2]/i")).click();
    $(By.xpath("/html/body/div[5]/div/div/div/div[3]/div[3]/a[2]/strong")).click();
    $(By.xpath("/html/body/div[4]/div[3]/div/div/div/form/div[1]/input")).sendKeys("simonnarang1@gmail.com");
    $(By.xpath("/html/body/div[4]/div[3]/div/div/div/form/div[2]/input")).sendKeys("pw1234");
    $(By.xpath("/html/body/div[4]/div[3]/div/div/div/form/button")).click();
    assertEquals("Search IMDB for the ID or URL", $(By.xpath("/html/body/div[4]/div[2]/div/div/div/div[1]/div/div/div/div[3]/form/div[1]/p/a[1]")).text());

  }

  // TC: when visitng discover page, website should show what is on today
  @Test
  public void testDiscover() {
    open("http://thetvdb.com/on-today");
    assertEquals("Airing Today", $(By.xpath("/html/body/div[4]/div[2]/h1")).text());
  }

  // TC: when visiting the search page, we should see some search instructions
  @Test
  public void testSearch() {
    open("https://thetvdb.com/search?query=tvdb");
    assertEquals("The Kennedy Center Honors", $(By.xpath("/html/body/div[4]/div[2]/div[1]/div[3]/div/div/ol/li[1]/div/div/h3/a")).text());
  }
}


