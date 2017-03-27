package com.zzy.appium.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.zzy.appiu.page.HangMeiPage;
import com.zzy.appium.init.*;
import com.zzy.appium.utils.*;

public class HangMei {
	AndroidDriverBase driver;

	private String apkpath = null;
	private String appPackage = null;
	private String udidString = null;

	@Test
	public void f() {
		HangMeiPage hangmei = new HangMeiPage(driver);
		hangmei.test();
	}
	
	@Parameters({"udid","port"})
	@BeforeClass
	public void beforeClass(String udid,String port){
		System.out.println("读到的udid是：" + udid + "读到的port是：" + port);
		ProUtil p=new ProUtil(CrazyPath.globalPath);
		String server=p.getPro("server");
		String capsPath=CrazyPath.capsPath;
//		String input="com.example.android.softkeyboard/.SoftKeyboard";
		try {
			driver=new AndroidDriverBase(server, port, capsPath, udid);
			driver.implicitlyWaitDefault();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@AfterClass
	public void afterClass(){
//		driver.resetApp();
		driver.quit();
	}
//	@AfterMethod
//	public void restart(){
//		String appPackge=(String)driver.getCapabilities().getCapability(AndroidMobileCapabilityType.APP_PACKAGE);
//		String appActivity=(String)driver.getCapabilities().getCapability(AndroidMobileCapabilityType.APP_ACTIVITY);
//		driver.startActivity(appPackge,appActivity);
//	}
	
}
