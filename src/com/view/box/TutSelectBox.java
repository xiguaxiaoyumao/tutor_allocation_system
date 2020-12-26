package com.view.box;


import com.pojo.Student;
import com.pojo.Tutor;
import com.service.TutorService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * 1.导师信息查询模块
 *
 * @date 2020/10/24 23:36
 */
public class TutSelectBox extends JPanel {
    private JLabel title = new JLabel("导师信息查询");
    private JLabel tutIdLabel = new JLabel("编号");
    private JTextField tutIdField = new JTextField();
    private JLabel tutNameLabel = new JLabel("姓名");
    private JTextField tutNameField = new JTextField();
    private JLabel tutGenderLabel = new JLabel("性别");
    private JRadioButton maleRadio = new JRadioButton("男");
    private JRadioButton femaleRadio = new JRadioButton("女");
    private JLabel tutPostLabel = new JLabel("职称");
    private JComboBox<String> PostJComboBox = new JComboBox<>();
    private JLabel tutorDegreeLabel = new JLabel("学位");
    private JComboBox<String> degreeComboBox = new JComboBox<>();
    private JLabel tutorEduLabel = new JLabel("学历");
    private JComboBox<String> eduComboBox = new JComboBox<>();
    private JLabel stuCountLabel = new JLabel("已带人数");
    private JTextField stuCountField = new JTextField();
    private JButton selectButton = new JButton("查询");

    //表格
    public JTable tutInfoTable = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private Vector<String> headVector = new Vector<String>();
    private Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
    private DefaultTableModel defaultTableModel;

    private TutorService tutorService = ObUtil.getTutorService();

    public TutSelectBox() {
        this.setSize(400, 600);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.maleRadio.setSelected(true);

        //表头添加数据
        this.headVector.add("编号");
        this.headVector.add("姓名");
        this.headVector.add("性别");
        this.headVector.add("职称");
        this.headVector.add("学位");
        this.headVector.add("学历");
        this.headVector.add("已带");
        //表模型
        this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
        this.tutInfoTable.setModel(defaultTableModel);
        this.jScrollPane.getViewport().add(tutInfoTable);
        //不能重新排序
        this.tutInfoTable.getTableHeader().setReorderingAllowed(false);
        //居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.tutInfoTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        this.jScrollPane.setBounds(30, 230, 340, 350);


        this.tutIdLabel.setBounds(30, 30, 55, 20);
        this.tutIdField.setBounds(85, 30, 100, 20);
        this.tutNameLabel.setBounds(215, 30, 55, 20);
        this.tutNameField.setBounds(270, 30, 100, 20);
        this.tutGenderLabel.setBounds(30, 80, 55, 20);
        this.maleRadio.setBounds(85, 80, 50, 20);
        this.femaleRadio.setBounds(135, 80, 50, 20);
        this.tutPostLabel.setBounds(215, 80, 55, 20);
        this.PostJComboBox.setBounds(270, 80, 100, 20);
        this.tutorDegreeLabel.setBounds(30, 130, 55, 20);
        this.degreeComboBox.setBounds(85, 130, 100, 20);
        this.tutorEduLabel.setBounds(215, 130, 55, 20);
        this.eduComboBox.setBounds(270, 130, 100, 20);
        this.stuCountLabel.setBounds(30, 180, 55, 20);
        this.stuCountField.setBounds(85, 180, 100, 20);
        this.selectButton.setBounds(270, 180, 100, 20);


        this.add(this.tutIdLabel);
        this.add(this.tutIdField);
        this.add(this.tutNameLabel);
        this.add(this.tutNameField);
        this.add(this.tutGenderLabel);
        this.add(this.maleRadio);
        this.add(this.femaleRadio);
        this.add(this.tutPostLabel);
        this.add(this.PostJComboBox);
        this.add(this.tutorDegreeLabel);
        this.add(this.degreeComboBox);
        this.add(this.tutorEduLabel);
        this.add(this.eduComboBox);
        this.add(this.stuCountLabel);
        this.add(this.stuCountField);
        this.add(this.selectButton);
        this.add(this.jScrollPane);
        //导师类型选择框添加数据
        this.PostJComboBox.addItem("");
        this.PostJComboBox.addItem("助教");
        this.PostJComboBox.addItem("讲师");
        this.PostJComboBox.addItem("副教授");
        this.PostJComboBox.addItem("教授");
        //学位类型选择框添加数据
        this.degreeComboBox.addItem("");
        this.degreeComboBox.addItem("学士");
        this.degreeComboBox.addItem("硕士");
        this.degreeComboBox.addItem("博士");
        //学历类型添加数据
        this.eduComboBox.addItem("");
        this.eduComboBox.addItem("专科");
        this.eduComboBox.addItem("本科");
        this.eduComboBox.addItem("硕士研究生");
        this.eduComboBox.addItem("博士研究生");
        //查询按钮添加事件
        this.selectButton.addActionListener((e) -> {
            dataVector.clear();
            //获取拼接的SQL语句
            String sql = appendSql();
            //调用服务的查询方法
            List<Tutor> tutors = tutorService.select(sql);
            for (Tutor tutor : tutors) {
                Vector<String> vector = new Vector<>();
                vector.add(tutor.getTutId());
                vector.add(tutor.getTutName());
                vector.add(tutor.getGender());
                vector.add(tutor.getPost());
                vector.add(tutor.getDegree());
                vector.add(tutor.getEdu());
                vector.add(tutor.getStuCount().toString());
                dataVector.add(vector);
            }
            this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
            this.tutInfoTable.setModel(defaultTableModel);
        });
    }

    //根据填写条件拼接SQL语句
    private String appendSql() {
        StringBuffer sb = new StringBuffer("select * from tutor where 1 = 1 ");
        if (!tutIdField.getText().isEmpty()) {
            sb.append("and tutId = '" + tutIdField.getText() + "'");
        }
        if (!tutNameField.getText().isEmpty()) {
            sb.append("and tutName = '" + tutNameField.getText() + "'");
        }
        if (maleRadio.isSelected()) {
            sb.append("and gender = '男' ");
        } else if (femaleRadio.isSelected()) {
            sb.append("and gender = '女' ");
        }
        if (!PostJComboBox.getSelectedItem().toString().isEmpty()) {
            sb.append("and post = '" + PostJComboBox.getSelectedItem().toString() + "' ");
        }
        if (!degreeComboBox.getSelectedItem().toString().isEmpty()) {
            sb.append("and degree = '" + degreeComboBox.getSelectedItem().toString() + "' ");
        }
        if (!eduComboBox.getSelectedItem().toString().isEmpty()) {
            sb.append("and edu = '" + eduComboBox.getSelectedItem().toString() + "' ");
        }
        if (!stuCountField.getText().isEmpty()) {
            sb.append("and stuCount = '" + stuCountField.getText() + "'");
        }
        return sb.toString();
    }
}
