import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Aplikasi_Apotek extends javax.swing.JFrame {
    private Object jTable;
    
    Statement st;
    ResultSet rs;
    koneksi koneksi;
    JasperReport jr;
    JasperPrint jp;
    Map param = new HashMap();
    JasperDesign jd;

    public Aplikasi_Apotek() {
        koneksi = new koneksi();
        initComponents();
        load_data_obat();
        load_data_karyawan();
        load_data_transaksi();
        load_data_laporanObat();
        load_data_laporanKaryawan();
        load_data_laporanTransaksi();
        
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
    }
    
    private void load_data_obat(){
        Object header[]={"Id Obat", "Nama Obat", "Kategori Obat", "Jenis Obat", "StokObat"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableObat.setModel(data);
        String sql = "SELECT idObat, namaObat, kategoriObat, jenisObat, stokObat FROM dataobat";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void kosong_obat(){
        jTFIdObat.setText("");
        jTFNamaObat.setText("");
        jTFKategoriObat.setText("");
        jCBJenisObat.setSelectedIndex(-1);
        jTFStokObat.setText("");
    }
    
    public void input_data_obat(){
        try{
            String sql = "INSERT INTO dataobat (idObat, namaObat, kategoriObat, jenisObat, stokObat) VALUES ('"
                   + jTFIdObat.getText() + "', '"
                   + jTFNamaObat.getText() + "', '"
                   + jTFKategoriObat.getText() + "', '"
                   + jCBJenisObat.getSelectedItem().toString() + "', '"
                   + jTFStokObat.getText() + "')";
            
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Obat Berhasil diinput");
            load_data_obat();
            load_data_laporanObat();
            kosong_obat();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void load_data_karyawan(){
        Object header[]={"Id Karyawan", "Nama Karyawan", "Jenis Kelamin", "Alamat", "No Telepon"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableKaryawan.setModel(data);
        String sql = "SELECT idKaryawan, namaKaryawan, jenisKelamin, alamat, noTelp FROM datakaryawan";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void kosong_karyawan(){
        jTFIdKaryawan.setText("");
        jTFNamaKaryawan.setText("");
        buttonGroup1.clearSelection();
        jTFAlamat.setText("");
        jTFNotelp.setText("");
    }
    
    public void input_data_karyawan(){
        try{
            String jk="";
            if(jRBLaki.isSelected())
            {
                jk=jRBLaki.getText();
            }else{
                jk=jRBPerempuan.getText();
            }
            
            String sql = "INSERT INTO datakaryawan (idKaryawan, namaKaryawan, jenisKelamin, alamat, noTelp) VALUES ('"
                   + jTFIdKaryawan.getText() + "', '"
                   + jTFNamaKaryawan.getText() + "', '"
                   + jk + "', '"
                   + jTFAlamat.getText() + "', '"
                   + jTFNotelp.getText() + "')";
            
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil diinput");
            load_data_karyawan();
            load_data_laporanKaryawan();
            kosong_karyawan();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void load_data_transaksi(){
        Object header[]={"Id Transaksi", "Nama Pelanggan", "Tanggal Transaksi", "Total Bayar", "Status Transaksi"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableTransaksi.setModel(data);
        String sql = "SELECT idTransaksi, namaPelanggan, tanggalTransaksi, totalBayar, statusTransaksi FROM datatransaksi";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void kosong_transaksi(){
        jTFIdTransaksi.setText("");
        jTFNamaPelanggan.setText("");
        jDCTanggalTransaksi.setDate(null);
        jTFTotalBayar.setText("");
        jCBStatusTransaksi.setSelectedIndex(-1);
    }
    
    public void input_data_transaksi(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalTransaksi = sdf.format(jDCTanggalTransaksi.getDate());
            
            String sql = "INSERT INTO datatransaksi (idTransaksi, namaPelanggan, tanggalTransaksi, totalBayar, statusTransaksi) VALUES ('"
                   + jTFIdTransaksi.getText() + "', '"
                   + jTFNamaPelanggan.getText() + "', '"
                   + tanggalTransaksi+ "', '"
                   + jTFTotalBayar.getText() + "', '"
                   + jCBStatusTransaksi.getSelectedItem().toString() + "')";
            
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Transaksi Berhasil diinput");
            load_data_transaksi();
            kosong_transaksi();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void cari_data_obat() {
        try {
            String sql = "SELECT * FROM dataobat WHERE " 
                       + jCBCariOb.getSelectedItem().toString() + " LIKE '%" 
                       + jTFCariOb.getText() + "%'";

            ResultSet rs = st.executeQuery(sql);

            Object header[] = {"Id Obat", "Nama Obat", "Kategori Obat", "Jenis Obat", "Stok Obat"};
            DefaultTableModel data = new DefaultTableModel(null, header);
            jTableObat.setModel(data);

            while (rs.next()) {
                String k1 = rs.getString("idObat");
                String k2 = rs.getString("namaObat");
                String k3 = rs.getString("kategoriObat");
                String k4 = rs.getString("jenisObat");
                String k5 = rs.getString("stokObat");

                String k[] = {k1, k2, k3, k4, k5};
                data.addRow(k);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void cari_data_karyawan(){
        try{
            String sql = "SELECT * FROM datakaryawan WHERE "
                    + jCBCariKy.getSelectedItem().toString() + " LIKE '%"
                    + jTFCariKy.getText() + "%'";
            
            ResultSet rs = st.executeQuery(sql);
            
            Object header[] = {"Id Karyawan", "Nama Karyawan", "Jenis Kelamin", "Alamat", "No Telepon"};
            DefaultTableModel data = new DefaultTableModel(null, header);
            jTableKaryawan.setModel(data);
            
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void cari_data_transaksi(){
        try{
            String sql = "SELECT * FROM datatransaksi WHERE "
                    + jCBCariTr.getSelectedItem().toString() + " LIKE '%"
                    + jTFCariTr.getText() + "%'";
            
            ResultSet rs = st.executeQuery(sql);
            
            Object header[]={"Id Transaksi", "Nama Pelanggan", "Tanggal Transaksi", "Total Bayar", "Status Transaksi"};
            DefaultTableModel data = new DefaultTableModel(null, header);
            jTableTransaksi.setModel(data);
            
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void load_data_laporanObat(){
        Object header[]={"Id Obat", "Nama Obat", "Kategori Obat", "Jenis Obat", "StokObat"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableLaporanDataObat.setModel(data);
        String sql = "SELECT idObat, namaObat, kategoriObat, jenisObat, stokObat FROM dataobat";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void load_data_laporanKaryawan(){
        Object header[]={"Id Karyawan", "Nama Karyawan", "Jenis Kelamin", "Alamat", "No Telepon"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableLaporanDataKaryawan.setModel(data);
        String sql = "SELECT idKaryawan, namaKaryawan, jenisKelamin, alamat, noTelp FROM datakaryawan";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void load_data_laporanTransaksi(){
        Object header[]={"Id Transaksi", "Nama Pelanggan", "Tanggal Transaksi", "Total Bayar", "Status Transaksi"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        jTableLaporanDataTransaksi.setModel(data);
        String sql = "SELECT idTransaksi, namaPelanggan, tanggalTransaksi, totalBayar, statusTransaksi FROM datatransaksi";
        try{
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBody = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jBtnBeranda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtnTransaksi = new javax.swing.JButton();
        jBtnObat = new javax.swing.JButton();
        jBtnKaryawan = new javax.swing.JButton();
        jBtnLaporan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jBtnKeluar = new javax.swing.JButton();
        jPanelContent = new javax.swing.JPanel();
        jPanelBeranda = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanelObat = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTFIdObat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTFNamaObat = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTFKategoriObat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jCBJenisObat = new javax.swing.JComboBox<>();
        jBtnSimpanOb = new javax.swing.JButton();
        jBtnEditOb = new javax.swing.JButton();
        jBtnHapusOb = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableObat = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jTFCariOb = new javax.swing.JTextField();
        jCBCariOb = new javax.swing.JComboBox<>();
        jTFStokObat = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanelKaryawan = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTFIdKaryawan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTFNamaKaryawan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jBtnSimpanKy = new javax.swing.JButton();
        jBtnEditKy = new javax.swing.JButton();
        jBtnHapusKy = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTFCariKy = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableKaryawan = new javax.swing.JTable();
        jRBPerempuan = new javax.swing.JRadioButton();
        jRBLaki = new javax.swing.JRadioButton();
        jTFAlamat = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTFNotelp = new javax.swing.JTextField();
        jCBCariKy = new javax.swing.JComboBox<>();
        jPanelTransaksi = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTFIdTransaksi = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTFNamaPelanggan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTFTotalBayar = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jBtnSimpanTr = new javax.swing.JButton();
        jBtnEditTr = new javax.swing.JButton();
        jBtnHapusTr = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTFCariTr = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        jCBStatusTransaksi = new javax.swing.JComboBox<>();
        jDCTanggalTransaksi = new com.toedter.calendar.JDateChooser();
        jCBCariTr = new javax.swing.JComboBox<>();
        jPanelLaporan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableLaporanDataObat = new javax.swing.JTable();
        jBtnCetakObat = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableLaporanDataKaryawan = new javax.swing.JTable();
        jBtnCetakKaryawan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableLaporanDataTransaksi = new javax.swing.JTable();
        jBtnCetakTransaksi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI APOTEK ADZHIF");
        setPreferredSize(new java.awt.Dimension(1100, 885));

        jPanelBody.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBody.setPreferredSize(new java.awt.Dimension(1100, 850));

        jPanelMenu.setBackground(new java.awt.Color(190, 191, 180));

        jBtnBeranda.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBeranda.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnBeranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.png"))); // NOI18N
        jBtnBeranda.setText("BERANDA");
        jBtnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBerandaActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logoApotek.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("APOTEK ADZHIF");

        jBtnTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        jBtnTransaksi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/transaksi.png"))); // NOI18N
        jBtnTransaksi.setText("TRANSAKSI");
        jBtnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTransaksiActionPerformed(evt);
            }
        });

        jBtnObat.setBackground(new java.awt.Color(255, 255, 255));
        jBtnObat.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/box.png"))); // NOI18N
        jBtnObat.setText("OBAT");
        jBtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnObatActionPerformed(evt);
            }
        });

        jBtnKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        jBtnKaryawan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/people(1).png"))); // NOI18N
        jBtnKaryawan.setText("KARYAWAN");
        jBtnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKaryawanActionPerformed(evt);
            }
        });

        jBtnLaporan.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLaporan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/laporan.png"))); // NOI18N
        jBtnLaporan.setText("LAPORAN");
        jBtnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLaporanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("SEHAT BERSAMA KAMI");

        jBtnKeluar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnKeluar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jBtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logout.png"))); // NOI18N
        jBtnKeluar.setText("KELUAR");
        jBtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnBeranda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnObat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(24, 24, 24)
                .addComponent(jBtnBeranda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnObat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanelContent.setBackground(new java.awt.Color(255, 255, 255));
        jPanelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelContent.setLayout(new java.awt.CardLayout());

        jPanelBeranda.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBeranda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BERANDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logoApotek(1).jpg"))); // NOI18N

        jTextPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTextPane1.setText("Apotek Adzhif adalah apotek yang berdedikasi menyediakan solusi kesehatan komprehensif. Kami menawarkan obat-obatan berkualitas, suplemen, dan konsultasi kesehatan dengan tim farmasis profesional. Apotek Adzhif berkomitmen membantu Anda mencapai hidup sehat dan bahagia.\n\nAlamat \t\t: Jln. Sukamaju No.150, Kuningan, Jawa Barat\nNo. Telepon\t: 081209348756\n");
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanelBerandaLayout = new javax.swing.GroupLayout(jPanelBeranda);
        jPanelBeranda.setLayout(jPanelBerandaLayout);
        jPanelBerandaLayout.setHorizontalGroup(
            jPanelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBerandaLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBerandaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBerandaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanelBerandaLayout.setVerticalGroup(
            jPanelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBerandaLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jPanelContent.add(jPanelBeranda, "card2");

        jPanelObat.setBackground(new java.awt.Color(255, 255, 255));
        jPanelObat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OBAT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("ID Obat");

        jTFIdObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Nama Obat");

        jTFNamaObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Kategori Obat");

        jTFKategoriObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Jenis Obat");

        jCBJenisObat.setBackground(new java.awt.Color(199, 200, 192));
        jCBJenisObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCBJenisObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tablet", "Pil", "Kapsul", "Sirup", "Powder" }));

        jBtnSimpanOb.setBackground(new java.awt.Color(199, 200, 192));
        jBtnSimpanOb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnSimpanOb.setText("SIMPAN");
        jBtnSimpanOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSimpanObActionPerformed(evt);
            }
        });

        jBtnEditOb.setBackground(new java.awt.Color(199, 200, 192));
        jBtnEditOb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnEditOb.setText("EDIT");
        jBtnEditOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditObActionPerformed(evt);
            }
        });

        jBtnHapusOb.setBackground(new java.awt.Color(199, 200, 192));
        jBtnHapusOb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnHapusOb.setText("HAPUS");
        jBtnHapusOb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHapusObActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("DATA OBAT");

        jTableObat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableObat);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Cari");

        jTFCariOb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFCariOb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCariObKeyPressed(evt);
            }
        });

        jCBCariOb.setBackground(new java.awt.Color(190, 191, 180));
        jCBCariOb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCBCariOb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IdObat", "NamaObat", "KategoriObat", "JenisObat" }));

        jTFStokObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Stok Obat");

        javax.swing.GroupLayout jPanelObatLayout = new javax.swing.GroupLayout(jPanelObat);
        jPanelObat.setLayout(jPanelObatLayout);
        jPanelObatLayout.setHorizontalGroup(
            jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObatLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelObatLayout.createSequentialGroup()
                        .addComponent(jBtnSimpanOb)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEditOb)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnHapusOb))
                    .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanelObatLayout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFStokObat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelObatLayout.createSequentialGroup()
                            .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFKategoriObat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCBJenisObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObatLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelObatLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jTFCariOb, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jCBCariOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanelObatLayout.setVerticalGroup(
            jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObatLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTFNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTFKategoriObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jCBJenisObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTFStokObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSimpanOb)
                    .addComponent(jBtnEditOb)
                    .addComponent(jBtnHapusOb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(30, 30, 30)
                .addGroup(jPanelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTFCariOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCariOb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jPanelContent.add(jPanelObat, "card3");

        jPanelKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        jPanelKaryawan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KARYAWAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("ID Karyawan");

        jTFIdKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Nama Karyawan");

        jTFNamaKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Jenis Kelamin");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Alamat");

        jBtnSimpanKy.setBackground(new java.awt.Color(199, 200, 192));
        jBtnSimpanKy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnSimpanKy.setText("SIMPAN");
        jBtnSimpanKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSimpanKyActionPerformed(evt);
            }
        });

        jBtnEditKy.setBackground(new java.awt.Color(199, 200, 192));
        jBtnEditKy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnEditKy.setText("EDIT");
        jBtnEditKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditKyActionPerformed(evt);
            }
        });

        jBtnHapusKy.setBackground(new java.awt.Color(199, 200, 192));
        jBtnHapusKy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnHapusKy.setText("HAPUS");
        jBtnHapusKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHapusKyActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("DATA KARYAWAN");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Cari");

        jTFCariKy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFCariKy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCariKyKeyPressed(evt);
            }
        });

        jTableKaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableKaryawan);

        buttonGroup1.add(jRBPerempuan);
        jRBPerempuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRBPerempuan.setText("Perempuan");

        buttonGroup1.add(jRBLaki);
        jRBLaki.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRBLaki.setText("Laki-laki");

        jTFAlamat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("No Telepon");

        jTFNotelp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jCBCariKy.setBackground(new java.awt.Color(190, 191, 180));
        jCBCariKy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCBCariKy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IdKaryawan", "NamaKaryawan", "JenisKelamin", "Alamat", "NoTelp", " " }));

        javax.swing.GroupLayout jPanelKaryawanLayout = new javax.swing.GroupLayout(jPanelKaryawan);
        jPanelKaryawan.setLayout(jPanelKaryawanLayout);
        jPanelKaryawanLayout.setHorizontalGroup(
            jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                        .addComponent(jBtnSimpanKy)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEditKy)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnHapusKy))
                    .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                        .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKaryawanLayout.createSequentialGroup()
                                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18))
                            .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(54, 54, 54)))
                        .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNotelp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTFIdKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                                    .addComponent(jRBLaki)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRBPerempuan)
                                    .addGap(44, 44, 44))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKaryawanLayout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jTFCariKy, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCariKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKaryawanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(274, 274, 274))
        );
        jPanelKaryawanLayout.setVerticalGroup(
            jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKaryawanLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTFIdKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTFNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jRBPerempuan)
                    .addComponent(jRBLaki))
                .addGap(18, 18, 18)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTFAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTFNotelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSimpanKy)
                    .addComponent(jBtnEditKy)
                    .addComponent(jBtnHapusKy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(33, 33, 33)
                .addGroup(jPanelKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTFCariKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCariKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jPanelContent.add(jPanelKaryawan, "card5");

        jPanelTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTransaksi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSAKSI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("ID Transaksi");

        jTFIdTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Nama Pelanggan");

        jTFNamaPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Tanggal Transaksi");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Total Biaya");

        jTFTotalBayar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Status Transaksi");

        jBtnSimpanTr.setBackground(new java.awt.Color(199, 200, 192));
        jBtnSimpanTr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnSimpanTr.setText("SIMPAN");
        jBtnSimpanTr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSimpanTrActionPerformed(evt);
            }
        });

        jBtnEditTr.setBackground(new java.awt.Color(199, 200, 192));
        jBtnEditTr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnEditTr.setText("EDIT");
        jBtnEditTr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditTrActionPerformed(evt);
            }
        });

        jBtnHapusTr.setBackground(new java.awt.Color(199, 200, 192));
        jBtnHapusTr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnHapusTr.setText("HAPUS");
        jBtnHapusTr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHapusTrActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setText("DATA TRANSAKSI");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Cari");

        jTFCariTr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFCariTr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCariTrKeyPressed(evt);
            }
        });

        jTableTransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTableTransaksi);

        jCBStatusTransaksi.setBackground(new java.awt.Color(199, 200, 192));
        jCBStatusTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCBStatusTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunas", "Belum Bayar" }));

        jDCTanggalTransaksi.setBackground(new java.awt.Color(199, 200, 192));
        jDCTanggalTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jCBCariTr.setBackground(new java.awt.Color(190, 191, 180));
        jCBCariTr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCBCariTr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IdTransaksi", "NamaPelanggan", "TanggalTransaksi", "TotalBayar", "StatusTransaksi", " " }));

        javax.swing.GroupLayout jPanelTransaksiLayout = new javax.swing.GroupLayout(jPanelTransaksi);
        jPanelTransaksi.setLayout(jPanelTransaksiLayout);
        jPanelTransaksiLayout.setHorizontalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jTFCariTr, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCariTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jBtnSimpanTr)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnEditTr)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnHapusTr))
                            .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addGap(32, 32, 32)
                                    .addComponent(jCBStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                    .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel28))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTFIdTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                        .addComponent(jTFNamaPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                        .addComponent(jTFTotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                        .addComponent(jDCTanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel32)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTransaksiLayout.setVerticalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTFIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTFNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jDCTanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTFTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jCBStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSimpanTr)
                    .addComponent(jBtnEditTr)
                    .addComponent(jBtnHapusTr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(34, 34, 34)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTFCariTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCariTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanelContent.add(jPanelTransaksi, "card4");

        jPanelLaporan.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLaporan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LAPORAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("DATA OBAT");

        jTableLaporanDataObat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableLaporanDataObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTableLaporanDataObat);

        jBtnCetakObat.setBackground(new java.awt.Color(199, 200, 192));
        jBtnCetakObat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnCetakObat.setText("CETAK");
        jBtnCetakObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakObatActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("DATA KARYAWAN");

        jTableLaporanDataKaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableLaporanDataKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTableLaporanDataKaryawan);

        jBtnCetakKaryawan.setBackground(new java.awt.Color(199, 200, 192));
        jBtnCetakKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnCetakKaryawan.setText("CETAK");
        jBtnCetakKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakKaryawanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("DATA TRANSAKSI");

        jTableLaporanDataTransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableLaporanDataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTableLaporanDataTransaksi);

        jBtnCetakTransaksi.setBackground(new java.awt.Color(199, 200, 192));
        jBtnCetakTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnCetakTransaksi.setText("CETAK");
        jBtnCetakTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCetakTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLaporanLayout = new javax.swing.GroupLayout(jPanelLaporan);
        jPanelLaporan.setLayout(jPanelLaporanLayout);
        jPanelLaporanLayout.setHorizontalGroup(
            jPanelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLaporanLayout.createSequentialGroup()
                .addGroup(jPanelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLaporanLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnCetakObat))
                    .addGroup(jPanelLaporanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane6)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLaporanLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBtnCetakKaryawan))
                            .addGroup(jPanelLaporanLayout.createSequentialGroup()
                                .addGroup(jPanelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLaporanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnCetakTransaksi)))
                .addContainerGap())
        );
        jPanelLaporanLayout.setVerticalGroup(
            jPanelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLaporanLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCetakObat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCetakKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCetakTransaksi)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jPanelContent.add(jPanelLaporan, "card6");

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBerandaActionPerformed
        // remove panel
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();
        //add panel
        jPanelContent.add(jPanelBeranda);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }//GEN-LAST:event_jBtnBerandaActionPerformed

    private void jBtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnObatActionPerformed
        // remove panel
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();
        //add panel
        jPanelContent.add(jPanelObat);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }//GEN-LAST:event_jBtnObatActionPerformed

    private void jBtnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKaryawanActionPerformed
        // remove panel
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();
        //add panel
        jPanelContent.add(jPanelKaryawan);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }//GEN-LAST:event_jBtnKaryawanActionPerformed

    private void jBtnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTransaksiActionPerformed
       // remove panel
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();
        //add panel
        jPanelContent.add(jPanelTransaksi);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }//GEN-LAST:event_jBtnTransaksiActionPerformed

    private void jBtnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLaporanActionPerformed
        // remove panel
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();
        //add panel
        jPanelContent.add(jPanelLaporan);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }//GEN-LAST:event_jBtnLaporanActionPerformed

    private void jBtnSimpanObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSimpanObActionPerformed
        if (jTFIdObat.getText().equals("") || jTFNamaObat.getText().equals("") ||
                jTFKategoriObat.getText().equals("") || jCBJenisObat.getSelectedItem() == null ||
                jTFStokObat.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Data Harus Diisi!", "Validasi data", JOptionPane.INFORMATION_MESSAGE); 
            return;
        }
        if(jBtnSimpanOb.getText().equals("SIMPAN")){
            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menyimpan data?", "Konfirmasi Simpan",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                input_data_obat();
            } else {
                System.out.println("Data tidak disimpan");;
            }
        }
    }//GEN-LAST:event_jBtnSimpanObActionPerformed

    private void jBtnSimpanKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSimpanKyActionPerformed
        if (jTFIdKaryawan.getText().equals("") || jTFNamaKaryawan.getText().equals("") ||
                buttonGroup1.getSelection() == null || jTFAlamat.getText().equals("") ||
                jTFNotelp.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Data Harus Diisi!", "Validasi data", JOptionPane.INFORMATION_MESSAGE); 
            return;
        }
        if(jBtnSimpanKy.getText().equals("SIMPAN")){
            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menyimpan data?", "Konfirmasi Simpan",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                input_data_karyawan();
            } else {
                System.out.println("Data tidak disimpan");
            }
        }
    }//GEN-LAST:event_jBtnSimpanKyActionPerformed

    private void jBtnSimpanTrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSimpanTrActionPerformed
        if (jTFIdTransaksi.getText().equals("") || jTFNamaPelanggan.getText().equals("") ||
                jDCTanggalTransaksi.getDate() == null || jTFTotalBayar.getText().equals("") ||
                jCBStatusTransaksi.getSelectedItem() == null){
            
            JOptionPane.showMessageDialog(null, "Data Harus Diisi!", "Validasi data", JOptionPane.INFORMATION_MESSAGE); 
            return;
        }
        if(jBtnSimpanTr.getText().equals("SIMPAN")){
            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menyimpan data?", "Konfirmasi Simpan",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                input_data_transaksi();
            } else {
                System.out.println("Data tidak disimpan");;
            }
        }
    }//GEN-LAST:event_jBtnSimpanTrActionPerformed

    private void jBtnEditObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditObActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mengedit data?", 
                "Konfirmasi Edit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION){
            try {
                String sql_edit = "UPDATE dataobat SET "
                    + "namaObat = '" + jTFNamaObat.getText() + "', "
                    + "kategoriObat = '" + jTFKategoriObat.getText() + "', "
                    + "jenisObat = '" + jCBJenisObat.getSelectedItem().toString() + "', "
                    + "stokObat = '" + jTFStokObat.getText() + "' "
                    + "WHERE idObat = '" + jTFIdObat.getText() + "'";

                st.executeUpdate(sql_edit);
                JOptionPane.showMessageDialog(null, "Data Obat Berhasil di Update");
                load_data_obat();
                load_data_laporanObat();
                kosong_obat();
             }catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e);
             } 
        }else{
            System.out.println("Data tidak diubah.");
        }                                       
    }//GEN-LAST:event_jBtnEditObActionPerformed

    private void jBtnEditKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditKyActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mengedit data?", 
                "Konfirmasi Edit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION){
            try {
                String jk="";
                if(jRBLaki.isSelected())
                {
                    jk = jRBLaki.getText();
                }else if (jRBPerempuan.isSelected()){
                    jk = jRBPerempuan.getText();
                }
                String sql_edit = "UPDATE datakaryawan SET "
                    + "namakaryawan = '" + jTFNamaKaryawan.getText() + "', "
                    + "jenisKelamin = '" + jk + "', "
                    + "alamat = '" + jTFAlamat.getText() + "', "
                    + "noTelp = '" + jTFNotelp.getText() + "' "
                    + "WHERE idKaryawan = '" + jTFIdKaryawan.getText() + "'";

                st.executeUpdate(sql_edit);
                JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil di Update");
                load_data_karyawan();
                load_data_laporanKaryawan();
                kosong_karyawan();
             }catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e);
             } 
        }else{
            System.out.println("Data tidak diubah.");
        }
    }//GEN-LAST:event_jBtnEditKyActionPerformed

    private void jBtnEditTrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditTrActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mengedit data?", 
                "Konfirmasi Edit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String tanggalTransaksi = sdf.format(jDCTanggalTransaksi.getDate());

                String sql_edit = "UPDATE datatransaksi SET "
                    + "namaPelanggan = '" + jTFNamaPelanggan.getText() + "', "
                    + "tanggalTransaksi = '" + tanggalTransaksi + "', "
                    + "totalBayar = '" + jTFTotalBayar.getText() + "', "
                    + "statusTransaksi = '" + jCBStatusTransaksi.getSelectedItem().toString() + "' "
                    + "WHERE idTransaksi = '" + jTFIdTransaksi.getText() + "'";

                st.executeUpdate(sql_edit);
                JOptionPane.showMessageDialog(null, "Data Transaksi Berhasil di Update");
                load_data_transaksi();
                load_data_laporanTransaksi();
                kosong_transaksi();
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e);
             } 
        } else {
            System.out.println("Data tidak diubah.");
        } 
    }//GEN-LAST:event_jBtnEditTrActionPerformed

    private void jBtnHapusObActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHapusObActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ini menghapus data?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
           try{
            st = koneksi.con.createStatement();
            String sql_delete = "DELETE FROM dataobat WHERE idObat = '" +jTFIdObat.getText()+"'";
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            load_data_obat();
            load_data_laporanObat();
            kosong_obat();
           }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
           } 
        }else{
            System.out.println("Data tidak dihapus");
        }
    }//GEN-LAST:event_jBtnHapusObActionPerformed

    private void jBtnHapusKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHapusKyActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ini menghapus data?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
           try{
            st = koneksi.con.createStatement();
            String sql_delete = "DELETE FROM datakaryawan WHERE idKaryawan = '" +jTFIdKaryawan.getText()+"'";
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
            load_data_karyawan();
            load_data_laporanKaryawan();
            kosong_karyawan();
           }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
           } 
        }else{
            System.out.println("Data tidak dihapus");
        }
    }//GEN-LAST:event_jBtnHapusKyActionPerformed

    private void jBtnHapusTrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHapusTrActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ini menghapus data?",
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
            try{
                st = koneksi.con.createStatement();
                String sql_delete = "DELETE FROM datatransaksi WHERE idTransaksi = '" +jTFIdTransaksi.getText()+"'";
                st.executeUpdate(sql_delete);
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
                load_data_transaksi();
                load_data_laporanTransaksi();
                kosong_transaksi();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            System.out.println("Data tidak dihapus");
        }
    }//GEN-LAST:event_jBtnHapusTrActionPerformed

    private void jTFCariObKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCariObKeyPressed
        cari_data_obat();
    }//GEN-LAST:event_jTFCariObKeyPressed

    private void jTFCariKyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCariKyKeyPressed
        cari_data_karyawan();
    }//GEN-LAST:event_jTFCariKyKeyPressed

    private void jTFCariTrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCariTrKeyPressed
        cari_data_transaksi();
    }//GEN-LAST:event_jTFCariTrKeyPressed

    private void jBtnCetakObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakObatActionPerformed
        try{
            File file = new File("C:\\Users\\adela\\Documents\\NetBeansProjects\\Project_Praktikum_BP1\\src\\LaporanDataObat.jrxml");
            jd = JRXmlLoader.load(file);
            param.clear();
            jr = JasperCompileManager.compileReport(jd);
            jp = JasperFillManager.fillReport(jr, param, koneksi.con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jBtnCetakObatActionPerformed

    private void jBtnCetakKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakKaryawanActionPerformed
        try{
            File file = new File("C:\\Users\\adela\\Documents\\NetBeansProjects\\Project_Praktikum_BP1\\src\\LaporanDataKaryawan.jrxml");
            jd = JRXmlLoader.load(file);
            param.clear();
            jr = JasperCompileManager.compileReport(jd);
            jp = JasperFillManager.fillReport(jr, param, koneksi.con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jBtnCetakKaryawanActionPerformed

    private void jBtnCetakTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCetakTransaksiActionPerformed
        try{
            File file = new File("C:\\Users\\adela\\Documents\\NetBeansProjects\\Project_Praktikum_BP1\\src\\LaporanDataTransaksi.jrxml");
            jd = JRXmlLoader.load(file);
            param.clear();
            jr = JasperCompileManager.compileReport(jd);
            jp = JasperFillManager.fillReport(jr, param, koneksi.con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jBtnCetakTransaksiActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        FormLogin fl = new FormLogin();
        fl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeluarActionPerformed

        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Apotek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Apotek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Apotek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Apotek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplikasi_Apotek().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnBeranda;
    private javax.swing.JButton jBtnCetakKaryawan;
    private javax.swing.JButton jBtnCetakObat;
    private javax.swing.JButton jBtnCetakTransaksi;
    private javax.swing.JButton jBtnEditKy;
    private javax.swing.JButton jBtnEditOb;
    private javax.swing.JButton jBtnEditTr;
    private javax.swing.JButton jBtnHapusKy;
    private javax.swing.JButton jBtnHapusOb;
    private javax.swing.JButton jBtnHapusTr;
    private javax.swing.JButton jBtnKaryawan;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnLaporan;
    private javax.swing.JButton jBtnObat;
    private javax.swing.JButton jBtnSimpanKy;
    private javax.swing.JButton jBtnSimpanOb;
    private javax.swing.JButton jBtnSimpanTr;
    private javax.swing.JButton jBtnTransaksi;
    private javax.swing.JComboBox<String> jCBCariKy;
    private javax.swing.JComboBox<String> jCBCariOb;
    private javax.swing.JComboBox<String> jCBCariTr;
    private javax.swing.JComboBox<String> jCBJenisObat;
    private javax.swing.JComboBox<String> jCBStatusTransaksi;
    private com.toedter.calendar.JDateChooser jDCTanggalTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelBeranda;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelKaryawan;
    private javax.swing.JPanel jPanelLaporan;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelObat;
    private javax.swing.JPanel jPanelTransaksi;
    private javax.swing.JRadioButton jRBLaki;
    private javax.swing.JRadioButton jRBPerempuan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTFAlamat;
    private javax.swing.JTextField jTFCariKy;
    private javax.swing.JTextField jTFCariOb;
    private javax.swing.JTextField jTFCariTr;
    private javax.swing.JTextField jTFIdKaryawan;
    private javax.swing.JTextField jTFIdObat;
    private javax.swing.JTextField jTFIdTransaksi;
    private javax.swing.JTextField jTFKategoriObat;
    private javax.swing.JTextField jTFNamaKaryawan;
    private javax.swing.JTextField jTFNamaObat;
    private javax.swing.JTextField jTFNamaPelanggan;
    private javax.swing.JTextField jTFNotelp;
    private javax.swing.JTextField jTFStokObat;
    private javax.swing.JTextField jTFTotalBayar;
    private javax.swing.JTable jTableKaryawan;
    private javax.swing.JTable jTableLaporanDataKaryawan;
    private javax.swing.JTable jTableLaporanDataObat;
    private javax.swing.JTable jTableLaporanDataTransaksi;
    private javax.swing.JTable jTableObat;
    private javax.swing.JTable jTableTransaksi;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
