package FullJavaPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
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
        jFrame.setBounds(300, 300, 500, 600);
        jFrame.getContentPane().setBackground(Color.orange);

    }

    private void setjScrollPane() {
        jScrollPane = new JScrollPane();
        jFrame.getContentPane().add(jScrollPane);
        jScrollPane.setBackground(Color.cyan);
        jScrollPane.setBounds(0, 0, 500, 300);
        jFrame.getContentPane().add(jScrollPane);
    }

    private void setjTable() {
        jTable = new JTable();
        defaultTableModel = new DefaultTableModel();
        jTable.setModel(defaultTableModel);
        jScrollPane.setViewportView(jTable);
        dbConnect();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from 90kalem");
            String[] sütun = new String[resultSet.getMetaData().getColumnCount()];
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                sütun[i] = resultSet.getMetaData().getColumnName(i + 1);
                defaultTableModel.setColumnIdentifiers(sütun);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        satır = new Object[2];
        satır[0] = "asdasd";
        satır[1] = "asd1231edasd";
        defaultTableModel.addRow(satır);

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
                satır[0] = jTextField.getText();
                defaultTableModel.addRow(new Object[]{satır[0]});
                dbConnect();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("insert into 90kalem(SıraNo) values( '" + jTextField.getText() + "')");

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
