package com.nuctech.network.listener;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;

import com.nuctech.network.App;
import com.nuctech.network.model.NetworkRecord;
import com.nuctech.network.service.NetworkStatusService;
import com.nuctech.network.service.NetworkTaskService;
import com.nuctech.network.spring.SpringContextUtil;

public class JButtonListener implements ActionListener{
    
    private String stationText;
    private String statusText;
    private String counterText;
    private String rcText;
    private String rfText;
    private String contentText;
    private String timeText;
    private int    type;

    public JButtonListener() {}
    
    public JButtonListener(int type) {
        this.type = type;
    }
    private NetworkStatusService networkStatusService;
    private NetworkTaskService   networkTaskService;
    @Override
    public void actionPerformed(ActionEvent e) {
        App.frame.setEnabled(false);
        networkTaskService =  SpringContextUtil.getApplicationContext().getBean(NetworkTaskService.class);
        networkTaskService.buildProperties();
        if (type == 0) { // 最新数据          
            networkStatusService = SpringContextUtil.getApplicationContext().getBean(NetworkStatusService.class);
            NetworkRecord record = networkStatusService.selectLastRecord();
            if (record != null) {
                String[] states = record.getState().split(",");
                String staStr = "本地:"+states[0]+",省中心:"+states[1]+",国家中心:"+states[2];
                App.stationText.setText(record.getStationId());
                App.statusText.setText(staStr);
                App.counterText.setText(record.getCounter().toString());
                App.rcText.setText(record.getRestartCounter().toString());
                App.rfText.setText(record.getRestartFlag().toString());
                App.contentText.setText(record.getContent());
                App.timeText.setText(new DateTime(record.getAt()).toString("yyyy-MM-dd HH:mm:ss"));
            }
        } else if (type == 1) { // 扩展 打开一个dialog
            App.frame.setBounds(App.frame.getX() - 300, App.frame.getY(), 500, 500);
            MyJDialog jd = new MyJDialog(App.frame,"22");
//            jd.add(BorderLayout.SOUTH, b);
            jd.setResizable(false);
            jd.setVisible(true);
        } else if (type == 2){ // 定时
            networkTaskService.init();
        } else if (type == 3){ // 重启本地连接
            networkTaskService.restartLocalConnect();
        } else if (type == 4){ // 重启VPN
            networkTaskService.restartVPN();;
        } else if (type == 5){ // 重启服务软件
            networkTaskService.restartService();;
        }  else { // 6 重启工控机
            networkTaskService.restartComputer();
        }
        App.frame.setEnabled(true);
    }
    
