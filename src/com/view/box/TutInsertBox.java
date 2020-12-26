package com.view.box;

import com.service.TutorService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * 1.导入导师信息模块
 *
 * @date 2020/10/24 19:14
 */
public class TutInsertBox extends JPanel {
    private JLabel title = new JLabel("导入导师信息");
    private JLabel excelLabel = new JLabel("导入Excel");
    private JButton excelField = new JButton("点击选择Excel文件");
    private JButton excelButton = new JButton("导入");
    private File file = null;
    private TutorService tutorService = ObUtil.getTutorService();

    public TutInsertBox() {
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.setSize(300, 200);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        this.excelLabel.setBounds(75, 35, 150, 20);
        this.excelField.setBounds(75, 90, 150, 20);
        this.excelButton.setBounds(75, 145, 150, 20);

        this.add(this.excelLabel);
        this.add(this.excelField);
        this.add(this.excelButton);
        //添加
        this.excelField.addActionListener((e) -> {
            //文件选择器
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName();
                    return name.toLowerCase().endsWith(".xls");
                }

                @Override
                public String getDescription() {
                    return "*.xls";
                }
            });
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.showDialog(new JLabel(), "选择工作簿");
            if (jfc.getSelectedFile() != null) {
                file = new File(jfc.getSelectedFile().getPath());
                excelField.setText(file.getPath());
            }
        });
        //导入按钮的监听事件
        this.excelButton.addActionListener((e) -> {
            if (file == null) {
                JOptionPane.showMessageDialog(null, "请选择文件", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                //调用服务导入
                int i = tutorService.insertFromExcel(file);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "数据导入成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
