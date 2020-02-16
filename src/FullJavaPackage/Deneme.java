package FullJavaPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Deneme {
    private JFrame jFrame;
    private JScrollPane jScrollPane;
    private JTable jTable;
    private JTextField jTextField_jenerikadı;
    private JTextField jTextField_miktar;
    private JTextField jTextField_birimi;
    private JTextField jTextField_mengücek;
    private JTextField jTextField_ilsağlık;
    private JTextField jTextField_refahiye;
    private JTextField jTextField_tercan;
    private JTextField jTextField_adsm;
    private JLabel jLabel_jenerikadı;
    private JLabel jLabel_miktar;
    private JLabel jLabel_birimi;
    private JLabel jLabel_mengücek;
    private JLabel jLabel_ilsağlık;
    private JLabel jLabel_refahiye;
    private JLabel jLabel_tercan;
    private JLabel jLabel_adsm;
    private JButton jButton_kaydet;
    private JButton jButton_sil;
    private JButton jButton_yenile;
    DefaultTableModel defaultTableModel;
    String[] sütun;
    Object[] satır;
    Connection connection;
    Dimension dimension;

    Deneme() {
        setjFrame();
        setjScrollPane();
        setjTable();
        setjTextField_jenerikadı();
        setjButton_kaydet();
        setjButton_sil();
        setjButton_yenile();
        setjTextField_miktar();
        setjTextField_birimi();
        setjTextField_mengücek();
        setjTextField_ilsağlık();
        setjTextField_refahiye();
        setjTextField_tercan();
        setjTextField_adsm();
        setjLabel_jenerikadı();
        setjLabel_miktar();
        setjLabel_birimi();
        setjLabel_mengücek();
        setjLabel_ilsağlık();
        setjLabel_refahiye();
        setjLabel_tercan();
        setjLabel_adsm();
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
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int seçim = jTable.getSelectedRow();
                jTextField_jenerikadı.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 1)));
                jTextField_miktar.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 2)));
                jTextField_birimi.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 3)));
                jTextField_mengücek.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 4)));
                jTextField_ilsağlık.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 5)));
                jTextField_refahiye.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 6)));
                jTextField_tercan.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 7)));
                jTextField_adsm.setText(String.valueOf(defaultTableModel.getValueAt(seçim, 8)));

                if (e.getButton() == MouseEvent.BUTTON3) {
                    jTable.getSelectionModel().clearSelection();
                    seçim = jTable.getSelectedRow();
                    if (seçim < 0) {
                        jTextField_jenerikadı.setText("");
                        jTextField_miktar.setText("");
                        jTextField_birimi.setText("");
                        jTextField_mengücek.setText("");
                        jTextField_ilsağlık.setText("");
                        jTextField_refahiye.setText("");
                        jTextField_tercan.setText("");
                        jTextField_adsm.setText("");
                    }
                }
            }


        });

    }

    private void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ihale?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setjTextField_jenerikadı() {
        jTextField_jenerikadı = new JTextField();
        jFrame.getContentPane().add(jTextField_jenerikadı);
        jTextField_jenerikadı.setBounds(0, 320, 120, 20);


    }

    private void setjLabel_jenerikadı() {
        jLabel_jenerikadı = new JLabel("Jenerik Adı:");
        jFrame.getContentPane().add(jLabel_jenerikadı);
        jLabel_jenerikadı.setBounds(0, 300, 120, 20);
    }


    private void setjTextField_miktar() {
        jTextField_miktar = new JTextField();
        jFrame.getContentPane().add(jTextField_miktar);
        jTextField_miktar.setBounds(120, 320, 120, 20);

    }

    private void setjLabel_miktar() {
        jLabel_miktar = new JLabel("Miktarı");
        jFrame.getContentPane().add(jLabel_miktar);
        jLabel_miktar.setBounds(120, 300, 120, 20);
    }

    private void setjTextField_birimi() {
        jTextField_birimi = new JTextField();
        jFrame.getContentPane().add(jTextField_birimi);
        jTextField_birimi.setBounds(240, 320, 120, 20);
    }

    private void setjLabel_birimi() {
        jLabel_birimi = new JLabel("Birimi");
        jFrame.getContentPane().add(jLabel_birimi);
        jLabel_birimi.setBounds(240, 300, 120, 20);

    }

    private void setjTextField_mengücek() {
        jTextField_mengücek = new JTextField();
        jFrame.getContentPane().add(jTextField_mengücek);
        jTextField_mengücek.setBounds(360, 320, 120, 20);
    }

    private void setjLabel_mengücek() {
        jLabel_mengücek = new JLabel("Mengücek");
        jFrame.getContentPane().add(jLabel_mengücek);
        jLabel_mengücek.setBounds(360, 300, 120, 20);
    }

    private void setjTextField_ilsağlık() {
        jTextField_ilsağlık = new JTextField();
        jFrame.getContentPane().add(jTextField_ilsağlık);
        jTextField_ilsağlık.setBounds(480, 320, 120, 20);
    }

    private void setjLabel_ilsağlık() {
        jLabel_ilsağlık = new JLabel("İl Sağlık");
        jFrame.getContentPane().add(jLabel_ilsağlık);
        jLabel_ilsağlık.setBounds(480, 300, 120, 20);
    }

    private void setjTextField_refahiye() {
        jTextField_refahiye = new JTextField();
        jFrame.getContentPane().add(jTextField_refahiye);
        jTextField_refahiye.setBounds(600, 320, 120, 20);
    }

    private void setjLabel_refahiye() {
        jLabel_refahiye = new JLabel("Refahiye");
        jFrame.getContentPane().add(jLabel_refahiye);
        jLabel_refahiye.setBounds(600, 300, 120, 20);
    }

    private void setjTextField_tercan() {
        jTextField_tercan = new JTextField();
        jFrame.getContentPane().add(jTextField_tercan);
        jTextField_tercan.setBounds(720, 320, 120, 20);
    }

    private void setjLabel_tercan() {
        jLabel_tercan = new JLabel("Tercan");
        jFrame.getContentPane().add(jLabel_tercan);
        jLabel_tercan.setBounds(720, 300, 120, 20);
    }

    private void setjTextField_adsm() {
        jTextField_adsm = new JTextField();
        jFrame.getContentPane().add(jTextField_adsm);
        jTextField_adsm.setBounds(840, 320, 120, 20);
    }

    private void setjLabel_adsm() {
        jLabel_adsm = new JLabel("Adsm");
        jFrame.getContentPane().add(jLabel_adsm);
        jLabel_adsm.setBounds(840, 300, 120, 20);
    }


    private void setjButton_kaydet() {
        jButton_kaydet = new JButton("Kaydet");
        jFrame.getContentPane().add(jButton_kaydet);
        jButton_kaydet.setBounds(130, 370, 120, 20);
        jButton_kaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                satır[1] = jTextField_jenerikadı.getText();
                satır[2] = jTextField_miktar.getText();
                satır[3] = jTextField_birimi.getText();
                satır[4] = jTextField_mengücek.getText();
                satır[5] = jTextField_ilsağlık.getText();
                satır[6] = jTextField_refahiye.getText();
                satır[7] = jTextField_tercan.getText();
                satır[8] = jTextField_adsm.getText();
                dbConnect();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "insert into 90kalem(JenerikAdı,miktarı,birimi," +
                                    "`Mengücek Gazi Eğitim ve Araştırma Hastanesi`," +
                                    "`İl Sağlık Müdürlüğü`,`Refahiye Devlet Hastanesi`," +
                                    "`Tercan Devlet Hastanesi`," +
                                    "`Ağız ve Diş Sağlığı`) values(?,?,?,?,?,?,?,?)");
                    Statement statement = connection.createStatement();
                    preparedStatement.setString(1, jTextField_jenerikadı.getText());
                    preparedStatement.setString(2, jTextField_miktar.getText());
                    preparedStatement.setString(3, jTextField_birimi.getText());
                    preparedStatement.setString(4, jTextField_mengücek.getText());
                    preparedStatement.setString(5, jTextField_ilsağlık.getText());
                    preparedStatement.setString(6, jTextField_refahiye.getText());
                    preparedStatement.setString(7, jTextField_tercan.getText());
                    preparedStatement.setString(8, jTextField_adsm.getText());
                    preparedStatement.executeQuery();
                    ResultSet resultSet = statement.executeQuery("select SıraNo from 90kalem");
                    while (resultSet.next()) {
                        satır[0] = resultSet.getString(1);
                    }
                    defaultTableModel.addRow(satır);
                    jTextField_jenerikadı.setText("");
                    jTextField_miktar.setText("");
                    jTextField_birimi.setText("");
                    jTextField_mengücek.setText("");
                    jTextField_ilsağlık.setText("");
                    jTextField_refahiye.setText("");
                    jTextField_tercan.setText("");
                    jTextField_adsm.setText("");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void setjButton_sil() {
        jButton_sil = new JButton("   Sil   ");
        jFrame.getContentPane().add(jButton_sil);
        jButton_sil.setBounds(250, 370, 120, 20);
        jButton_sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int silinecek_satır = jTable.getSelectedRow();
                String satır_sil = String.valueOf(defaultTableModel.getValueAt(silinecek_satır, 0));
                System.out.println(satır_sil);
                defaultTableModel.removeRow(silinecek_satır);
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("delete from 90kalem where SıraNo=" + satır_sil);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setjButton_yenile() {
        jButton_yenile = new JButton("Yenile");
        jFrame.getContentPane().add(jButton_yenile);
        jButton_yenile.setBounds(370, 370, 120, 20);
        jButton_yenile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
///////

            }
        });
    }


    public static void main(String[] args) {
        Deneme deneme = new Deneme();
    }
}
