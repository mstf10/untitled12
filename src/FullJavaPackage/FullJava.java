package FullJavaPackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class FullJava {
    //Jframee
    private JFrame anapencere;
    private JFrame yenipencere;
    //jpanel
    private JPanel anapanel;
    private JPanel loginpanel;
    private JPanel adminpanel;
    private JPanel içerikPanel;
    //jtextfield
    private JTextField kullanıcıadı_tf;
    private JTextField kullanıcışifre_tf;
    private JTextField kullanıcıbilgi_tf;
    private JTextField databasebilgi_tf;
    private JTextField tabloekle_tf;
    //jlabel
    private JLabel kullanıcısonuc_lbl;

    //jbutton
    private JButton kullanıcıgiriş_btn;
    private JButton kullanıcıçıkış_btn;
    private JButton kullanıcıekle_btn;
    private JButton kullanıcıbul_btn;
    private JButton kullanıcısil_btn;
    private JButton databaseekle_btn;
    private JButton databaselistele_btn;
    private JButton tabloekle_btn;
    //Jlist
    private JList databaselist_jlist;
    //Jtable
    private JTable ihalesabit_jtable;
    //Jlistmodel
    private DefaultListModel databaselistmodel;

    //dimension
    Dimension dimension;
    //connection
    Connection connection;
    //


    FullJava() {
        anaPencere();
        anaPanel();
        loginPanel();
        adminPanel();
        içerikPanel();
        setKullanıcıgiriş_btn();
        setKullanıcıçıkış_btn();
        setKullanıcıbul_btn();
        setKullanıcıekle_btn();
        setKullanıcısil_btn();
        setDatabaseekle_btn();
        setDatabaselistele_btn();
        setDatabaselist_jlist();
        setTabloekle_btn();
        ihalesabit();
    }


    private void anaPencere() {
        anapencere = new JFrame("Ana Pencere");
        anapencere.setVisible(true);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        anapencere.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        anapencere.setLayout(null);
        anapencere.setSize(dimension.width / 2, dimension.height / 2);
        anapencere.setLocationRelativeTo(null);
    }

    private void anaPanel() {
        anapanel = new JPanel();
        anapanel.setBackground(Color.cyan);
        anapanel.setSize(dimension.width, dimension.height);
        anapanel.setLayout(null);
    }

    private void loginPanel() {
        loginpanel = new JPanel();
        anapencere.add(loginpanel);
        loginpanel.setBackground(Color.orange);
        loginpanel.setBounds(0, 0, dimension.width / 8, dimension.height);
        loginpanel.setLayout(null);
        kullanıcıadı_tf = new JTextField(20);
        kullanıcıadı_tf.setToolTipText("Kullanıcı Adını Gir");
        kullanıcışifre_tf = new JPasswordField(20);
        kullanıcışifre_tf.setToolTipText("Şİfre Gir");
        kullanıcıgiriş_btn = new JButton("Giriş");
        kullanıcıgiriş_btn.setToolTipText("Giriş İçin Tıkla..");
        loginpanel.add(kullanıcıadı_tf).setBounds(10, 10, 60, 20);
        loginpanel.add(kullanıcışifre_tf).setBounds(10, 30, 60, 20);
        loginpanel.add(kullanıcıgiriş_btn).setBounds(10, 50, 60, 20);
    }

    private void adminPanel() {
        adminpanel = new JPanel();
        anapanel.add(adminpanel);
        adminpanel.setLayout(null);
        adminpanel.setBackground(Color.darkGray);
        adminpanel.setBounds(dimension.width - dimension.width / 8, 0, dimension.width / 8, dimension.height);
        kullanıcıçıkış_btn = new JButton("Çıkış");
        kullanıcıbilgi_tf = new JTextField(12);
        kullanıcıekle_btn = new JButton("Kullanıcı Ekle");
        kullanıcıbul_btn = new JButton("Kullanıcı Bul");
        kullanıcısonuc_lbl = new JLabel("İşlem Sonucu:");
        kullanıcısonuc_lbl.setForeground(Color.orange);
        kullanıcısil_btn = new JButton("Kullanıcı Sil");
        databasebilgi_tf = new JTextField(20);
        databaseekle_btn = new JButton("Database Ekle");
        databaselistele_btn = new JButton("Database Listele");
        databaselist_jlist = new JList();
        adminpanel.add(kullanıcıçıkış_btn).setBounds(80, 0, 80, 20);
        adminpanel.add(kullanıcıbilgi_tf).setBounds(80, 70, 120, 20);
        adminpanel.add(kullanıcıekle_btn).setBounds(120, 90, 120, 20);
        adminpanel.add(kullanıcıbul_btn).setBounds(0, 90, 120, 20);
        adminpanel.add(kullanıcısil_btn).setBounds(120, 110, 120, 20);
        adminpanel.add(kullanıcısonuc_lbl).setBounds(0, 150, 220, 20);
        adminpanel.add(databasebilgi_tf).setBounds(80, 200, 120, 20);
        adminpanel.add(databaseekle_btn).setBounds(0, 230, 120, 20);
        adminpanel.add(databaselistele_btn).setBounds(120, 230, 120, 20);
        adminpanel.add(databaselist_jlist).setBounds(0, 250, 220, 200);
    }


    private void içerikPanel() {
        içerikPanel = new JPanel();
        içerikPanel.setBounds(0, 0, dimension.width - dimension.width / 8, dimension.height);
        anapanel.add(içerikPanel);
        içerikPanel.setBackground(Color.getColor("#97ffff"));



    }

    private void loginDbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/BASH?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ihaleDbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ihale?user=root&password=1234");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void setKullanıcıgiriş_btn() {
        kullanıcıgiriş_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginDbConnect();
                    PreparedStatement preparedStatement = connection.prepareStatement("select kullanici_adi,kullanici_sifre from logindb where kullanici_adi=? and kullanici_sifre=?");
                    preparedStatement.setString(1, kullanıcıadı_tf.getText());
                    preparedStatement.setString(2, kullanıcışifre_tf.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        anapencere.remove(loginpanel);
                        anapencere.add(anapanel);
                        anapanel.revalidate();
                        anapanel.repaint();
                        anapencere.setExtendedState(Frame.MAXIMIZED_BOTH);
                        kullanıcıadı_tf.setText("");
                        kullanıcışifre_tf.setText("");
                    } else {
                        kullanıcıgiriş_btn.setText("hata");
                    }

                } catch (SQLException ef) {
                    ef.printStackTrace();
                }
            }
        });

    }

    private void setKullanıcıçıkış_btn() {
        kullanıcıçıkış_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anapencere.remove(anapanel);
                yenipencere.dispose();
                anapencere.add(loginpanel);
                anapencere.repaint();
                anapencere.revalidate();
                anapencere.setSize(dimension.width / 2, dimension.height / 2);
                anapencere.setLocationRelativeTo(null);
            }
        });
    }

    private void setKullanıcıbul_btn() {
        kullanıcıbul_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDbConnect();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("select kullanici_adi from logindb where kullanici_adi=?");
                    preparedStatement.setString(1, kullanıcıbilgi_tf.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        kullanıcısonuc_lbl.setText("Kullanıcı Adı:  " + resultSet.getString("kullanici_adi"));
                    } else {
                        kullanıcısonuc_lbl.setText("Kullanıcı Bulunamadı..");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void setKullanıcıekle_btn() {
        kullanıcıekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDbConnect();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into logindb(kullanici_adi) values(?)");
                    preparedStatement.setString(1, kullanıcıbilgi_tf.getText());
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void setKullanıcısil_btn() {
        kullanıcısil_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDbConnect();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("delete from logindb where (kullanici_adi=?)");
                    preparedStatement.setString(1, kullanıcıbilgi_tf.getText());
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setDatabaseekle_btn() {
        databaseekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnect();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeQuery("CREATE DATABASE " + databasebilgi_tf.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private void setDatabaselistele_btn() {
        databaselistele_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnect();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("show databases");

                    while (resultSet.next()) {

                        databaselistmodel.addElement(resultSet.getString("database"));
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void setDatabaselist_jlist() {
        databaselistmodel = new DefaultListModel();
        databaselist_jlist.setModel(databaselistmodel);
        yenipencere = new JFrame();
        tabloekle_tf = new JTextField(20);
        tabloekle_btn = new JButton("Tablo Ekle");
        databaselist_jlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                yenipencere.setVisible(true);
                yenipencere.setSize(600, 400);
                if (e.getValueIsAdjusting()) {
                    String değer = (String) databaselist_jlist.getSelectedValue();
                    yenipencere.setTitle(değer);
                    yenipencere.setLayout(null);
                    yenipencere.add(tabloekle_tf);
                    yenipencere.add(tabloekle_btn);
                    tabloekle_tf.setBounds(20, 10, 100, 20);
                    tabloekle_btn.setBounds(120, 10, 100, 20);


                }


            }
        });
    }

    private void setTabloekle_btn() {

        tabloekle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String database = yenipencere.getTitle();
                    connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=1234");
                    Statement statement = connection.createStatement();
                    statement.executeQuery("CREATE TABLE `" + tabloekle_tf.getText() + "` (\n"
                            + "	`id` INT(10) NULL DEFAULT NULL,\n"
                            + "	`ihale_adi` CHAR(255) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`ihale_no` CHAR(255) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`firma_vergi_no` CHAR(11) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`firma_adi` CHAR(255) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`sozlesme_bas_tarihi` CHAR(10) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`sozlesme_bts_tarihi` CHAR(10) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci',\n"
                            + "	`saglik_tesisi` CHAR(255) NULL DEFAULT NULL COLLATE 'utf8_turkish_ci'\n"
                            + ")\n"
                            + "COLLATE='utf8_turkish_ci'\n"
                            + "ENGINE=InnoDB\n");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void ihalesabit() {
        ihaleDbConnect();

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        JTable jTable = new JTable();
        jTable.setModel(defaultTableModel);



        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from 90kalem");
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
            while (resultSet.next()) {


                String ihaleid = resultSet.getString(1);
                System.out.println(resultSetMetaData.getColumnCount());
                System.out.println(ihaleid);

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public static void main(String[] args) {
        FullJava fullJava = new FullJava();


    }
}
