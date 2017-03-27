package com.zzy.appium.server;

import java.util.ArrayList;
import java.util.List;

import com.zzy.appium.utils.DosCmd;
import com.zzy.appium.utils.Log;


/**
 * 此类封装appium server所有需要的端口分配的诸多方法
 *
 */
public class Port {
	private DosCmd execDos;
	private Log logger=Log.getLogger(Port.class);
	public Port(DosCmd execDos){
		this.execDos=execDos;
	}
	/**
	 * 验证端口是否被占用
	 * @param portNum
	 * @return boolean
	 */
	public  Boolean isPortUsed(int portNum) {
		List<String> portRes = new ArrayList<String>();
		String osName=System.getProperty("os.name");
		boolean flag=true;
		try {
			String command="";
			if(osName.toLowerCase().contains("mac")){
				command="netstat -an|grep " + portNum;
			}else if(osName.toLowerCase().contains("win")){
				command="netstat -ano|findstr " + portNum;
			}
			portRes = execDos.execCmdConsole(command);
			System.out.println(portRes.size());
			if (portRes.size() > 0) {
				logger.debug(String.valueOf(portNum)+" port has been occupied");
			}else{
				logger.debug(String.valueOf(portNum)+" port unoccupied");
				flag=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(String.valueOf(portNum)+"get port occupied status failure"+e);
		}
		return flag;
	}
	/**
	 * 基于当前连接的设备数量生成可用端口
	 * @param portStart,Starting ports
	 * @param deviceTotal,Total number of devices
	 * @return List<Integer>
	 */
	public  List<Integer> GeneratPortList(int portStart, int deviceTotal) {
		List<Integer> portList = new ArrayList<Integer>();
		int i=0;
		while (portList.size() != deviceTotal) {
			if (portStart >= 0 && portStart <= 60000+i) {
				if (!isPortUsed(portStart)) {
					portList.add(portStart);
				}
				portStart++;
				i++;
			}
			
		}
		return portList;
	}
}
