package com.example.app;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
// import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
// import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


class Weather {
  public static void main(String args[]) {}

  @ParameterizedTest
  @CsvFileSource(resources = "/Weather.csv", numLinesToSkip = 1)
  void openWeather(String ward, String area) {
    // String area = System.getProperty("area");

    Configuration.browser = WebDriverRunner.CHROME;
    Configuration.screenshots = false; // 失敗時にスクリーンショットを撮る
    Configuration.savePageSource = false; // スクリーショット取得時にHTMLファイルも保存する
    Configuration.headless = true; // CircleCIで実行する場合はヘッドレスで実行

    // Yahoo天気ページに遷移
    open("https://weather.yahoo.co.jp/weather/");

    $("#searchText").setValue(ward);
    $("#yjw_button_search").click();
    $x("//*[@id='rsltmuni']/div[2]/table/tbody/tr/td/a").shouldHave(text(area));
    $x("//a[text()= '" + ward + "']").click();

    close();

  }

}
