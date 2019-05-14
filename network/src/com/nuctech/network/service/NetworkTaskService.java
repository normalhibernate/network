/*
 * @(#)NetworkTaskService.java 2019-4-1
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nuctech.network.adptor.NetworkStatusAdaptor;
import com.nuctech.network.bean.Const;
import com.nuctech.network.bean.DeviceType;
import com.nuctech.network.bean.NetworkResp;
import com.nuctech.network.bean.WsControl;
import com.nuctech.network.mapper.NetworkRecordMapper;
import com.nuctech.network.mapper.NetworkStatusMapper;
import com.nuctech.network.model.DeviceInfo;
import com.nuctech.network.model.NetworkRecord;
import com.nuctech.network.model.ServerStatus;
import com.nuctech.network.task.NetworkTask;


/**<p>上位机-下位机通讯中断与设备故障处理策略<br/>
 * @className NetworkTaskService.java<br/>
 * @packageName com.nuctech.network.service<br/>
 * @date 2019-4-1 上午9:59:33<br/>
 * </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-1
 * @version v1.0.0
 */
@Service
public class NetworkTaskService {
    
    private Logger                logger               = LoggerFactory.getLogger(getClass());
    private static String[]       SERVERSTATE          = { "1,2,1", "1,1,2", "1,1,1" };
    private static String         LOCAL_CONNECT_NAME;
    private static String         SERVICE_NAME;
    private static String         STATION_ID;
    private static Integer        INTERVAL;
    private static Integer        RESTART_FLAG;
    private static String         GATEWAY;
    private static String         PROVINCE;
    private static String         COUNTRY;
    private static Integer        VPN_INTERVAL         = 10;
    static Properties             properties           = new Properties();
    // 使用InPutStream流读取properties文件
    static BufferedReader         bufferedReader;
    private static volatile Timer timer                = new Timer("CHECK_CONNECT", true);
    @Autowired
    private NetworkStatusMapper   networkStatusMapper;
    @Autowired
    private NetworkRecordMapper   networkRecordMapper;
    private NetworkStatusAdaptor  networkStatusAdaptor = new NetworkStatusAdaptor();
    
