package com.nuctech.network;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nuctech.network.listener.JButtonListener;
import com.nuctech.network.service.NetworkTaskService;
import com.nuctech.network.spring.SpringContextUtil;


public class App {
    public static JFrame     frame;
    JButton                  button        = new JButton("查询当前状态");
    JButton                  timerButton   = new JButton("启动定时");
    JButton                  detailButton  = new JButton("历史记录");
    JButton                  rsNetworkCard = new JButton("重启网卡");
    JButton                  rsVpn         = new JButton("重启VPN");
    JButton                  rsService     = new JButton("重启软件");
    JButton                  reboot        = new JButton("重启工控机");
    JLabel                   station;
    public static JTextField stationText;
    JLabel                   status;
    public static JTextField statusText;
    JLabel                   counter;
    public static JTextField counterText;
    JLabel                   rc;
    public static JTextField rcText;
    JLabel                   rf;
    public static JTextField rfText;
    JLabel                   content;
    public static JTextField contentText;
    JLabel                   time;
    public static JTextField timeText;
    
    public static void main(String[] args) throws Exception {
        App s = new App();
        s.start();
    }
    /**
     * 创建
     */
    public void start() {
        try {
//            frame = new JFrame();
//            frame.getContentPane().setForeground(Color.WHITE);
//            frame.setTitle("网络情况查看器");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮不做
//            frame.addWindowListener(new JFrameListener(frame)); // 给关闭按钮增加监听
//            frame.setBounds(100, 100, 500, 500);
//            frame.getContentPane().setLayout(null);
//            // 设置程序运行窗口处于主窗口中心位置
//            frame.setLocationRelativeTo(null); 
//            this.createPage();
//            frame.setVisible(true);
            NetworkTaskService networkTaskService = null;
            while(true) {
                if (networkTaskService == null) {
                    networkTaskService = SpringContextUtil.getApplicationContext().getBean(NetworkTaskService.class);
                    networkTaskService.buildProperties();
                    networkTaskService.init();
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    /**
     * 构建页面
     */
    public void createPage() {
       
        buildLabels();
        buildTextFields();
        buildButtons();
        
    }
    public void buildLabels() {
        station = new JLabel("站点编号");
        station.setBounds(60, 20, 115, 18);
        station.setHorizontalAlignment(JLabel.RIGHT); // 设置文字右对齐
        frame.getContentPane().add(station);
        
        status = new JLabel("状态");
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        status.setBounds(60, 60, 115, 18);
        frame.getContentPane().add(status);
        
        counter = new JLabel("时间计数器");
        counter.setHorizontalAlignment(SwingConstants.RIGHT);
        counter.setBounds(60, 100, 115, 18);
        frame.getContentPane().add(counter);
        
        rc = new JLabel("工控机重启计数器");
        rc.setHorizontalAlignment(SwingConstants.RIGHT);
        rc.setBounds(60, 140, 115, 18);
        frame.getContentPane().add(rc);
        
        rf = new JLabel("重启定时执行次数");
        rf.setHorizontalAlignment(SwingConstants.RIGHT);
        rf.setBounds(60, 180, 115, 18);
        frame.getContentPane().add(rf);
        
        content = new JLabel("内容");
        content.setHorizontalAlignment(SwingConstants.RIGHT);
        content.setBounds(60, 220, 115, 18);
        frame.getContentPane().add(content);
        
        time = new JLabel("时间");
        time.setHorizontalAlignment(SwingConstants.RIGHT);
        time.setBounds(60, 260, 115, 18);
        frame.getContentPane().add(time);
    }
    /**
     * 构建文本框
     */
    public void buildTextFields() {
        stationText = new JTextField();
        stationText.setToolTipText(stationText.getText());
        stationText.setBounds(190, 19, 200, 24);
        stationText.setColumns(5);
        stationText.setEditable(false);
        frame.getContentPane().add(stationText);
        
        statusText = new JTextField();
        statusText.setToolTipText(statusText.getText());
        statusText.setColumns(15);
        statusText.setBounds(190, 59, 200, 24);
        statusText.setEditable(false);
        frame.getContentPane().add(statusText);
        
        counterText = new JTextField();
        counterText.setToolTipText(counterText.getText());
        counterText.setColumns(15);
        counterText.setBounds(190, 99, 200, 24);
        counterText.setEditable(false);
        frame.getContentPane().add(counterText);
        
        rcText = new JTextField();
        rcText.setToolTipText(rcText.getText());
        rcText.setColumns(15);
        rcText.setBounds(190, 139, 200, 24);
        rcText.setEditable(false);
        frame.getContentPane().add(rcText);
        
        rfText = new JTextField();
        rfText.setToolTipText(rf.getText());
        rfText.setColumns(15);
        rfText.setBounds(190, 179, 200, 24);
        rfText.setEditable(false);
        frame.getContentPane().add(rfText);
        
        contentText = new JTextField();
        contentText.setToolTipText(contentText.getText());
        contentText.setColumns(15);
        contentText.setBounds(190, 219, 200, 24);
        contentText.setEditable(false);
        frame.getContentPane().add(contentText);
        
        timeText = new JTextField();
        timeText.setToolTipText(time.getText());
        timeText.setColumns(15);
        timeText.setBounds(190, 259, 200, 24);
        timeText.setEditable(false);
        frame.getContentPane().add(timeText);
    }
    /**
     * 构建按钮
     */
    public void buildButtons() {
        // 查询按钮
        button.setBounds(40, 300, 120, 30);
        JButtonListener jb = new JButtonListener(0);
        button.addActionListener(jb);
        frame.getContentPane().add(button);
        // 扩展
        detailButton.setBounds(320, 300, 120, 30);
        JButtonListener detailListener = new JButtonListener(1);
        detailButton.addActionListener(detailListener);
        frame.getContentPane().add(detailButton);
        // 定时按钮
//        timerButton.setBounds(180, 300, 120, 30);
//        JButtonListener timerListener = new JButtonListener(2);
//        timerButton.addActionListener(timerListener);
//        frame.getContentPane().add(timerButton);
        // 重启本地连接
        rsNetworkCard.setBounds(180, 300, 120, 30);
        JButtonListener rsNetworkCardListener = new JButtonListener(3);
        rsNetworkCard.addActionListener(rsNetworkCardListener);
        frame.getContentPane().add(rsNetworkCard);
        // 重启VPN
        rsVpn.setBounds(40, 350, 120, 30);
        JButtonListener rsVpnListener = new JButtonListener(4);
        rsVpn.addActionListener(rsVpnListener);
        frame.getContentPane().add(rsVpn);
        
        rsService.setBounds(320, 350, 120, 30);
        JButtonListener rsServiceListener = new JButtonListener(5);
        rsService.addActionListener(rsServiceListener);
        frame.getContentPane().add(rsService);
        // 重启工控机
        reboot.setBounds(180, 350, 120, 30);
        JButtonListener rebootListener = new JButtonListener(6);
        reboot.addActionListener(rebootListener);
        frame.getContentPane().add(reboot);
    }
   
    /**
     * 
     * @param rows 行数
     * @param col 列数
     * @param hgap 垂直空白
     * @param vgap 水平空白
     * @return
     */
    static GridLayout getGridLayout(int rows, int cols, int hgap, int vgap) {
        return new GridLayout(rows, cols, hgap, vgap);
    }
    /**
     * 
     * @param rows 行数
     * @param cols 列数
     * @return
     */
    static GridLayout getGridLayout(int rows, int cols) {
        return getGridLayout(rows, cols, 0, 0);
    }
   /**
    * 流布局
    * @param align 水平对齐方式
    * @param hgap 垂直空白
    * @param vgap 水平空白
    * @return
    */
    static FlowLayout setFlowLayout(int align, int hgap, int vgap) {
        return new FlowLayout(align, hgap, vgap);
    }
    /**
     * 流布局
     * @param align
     * @return
     */
    static FlowLayout setFlowLayout(int align) {
        return new FlowLayout(align);
    }
}
