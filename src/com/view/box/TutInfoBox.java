package com.view.box;

import com.pojo.Post;
import com.service.PostService;
import com.service.TutorService;
import com.util.ObUtil;
import com.util.SesUtil;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 1.导师基本信息模块
 *
 * @date 2020/10/24 19:19
 */
public class TutInfoBox extends JPanel {
    private JLabel title = new JLabel("基本信息");
    private JLabel postLabel = new JLabel("职称：");
    private JComboBox<Post> postComboBox = new JComboBox<Post>();
    private JLabel stuCountLabel = new JLabel("能带人数：");
    private JTextField stuCountField = new JTextField();
    private JButton confirmChange = new JButton("确认修改");
    private JLabel totalTutCountLabel = new JLabel("导师总数：");
    private JLabel canCountLabel = new JLabel("总共能带学生数：");
    private JLabel haveCountLabel = new JLabel("总共已带学生数：");
    private JButton refresh = new JButton("刷新");

    private JLabel totalTutCount = new JLabel("0人");
    private JLabel canCount = new JLabel("0人");
    private JLabel haveCount = new JLabel("0人");

    private PostService postService = ObUtil.getPostService();
    private TutorService tutorService = ObUtil.getTutorService();
    Map<String, Integer> map = null;

    public TutInfoBox() {
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.setSize(300, 385);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        this.refresh.setBounds(220, 0, 80, 20);
        this.postLabel.setBounds(30, 30, 120, 20);
        this.postComboBox.setBounds(170, 30, 100, 20);
        this.stuCountLabel.setBounds(30, 80, 120, 20);
        this.stuCountField.setBounds(170, 80, 100, 20);
        this.confirmChange.setBounds(100, 130, 100, 20);
        this.totalTutCountLabel.setBounds(30, 180, 120, 20);
        this.canCountLabel.setBounds(30, 230, 120, 20);
        this.haveCountLabel.setBounds(30, 280, 120, 20);
        this.totalTutCount.setBounds(170, 180, 100, 20);
        this.canCount.setBounds(170, 230, 100, 20);
        this.haveCount.setBounds(170, 280, 100, 20);

        this.add(this.postLabel);
        this.add(this.postComboBox);
        this.add(this.stuCountLabel);
        this.add(this.stuCountField);
        this.add(this.confirmChange);
        this.add(this.refresh);
        this.add(this.totalTutCountLabel);
        this.add(this.canCountLabel);
        this.add(this.haveCountLabel);
        this.add(this.totalTutCount);
        this.add(this.canCount);
        this.add(this.haveCount);

        //查询所有职称
        List<Post> posts = postService.selectAll();
        SesUtil.setObject("posts", posts);
        Post post1 = new Post();
        post1.setPostType("");
        post1.setCount("");
        postComboBox.addItem(post1);
        for (Post post : posts) {
            postComboBox.addItem(post);
        }
        //基本信息
        map = tutorService.selectInfo();
        this.setBaseInfo(map);
        //导师的类型
        postComboBox.addActionListener((e) -> {
            if ("助教".equals(((Post) postComboBox.getSelectedItem()).getPostType())) {
                stuCountField.setText(((Post) postComboBox.getSelectedItem()).getCount());
            }
            if ("讲师".equals(((Post) postComboBox.getSelectedItem()).getPostType())) {
                stuCountField.setText(((Post) postComboBox.getSelectedItem()).getCount());
            }
            if ("副教授".equals(((Post) postComboBox.getSelectedItem()).getPostType())) {
                stuCountField.setText(((Post) postComboBox.getSelectedItem()).getCount());
            }
            if ("教授".equals(((Post) postComboBox.getSelectedItem()).getPostType())) {
                stuCountField.setText(((Post) postComboBox.getSelectedItem()).getCount());
            }
        });
        //确认修改的监听事件
        this.confirmChange.addActionListener((e) -> {
            if (stuCountField.getText().isEmpty()) {
                return;
            } else {
                Post post = ((Post) postComboBox.getSelectedItem());
                post.setCount(stuCountField.getText());
                int update = postService.update(post);
                if (update == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //更新基本信息
        this.refresh.addActionListener((e) -> {
            //从服务中获取
            map = tutorService.selectInfo();
            this.setBaseInfo(map);
        });
    }

    //设置基本信息
    public void setBaseInfo(Map<String, Integer> map) {
        this.totalTutCount.setText(map.get("total") + "人");
        this.canCount.setText(map.get("distributed") + "人");
        this.haveCount.setText(map.get("undistributed") + "人");
    }
}
