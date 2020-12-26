package com.view;

import com.view.box.TutInfoBox;
import com.view.box.TutInsertBox;
import com.view.box.TutSelectBox;

import javax.swing.*;

/**
 * 1.导师信息界面
 *
 * @date 2020/10/24 16:25
 */
public class ToturInfo extends JPanel {
    private JLabel title = new JLabel("导师信息");
    //导师信息查询模块
    private TutSelectBox tutSelectBox = new TutSelectBox();
    //导入导师信息模块
    private TutInsertBox tutInsertBox = new TutInsertBox();
    //导师基本信息模块
    private TutInfoBox tutInfoBox = new TutInfoBox();

    public ToturInfo() {
        this.setLayout(null);
        this.title.setBounds(0, 0, 100, 15);
        this.tutSelectBox.setLocation(30, 60);
        this.tutInsertBox.setLocation(445, 60);
        this.tutInfoBox.setLocation(445, 275);
        this.add(this.title);
        this.add(this.tutSelectBox);
        this.add(this.tutInsertBox);
        this.add(this.tutInfoBox);

    }
}
