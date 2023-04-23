package com.example.tvdbuitest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
  MainPage mainPage = new MainPage();

  @BeforeAll
  public static void setUpAll() {
    Configuration.browserSize = "1280x800";
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @BeforeEach
  public void setUp() {
    open("http://thetvdb.com/");
  }

  @Test
  public void search() {
    assertTrue($(By.xpath("/html/body/div[4]/div[2]/div/p")).text().equals("You've found the most accurate source for TV and film. Our information comes from fans like you, so create a free account and help your favorite shows and movies. Everything added is shared with many other sites, mobile apps, and devices."));
  }

  @Test
  public void toolsMenu() {
    mainPage.toolsMenu.click();

    $("div[data-test='main-submenu']").shouldBe(visible);
  }

  @Test
  public void navigationToAllTools() {
    mainPage.seeDeveloperToolsButton.click();
    mainPage.findYourToolsButton.click();

    $("#products-page").shouldBe(visible);

    assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
  }
}
