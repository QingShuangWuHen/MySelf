package com.zzy.appium.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.zzy.appium.server.Port;


/**
 * 此类完成dos或shell命令的执行封装
 *
 */
public class DosCmd {
	private Log logger=Log.getLogger(DosCmd.class);
	String osName=System.getProperty("os.name");
	
	/**
	 * execute dos command
	 * @param dos command,String
	 * @return boolean.succeed is true,Failure is false
	 * 
	 */
	public boolean execCmd(String cmdString){
		Runtime p = Runtime.getRuntime();//获取当前执行环境
		
		try {
			if(osName.toLowerCase().contains("mac")){
				String[] command={"/bin/sh","-c",cmdString};
				Process process=p.exec(command);
			}else if(osName.toLowerCase().contains("win")){
				Process process=p.exec("cmd /c "+cmdString);
			}
			//Thread.sleep(10000);
			//appium -p 4490 -bp 2253 -U 127.0.0.1:62001>xxx
			//当执行启动appium命令的时候获取端口号，判断端口是否启动成功，直到appium服务启动成功
			int portNum=RandomUtil.getInt(cmdString, 0);
			System.out.println(portNum);
			if(portNum>0){
				Port port=new Port(new DosCmd());
				while(!port.isPortUsed(portNum)){
					Thread.sleep(1000);
				}
			}
			System.out.println("dos命令执行完成");
			logger.debug("execute  command "+cmdString+" Succeed");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			logger.error("execute  command "+cmdString+" Failure", e);
			return false;
		}
	}
	/**
	 * get result of command, after execute dos command 
	 * @param dos command,String
	 * @return List<String>
	 */
	public List<String> execCmdConsole(String cmdString) throws InterruptedException {
		List<String> dosRes = new ArrayList<String>();
		try {
			Process process=null;
			//System.out.println(cmdString);
			if(osName.toLowerCase().contains("mac")){
				String[] command={"/bin/sh","-c",cmdString};
				process = Runtime.getRuntime().exec(command);
			}else if(osName.toLowerCase().contains("win")){
				process = Runtime.getRuntime().exec("cmd /c "+cmdString);
			}
			InputStream in = process.getInputStream();
			BufferedReader inr = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = inr.readLine()) != null) {
				dosRes.add(line);
			}
			process.waitFor();
			process.destroy();
			logger.debug("get result of command after execute dos command "+cmdString+" Succeed ");
		} catch (IOException e) {
			logger.error("get result of command after execute dos command "+cmdString+" Failure", e);
		}
		return dosRes;
	}
	/**
	 * kill server with appium servers
	 * 
	 * @return boolean
	 */
	public boolean killServer(){
		String command="taskkill -F -PID node.exe";
		if(osName.toLowerCase().contains("mac")){
			command="killall node";
		}else if(osName.toLowerCase().contains("win")){
			command="taskkill -F -PID node.exe";
		}else{
			command="taskkill -F -PID node.exe";
		}
		if(execCmd(command)){
			logger.debug("kill server node  Succeed");
			return true;
		}else{
			logger.error("kill server node Failure");
			return false;
		}
	}
}
