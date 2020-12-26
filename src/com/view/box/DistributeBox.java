package com.view.box;

import com.service.SysService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * @author zht
 * @date 2020/10/25 9:54
 */
public class DistributeBox extends JPanel {

    private JLabel title = new JLabel("导师分配");
    private JButton start = new JButton("开始分配");
    private JButton restart = new JButton("重新分配");
    private JButton exportExcel = new JButton("导出分配结果");
    //获取服务对象
    private SysService sysService = ObUtil.getSysService();

    private File file = null;
    public DistributeBox() {
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.setSize(300, 385);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        this.start.setBounds(90, 80, 120, 20);
        this.restart.setBounds(90, 180, 120, 20);
        this.exportExcel.setBounds(90, 280, 120, 20);

        this.add(this.title);
        this.add(this.start);
        this.add(this.restart);
        this.add(this.exportExcel);
        //开始分配的点击事件
        this.start.addActionListener((e) -> {
            //直接调用服务中写好的相关业务
            boolean distribute = sysService.distribute();
            if (distribute == true) {
                JOptionPane.showMessageDialog(null, "分配成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "分配失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        //重新分配的点击事件
        this.restart.addActionListener((e) -> {
            //直接调用服务中写好的相关业务
            boolean distribute = sysService.redistribute();
            if (distribute == true) {
                JOptionPane.showMessageDialog(null, "分配成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "分配失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        //导出分配结果的监听事件
        this.exportExcel.addActionListener((e) -> {
            //文件选择框
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showDialog(new JLabel(), "选择保存路径");
            if (jfc.getSelectedFile() != null) {
                file = new File(jfc.getSelectedFile().getPath());
                //调用服务中的相关业务
                sysService.outputExcel(file);
                JOptionPane.showMessageDialog(null, "导出成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
