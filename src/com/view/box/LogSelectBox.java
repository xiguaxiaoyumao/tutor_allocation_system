package com.view.box;

import com.pojo.Log;
import com.pojo.Student;
import com.service.LogService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * @author zht
 * @date 2020/10/25 9:35
 */
public class LogSelectBox extends JPanel {
    private JLabel title = new JLabel("日志查询");
    private JLabel opTypeLabel = new JLabel("操作类型");
    private JComboBox<String> opTypeComboBox = new JComboBox<>();
    private JButton selectButton = new JButton("查询");


    //表格
    public JTable logInfoTable = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private Vector<String> headVector = new Vector<String>();
    private Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
    private DefaultTableModel defaultTableModel;
    //获取服务
    private LogService logService = ObUtil.getLogService();

    public LogSelectBox() {

        opTypeComboBox.addItem("");
        opTypeComboBox.addItem("添加");
        opTypeComboBox.addItem("删除");
        opTypeComboBox.addItem("修改");
        opTypeComboBox.addItem("查询");
        this.setSize(400, 600);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);

        //表头添加数据
        this.headVector.add("操作类型");
        this.headVector.add("详情描述");
        this.headVector.add("时间");

        //表模型
        this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
        this.logInfoTable.setModel(defaultTableModel);
        this.jScrollPane.getViewport().add(logInfoTable);
        //不能重新排序
        this.logInfoTable.getTableHeader().setReorderingAllowed(false);
        //居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.logInfoTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        this.jScrollPane.setBounds(30, 80, 340, 500);

        this.opTypeLabel.setBounds(30, 30, 55, 20);
        this.opTypeComboBox.setBounds(85, 30, 100, 20);
        this.selectButton.setBounds(270, 30, 100, 20);

        this.add(this.opTypeLabel);
        this.add(this.opTypeComboBox);
        this.add(this.selectButton);
        this.add(this.jScrollPane);
        //查询监听事件
        this.selectButton.addActionListener((e) -> {
            List<Log> logs = logService.select(opTypeComboBox.getSelectedItem().toString());
            dataVector.clear();
            for (Log log : logs) {
                Vector<String> vector = new Vector<>();
                vector.add(log.getOpType());
                vector.add(log.getOpDetail());
                vector.add(log.getOpTime().toString());
                dataVector.add(vector);
            }
            this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
            this.logInfoTable.setModel(defaultTableModel);
        });
    }
}
