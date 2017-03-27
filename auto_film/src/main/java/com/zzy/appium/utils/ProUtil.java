package com.zzy.appium.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class ProUtil {
	private String filePath;
	private Properties prop;

	/**
	 * 构造方法
	 * 
	 * @param filePath
	 */
	public ProUtil(String filePath) {
		this.filePath = filePath;
		this.prop = readProperties();
	}

	/**
	 * 读取资源文件,并处理中文乱码
	 * 
	 * @return
	 */
	private Properties readProperties() {
		Properties properties = new Properties();
		try {
			// 修改的原因是，打成jar包后，无法读取文件
			InputStream inputStream = this.getClass().getResourceAsStream(
					filePath);
			// InputStream inputStream = new FileInputStream(filePath);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			properties.load(bf);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 获取某项文本内容
	 * 
	 * @param key
	 * @return
	 */
	public String getPro(String key) {
		if (prop.containsKey(key)) {
			return prop.getProperty(key);
		} else {
			System.out.println("获取的键不存在");
			return "";
		}
	}

	public void setProp(String key, String value) {
		if (prop == null) {
			prop = new Properties();
		}
		try {
			OutputStream outputStream = new FileOutputStream(filePath);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					outputStream, "utf-8"));
			prop.setProperty(key, value);
			prop.store(bw, key + " value");
			bw.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
