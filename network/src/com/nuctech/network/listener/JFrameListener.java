package com.nuctech.network.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JFrameListener implements WindowListener {
    JFrame frame;
    
    public JFrameListener() {
    }
    public JFrameListener(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosing(WindowEvent e) {
        // frame.setState(JFrame.ICONIFIED); // 点击关闭最小化
        int flag = JOptionPane.showConfirmDialog(frame, "确认关闭吗?", "确认关闭", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (JOptionPane.YES_OPTION == flag) {
            System.exit(0);
        } else {
            return;
        }
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