    class MyJDialog extends JDialog{
        // 定义一维数据作为列标题
        Object[]                  columnTitle      = { "站点编号", "状态", "时间计数器", "工控机重启计数器", "定时次数", "内容" };
        DefaultTableModel         dtm              = null;
        JTable                    table            = null;
        JScrollPane               jsp              = null;
        Integer                   currentPage      = null;
        Integer                   lastPage         = null;
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public MyJDialog(JFrame frame){    // 构造函数 可以不写 那个 void 
            super(frame,"所有数据",true);// 实例化一个JDialog 类对象，指定对话框的父窗体 窗体标题 和类型
            Container container = getContentPane();
            container.add(new JLabel("这是一个对话框"));
            setBounds(frame.getX() + frame.getWidth() + 1, frame.getY(), 500, 500);
            networkStatusService = SpringContextUtil.getApplicationContext().getBean(NetworkStatusService.class);
            List<NetworkRecord> networkStatusList = networkStatusService.selectLastGroupRecord();
            Object[][] tableData = new Object[networkStatusList.size()][];

            for (int i = 0; i < networkStatusList.size(); i++) {
                NetworkRecord n = networkStatusList.get(i);
                tableData[i] = new Object[] { 
                        n.getStationId(), 
                        n.getState(), 
                        n.getCounter(),
                        n.getRestartCounter(),
                        n.getRestartFlag(),
                        n.getContent()
                        };
            }
            // 以二维数组和一维数组来创建一个JTable对象
            JTable table = new JTable(tableData, columnTitle);
            JScrollPane sp = new JScrollPane(table);
            add(sp);
            pack();
        }
        public MyJDialog(JFrame frame, String str) {
            dtm=new DefaultTableModel(columnTitle, 0);
            table=new JTable(dtm);
            jsp = new JScrollPane();
            jsp.setViewportView(table);
            getContentPane().add(jsp);
            
            showTable(1);
            
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.SOUTH);
            setBounds(frame.getX() + frame.getWidth() + 1, frame.getY(), 495, 495);
            JButton button = new JButton("首页");
            button.addActionListener(new PageListener());
            button.setActionCommand("首页");
            panel.add(button);
            JButton button1 = new JButton("上一页");
            button1.addActionListener(new PageListener());
            panel.add(button1);
            JButton button2 = new JButton("下一页");
            button2.addActionListener(new PageListener());
            panel.add(button2);
            JButton button3 = new JButton("末页");
            button3.addActionListener(new PageListener());
            panel.add(button3);
            setVisible(true);    

        }
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public void showTable(int currentPage) {
           
            networkStatusService = SpringContextUtil.getApplicationContext().getBean(NetworkStatusService.class);
            Integer totalCount = networkStatusService.selectCount();
            Integer totalPage;
            
            
            if ((totalCount % 7) == 0) {
                totalPage = (int)totalCount / (int)7;               // 将操作数转化为int型数据
            } else {
                totalPage = ((int)totalCount / (int)7) + 1;         // 将操作数转化为int型数据
            }
            setLastPage(totalPage);
            setCurrentPage(currentPage);
            List<NetworkRecord> networkStatusList = networkStatusService.selectByPage(currentPage,totalPage);
            if (networkStatusList==null || networkStatusList.size()==0) {
                return;
            }
                
            dtm.setRowCount(0);
            Object[][] tableData = new Object[networkStatusList.size()][];
            for (int i = 0; i < networkStatusList.size(); i++) {
                NetworkRecord n = networkStatusList.get(i);
                String[] states = n.getState().split(",");
                String staStr = "本地:"+states[0]+",省中心:"+states[1]+",国家中心:"+states[2];
                Vector rowV = new Vector();
                rowV.add(n.getStationId());
                rowV.add(staStr);
                rowV.add(n.getCounter());
                rowV.add(n.getRestartCounter());
                rowV.add(n.getRestartFlag());
                rowV.add(n.getContent());
                tableData[i] = new Object[] { 
                        n.getStationId(), 
                        n.getState(), 
                        n.getCounter(),
                        n.getRestartCounter(),
                        n.getRestartFlag(),
                        n.getContent()
                        };
                dtm.addRow(rowV);
            }
        }
        public class PageListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("首页")) {
                    showTable(1);
                }
                if (e.getActionCommand().equals("上一页")) {
                    if (getCurrentPage() <= 1) {
                        setCurrentPage(2);
                    }
                    showTable(getCurrentPage() - 1);
                }
                if (e.getActionCommand().equals("下一页")) {
                    if (getCurrentPage() < getLastPage()) {
                        showTable(getCurrentPage() + 1);
                    } else {
                        showTable(getLastPage());
                    }
                }
                if (e.getActionCommand().equals("末页")) {
                    showTable(getLastPage());
                }
                
            }
        }
        public Integer getCurrentPage() {
            return currentPage;
        }
        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }
        public Integer getLastPage() {
            return lastPage;
        }
        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }
    }
   
    
    public String getStationText() {
        return stationText;
    }
    public void setStationText(String stationText) {
        this.stationText = stationText;
    }
    public String getStatusText() {
        return statusText;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    public String getCounterText() {
        return counterText;
    }
    public void setCounterText(String counterText) {
        this.counterText = counterText;
    }
    public String getRcText() {
        return rcText;
    }
    public void setRcText(String rcText) {
        this.rcText = rcText;
    }
    public String getRfText() {
        return rfText;
    }
    public void setRfText(String rfText) {
        this.rfText = rfText;
    }
    public String getContentText() {
        return contentText;
    }
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    public String getTimeText() {
        return timeText;
    }
    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }
    public NetworkStatusService getNetworkStatusService() {
        return networkStatusService;
    }
    public void setNetworkStatusService(NetworkStatusService networkStatusService) {
        this.networkStatusService = networkStatusService;
    }
}
