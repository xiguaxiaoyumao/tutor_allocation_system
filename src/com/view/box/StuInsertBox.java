package com.view.box;

import com.service.StudentService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * 1.导入学生信息的模块
 *
 * @date 2020/10/24 19:14
 */
public class StuInsertBox extends JPanel {
    private JLabel title = new JLabel("导入学生信息");
    private JLabel excelLabel = new JLabel("导入Excel");
    private JButton excelField = new JButton("点击选择Excel文件");
    private JButton excelButton = new JButton("导入");
    private File file = null;
    private StudentService studentService = ObUtil.getStudentService();

    public StuInsertBox() {
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

        this.excelField.addActionListener((e) -> {
            //文件选择框
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {
                //匿名内部类 只允许选择.xls格式的文件
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
                int i = studentService.insertFromExcel(file);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "数据导入成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
