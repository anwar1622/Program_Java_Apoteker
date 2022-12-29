import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class apoteker extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField kd_obat;
    private JTextField nm_obat;
    private JTextField jns_obat;
    private JTextField stok_obat;
    private JTextField harga_obat;
    private JTextField total_obat;
    private JButton btnHitung;
    private JButton btnSimpan;
    private JButton btnHapus;
    private JButton btnKeluar;

    private JButton btnEdit;
    private JLabel lblKodeObat;
    private JLabel lblNamaObat;
    private JLabel lblJenisObat;
    private JLabel lblStokObat;
    private JLabel lblHargaObat;
    private JLabel lblTotalObat;
    private JLabel lblJudul;

    private JTable tabel;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private Object namaKolom[] = {"Kode Obat", "Nama Obat", "Jenis Obat", "Stok Obat", "Harga Obat", "Total Obat"};

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    apoteker frame = new apoteker();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public apoteker() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("Aplikasi Apoteker");
        getContentPane().setLayout(null);

        lblJudul = new JLabel("Apotek 24 Jam");
        lblJudul.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblJudul.setBounds(10, 11, 414, 14);
        getContentPane().add(lblJudul);

        lblKodeObat = new JLabel("Kode Obat");
        lblKodeObat.setBounds(10, 36, 100, 14);
        getContentPane().add(lblKodeObat);

        lblNamaObat = new JLabel("Nama Obat");
        lblNamaObat.setBounds(10, 61, 100, 14);
        getContentPane().add(lblNamaObat);

        lblJenisObat = new JLabel("Jenis Obat");
        lblJenisObat.setBounds(10, 86, 100, 14);
        getContentPane().add(lblJenisObat);

        lblStokObat = new JLabel("Stok Obat");
        lblStokObat.setBounds(10, 111, 100, 14);
        getContentPane().add(lblStokObat);

        lblHargaObat = new JLabel("Harga Obat");
        lblHargaObat.setBounds(10, 136, 100, 14);
        getContentPane().add(lblHargaObat);

        lblTotalObat = new JLabel("Total Obat");
        lblTotalObat.setBounds(10, 161, 100, 14);
        getContentPane().add(lblTotalObat);

        kd_obat = new JTextField();
        kd_obat.setBounds(120, 33, 100, 20);
        getContentPane().add(kd_obat);

        nm_obat = new JTextField();
        nm_obat.setBounds(120, 58, 100, 20);
        getContentPane().add(nm_obat);

        jns_obat = new JTextField();
        jns_obat.setBounds(120, 83, 100, 20);
        getContentPane().add(jns_obat);

        stok_obat = new JTextField();
        stok_obat.setBounds(120, 108, 100, 20);
        getContentPane().add(stok_obat);

        harga_obat = new JTextField();
        harga_obat.setBounds(120, 133, 100, 20);
        getContentPane().add(harga_obat);

        total_obat = new JTextField();
        total_obat.setBounds(120, 158, 100, 20);
        getContentPane().add(total_obat);

        btnHitung = new JButton("Hitung");
        btnHitung.setBounds(10, 187, 89, 23);
        getContentPane().add(btnHitung);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(109, 187, 89, 23);
        getContentPane().add(btnSimpan);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(208, 187, 89, 23);
        getContentPane().add(btnHapus);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(307, 187, 89, 23);
        getContentPane().add(btnKeluar);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(406, 187, 89, 23);
        getContentPane().add(btnEdit);



        tabel = new JTable();
        tableModel = new DefaultTableModel(namaKolom, 0);
        tabel.setModel(tableModel);
        scrollPane = new JScrollPane(tabel);
        scrollPane.setBounds(10, 221, 464, 229);
        getContentPane().add(scrollPane);

        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int stok = Integer.parseInt(stok_obat.getText());
                int harga = Integer.parseInt(harga_obat.getText());
                int total = stok * harga;
                total_obat.setText(String.valueOf(total));
            }
        });

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apoteker", "root", "221299iril");
                    String sql = "INSERT INTO obat VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, kd_obat.getText());
                    statement.setString(2, nm_obat.getText());
                    statement.setString(3, jns_obat.getText());
                    statement.setString(4, stok_obat.getText());
                    statement.setString(5, harga_obat.getText());
                    statement.setString(6, total_obat.getText());
                    int row = statement.executeUpdate();
                    if (row > 0) {
                        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                    }
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan");
                }
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apoteker", "root", "");
                    String sql = "DELETE FROM obat WHERE kd_obat = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, kd_obat.getText());
                    int row = statement.executeUpdate();
                    if (row > 0) {
                        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    }
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus");
                }
            }
        });

        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apoteker", "root", "");
                    String sql = "UPDATE obat SET nm_obat = ?, jns_obat = ?, stok_obat = ?, harga_obat = ?, total_obat = ? WHERE kd_obat = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, nm_obat.getText());
                    statement.setString(2, jns_obat.getText());
                    statement.setString(3, stok_obat.getText());
                    statement.setString(4, harga_obat.getText());
                    statement.setString(5, total_obat.getText());
                    statement.setString(6, kd_obat.getText());
                    int row = statement.executeUpdate();
                    if (row > 0) {
                        JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
                    }
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal diupdate");
                }
            }
        });

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = tabel.getSelectedRow();
                if (i > -1) {
                    kd_obat.setText(tableModel.getValueAt(i, 0).toString());
                    nm_obat.setText(tableModel.getValueAt(i, 1).toString());
                    jns_obat.setText(tableModel.getValueAt(i, 2).toString());
                    stok_obat.setText(tableModel.getValueAt(i, 3).toString());
                    harga_obat.setText(tableModel.getValueAt(i, 4).toString());
                    total_obat.setText(tableModel.getValueAt(i, 5).toString());
                }
            }
        });

        tampilData();
        setSize(500, 500);
        setVisible(true);
    }

    private void tampilData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apoteker","root","");
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM obat";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Object[] obat = new Object[6];
                obat[0] = resultSet.getString("kd_obat");
                obat[1] = resultSet.getString("nm_obat");
                obat[2] = resultSet.getString("jns_obat");
                obat[3] = resultSet.getString("stok_obat");
                obat[4] = resultSet.getString("harga_obat");
                obat[5] = resultSet.getString("total_obat");
                tableModel.addRow(obat);
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditampilkan");
        }
    }
}