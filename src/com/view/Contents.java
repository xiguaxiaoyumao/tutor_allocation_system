package com.view;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * 1.登录之后主界面左侧的目录
 *
 * @date 2020/10/24 15:18
 */
public class Contents extends JTree {
    private DefaultTreeModel contentsModel = null;
    //目录的 各个节点
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统菜单");
    private DefaultMutableTreeNode student = new DefaultMutableTreeNode("学生");
    private DefaultMutableTreeNode studentInfo = new DefaultMutableTreeNode("学生信息");
    private DefaultMutableTreeNode tutor = new DefaultMutableTreeNode("导师");
    private DefaultMutableTreeNode tutorInfo = new DefaultMutableTreeNode("导师信息");
    private DefaultMutableTreeNode admin = new DefaultMutableTreeNode("管理员");
    private DefaultMutableTreeNode sysManage = new DefaultMutableTreeNode("系统管理");

    //构造函数传入分割面板的引用
    public Contents(JSplitPane jSplitPane) {

        this.root.add(this.student);
        this.root.add(this.tutor);
        this.root.add(this.admin);

        this.student.add(this.studentInfo);
        this.tutor.add(this.tutorInfo);
        this.admin.add(this.sysManage);

        this.contentsModel = new DefaultTreeModel(root);


        this.addTreeSelectionListener(new TreeSelectionListener() {
            //节点改变的监听事件
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                Object path = treeSelectionEvent.getNewLeadSelectionPath().getLastPathComponent();
                switch (path.toString()) {
                    case "学生信息": {
                        //选择学生信息让分割面板右侧显示学生信息的界面 下面作用相同
                        StudentInfo studentInfo = new StudentInfo();
                        jSplitPane.setRightComponent(studentInfo);
                        break;
                    }
                    case "导师信息": {
                        ToturInfo toturInfo = new ToturInfo();
                        jSplitPane.setRightComponent(toturInfo);
                        break;
                    }
                    case "系统管理": {
                        SysManage sysManage = new SysManage();
                        jSplitPane.setRightComponent(sysManage);
                        break;
                    }
                }
                jSplitPane.setDividerLocation(200);
            }
        });
        this.setModel(contentsModel);
    }
}
