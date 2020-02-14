package FullJavaPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deneme {
    private JFrame jFrame;
    private JScrollPane jScrollPane;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButton;
    DefaultTableModel defaultTableModel;
    String[] sütun;
    Object[] satır;
    Connection connection;
    Dimension dimension;

    Deneme() {
        setjFrame();
        setjScrollPane();
        setjTable();
        setjTextField();
        setjButton();
    }

    private void setjFrame() {
        jFrame = new JFrame("Deneme");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(Color.orange);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setBounds(0, 0, dimension.width, dimension.height);
    }

    private void setjScrollPane() {
        jScrollPane = new JScrollPane();
        jFrame.getContentPane().add(jScrollPane);
        jScrollPane.setBackground(Color.cyan);
        jScrollPane.setBounds(0, 0, dimension.width / 2, 300);
        jFrame.getContentPane().add(jScrollPane);
    }

    private void setjTable() {
        jTable = new JTable();
        defaultTableModel = new DefaultTableModel();
//        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(defaultTableModel);
        jScrollPane.setViewportView(jTable);
        jTable.setFillsViewportHeight(true);
        dbConnect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from 90kalem");
            sütun = new String[resultSet.getMetaData().getColumnCount()];
            satır = new Object[resultSet.getMetaData().getColumnCount()];
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                sütun[i] = resultSet.getMetaData().getColumnName(i + 1);
                defaultTableModel.setColumnIdentifiers(sütun);
            }

            while (resultSet.next()) {
                for (int ix = 0; ix < resultSet.getMetaData().getColumnCount(); ix++) {
                    satır[ix] = resultSet.getString(ix + 1);
                }
                defaultTableModel.addRow(satır);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ihale?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setjTextField() {
        jTextField = new JTextField();
        jFrame.getContentPane().add(jTextField);
        jTextField.setBounds(0, 320, 120, 20);
    }

    private void setjButton() {
        jButton = new JButton("Kaydet");
        jFrame.getContentPane().add(jButton);
        jButton.setBounds(130, 320, 120, 20);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                satır[1] = jTextField.getText();
                dbConnect();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "insert into 90kalem(JenerikAdı) values(?)");
                    Statement statement = connection.createStatement();
                    preparedStatement.setString(1, jTextField.getText());
                    preparedStatement.executeQuery();
                    ResultSet resultSet = statement.executeQuery("select SıraNo from 90kalem");
                    while (resultSet.next()) {
                        satır[0] = resultSet.getString(1);
                    }
                    defaultTableModel.addRow(satır);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) {
        Deneme deneme = new Deneme();
    }
}
