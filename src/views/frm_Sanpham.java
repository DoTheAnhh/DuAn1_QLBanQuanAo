/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ChatLieu;
import models.ChiTietSP;
import models.DanhMucSP;
import models.KhuyenMai;
import models.KichCo;
import models.MauSac;
import models.NSX;
import models.ThuongHieu;
import org.bridj.cpp.com.OLEAutomationLibrary;
import services.IChatLieuServices;
import services.IChiTietSPServices;
import services.IDanhMucSPServices;
import services.IKhuyenmaiService;
import services.IKichCoServices;
import services.IMauSacServices;
import services.INSXServices;
import services.IThuongHieuServices;
import services.imp.ChatLieuServices;
import services.imp.ChiTietSPServices;
import services.imp.DanhMucSPServices;
import services.imp.KhuyenmaiService;
import services.imp.KichCoServices;
import services.imp.MauSacServices;
import services.imp.NSXServices;
import services.imp.ThuongHieuServices;
import viewmodels.ChiTietSPViewModel;
import viewmodels.KhuyenmaiViewmodel;
import viewmodels.SanPhamViewModel;

/**
 *
 * @author hungh
 */
public class frm_Sanpham extends javax.swing.JPanel {

    private IChiTietSPServices iChiTietSPServices;
    private INSXServices iNSXServices;
    private IMauSacServices iMauSacServices;
    private IDanhMucSPServices iDanhMucSPServices;
    private IKichCoServices iKichCoServices;
    private IChatLieuServices iChatLieuServices;
    private IThuongHieuServices iThuongHieuServices;
    private IKhuyenmaiService iKhuyenmaiService;
    DefaultTableModel defaultTableModel;
    private boolean hish = false;
    String sp = null;

    public frm_Sanpham() {
        initComponents();

        innitTable();
//        listctsp = iChiTietSPServices.getAll();
        iChiTietSPServices = new ChiTietSPServices();
        iNSXServices = new NSXServices();
        iMauSacServices = new MauSacServices();
        iDanhMucSPServices = new DanhMucSPServices();
        iKichCoServices = new KichCoServices();
        iChatLieuServices = new ChatLieuServices();
        iThuongHieuServices = new ThuongHieuServices();
        iKhuyenmaiService = new KhuyenmaiService();
        initcbo();
        loadData(iChiTietSPServices.getAll());
    }

    public void initcbo() {
        List<NSX> listnsx = iNSXServices.getAll();
        cbo_nsx.setModel(new DefaultComboBoxModel(listnsx.toArray()));

        List<MauSac> listms = iMauSacServices.getAll();
        cbo_mausac.setModel(new DefaultComboBoxModel(listms.toArray()));

        List<DanhMucSP> listdmsp = iDanhMucSPServices.getAll();
        cbo_danhmuc.setModel(new DefaultComboBoxModel(listdmsp.toArray()));

        List<KichCo> listkc = iKichCoServices.getAll();
        cbo_size.setModel(new DefaultComboBoxModel(listkc.toArray()));

        List<ChatLieu> listcl = iChatLieuServices.getAll();
        cbo_chatlieu.setModel(new DefaultComboBoxModel(listcl.toArray()));

        List<ThuongHieu> listth = iThuongHieuServices.getAll();
        cbo_thuonghieu1.setModel(new DefaultComboBoxModel(listth.toArray()));

    }

