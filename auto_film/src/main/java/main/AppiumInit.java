package main;


import com.zzy.appium.server.*;
import com.zzy.appium.utils.*;

public class AppiumInit {
	public static void init(){
		Servers servers=new Servers(new Port(new DosCmd()), new DosCmd());
		DosCmd dc=new DosCmd();
		if(dc.killServer()){
			try {
				System.out.println("开始启动服务");
				servers.startServers();
				System.out.println("服务启动完毕");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				XmlUtil.createTestngXml("com.zzy.appium.test.HangMei");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("清除appium服务失败");
		}
	}
//	public static void main(String[] args) {
//		init();
//	}

}
