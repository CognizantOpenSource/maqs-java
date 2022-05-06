/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

public class WebScrapper {

  private WebScrapper() {
  }

  public static void scrapWebPage(WebDriver webDriver, String fileName,
      String destinationOfFile, boolean lazyElement) throws IOException {
    // Here we create a document object and use JSoup to fetch the website
    Document doc = Jsoup.connect(webDriver.getCurrentUrl()).get();

    String stringBuilder = concatClassStart(fileName, webDriver.getCurrentUrl())
        + concatElements(doc.select("body input[type=\"checkbox\"]"), "Checkbox")
        + concatElements(doc.select("body input[type=\"radio\"]"), "Radio Button")
        + concatElements(doc.select("body input[type=\"text\"]"), "Text Box")
        + concatElements(doc.select("body button"), "Button")
        + concatElements(doc.select("body a[href]"), "Link")
        + concatElements(doc.select("body table"), "Table")
        + concatClassMethods(fileName);

    String string = stringBuilder;

//    FileUtils.writeStringToFile(new File(destinationOfFile + File.separator + fileName + ".java"),
//        stringBuilder, StandardCharsets.UTF_8);
  }

  private static String concatClassStart(String fileName, String currentUrl) {
    return System.lineSeparator()
        + "// TODO: write in package here" + System.lineSeparator()
        + "package" + System.lineSeparator()
        + System.lineSeparator()
        + "import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;" + System.lineSeparator()
        + "import com.magenic.jmaqs.selenium.SeleniumConfig;" + System.lineSeparator()
        + "import com.magenic.jmaqs.selenium.SeleniumTestObject;" + System.lineSeparator()
        + "import com.magenic.jmaqs.selenium.factories.UIWaitFactory;" + System.lineSeparator()
        + "import org.openqa.selenium.By;" + System.lineSeparator()
        + System.lineSeparator()
        + "/**" + System.lineSeparator()
        + "* The " + fileName + " page model." + System.lineSeparator()
        + "*/" + System.lineSeparator()
        + "public class " + fileName + " extends BaseSeleniumPageModel {" + System.lineSeparator()
        + System.lineSeparator()
        + "/**" + System.lineSeparator()
        + "* The URL for the page." + System.lineSeparator()
        + "*/" + System.lineSeparator()
        + "private static final String PAGE_URL = \"" + currentUrl + "\";" + System.lineSeparator()
        + System.lineSeparator()
        + "/**" + System.lineSeparator()
        + "* The URL for the page." + System.lineSeparator()
        + "*/" + System.lineSeparator()
        + "private static final String CONFIG_PAGE_URL = SeleniumConfig.getWebSiteBase();" + System.lineSeparator()
        + System.lineSeparator();
  }

  private static String concatElements(Elements checkBoxes, String elementType) {
    StringBuilder checkBoxesString = new StringBuilder();
    int num = 2;

    for (Element element : checkBoxes) {
      String selector = element.cssSelector();
      String commentName = getElementName(element).replace("-", " ");
      String selectorName = commentName.replace(" ", "_");

      if (checkBoxesString.toString().contains(commentName)) {
        commentName = commentName + " " + num;
      }

      if (checkBoxesString.toString().contains(selectorName)) {
        selectorName = selectorName + num++;
      }

      checkBoxesString.append("/**").append(System.lineSeparator())
          .append("* The ").append(commentName).append(" ").append(elementType)
          .append(" Selector.").append(System.lineSeparator())
          .append("*/").append(System.lineSeparator()).append("private static final By ")
          .append(selectorName.toUpperCase()).append(" = By.cssSelector(\"")
          .append(selector).append("\");").append(System.lineSeparator())
          .append(System.lineSeparator());
    }

    return checkBoxesString.toString();
  }

  private static String getElementName(Element element) {
    if (!element.id().isBlank()) {
      return element.id();
    } else if (!element.attr("name").isBlank()) {
      return element.attr("name");
    } else if (!element.val().isBlank()) {
      return element.val();
    } else if (!element.text().isBlank()) {
      return element.text();
    } else {
      return element.className();
    }
  }

  private static String concatClassMethods(String fileName) {
    return "/**" + System.lineSeparator()
            + "* Instantiates a new " + fileName + " page model." + System.lineSeparator()
            + "*" + System.lineSeparator()
            + "* @param testObject the test object" + System.lineSeparator()
            + "*/" + System.lineSeparator()
            + "public " + fileName + "(SeleniumTestObject testObject) {" + System.lineSeparator()
            + "super(testObject);" + System.lineSeparator()
            + "}" + System.lineSeparator()
            + System.lineSeparator()
            + "/**" + System.lineSeparator()
            + "* Check if the " + fileName + " page has been loaded" + System.lineSeparator()
            + "*" + System.lineSeparator()
            + "* @return True if the page was loaded" + System.lineSeparator()
            + "*/" + System.lineSeparator()
            + "public boolean isPageLoaded() {" + System.lineSeparator()
            + "// TODO: replace WELCOME_MESSAGE with a legitimate By element" + System.lineSeparator()
            + "return UIWaitFactory.getWaitDriver(getWebDriver()).waitForVisibleElement(WELCOME_MESSAGE)"
            + ".isDisplayed();" + System.lineSeparator()
            + "}" + System.lineSeparator()
            + "}";
  }
}
