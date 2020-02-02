package FullJavaPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Asd {
    JFrame jFrame;
    JScrollPane jScrollPane;
    DefaultTableModel defaultTableModel;
    JTable jTable;
    JButton kayitekle_btn;
    JTextField ihaleadi_tf;
    Connection connection;
    ResultSet resultSet;

    //7878
    Asd() {
        jFrame = new JFrame();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(600, 500);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        kayitekle_btn = new JButton("Kayıt Ekle");
        kayitekle_btn.setBounds(150, 250, 100, 20);
        ihaleadi_tf = new JTextField(20);
        ihaleadi_tf.setBounds(30, 250, 100, 20);

        jTable = new JTable();
        Object[] data = new Object[9];
        Object[] column = new Object[9];
        defaultTableModel = new DefaultTableModel();
        jTable.setModel(defaultTableModel);
        jTable.setAutoCreateRowSorter(true);
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);


        jScrollPane.setBounds(0, 0, 700, 200);
        jFrame.setContentPane(new JPanel());

        jFrame.getContentPane().add(jScrollPane);
        jFrame.getContentPane().add(kayitekle_btn);
        jFrame.getContentPane().add(ihaleadi_tf);
        jFrame.getContentPane().setLayout(null);

        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ihale?user=root&password=1234");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from 90kalem");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {


                column[0] = resultSetMetaData.getColumnName(1);
                column[1] = resultSetMetaData.getColumnName(2);
                column[2] = resultSetMetaData.getColumnName(3);
                column[3] = resultSetMetaData.getColumnName(4);
                column[4] = resultSetMetaData.getColumnName(5);
                column[5] = resultSetMetaData.getColumnName(6);
                column[6] = resultSetMetaData.getColumnName(7);
                column[7] = resultSetMetaData.getColumnName(8);
                column[8] = resultSetMetaData.getColumnName(9);

                defaultTableModel.setColumnIdentifiers(column);

                data[0] = resultSet.getString(1);
                data[1] = resultSet.getString(2);
                data[2] = resultSet.getString(3);
                data[3] = resultSet.getString(4);
                data[4] = resultSet.getString(5);
                data[5] = resultSet.getString(6);
                data[6] = resultSet.getString(7);
                data[7] = resultSet.getString(8);
                data[8] = resultSet.getString(9);
                defaultTableModel.addRow(data);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        kayitekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data[0] = ihaleadi_tf.getText();
                defaultTableModel.addRow(data);
            }
        });
    }


    public static void main(String[] args) {
        Asd asd = new Asd();
        asd.jFrame.setVisible(true);


    }
}