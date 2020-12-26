package com.view;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.*;

/**
 * 1.开始界面
 * @date 2020/10/24 16:39
 */
public class StartPage extends JPanel {
    private JLabel label = new JLabel("欢迎使用毕业生导师分配系统");
    private Font font = new Font("微软雅黑", Font.BOLD, 20);

    public StartPage() {
        this.label.setFont(this.font);
        this.add(this.label);
    }
}