    private int getindexmausac(ChiTietSPViewModel x) {
        List<MauSac> lst = iMauSacServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getMausac().getId()) {
                index = i;
            }
        }
        return index;
    }

    private void xuatbarcode(ChiTietSPViewModel x) {
        System.out.println(x.toString());
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(x.getQrcode());
            barcode.setI(11.0f);
            barcode.renderBarcode("D:\\QRcode\\" + x.getTen() + ".png");
            System.out.println("xuất thành công");
        } catch (Exception e) {
            System.out.println("xuất thất bại");
        }
    }

    public static void generateQRcode(String data, String path, Map map, int h, int w) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, w, h);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        } catch (Exception ex) {
            Logger.getLogger(frm_Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getindexnsx(ChiTietSPViewModel x) {
        List<NSX> lst = iNSXServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getNsx().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexdanhmuc(ChiTietSPViewModel x) {
        List<DanhMucSP> lst = iDanhMucSPServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getDanhmuc().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexsize(ChiTietSPViewModel x) {
        List<KichCo> lst = iKichCoServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getKichco().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexthuonghieu(ChiTietSPViewModel x) {
        List<ThuongHieu> lst = iThuongHieuServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getThuonghieu().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexchatlieu(ChiTietSPViewModel x) {
        List<ChatLieu> lst = iChatLieuServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getChatlieu().getId()) {
                index = i;
            }
        }
        return index;

    }

    private String zenbarcode() {
        Random random = new Random();
        int ran = random.nextInt(999999);
        int dom = random.nextInt(999999);
        return ran + "" + dom;
    }

    private ChiTietSPViewModel getdadtafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_ma.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm!");
            return null;

        }
        if (txt_ma.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không quá 15 kí tự!");
            return null;
        }
//  tên sp
        if (txt_ten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm!");
            return null;
        }
        
        if (p.matcher(txt_ten.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm phải là chữ!");
            return null;
        }
        
        if (txt_ten.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không quá 30 kí tự!");
            return null;
        }
// Số lượng tồn
        if (txt_soluongton.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng tồn!");
            return null;
        }

        try {
            Integer.valueOf(txt_soluongton.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số!");
            return null;
        }

        if (Integer.valueOf(txt_soluongton.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải lớn hơn 0!");
            return null;
        }
// giá nhập

        if (txt_gianhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá nhập!");
            return null;
        }

        try {
            Double.valueOf(txt_gianhap.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số!");
            return null;
        }

        if (Double.valueOf(txt_gianhap.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0!");
            return null;
        }
// giá bán
        if (txt_giaban.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá bán!");
            return null;
        }

        try {
            Double.valueOf(txt_giaban.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số!");
            return null;
        }

        if (Double.valueOf(txt_giaban.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
            return null;
        }
///////////////
        String mota = txt_mota.getText();
        if (txt_mota.getText().equals("")) {
            mota = "không có";
        }
        NSX nsx = (NSX) cbo_nsx.getSelectedItem();
        DanhMucSP danhmuc = (DanhMucSP) cbo_danhmuc.getSelectedItem();
        MauSac mausac = (MauSac) cbo_mausac.getSelectedItem();
        ChatLieu chatlieu = (ChatLieu) cbo_chatlieu.getSelectedItem();
        ThuongHieu thuonghieu = (ThuongHieu) cbo_thuonghieu1.getSelectedItem();
        KichCo kichco = (KichCo) cbo_size.getSelectedItem();
        double gianhap;
        double giaban;
        int soluong;
        try {
            gianhap = Double.parseDouble(txt_gianhap.getText());
            giaban = Double.parseDouble(txt_giaban.getText());
            soluong = Integer.parseInt(txt_soluongton.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
        gianhap = Double.parseDouble(txt_gianhap.getText());
        giaban = Double.parseDouble(txt_giaban.getText());
        soluong = Integer.parseInt(txt_soluongton.getText());
        
        ChiTietSPViewModel ctsp = new ChiTietSPViewModel(txt_ma.getText(), txt_ten.getText(), nsx, mausac, danhmuc, kichco, chatlieu, thuonghieu, null, soluong, gianhap, giaban, mota, zenbarcode());
        return ctsp;
    }

    private ChiTietSPViewModel getdataTB(int row) {
        String ma = tbl_sp.getValueAt(row, 0).toString();
        String ten = tbl_sp.getValueAt(row, 1).toString();
        NSX nsx = (NSX) tbl_sp.getValueAt(row, 2);
        MauSac ms = (MauSac) tbl_sp.getValueAt(row, 3);
        DanhMucSP dmsp = (DanhMucSP) tbl_sp.getValueAt(row, 4);
        KichCo kc = (KichCo) tbl_sp.getValueAt(row, 5);
        ChatLieu cl = (ChatLieu) tbl_sp.getValueAt(row, 6);
        ThuongHieu th = (ThuongHieu) tbl_sp.getValueAt(row, 7);
        KhuyenMai km = (KhuyenMai) tbl_sp.getValueAt(row, 8);
        int soluong = (int) tbl_sp.getValueAt(row, 9);
        double gianhap = (double) tbl_sp.getValueAt(row, 10);
        double giaban = (double) tbl_sp.getValueAt(row, 11);
        String mota = tbl_sp.getValueAt(row, 12).toString();
        String barcode = tbl_sp.getValueAt(row, 13).toString();
        
        return new ChiTietSPViewModel(ma, ten, nsx, ms, dmsp, kc, cl, th, km, soluong, gianhap, giaban, mota, barcode);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradiente1 = new swing.PanelGradiente();
        panelBorder1 = new swing.PanelBorder();
        txt_ma = new swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_ten = new swing.MyTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_soluongton = new swing.MyTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_gianhap = new swing.MyTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_giaban = new swing.MyTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        cbo_nsx = new javax.swing.JComboBox<>();
        cbo_mausac = new javax.swing.JComboBox<>();
        cbo_danhmuc = new javax.swing.JComboBox<>();
        cbo_size = new javax.swing.JComboBox<>();
        cbo_chatlieu = new javax.swing.JComboBox<>();
        btn_capnhat = new swing.MyButton();
        btn_lammoi = new swing.MyButton();
        btn_xoa = new swing.MyButton();
        btn_them = new swing.MyButton();
        cbo_thuonghieu1 = new javax.swing.JComboBox<>();
        myButton1 = new swing.MyButton();
        panelBorder2 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();
        panelBorder3 = new swing.PanelBorder();
        searchText = new swing.SearchText();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hideshow = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        panelGradiente1.setColorPrimario(new java.awt.Color(204, 204, 204));
        panelGradiente1.setColorSecundario(new java.awt.Color(51, 51, 51));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));
        panelBorder1.add(txt_ma);
        txt_ma.setBounds(30, 30, 210, 40);

        jLabel1.setText("Mã sản phẩm");
        panelBorder1.add(jLabel1);
        jLabel1.setBounds(30, 10, 210, 20);
        panelBorder1.add(txt_ten);
        txt_ten.setBounds(30, 100, 210, 40);

        jLabel2.setText("Tên sản phẩm");
        panelBorder1.add(jLabel2);
        jLabel2.setBounds(30, 80, 210, 20);

        jLabel3.setText("Nhà sản xuất");
        panelBorder1.add(jLabel3);
        jLabel3.setBounds(30, 150, 210, 20);

        jLabel4.setText("Màu sắc");
        panelBorder1.add(jLabel4);
        jLabel4.setBounds(30, 220, 210, 20);

        jLabel5.setText("Danh mục");
        panelBorder1.add(jLabel5);
        jLabel5.setBounds(280, 10, 210, 20);

        jLabel6.setText("Size");
        panelBorder1.add(jLabel6);
        jLabel6.setBounds(280, 80, 210, 20);

        jLabel7.setText("Chất liệu");
        panelBorder1.add(jLabel7);
        jLabel7.setBounds(280, 150, 210, 20);

        jLabel8.setText("Thương hiệu");
        panelBorder1.add(jLabel8);
        jLabel8.setBounds(280, 220, 210, 20);
        panelBorder1.add(txt_soluongton);
        txt_soluongton.setBounds(510, 30, 210, 40);

        jLabel10.setText("Số lượng tồn");
        panelBorder1.add(jLabel10);
        jLabel10.setBounds(510, 10, 210, 20);
        panelBorder1.add(txt_gianhap);
        txt_gianhap.setBounds(510, 100, 210, 40);

        jLabel11.setText("Giá nhập");
        panelBorder1.add(jLabel11);
        jLabel11.setBounds(510, 80, 210, 20);
        panelBorder1.add(txt_giaban);
        txt_giaban.setBounds(510, 170, 210, 40);

        jLabel12.setText("Mô tả");
        panelBorder1.add(jLabel12);
        jLabel12.setBounds(510, 210, 220, 20);

        txt_mota.setColumns(20);
        txt_mota.setRows(2);
        txt_mota.setTabSize(0);
        txt_mota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jScrollPane4.setViewportView(txt_mota);

        panelBorder1.add(jScrollPane4);
        jScrollPane4.setBounds(510, 230, 220, 70);

        jLabel13.setText("Giá bán");
        panelBorder1.add(jLabel13);
        jLabel13.setBounds(510, 150, 210, 20);

        cbo_nsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_nsx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelBorder1.add(cbo_nsx);
        cbo_nsx.setBounds(30, 170, 210, 40);

        cbo_mausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_mausac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelBorder1.add(cbo_mausac);
        cbo_mausac.setBounds(30, 240, 210, 40);

        cbo_danhmuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_danhmuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        cbo_danhmuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_danhmucActionPerformed(evt);
            }
        });
        panelBorder1.add(cbo_danhmuc);
        cbo_danhmuc.setBounds(280, 30, 210, 40);

        cbo_size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_size.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelBorder1.add(cbo_size);
        cbo_size.setBounds(280, 100, 210, 40);

        cbo_chatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_chatlieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelBorder1.add(cbo_chatlieu);
        cbo_chatlieu.setBounds(280, 170, 210, 40);

        btn_capnhat.setBackground(new java.awt.Color(125, 224, 237));
        btn_capnhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        btn_capnhat.setText("Cập nhật");
        btn_capnhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });
        panelBorder1.add(btn_capnhat);
        btn_capnhat.setBounds(810, 120, 140, 40);

        btn_lammoi.setBackground(new java.awt.Color(125, 224, 237));
        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_lammoi.setText("Làm mới");
        btn_lammoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });
        panelBorder1.add(btn_lammoi);
        btn_lammoi.setBounds(810, 20, 140, 40);

        btn_xoa.setBackground(new java.awt.Color(125, 224, 237));
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file.png"))); // NOI18N
        btn_xoa.setText("In QRcode");
        btn_xoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        panelBorder1.add(btn_xoa);
        btn_xoa.setBounds(810, 170, 140, 40);

        btn_them.setBackground(new java.awt.Color(125, 224, 237));
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        panelBorder1.add(btn_them);
        btn_them.setBounds(810, 70, 140, 40);

        cbo_thuonghieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_thuonghieu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelBorder1.add(cbo_thuonghieu1);
        cbo_thuonghieu1.setBounds(280, 240, 210, 40);

        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });
        panelBorder1.add(myButton1);
        myButton1.setBounds(760, 20, 40, 40);

        panelGradiente1.add(panelBorder1);
        panelBorder1.setBounds(8, 0, 990, 320);

        panelBorder2.setBackground(new java.awt.Color(204, 204, 204));

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sp.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_sp.setRowHeight(25);
        tbl_sp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_spMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sp);

        panelBorder2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 60, 990, 240);

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });
        panelBorder3.add(searchText);
        searchText.setBounds(10, 0, 250, 40);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png"))); // NOI18N
        panelBorder3.add(jLabel14);
        jLabel14.setBounds(270, 0, 24, 40);

        panelBorder2.add(panelBorder3);
        panelBorder3.setBounds(660, 10, 310, 40);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new-page.png"))); // NOI18N
        jLabel15.setText("Thêm thuộc tính");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        panelBorder2.add(jLabel15);
        jLabel15.setBounds(260, 10, 180, 40);

        hideshow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hideshow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        hideshow.setText("  Hiện sản phẩm hết");
        hideshow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideshowMouseClicked(evt);
            }
        });
        panelBorder2.add(hideshow);
        hideshow.setBounds(40, 10, 180, 40);

        panelGradiente1.add(panelBorder2);
        panelBorder2.setBounds(10, 329, 990, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        loadData(iChiTietSPServices.getAll());
        clearForm();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        ChiTietSPViewModel x = getdadtafrom();
        if (x == null) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm sản phẩm ?", "Thêm sản phẩm mới", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            boolean kq = iChiTietSPServices.Add(x);
            if (kq == true) {
                loadData(iChiTietSPServices.getAll());
//            xuatbarcode(x);
                String data = x.getQrcode();
                String path = "D:\\QRcode\\Qr" + x.getTen() + ".png";
                Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                generateQRcode(data, path, hashMap, 200, 200);
                System.out.println("QR Code created successfully.");
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, " Bạn cần chọn 1 dòng để cập nhật");
            return;
        }
        String ma = (String) tbl_sp.getValueAt(row, 0);
        ChiTietSPViewModel x = getdadtafrom();
        if (x == null) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có chắc muốn cập nhật lại sản phẩm ?", "Update", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            boolean kq = iChiTietSPServices.Update(ma, x);
            if (kq == true) {
                loadData(iChiTietSPServices.getAll());
                JOptionPane.showMessageDialog(this, "Thành công", "Update", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại", "Update", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }//GEN-LAST:event_btn_capnhatActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "chọn sản phẩm cần in barcode");
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
//        xuatbarcode(x);
        String data = x.getQrcode();
        String path = "D:\\QRcode\\Qr" + x.getTen() + ".png";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQRcode(data, path, hashMap, 200, 200);
        JOptionPane.showMessageDialog(this, "In QR Code thành công");
        System.out.println("QR Code created successfully.");
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void tbl_spMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_spMouseClicked
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
        txt_ma.setText(x.getMa());
        txt_ten.setText(x.getTen());
        txt_soluongton.setText(x.getSoluongton() + "");
        txt_giaban.setText(x.getGiaban() + "");
        txt_gianhap.setText(x.getGianhap() + "");
        txt_mota.setText(x.getMota());
        cbo_chatlieu.setSelectedIndex(getindexchatlieu(x));
        cbo_danhmuc.setSelectedIndex(getindexdanhmuc(x));
        cbo_mausac.setSelectedIndex(getindexmausac(x));
        cbo_nsx.setSelectedIndex(getindexnsx(x));
        cbo_size.setSelectedIndex(getindexsize(x));
        cbo_thuonghieu1.setSelectedIndex(getindexthuonghieu(x));

    }//GEN-LAST:event_tbl_spMouseClicked

    private void hideshowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideshowMouseClicked

        if (hish == false) {
            hish = true;
            hideshow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png")));
            loadData(iChiTietSPServices.getSPhet());
            hideshow.setText("Ẩn sản phẩm hết");
        } else {
            hish = false;
            hideshow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png")));
            hideshow.setText("Hiện sản phẩm hết");
            loadData(iChiTietSPServices.getAll());
        }

    }//GEN-LAST:event_hideshowMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        new frm_themthuoctinh(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        loadData(iChiTietSPServices.getlistbyTen("%" + searchText.getText() + "%"));
    }//GEN-LAST:event_searchTextKeyReleased

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        initcbo();
    }//GEN-LAST:event_myButton1ActionPerformed

    private void cbo_danhmucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_danhmucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_danhmucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btn_capnhat;
    private swing.MyButton btn_lammoi;
    private swing.MyButton btn_them;
    private swing.MyButton btn_xoa;
    private javax.swing.JComboBox<String> cbo_chatlieu;
    private javax.swing.JComboBox<String> cbo_danhmuc;
    private javax.swing.JComboBox<String> cbo_mausac;
    private javax.swing.JComboBox<String> cbo_nsx;
    private javax.swing.JComboBox<String> cbo_size;
    private javax.swing.JComboBox<String> cbo_thuonghieu1;
    private javax.swing.JLabel hideshow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private swing.MyButton myButton1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelGradiente panelGradiente1;
    private swing.SearchText searchText;
    private javax.swing.JTable tbl_sp;
    private swing.MyTextField txt_giaban;
    private swing.MyTextField txt_gianhap;
    private swing.MyTextField txt_ma;
    private javax.swing.JTextArea txt_mota;
    private swing.MyTextField txt_soluongton;
    private swing.MyTextField txt_ten;
    // End of variables declaration//GEN-END:variables

    private void innitTable() {
        defaultTableModel = (DefaultTableModel) tbl_sp.getModel();
        defaultTableModel.setColumnCount(0);
        defaultTableModel.addColumn("Mã");
        defaultTableModel.addColumn("Tên");
        defaultTableModel.addColumn("NSX");
        defaultTableModel.addColumn("Màu sắc");
        defaultTableModel.addColumn("Danh mục");
        defaultTableModel.addColumn("Size");
        defaultTableModel.addColumn("Chất liệu");
        defaultTableModel.addColumn("Thương hiệu");
        defaultTableModel.addColumn("Khuyến mãi");
        defaultTableModel.addColumn("Số lượng tồn");
        defaultTableModel.addColumn("Giá nhập");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Mô tả");
        defaultTableModel.addColumn("Barcode");

    }

    private void loadData(List<ChiTietSPViewModel> lst) {
        defaultTableModel = (DefaultTableModel) tbl_sp.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSPViewModel x : lst) {
            defaultTableModel.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getNsx(),
                x.getMausac(),
                x.getDanhmuc(),
                x.getKichco(),
                x.getChatlieu(),
                x.getThuonghieu(),
                x.getKhuyenmai(),
                x.getSoluongton(),
                x.getGianhap(),
                x.getGiaban(),
                x.getMota(),
                x.getQrcode()
            });
        }
    }

