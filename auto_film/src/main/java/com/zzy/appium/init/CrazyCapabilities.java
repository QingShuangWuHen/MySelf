package com.zzy.appium.init;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.zzy.appium.utils.*;

;

public class CrazyCapabilities {
	public DesiredCapabilities initCaps(String capsPath, String udid) {
		ProUtil p = new ProUtil(capsPath);
		// File apkPath = new File(p.getPro("apkpath"));
		DesiredCapabilities caps = new DesiredCapabilities();
		try {
			// caps.setCapability(MobileCapabilityType.APP,
			// apkPath.getAbsolutePath());
			caps.setCapability(AndroidCapabilityType.DEVICE_NAME,
					p.getPro(AndroidCapabilityType.DEVICE_NAME));
			caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
					p.getPro("appPackage"));
			caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					p.getPro("appActivity"));
			// caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,
			// p.getPro(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY));
			caps.setCapability(AndroidMobileCapabilityType.NO_SIGN,
					p.getPro(AndroidCapabilityType.NO_SIGN));
			caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,
					p.getPro(AndroidCapabilityType.UNICODE_KEY_BOARD));
			caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD,
					p.getPro(AndroidCapabilityType.RESET_KEY_BOARD));
			caps.setCapability(AndroidCapabilityType.UDID, udid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caps;
	}

	public DesiredCapabilities initCapsWait(String capsPath, String udid) {
		ProUtil p = new ProUtil(capsPath);
		File apkPath = new File(p.getPro("apkpath"));
		DesiredCapabilities caps = new DesiredCapabilities();
		try {
			// caps.setCapability(MobileCapabilityType.APP,
			// apkPath.getAbsolutePath());
			caps.setCapability(AndroidCapabilityType.DEVICE_NAME,
					p.getPro(AndroidCapabilityType.DEVICE_NAME));
			caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
					p.getPro("appPackage"));
			caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					p.getPro("appActivity"));
			// caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,
			// p.getPro(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY));
			caps.setCapability(AndroidMobileCapabilityType.NO_SIGN,
					p.getPro(AndroidCapabilityType.NO_SIGN));
			caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,
					p.getPro(AndroidCapabilityType.UNICODE_KEY_BOARD));
			caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD,
					p.getPro(AndroidCapabilityType.RESET_KEY_BOARD));
			caps.setCapability(AndroidCapabilityType.UDID, udid);
			caps.setCapability(AndroidCapabilityType.NEW_COMMAND_TIMEOUT,
					p.getPro(AndroidCapabilityType.NEW_COMMAND_TIMEOUT));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caps;
	}
}
