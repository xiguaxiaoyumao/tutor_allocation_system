package com.view.box;

import com.pojo.Student;
import com.service.StudentService;
import com.util.ObUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * 1.学生信息查询模块
 * @date 2020/10/24 19:05
 */
public class StuSelectBox extends JPanel {
    private JLabel title = new JLabel("学生信息查询");
    private JLabel stuIdLabel = new JLabel("学号");
    private JTextField stuIdField = new JTextField();
    private JLabel stuNameLabel = new JLabel("姓名");
    private JTextField stuNameField = new JTextField();
    private JLabel stuGenderLabel = new JLabel("性别");
    private JRadioButton maleRadio = new JRadioButton("男");
    private JRadioButton femaleRadio = new JRadioButton("女");
    private JLabel stuClassLabel = new JLabel("班级");
    private JComboBox<String> classJComboBox = new JComboBox<>();
    private JLabel tutorLabel = new JLabel("导师");
    private JTextField tutorField = new JTextField();
    private JButton selectButton = new JButton("查询");

    //表格
    public JTable stuInfoTable = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private Vector<String> headVector = new Vector<String>();
    private Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
    private DefaultTableModel defaultTableModel;
    //获取服务对象
    private StudentService studentService = ObUtil.getStudentService();

    public StuSelectBox() {
        this.setSize(400, 600);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.title.setBounds(0, 0, 100, 15);
        this.add(this.title);
        this.maleRadio.setSelected(true);

        //表头添加数据
        this.headVector.add("学号");
        this.headVector.add("姓名");
        this.headVector.add("性别");
        this.headVector.add("班级");
        this.headVector.add("导师");


        //表模型
        this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
        this.stuInfoTable.setModel(defaultTableModel);
        this.jScrollPane.getViewport().add(stuInfoTable);
        //不能重新排序
        this.stuInfoTable.getTableHeader().setReorderingAllowed(false);
        //居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.stuInfoTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        this.jScrollPane.setBounds(30, 180, 340, 400);

        this.stuIdLabel.setBounds(30, 30, 55, 20);
        this.stuIdField.setBounds(85, 30, 100, 20);
        this.stuNameLabel.setBounds(215, 30, 55, 20);
        this.stuNameField.setBounds(270, 30, 100, 20);
        this.stuGenderLabel.setBounds(30, 80, 55, 20);
        this.maleRadio.setBounds(85, 80, 50, 20);
        this.femaleRadio.setBounds(135, 80, 50, 20);
        this.stuClassLabel.setBounds(215, 80, 55, 20);
        this.classJComboBox.setBounds(270, 80, 100, 20);
        this.tutorLabel.setBounds(30, 130, 55, 20);
        this.tutorField.setBounds(85, 130, 100, 20);
        this.selectButton.setBounds(270, 130, 100, 20);

        this.add(this.stuIdLabel);
        this.add(this.stuIdField);
        this.add(this.stuNameLabel);
        this.add(this.stuNameField);
        this.add(this.stuGenderLabel);
        this.add(this.maleRadio);
        this.add(this.femaleRadio);
        this.add(this.stuClassLabel);
        this.add(this.classJComboBox);
        this.add(this.tutorLabel);
        this.add(this.tutorField);
        this.add(this.selectButton);
        this.add(this.jScrollPane);

        List<Student> classType = this.studentService.selectClassType();
        //初始化班级信息
        this.classJComboBox.addItem("");
        for (Student type : classType) {
            this.classJComboBox.addItem(type.getStuClass());
        }
        //多条件查询监听事件
        this.selectButton.addActionListener((e) -> {
            dataVector.clear();
            //更新填写类容来拼接SQL语句
            String sql = appendSql();
            List<Student> students = studentService.select(sql);
            for (Student student : students) {
                Vector<String> vector = new Vector<>();
                vector.add(student.getStuId());
                vector.add(student.getStuName());
                vector.add(student.getGender());
                vector.add(student.getStuClass());
                vector.add(student.getTutor());
                dataVector.add(vector);
            }
            this.defaultTableModel = new DefaultTableModel(dataVector, headVector);
            this.stuInfoTable.setModel(defaultTableModel);
        });
    }
    //根据填写条件拼接SQL了语句 填了就加条件 不填就不加
    public String appendSql() {
        //先拼接一个where 1 = 1 这里是个技巧
        StringBuffer sb = new StringBuffer("select * from student where 1 = 1 ");
        if (!stuIdField.getText().isEmpty()) {
            sb.append("and stuId = '" + stuIdField.getText() + "' ");
        }
        if (!stuNameField.getText().isEmpty()) {
            sb.append("and stuName = '" + stuNameField.getText() + "' ");
        }
        if (maleRadio.isSelected()) {
            sb.append("and gender = '男' ");
        } else if (femaleRadio.isSelected()) {
            sb.append("and gender = '女' ");
        }
        if (!classJComboBox.getSelectedItem().toString().isEmpty()) {
            sb.append("and stuClass = '" + classJComboBox.getSelectedItem().toString() + "' ");
        }
        if (!tutorField.getText().isEmpty()) {
            sb.append("and stuName = '" + tutorField.getText() + "' ");
        }
        return sb.toString();
    }
}
