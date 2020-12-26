package com.view;

import com.pojo.Admin;
import com.service.AdminService;
import com.util.ObUtil;
import com.util.SesUtil;
import javax.swing.*;
import java.sql.SQLException;

/**
 * 1.登录界面
 *
 * @date 2020/10/24 13:49
 */
public class Login extends JFrame {
    private JLabel usernameLabel = new JLabel("用户名");
    private JLabel passwordLabel = new JLabel("密码");
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JRadioButton adminRadio = new JRadioButton("管理员");
    private JRadioButton teacherRadio = new JRadioButton("导师");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton loginButton = new JButton("登录");
    private JButton registButton = new JButton("注册");

    private AdminService adminService = ObUtil.getAdminService();

    public Login() {
        /* 窗口基本设置 */
        this.setLayout(null);
        this.setTitle("毕业生导师分配系统 - 登录");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        /* 控件基本设置 */
        this.usernameLabel.setBounds(150, 100, 100, 20);
        this.passwordLabel.setBounds(150, 150, 100, 20);

        this.usernameField.setBounds(250, 100, 100, 20);
        this.passwordField.setBounds(250, 150, 100, 20);

        this.adminRadio.setBounds(150, 180, 100, 20);
        this.teacherRadio.setBounds(250, 180, 100, 20);

        this.loginButton.setBounds(150, 210, 90, 20);
        this.registButton.setBounds(250, 210, 90, 20);

        /* 添加控件 */
        this.add(this.usernameLabel);
        this.add(this.passwordLabel);

        this.add(this.usernameField);
        this.add(this.passwordField);

        this.add(this.adminRadio);
        this.add(this.teacherRadio);
        this.adminRadio.setSelected(true);
        this.teacherRadio.setEnabled(false);
        this.buttonGroup.add(this.adminRadio);
        this.buttonGroup.add(this.teacherRadio);

        this.add(this.loginButton);
        this.add(this.registButton);

        /* 登录 */
        this.loginButton.addActionListener((e) -> {
            try {
                this.login();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.setVisible(true);
    }

    public void login() throws SQLException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            //调用管理员服务中的登录
            Admin admin = adminService.login(usernameField.getText(), passwordField.getText());
            if (admin == null) {
                //没有查询到相关的信息
                JOptionPane.showMessageDialog(null, "用户名或者密码错误", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                //查到就存到会话类，要用的时候随时取出 后面修改密码就可以取出修改密码
                SesUtil.setObject("admin", admin);
                this.setVisible(false);
                new MainView();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
