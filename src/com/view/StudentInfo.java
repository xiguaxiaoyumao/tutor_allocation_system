package com.view;

import com.view.box.StuInsertBox;
import com.view.box.StuSelectBox;
import com.view.box.StuInfoBox;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.学生信息界面
 * @date 2020/10/24 16:24
 */
public class StudentInfo extends JPanel {
    private JLabel title = new JLabel("学生信息");
    //查询模块
    private StuSelectBox stuSelectBox = new StuSelectBox();
    //导入模块
    private StuInsertBox stuInsertBox = new StuInsertBox();
    //基本信息模块
    private StuInfoBox stuInfoBox = new StuInfoBox();

    public StudentInfo() {
        this.setLayout(null);
        this.title.setBounds(0, 0, 100, 15);
        this.stuSelectBox.setLocation(30, 60);
        this.stuInsertBox.setLocation(445, 60);
        this.stuInfoBox.setLocation(445, 275);
        this.add(this.title);
        this.add(this.stuSelectBox);
        this.add(this.stuInsertBox);
        this.add(this.stuInfoBox);
    }

}
