import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Install_uninstall_test {

	private String apkpath = null;
	private String appPackage = null;
	private String udidString = null;
	private String apkString = null;
	private String capsString = "/config/caps.properties";
	private static List<String> udidList = null;
	private static List<String> apkList = null;

	public Install_uninstall_test() {
		// TODO Auto-generated constructor stub
		ProUtil p = new ProUtil(capsString);
		apkpath = p.getPro("apkpath");
		appPackage = p.getPro("appPackage");
		System.out.println("apkpath:" + apkpath);
		System.out.println("appPackage:" + appPackage);
		System.out.println("执行测试 安装卸载");
	}

	// public void getUdid() {
	// String installCmd = "adb devices";// PM指令不支持中文
	// Process process = null;
	// System.out.println("installCmd :" + installCmd);
	// try {
	// process = Runtime.getRuntime().exec(installCmd);
	// BufferedReader input = new BufferedReader(new InputStreamReader(
	// process.getInputStream()));
	// String line = null;
	// while ((line = input.readLine()) != null) {
	// if (line.endsWith("device")) {
	// udidString = line.substring(0,(line.length()-7));
	// System.out.println("获取设备udid:"+udidString);
	// }else if (line.equals("")) {
	// System.out.println("获取不到udid");
	// }
	// }
	// process.getOutputStream().close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	//
	// }
	//
	// }
	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}

	public static void getAllFileName(String path, ArrayList<String> fileName) {
		File file = new File(path);
		File[] files = file.listFiles();
		String[] names = file.list();
		if (names != null)
			fileName.addAll(Arrays.asList(names));
		for (File a : files) {
			if (a.isDirectory()) {
				getAllFileName(a.getAbsolutePath(), fileName);
			}
		}
	}

	public void getApkS() {
		File[] files = (new File(apkpath)).listFiles();
		for (File f : files)
			if (f.getName().endsWith(".apk")) {
				apkList.add(f.getName());
			}
		System.out.println("获取的APK：" + apkList);
	}

	public void getUdidS() {
		String installCmd = "adb devices";// PM指令不支持中文
		Process process = null;
		System.out.println("installCmd :" + installCmd);
		try {
			process = Runtime.getRuntime().exec(installCmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				if (line.endsWith("device")) {
					udidString = line.substring(0, (line.length() - 7));
					udidList.add(udidString);
				}
			}
			System.out.println("获取设备udidList:" + udidList);
			process.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void test_001_install(int udidcount, int apkcount) {
		System.out.println("执行测试 安装");
		System.out.println("udid:" + udidList.get(udidcount));
		System.out.println("apk:" + apkList.get(apkcount));
		String installCmd = "adb -s " + udidList.get(udidcount) + " install "
				+ apkpath + "\\" + apkList.get(apkcount);// PM指令不支持中文
		Process process = null;
		System.out.println("installCmd :" + installCmd);
		try {
			process = Runtime.getRuntime().exec(installCmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			String resultString = null;
			while ((line = input.readLine()) != null) {
				if (!line.equals("")) {
					resultString = line;
				}
			}
			System.out.println(resultString);
			process.getOutputStream().close();
			if (resultString.equals("Success")) {
				System.out.println("安装成功");
			} else {
				System.out.println("安装失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public void test_001_uninstall(int count) {
		System.out.println("执行测试 卸载");
		System.out.println("udid:" + udidList.get(count));
		String uninstallCmd = "adb -s " + udidList.get(count) + " uninstall "
				+ appPackage;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(uninstallCmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream(), "GBK"));
			String line = null;
			String resultString = null;
			while ((line = input.readLine()) != null) {
				if (!line.equals("")) {
					resultString = line;
					System.out.println(resultString);
				}
			}
			process.getOutputStream().close();
			if (resultString.equals("Success")) {
				System.out.println("卸载成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		udidList = new ArrayList<>();
		apkList = new ArrayList<>();
		Install_uninstall_test iut = new Install_uninstall_test();
		iut.getUdidS();
		iut.getApkS();
		for (int i = 0; i < udidList.size(); i++) {
			for (int j = 0; j < apkList.size(); j++) {
				iut.test_001_install(i, j);
				iut.test_001_uninstall(i);
			}
		}
		System.out.println("安装 卸载 执行完毕");
	}

}
