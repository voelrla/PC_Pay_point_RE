package PC_paytest.PC_Credit_card;

import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.TakeScreenshot;
import com.codeborne.selenide.commands.TakeScreenshotAsImage;

public class AppTest {
	public static WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverRunner.setWebDriver(driver);

		// 브라우저 오픈
		open("http://test.wemakeprice.com/");

		Cookie setGM = new Cookie("setGM",
				"046899cdd0f42e0c44f3426cc7f57a43be82ca8db71abac6f8e25f4b4c8a46d504a3abaeb1276d45b7300472843e102db4ac260d5b52e0d539636edea6f9a67947f35bddfc23e2b470b50b8effffe912346c6f4b09637a197d47a14b03965232c42887aa7433f5d2861d12800ba0ccd6658ad87be6538da1a12f753574daf24299933dc63b19c9a68f604bfa8251765811b071cde32302e6877fbc7418a2f16ae0e0219e4f856307ee00309548129d448fbe1544ab87acd0d21ad4d328cb21b75f9d965f5ac502ff96bfb15430a0ffa5");

		driver.manage().addCookie(setGM);
	}

	@Test
	public void test() {
//		open("http://test.wemakeprice.com/deal/adeal/1617166");    //0원 딜

		try {
			open("http://test.wemakeprice.com/deal/adeal/1618322");
			$(By.xpath("//*[@id=\"contents\"]/div[2]/div[1]/div/div[2]/div[1]/div[6]/a[2]")).click();
		} catch (Exception e) {
			System.out.println("브라우저 오픈 실패 다시 오픈" + e);
			open("http://test.wemakeprice.com/deal/adeal/1618480");
			$(By.xpath("//*[@id=\"contents\"]/div[2]/div[1]/div/div[2]/div[1]/div[6]/a[2]")).click();
		}

		// 전화번호 입력
		$(By.id("mobile2")).sendKeys("3381");
		$(By.id("mobile3")).sendKeys("3358");

		// 포인트 결제
		$(By.id("wmpPoint")).click();
		$(By.id("use_point_password")).sendKeys("qwer1234");

		// 약관공의 후 결제
		$(By.id("policy_agree_all")).click();
		$(By.id("pay_submit_btn")).click();

		try {
			$(By.xpath("//button[@onclick=\"location.href='/mypage/buylist'\"]")).getText(); // 버튼이 있는지 체크
			screenshot("pay");
		} catch (Exception e) {
			System.out.println("구매목록 확인 버튼이 없습니다.(구매실패 또는 완료화면 비노출)" + e);
			screenshot("pay");
		}
	}

	@AfterMethod
	public void end() {

	}

}