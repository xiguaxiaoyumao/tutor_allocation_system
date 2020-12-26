package com.view.box;

import com.service.StudentService;
import com.util.ObUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * @author zht
 * @date 2020/10/24 19:19
 */
public class StuInfoBox extends JPanel {
    private JLabel title = new JLabel("基本信息");

    private JLabel totalStuCountLabel = new JLabel("学生总数：");
    private JLabel distributedStuCountLabel = new JLabel("已分配导师学生数：");
    private JLabel undistributedStuCountLabel = new JLabel("未分配导师学生数：");
    private JButton refresh = new JButton("刷新");

    private JLabel totalStuCount = new JLabel("0人");
    private JLabel distributedStuCount = new JLabel("0人");
    private JLabel undistributedStuCount = new JLabel("0人");
    //获取服务对象
    private StudentService studentService = ObUtil.getStudentService();
    Map<String, Integer> map = null;

    public StuInfoBox() {
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.setSize(300, 385);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.refresh.setBounds(220, 0, 80, 20);
        this.totalStuCountLabel.setBounds(30, 80, 120, 20);
        this.distributedStuCountLabel.setBounds(30, 180, 120, 20);
        this.undistributedStuCountLabel.setBounds(30, 280, 120, 20);

        this.totalStuCount.setBounds(170, 80, 100, 20);
        this.distributedStuCount.setBounds(170, 180, 100, 20);
        this.undistributedStuCount.setBounds(170, 280, 100, 20);
        this.add(this.refresh);
        this.add(this.totalStuCountLabel);
        this.add(this.distributedStuCountLabel);
        this.add(this.undistributedStuCountLabel);
        this.add(this.totalStuCount);
        this.add(this.distributedStuCount);
        this.add(this.undistributedStuCount);
        //通过服务获取信息
        map = studentService.getBaseInfo();
        this.setBaseInfo(map);
        //刷新的监听事件
        this.refresh.addActionListener((e) -> {
            map = studentService.getBaseInfo();
            this.setBaseInfo(map);
        });
    }
    //设置基本信息的显示
    public void setBaseInfo(Map<String, Integer> map) {
        this.totalStuCount.setText(map.get("total") + "人");
        this.distributedStuCount.setText(map.get("distributed") + "人");
        this.undistributedStuCount.setText(map.get("undistributed") + "人");
    }
}
