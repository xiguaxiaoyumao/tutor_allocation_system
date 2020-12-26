package com.view;


import javax.swing.*;
import java.awt.*;

/**
 * 1.主界面
 *
 * @date 2020/10/24 14:24
 */
public class MainView extends JFrame {
    //分割面板
    private JSplitPane globalSplit = new JSplitPane();
    //左边的目录
    private Contents contents = new Contents(this.globalSplit);
    //右边的开始界面
    private StartPage startPage = new StartPage();

    public MainView() {
        /* 窗口基本设置 */
        this.setTitle("毕业生导师分配系统 - 主界面");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.globalSplit.setLeftComponent(this.contents);
        this.globalSplit.setRightComponent(this.startPage);
        //设置分割线与左侧边框的距离
        this.globalSplit.setDividerLocation(200);
        this.globalSplit.setEnabled(false);
        this.add(this.globalSplit, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
