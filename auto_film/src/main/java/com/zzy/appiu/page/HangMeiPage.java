package com.zzy.appiu.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HangMeiPage {

	static AndroidDriver<AndroidElement> driver;

	public HangMeiPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void test() {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElementByName("影视").click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElementByXPath("//android.view.View[@content-desc='电影']").click();
//		driver.findElementByName("剧情").click();
	}
	public void test_hot(){
		//左右滑动Banner
		String banner = "//android.webkit.WebView/android.view.View[2]/android.view.View/android.view.View";
		List<AndroidElement> bannerList = driver.findElements(By.xpath(banner+"/android.widget.Image"));
		System.out.println(bannerList.size()+"bannerList.size()");
		List<String> stingList = new ArrayList<String>();
		for (int i = 0; i < bannerList.size(); i++) {
			System.out.println(bannerList.get(i).getAttribute("name"));
		}
	}
	
	public void swipe_3(){
		AndroidElement listElement = driver.findElementByXPath("//android.webkit.WebView/android.view.View[2]");
		int element_height = listElement.getSize().height;
		System.out.println(element_height+"高度");
		int element_y = listElement.getLocation().y;
		System.err.println(element_y+"中心点y");
		int appWidth = driver.manage().window().getSize().width;
		int appHeight = driver.manage().window().getSize().height;
		for (int i = 0; i < 2; i++) {
			driver.swipe(appWidth/2,appHeight/2, appWidth/2, element_y, 1000);
		}
	}

}