    public void init() {
        // 将系统需要执行的定时任务装入定时任务容器中 ,默认 30秒
        try {
            logger.info("定时任务开始");
            timer.schedule(new NetworkTask(), 1000);
            logger.info("定时任务正在运行！");
        } catch (Exception e) {
            logger.error("加载配置文件失败！", e.getMessage(), e);
        }
    }
    public void connectCheck() {
        try {
            NetworkRecord last = this.networkRecordMapper.selectLastRecord();
            if (last == null) {
                connectCheck(null);
            } else {
                if (last.getRestartCounter() == 0) {
                    last.counterAdd();
                    connectCheck(last);
                } else {
                    if (last.getCounter() >= 7) {
                        connectCheck(null, last.getRestartCounter());
                    }
                    if (last.getCounter() < 3) {
                        last.counterAdd();
                        connectCheck(last);
                    } else {
                        connectCheck(null, last.getRestartCounter());
                    }
                }
            }
        } catch (Exception e) {
            logger.info("定时任务加载异常！", e.getMessage(), e);
        } finally {
            timer.schedule(new NetworkTask(), INTERVAL * 1000);
        }
    }
    /**
     * 创建日志数据检查<p><br/>
     * @title connectCheck<br/>
     * @date 2019-4-19 上午8:39:43<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-19
     * @version v1.0.0
     * </p>
     * @param nr
     */
    public void connectCheck(NetworkRecord nr) {
        if (nr == null) {
            nr = new NetworkRecord(STATION_ID);
        }
        this.createRecord(nr);
    }
	/**
	 * 工控机重启计数器为1时，只重启软件和本地连接
	 * @param nr
	 * @param restartCounter
	 */
    public void connectCheck(NetworkRecord nr, Integer restartCounter) {
        if (nr == null) {
            nr = new NetworkRecord(STATION_ID);
            nr.setRestartCounter(restartCounter);
        }
        this.createRecord(nr);
    }
	/**
	 * 执行步骤<p><br/>
	 * @title createRecord<br/>
	 * @date 2019-4-19 上午8:40:29<br/>
	 * @author Guo Yu Ting<br/>
	 * @since 2019-4-19
	 * @version v1.0.0
	 * </p>
	 * @param nr
	 */
	private void createRecord(NetworkRecord nr) {
        try {
            switch (nr.getCounter()) {
                case 0:// 初始化
                case 1:
                    doCounter1(nr);
                case 2:
                    doCounter2(nr);
                case 3:
					doCounter3(nr);
                    break;
                case 4:
                    doCounter4(nr);
                case 5:
                    doCounter5(nr);
                    break;
                case 6:
                    doCounter6(nr);
                case 7:
                    doCounter7(nr);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始计数<p><br/>
     * @title counter1<br/>
     * @date 2019-4-8 下午2:17:32<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-8
     * @version v1.0.0
     * </p>
     * @param n
     */

    private void doCounter1(NetworkRecord n) {
        // counter1 计数器开始计数加1
        n.counterStart();
        n.setContent("初始化");
        
        // 记录日志
        insert(n);
        // 增加
        n.counterAdd();
    }
    private void doCounter2(NetworkRecord n) {
    	 List<ServerStatus> servers  = this.buildNetworkAddress();
         for (ServerStatus server : servers) {
             this.buildState(server, n, checkConnect(server));
         }
         n.setRestartFlag(0);
         n.setContent("开始检查连接状态:Counter2");
         // 记录日志
         insert(n);
         // 增加
         n.counterAdd();
    }
    private void doCounter3(NetworkRecord n) {

        String[] sts = n.getState().split(",");
        n.setContent("开始检查连接状态:Counter3");
        Integer rfCount = 0;
        NetworkRecord nr = this.networkRecordMapper.selectLastByCounter(3);
        if (nr != null) {
        	rfCount = nr.getRestartFlag();
        }
        // "1,2,1", "1,1,2", "1,1,1" 
		if (n.getState().equals(SERVERSTATE[0]) || n.getState().equals(SERVERSTATE[1]) || n.getState().equals(SERVERSTATE[2])) {
			// 时间计数器清零
			n.counterReset();
			// 工控机重启计数器清零
			n.restartCounterReset();
			if (!n.getState().equals(SERVERSTATE[2])) {
				// 报网络故障，提示省中心或国家中心网络不通
				n.setContent("网络故障，提示省中心或国家中心网络不通："+n.getState());
			}

		} else {
		    StringBuilder sb = new StringBuilder();
			if (sts[0].equals("2")) { // 重启本地连接
				if (!checkLocalConnect()) { // 如果网络没有启动
					this.setLocalConnect("enable"); // 启动
				} else {
					this.restartLocalConnect(); // 如果启动，重启
				}
				sb.append("重启本地连接;");
			}
			if (sts[1].equals("2") && sts[2].equals("2")) {
				if (rfCount.compareTo(RESTART_FLAG) >= 0) {
					// 重启上位机软件
					String exec = "sc query " + SERVICE_NAME;
					if (this.checkService(exec)) { // 运行状态
						this.restartService();
					} else { // 停止状态
						this.setServce("start");
					}
					
					sb.append("重启上位机软件");
					n.setRestartFlag(0);
				} else {
					n.setRestartFlag(rfCount + 1);
				}
				
			}
			n.setContent(sb.toString());
		}
        // 记录日志
        insert(n.rebuildPK());
        // 增加
        n.counterAdd();
    }

    private void doCounter4(NetworkRecord n) {
        List<ServerStatus> servers = this.buildNetworkAddress();
        for (ServerStatus server : servers) {
            this.buildState(server, n, checkConnect(server));
        }
        n.setRestartFlag(0);
        n.setContent("开始检查连接状态:Counter4");
        // 记录日志
        insert(n);
        // 增加
        n.counterAdd();
    }

	private void doCounter5(NetworkRecord n) {
		Integer rfCount = 0;
		n.setContent("开始检查连接状态:Counter5");
		NetworkRecord nr = this.networkRecordMapper.selectLastByCounter(5);
		if (nr != null) {
			rfCount = nr.getRestartFlag();
		}
		if (n.getState().equals(SERVERSTATE[0]) || n.getState().equals(SERVERSTATE[1]) || n.getState().equals(SERVERSTATE[2])) {
			// 时间计数器清零
			n.counterReset();
			// 工控机重启计数器清零
			n.restartCounterReset();
			if (n.getState().equals(SERVERSTATE[2])) {
				// 报国家中心或省中心网络故障恢
				n.setContent("国家中心或省中心网络故障恢");
			}
		} else {
			if (n.getRestartCounter() == 0) {
				if (rfCount.compareTo(RESTART_FLAG) >= 0) {
					// 重启vpn模块
					restartVPN();
					n.setContent("重启VPN");
					n.setRestartFlag(0);
				} else {
					n.setRestartFlag(rfCount + 1);
				}
			} else {
				// 时间计数器清零
				n.counterReset();
				// 报故障需人员处理，判定是否去人维修
				n.setContent("故障需人员处理");
			}
		}
		// 记录日志
		insert(n.rebuildPK());
		// 增加
		n.counterAdd();

	}

    private void doCounter6(NetworkRecord n) {
        List<ServerStatus> servers = this.buildNetworkAddress();
        for (ServerStatus server : servers) {
            this.buildState(server, n, checkConnect(server));
        }
        // 重置重启计数
        n.setRestartFlag(0);
        n.setContent("开始检查连接状态:Counter6");
        // 记录日志
        insert(n);
        // 增加
        n.counterAdd();
    }
	private void doCounter7(NetworkRecord n) {
		Integer rfCount = 0;
		n.setContent("开始检查连接状态:Counter7");
        NetworkRecord nr = this.networkRecordMapper.selectLastByCounter(7);
        if (nr != null) {
        	rfCount = nr.getRestartFlag();
        }
		if (n.getState().equals(SERVERSTATE[0]) || n.getState().equals(SERVERSTATE[1]) || n.getState().equals(SERVERSTATE[2])) {
			// 时间计数器清零
			n.counterReset();
			// 工控机重启计数器清零
			n.restartCounterReset();
			if (n.getState().equals(SERVERSTATE[2])) {
				// 报国家中心或省中心网络故障恢
				n.setContent("国家中心或省中心网络故障恢");
			}
		} else {
			if (n.getRestartCounter() == 0) {
				n.restartCounterAdd();
				if (rfCount.compareTo(RESTART_FLAG) >= 0) {
					// 重启vpn
					restartVPN();
					// 重启间隔时间
					resatartWaitInterval(INTERVAL);
					// 重启工控机
					restartComputer();
					n.setContent("重启VPN和工控机");
					n.setRestartFlag(0);
				} else {
					n.setRestartFlag(rfCount + 1);
                }
			}
		}
		// 记录日志
		insert(n.rebuildPK());

	}
	private void resatartWaitInterval (Integer interval){
	    try {
            Thread.sleep(interval * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void buildProperties(){
	    try {
            bufferedReader = new BufferedReader(new FileReader("config/config.properties"));
            properties.load(bufferedReader);
            // 获取key对应的value值
            LOCAL_CONNECT_NAME = properties.getProperty("LOCAL_CONNECT_NAME");
            SERVICE_NAME = properties.getProperty("SERVICE_NAME");
            STATION_ID = properties.getProperty("SYS_STATION_ID");
            INTERVAL = Integer.parseInt(properties.getProperty("INTERVAL"));
            RESTART_FLAG = Integer.parseInt(properties.getProperty("RESTART_FLAG"));
            GATEWAY = properties.getProperty("GATEWAY");
            PROVINCE = properties.getProperty("PROVINCE");
            COUNTRY = properties.getProperty("COUNTRY");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    private List<ServerStatus> buildNetworkAddress() {
        ServerStatus gateway = new ServerStatus();
        gateway.setIp(GATEWAY);
        gateway.setName("网关");
        ServerStatus provice = new ServerStatus();
        provice.setIp(PROVINCE);
        provice.setName("省中心");
        ServerStatus country = new ServerStatus();
        country.setIp(COUNTRY);
        country.setName("国家中心");
        List<ServerStatus> servers = Lists.newArrayList();
        servers.add(gateway);
        servers.add(provice);
        servers.add(country);
        return servers;
    }
    private void buildState(ServerStatus server, NetworkRecord n, boolean isConnect) {
        String[] s = n.getState().split(",");
        if (!isConnect) {
            // counter2 发送测试连接命令
            if (server.getName().indexOf("省") >= 0) {
                s[1] = "2";
            } else if (server.getName().indexOf("国家") >= 0) {
                s[2] = "2";
            } else {
                s[0] = "2";
            }
        } else {
            // counter2 发送测试连接命令
            if (server.getName().indexOf("省") >= 0) {
                s[1] = "1";
            } else if (server.getName().indexOf("国家") >= 0) {
                s[2] = "1";
            } else {
                s[0] = "1";
            }
        }
        n.setState(StringUtils.join(s,","));
    }
   
    /**
     * 重启vpn<p><br/>
     * @title restartVPN<br/>
     * @date 2019-4-4 上午10:29:27<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-4
     * @version v1.0.0
     * </p>
     */
    public void restartVPN() {
        DeviceInfo di = this.networkStatusMapper.getDeviceInfoByDeviceType(STATION_ID, DeviceType.ACControlAdaptor.getKey(), 0);//  查询所有字段 true-是 false-否(不查询states、properties、commands)
        if (di == null) {
            logger.info("该设备目前不存在，无法执行VPN重启！");
            return;
        }
        try {
            //1、断开交流控制器中的通讯传输模块
            if (this.setOffVPN(di.getDeviceId())) {
                // 重启间隔时间
                resatartWaitInterval(VPN_INTERVAL);
                //2、闭合交流控制器中的通讯传输模块
                this.setOnVPN(di.getDeviceId());
            }
        } catch (Exception e) {
            logger.error("重启VPN异常！", e);
        } 
    }

    @SuppressWarnings("rawtypes")
    public boolean setOnVPN(String deviceId) {
        WsControl control = new WsControl();
        control.setDO2("1");// 1（闭合），0是断开）
        NetworkResp resp = networkStatusAdaptor.controlParamSetterVPN(control, deviceId);
        if (resp != null && Const.ErrCode_OK.equals(resp.getErrCode()) && Const.ErrMsg_OK.equals(resp.getErrMsg())) {
            logger.info("通讯传输模块及VPN，闭合成功！");
            return true;
        } else {
            logger.error("网络异常，通讯传输模块及VPN，闭合失败！");
            return false;
        }
    }
    @SuppressWarnings("rawtypes")
    public boolean setOffVPN(String deviceId) {
        WsControl control = new WsControl();
        control.setDO2("0");// 1（闭合），0是断开）
        NetworkResp resp = networkStatusAdaptor.controlParamSetterVPN(control, deviceId);
        if(resp != null && Const.ErrCode_OK.equals(resp.getErrCode()) && Const.ErrMsg_OK.equals(resp.getErrMsg())) {
            logger.info("通讯传输模块及VPN，断开成功！");
            return true;
        }else{
            logger.error("网络异常，通讯传输模块及VPN，断开失败！");
            return false;
        }
    }
    /**
     * 重启工控机<p><br/>
     * @title restartComputer<br/>
     * @date 2019-4-4 上午10:29:37<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-4
     * @version v1.0.0
     * </p>
     */
    @SuppressWarnings("rawtypes")
    public boolean restartComputer() {

        networkStatusAdaptor = new NetworkStatusAdaptor();
        NetworkResp resp = networkStatusAdaptor.controlParamSetterComputer("11111111");
        if(resp != null && Const.ErrCode_OK.equals(resp.getErrCode()) && Const.ErrMsg_OK.equals(resp.getErrMsg())) {
            logger.info("工控机重启成功！");
            return true;
        }else{
            logger.error("工控机重启失败！");
            return false;
        }
    }
  
    public void restartLocalConnect() {
        try {
            this.setLocalConnect("disable");
            Thread.sleep(5 * 1000);
            this.setLocalConnect("enable");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 1.断开本地连接1
       2.启动本地连接1
       *注意中间留一定间隔比如1s，确保2条指令能正常执行 <br/>
     * @title restartLocalConnect<br/>
     * @date 2019-4-4 上午10:27:28<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-4
     * @version v1.0.0
     * </p>
     */
    public void setLocalConnect(String enable) {

        String exec = "netsh interface set interface name = \"" + LOCAL_CONNECT_NAME + "\" admin=" + enable;// javaExcute为你要在CMD中执行的字符串
        logger.info("设置本地连接语句：{}", exec);
        Process p = null;
        InputStream in = null;
        BufferedReader bf = null;
        try {
            // 执行CMD代码,返回一个Process
            p = Runtime.getRuntime().exec(exec);
            in = p.getInputStream();
            bf = new BufferedReader(new InputStreamReader(in, "GBK"));
            String message = bf.readLine();
            while (message != null) {
                message = bf.readLine();
            }
            p.waitFor();
            if (p.exitValue() == 0) {
                logger.error("设置本地联机{}成功：{}", enable, message);
            } else {
                logger.error("设置本地联机{}失败：{}", enable, message);
            }

        } catch (Exception e) {
            logger.error("设置本地联机{}失败：{}", enable, e);
        } finally {
            try {
                if (p != null)
                    p.destroy();
                if (in != null)
                    in.close();
                if (bf != null)
                    bf.close();
            } catch (IOException e) {
            }
        }
    }
   
	@SuppressWarnings("resource")
	public boolean checkLocalConnect() {
		String exec = "netsh interface show interface name = \"" + LOCAL_CONNECT_NAME + "";
		System.out.println("查询本地连接语句：" + exec);
		Process p = null;
		InputStream in = null;
		BufferedReader bf = null;
		try {

			// 执行CMD代码,返回一个Process
			p = Runtime.getRuntime().exec(exec);
			in = p.getInputStream();
			bf = new BufferedReader(new InputStreamReader(in, "GBK"));
			String message = bf.readLine();
			while (message != null) {
				if (message.indexOf("已启用") >= 0) {
					return true;
				}
				if (message.indexOf("禁用") >= 0) {
					return false;
				}
				message = bf.readLine();
			}
			p.waitFor();
			if (p.exitValue() == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		} finally {
			try {
				if (p != null)
					p.destroy();
				if (in != null)
					in.close();
				if (bf != null)
					bf.close();
			} catch (IOException e) {
			}
		}
		return false;
	}

    public boolean checkConnect(ServerStatus server) {
//        if (server.getName().indexOf("网关") >= 0) {
//            try {
//                return NetworkTaskService.ping(server.getIp());
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        } else {
//            Socket client = null;
//            try {
//                String hostname = server.getIp().trim();
//                int port = Integer.parseInt(server.getHostPort());
//                SocketAddress address = new InetSocketAddress(hostname, port);
//                client = new Socket();
//                client.connect(address, 30);
//            } catch (Exception e) {
//                logger.error("检查服务器连接失败", e.getMessage());
//                return false;
//            } finally {
//                try {
//                    if (client != null) {
//                        client.close();
//                    }
//                } catch (Exception e) {
//                }
//            }
//            return true;
//        }
        try {
            return NetworkTaskService.ping(server.getIp());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkService(String exec) {
        try {
            Process p = Runtime.getRuntime().exec(exec);
            InputStream in = p.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "GBK"));
            String message = bf.readLine();
            while (message != null) {
                if (message.indexOf("STOPPED") >= 0) {
                    return false;
                }
                if (message.indexOf("RUNNING") >= 0) {
                    return true;
                }
                message = bf.readLine();
            }
            p.waitFor();
            if (p.exitValue() == 0) {
                System.out.println("设置本地联机{}成功：{}");
            } else {
                System.out.println("设置本地联机{}失败：{}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 重启本地服务<p><br/>
     * @title restartService<br/>
     * @date 2019-4-8 上午10:39:05<br/>
     * @author Guo Yu Ting<br/>
     * @since 2019-4-8
     * @version v1.0.0
     * </p>
     * @param oper
     */
    public void restartService() {
        try {
            this.setServce("stop");
            Thread.sleep(5 * 1000);// 设置暂停的时间 5 秒
            this.setServce("start");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setServce(String oper) {
        String exec = "net " + oper + " " + SERVICE_NAME;
        logger.info("设置服务语句：{}", exec);
        Process p = null;
        InputStream in = null;
        BufferedReader bf = null;
        try {
            // 执行CMD代码,返回一个Process
            p = Runtime.getRuntime().exec(exec);
            in = p.getInputStream();
            bf = new BufferedReader(new InputStreamReader(in, "GBK"));
            String message = bf.readLine();
            while (message != null) {
                message = bf.readLine();
                System.out.println(message);
            }
            p.waitFor();
            if (p.exitValue() == 0) {
                logger.error("设置服务{}成功：{}", oper, message);
            } else {
                logger.error("设置服务{}失败：{}", oper, message);
            }

        } catch (Exception e) {
            logger.error("设置服务{}失败：{}", oper, e);
        } finally {
            try {
                if (p != null)
                    p.destroy();
                if (in != null)
                    in.close();
                if (bf != null)
                    bf.close();
            } catch (IOException e) {
            }
        }
    }

    public ServerStatus getLocalGateway() {
        ServerStatus server = null;
        try {
            Process pro = Runtime.getRuntime().exec("cmd /c ipconfig /all");
            InputStreamReader isr = new InputStreamReader(pro.getInputStream(), "gbk");
            BufferedReader br = new BufferedReader(isr);
            String str = br.readLine();
            while (str != null) {
                if (str.indexOf("网关") >= 0) {
                    System.out.println(str);
                    server = new ServerStatus();
                    server.setName(str.split(":")[0].trim());
                    server.setIp(str.split(":")[1].trim());
                }

                str = br.readLine();
            }
            br.close();
            isr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

    public static boolean ping(String ipAddress) throws Exception {
        int timeOut = 3000; // 超时应该在3钞以上
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut); // 当返回值是true时，说明host是可用的，false则不可。
        return status;
    }

    private int insert(NetworkRecord n) {
        try {
            return networkRecordMapper.insert(n.rebuildPK());
        } catch (Exception e) {
            logger.info("插入失败", e.getMessage(), e);
        } finally {
        }
       return 0;
    }


}
