package com.view.box;

import com.pojo.Admin;
import com.service.AdminService;
import com.util.ObUtil;
import com.util.SesUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 1.修改密码模块
 *
 * @date 2020/10/25 9:24
 */
public class ChangePassBox extends JPanel {

    private JLabel title = new JLabel("修改密码");
    private JLabel oldPasswordLabel = new JLabel("原密码");
    private JLabel newPasswordLabel = new JLabel("新密码");
    private JLabel confirmPassworld = new JLabel("确认新密码");

    private JPasswordField oldPassword = new JPasswordField();
    private JPasswordField newPassword = new JPasswordField();
    private JPasswordField confirm = new JPasswordField();

    private JButton changePass = new JButton("确认修改");
    //管理员服务类获取
    private AdminService adminService = ObUtil.getAdminService();
    private Admin admin = null;

    public ChangePassBox() {
        //从会话中获取管理员信息
        admin = (Admin) SesUtil.getObject("admin");
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.setSize(300, 200);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        this.oldPasswordLabel.setBounds(30, 30, 100, 20);
        this.newPasswordLabel.setBounds(30, 70, 100, 20);
        this.confirmPassworld.setBounds(30, 110, 100, 20);


        this.oldPassword.setBounds(100, 30, 140, 20);
        this.newPassword.setBounds(100, 70, 140, 20);
        this.confirm.setBounds(100, 110, 140, 20);
        this.changePass.setBounds(100, 150, 100, 20);

        this.add(this.oldPasswordLabel);
        this.add(this.newPasswordLabel);
        this.add(this.confirmPassworld);
        this.add(this.oldPassword);
        this.add(this.newPassword);
        this.add(this.confirm);
        this.add(this.changePass);

        this.changePass.addActionListener((e) -> {
            reserPassword();
        });
    }

    private void reserPassword() {
        //三个文本框不为空才开始修改操作
        if (oldPassword.getText().isEmpty() || newPassword.getText().isEmpty() || confirm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "密码不能为空", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (!admin.getAdPassword().equals(oldPassword.getText())) {
                JOptionPane.showMessageDialog(null, "原密码错误", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!newPassword.getText().equals(confirm.getText())) {
                JOptionPane.showMessageDialog(null, "新密码不一致", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //将admin密码修改成新密码
            this.admin.setAdPassword(newPassword.getText());
            //调用服务更新到数据库中
            if (adminService.resetPassword(admin) != 1) {
                JOptionPane.showMessageDialog(null, "密码修改失败", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "密码修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
