package FullJavaPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GiderTakip {
    private JFrame jFrame;
    private JPanel jPanel_anapanel;
    private JPanel jPanel_adminpanel;
    private JButton jButton_databaseekle;
    private Connection connection;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();


    GiderTakip() {
        setjFrame();
        setjPanel_anapanel();
        setjPanel_adminpanel();
        setjButton_database();
        setConnection();

    }

    private void setjFrame() {
        jFrame = new JFrame("Gider Takip");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(dimension.width / 3, dimension.height);
        jFrame.setLayout(null);

    }

    private void setjPanel_anapanel() {
        jPanel_anapanel = new JPanel();
        jFrame.setContentPane(jPanel_anapanel);
        jPanel_anapanel.setBackground(Color.orange);
        jPanel_anapanel.setLayout(null);
    }

    private void setjPanel_adminpanel() {
        jPanel_adminpanel = new JPanel();
        jFrame.getContentPane().add(jPanel_adminpanel);
        jPanel_adminpanel.setBackground(Color.cyan);
        jPanel_adminpanel.setBounds(0, 0, dimension.width / 2, dimension.height / 2);
        jPanel_adminpanel.setLayout(null);
    }

    private void setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setjButton_database() {
        jButton_databaseekle = new JButton("Database");
        jPanel_adminpanel.add(jButton_databaseekle);
        jButton_databaseekle.setBounds(20, 20, 120, 30);
        jButton_databaseekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu jPopupMenu = new JPopupMenu();
                GroupLayout groupLayout = new GroupLayout(jPopupMenu);
                jPopupMenu.setLayout(groupLayout);

                JTextField jTextField = new JTextField(20);
                JLabel jLabel = new JLabel("Bağlantı Durumu");
                JButton jButton = new JButton("Ekle");
                JButton jButton1 = new JButton("Sil");
                JButton jButton2 = new JButton("Listele");


                groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                        .addComponent(jTextField)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(jButton)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(jLabel)));
                groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                        .addComponent(jTextField)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(jButton)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(jLabel)));
                if (connection == null) {
                    jLabel.setText("Bağlantı yok..");
                } else {
                    jLabel.setText("Bağlandı..");
                }
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeQuery("create database " + jTextField.getText());
                            jLabel.setText(jTextField.getText() + " database oluşturuldu..");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("drop database " + jTextField.getText());
                            jLabel.setText(jTextField.getText() + " silindi..");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                jButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("show databases");
                            while (resultSet.next()) {
                                jLabel.setText(resultSet.getString(1));
                                System.out.println(resultSet.getString("database"));
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                jPopupMenu.show(jPanel_adminpanel, 300, 30);
            }
        });

    }


    public static void main(String[] args) {
        GiderTakip giderTakip = new GiderTakip();
    }
}