//    public ChiTietSP getData() {
//        String ma = txt_ma.getText();
//        String ten = txt_ten.getText();
//        NSX nsx =(NSX) cbo_nsx.getSelectedItem();
//        MauSac ms = (MauSac) cbo_mausac.getSelectedItem();
//        DanhMucSP dmsp = (DanhMucSP) cbo_danhmuc.getSelectedItem();
//        KichCo kc = (KichCo) cbo_size.getSelectedItem();
//        ChatLieu cl = (ChatLieu) cbo_chatlieu.getSelectedItem();
//        ThuongHieu th = (ThuongHieu) cbo_thuonghieu1.getSelectedItem();
//        KhuyenmaiViewmodel km = (KhuyenmaiViewmodel) cbo_khuyenmai.getSelectedItem();
//        int slt = Integer.parseInt(txt_soluongton.getText());
//        double gianhap = Double.parseDouble(txt_gianhap.getText());
//        double giaban = Double.parseDouble(txt_giaban.getText());
//        String mota = txt_mota.getText();
//        String qrcode = "";
//
//        return new ChiTietSP(ma, ten, nsx.getTen(), ms.getTen(), dmsp.getTen(), kc.getTen(), cl.getTen(), th.getTen(), km.getTenKM(), slt, gianhap, giaban, mota, qrcode);
//    }
    public void clearForm() {
        txt_ma.setText("");
        txt_ten.setText("");
        txt_soluongton.setText("");
        txt_gianhap.setText("");
        txt_giaban.setText("");
        txt_mota.setText("");
    }

//    public boolean check() {
//        if (txt_ma.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        if (txt_ten.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        
//        if (txt_soluongton.getText() == null) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        if (txt_gianhap.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        if (txt_giaban.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        if (txt_mota.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Bạn không đc để trống");
//            return false;
//        }
//        return true;
//    }
//
//    public int getId() {
//        Integer row = tbl_sp.getSelectedRow();
//        int id = (int) tbl_sp.getValueAt(row, 0);
//        return id;
//
//    }
}
