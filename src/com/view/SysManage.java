package com.view;

import com.view.box.ChangePassBox;
import com.view.box.DistributeBox;
import com.view.box.LogSelectBox;

import javax.swing.*;

/**
 * 1.系统管理界面
 * @date 2020/10/24 16:26
 */
public class SysManage extends JPanel {
    private JLabel title = new JLabel("系统管理");
    //日志查询模块
    private LogSelectBox logSelectBox = new LogSelectBox();
    //密码修改模块
    private ChangePassBox changePassBox = new ChangePassBox();
    //分配导师模块
    private DistributeBox distributeBox = new DistributeBox();

    public SysManage() {
        this.setLayout(null);
        this.title.setBounds(0, 0, 100, 15);
        this.logSelectBox.setLocation(30, 60);
        this.changePassBox.setLocation(445, 60);
        this.distributeBox.setLocation(445, 275);
        this.add(title);
        this.add(this.logSelectBox);
        this.add(this.changePassBox);
        this.add(this.distributeBox);
    }
}
