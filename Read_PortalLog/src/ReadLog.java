import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.cf.Threshold;

import com.google.gson.Gson;

public class ReadLog {
	static Log logger = Log.getLogger(ReadLog.class);
	public static void main(String[] args) {
		new ReadLog().getInfo();
	}

	public ReadLog() {

	}

	public Boolean IsFileExit() {
		String filepath = "/sdcard/ihangmei/wangfan_write.log";
		String findcmd = "adb shell ls /sdcard/ihangmei/wangfan_write.log";
		Process process = null;
		BufferedReader input = null;
		System.out.println("执行CMD命令 :" + findcmd);
		try {
			process = Runtime.getRuntime().exec(findcmd);
			input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				if (line.equals(filepath)) {
					return true;
				} else {
					process = Runtime.getRuntime().exec(findcmd);
					input = new BufferedReader(new InputStreamReader(
							process.getInputStream()));
				}
			}
			process.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	public List<String> getInfo() {
		List<String> resultList = new ArrayList<String>();
		String writelogPath = "adb shell cat /sdcard/ihangmei/wangfan_write.log";
		Process process = null;
		// System.out.println("wangfan_write.log :" + writelogPath);
		try {
			if (IsFileExit()) {
				process = Runtime.getRuntime().exec(writelogPath);
			}
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				if (!line.equals("")) {
					System.out.println("write.log :" + line);
					// JSONObject ooObject = JSONObject.fromObject(line);
					// System.out.println(ooObject.get("hitID"));
					Gson gson = new Gson();
					HashMap<String, String> map = gson.fromJson(line,
							HashMap.class);
					// System.out.println("hitID: " + map.get("hitID")
					// + ", type: " + map.get("type"));
					// this.hitID = map.get("hitID");
					// this.type = Integer.valueOf(map.get("type"));
					String result = map.get("hitID") + "," + map.get("type");
					resultList.add(result);
					System.out.println("result:" + result);
				}
			}
			process.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return resultList;
	}
}
