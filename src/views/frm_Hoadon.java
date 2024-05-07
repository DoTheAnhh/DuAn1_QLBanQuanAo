package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.IHoaDonServiec;
import services.imp.HoaDonServiec;
import viewmodels.HoaDonCHiTietViewModel;
import viewmodels.HoaDonViewModel;

public class frm_Hoadon extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    private HoaDonServiec HoaDonServiec;
    private HoaDonServiec HoaDonCTServiec;

    public frm_Hoadon() {
        initComponents();

        HoaDonServiec = new HoaDonServiec();
        HoaDonCTServiec = new HoaDonServiec();

        loatdate1();

    }

    private void loatdate1() {
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.getRowCount();
        List<HoaDonViewModel> hdv = HoaDonServiec.getAllHD();
        for (HoaDonViewModel hoaDonViewModel : hdv) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),
                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),
            });
        }

    }

    public void TKTenHoaDOn() {

//        if (txt_tk.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
//            return;
//        }
//        if (HoaDonService.getTimHDTen(txt_tk.getText()).isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tên này không có trong danh sách");
//            return;
//        }
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonViewModel> list = HoaDonServiec.getTimHDTen("%" + txt_tk.getText() + "%");
        for (HoaDonViewModel hoaDonViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),
                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),});

        }
    }

    public void TKTTHoaDOn() {

        int tt = cbo_trangthai.getSelectedIndex();
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonViewModel> list = HoaDonServiec.getTimHDTrangThai((int) tt);
        for (HoaDonViewModel hoaDonViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),


                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        cbo_thang = new javax.swing.JComboBox<>();
        cbo_nam = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        panelGradiente1 = new swing.PanelGradiente();
        panelBorder1 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        panelBorder3 = new swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        txt_tk = new swing.SearchText();
        cbo_trangthai = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Date_NgayTT = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HoaDonCT = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Hóa đơn");

        cbo_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbo_nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tháng");

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        panelGradiente1.setColorPrimario(new java.awt.Color(204, 204, 204));
        panelGradiente1.setColorSecundario(new java.awt.Color(204, 204, 204));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MA", "TÊN NHÂN VIÊN", "TÊN KHÁCH HÀNG", "TỔNG TIỀN", "NGÀY TẠO", "NGÀY TT", "TRẠNG THÁI", "GHI CHÚ"
            }
        ));
        tbl_hoadon.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoadon);
        if (tbl_hoadon.getColumnModel().getColumnCount() > 0) {
            tbl_hoadon.getColumnModel().getColumn(3).setResizable(false);
        }

        panelBorder1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 190, 990, 180);

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        panelBorder3.add(jLabel3);
        jLabel3.setBounds(750, 0, 40, 40);

        txt_tk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tkMouseClicked(evt);
            }
        });
        txt_tk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tkKeyReleased(evt);
            }
        });
        panelBorder3.add(txt_tk);
        txt_tk.setBounds(10, 0, 730, 40);

        panelBorder1.add(panelBorder3);
        panelBorder3.setBounds(100, 30, 810, 40);

        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
        cbo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangthaiActionPerformed(evt);
            }
        });
        panelBorder1.add(cbo_trangthai);
        cbo_trangthai.setBounds(180, 120, 220, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("NGÀY THANH TOÁN");
        panelBorder1.add(jLabel4);
        jLabel4.setBounds(510, 90, 140, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Trạng thái");
        panelBorder1.add(jLabel5);
        jLabel5.setBounds(180, 100, 220, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Hóa Đơn");
        panelBorder1.add(jLabel7);
        jLabel7.setBounds(0, 160, 220, 30);

        Date_NgayTT.setDateFormatString("dd/MM/yyyy");
        Date_NgayTT.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Date_NgayTTAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        panelBorder1.add(Date_NgayTT);
        Date_NgayTT.setBounds(510, 116, 210, 40);

        jButton1.setText("Tìm kiếm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        panelBorder1.add(jButton1);
        jButton1.setBounds(750, 130, 100, 23);

        panelGradiente1.add(panelBorder1);
        panelBorder1.setBounds(10, -1, 990, 370);

        panelBorder2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Hóa đơn chi tiết");
        panelBorder2.add(jLabel1);
        jLabel1.setBounds(10, 0, 220, 30);

        tbl_HoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HoaDonCT.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tbl_HoaDonCT);

        panelBorder2.add(jScrollPane2);
        jScrollPane2.setBounds(0, 30, 990, 220);

        panelGradiente1.add(panelBorder2);
        panelBorder2.setBounds(10, 380, 990, 250);

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

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
//        if (JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xem Hóa Đơn Chi Tiết Không !") != JOptionPane.YES_OPTION) {
//            return;
//        }

        int row = tbl_hoadon.getSelectedRow();
        defaultTableModel = (DefaultTableModel) tbl_HoaDonCT.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonCHiTietViewModel> list = HoaDonCTServiec.gettimma(tbl_hoadon.getValueAt(row, 0).toString());
        for (HoaDonCHiTietViewModel CT : list) {
            defaultTableModel.addRow(new Object[]{
                CT.getHaDon().getMa(),
                CT.getSanPham().getMa(),
                CT.getSanPham().getTen(),
                CT.getSoluong(),
                CT.getDonGia(),
                CT.getThanhTien()});
        }

    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void txt_tkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tkMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_tkMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if (Date_NgayTT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(Date_NgayTT.getDate());
            if (HoaDonServiec.GetTimNTT(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Có Ngày Thanh Toán Nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            List<HoaDonViewModel> list = HoaDonServiec.GetTimNTT(date3);
            for (HoaDonViewModel hoaDonViewModel : list) {
                defaultTableModel.addRow(new Object[]{
                    hoaDonViewModel.getMa(),
                    hoaDonViewModel.getUs().getTen(),
                    hoaDonViewModel.getKh().getTen(),
                    hoaDonViewModel.getTongTien(),
                    hoaDonViewModel.getNgayTao(),
                    hoaDonViewModel.getNgayThanhToan(),
                    hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hoaDonViewModel.getGhiChu(),});

            }

        }


    }//GEN-LAST:event_jLabel3MouseClicked

    private void cbo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangthaiActionPerformed
        // TODO add your handling code here:

        TKTTHoaDOn();
    }//GEN-LAST:event_cbo_trangthaiActionPerformed

    private void txt_tkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tkKeyReleased
        // TODO add your handling code here:
        TKTenHoaDOn();
    }//GEN-LAST:event_txt_tkKeyReleased

    private void Date_NgayTTAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Date_NgayTTAncestorAdded
        if (Date_NgayTT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(Date_NgayTT.getDate());
            if (HoaDonServiec.GetTimNTT(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Có Ngày Thanh Toán Nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            List<HoaDonViewModel> list = HoaDonServiec.GetTimNTT(date3);
            for (HoaDonViewModel hoaDonViewModel : list) {
                defaultTableModel.addRow(new Object[]{
                    hoaDonViewModel.getMa(),
                    hoaDonViewModel.getUs().getTen(),
                    hoaDonViewModel.getKh().getTen(),
                    hoaDonViewModel.getTongTien(),
                    hoaDonViewModel.getNgayTao(),
                    hoaDonViewModel.getNgayThanhToan(),
                    hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hoaDonViewModel.getGhiChu(),});

            }

        }

    }//GEN-LAST:event_Date_NgayTTAncestorAdded

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (Date_NgayTT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(Date_NgayTT.getDate());
            if (HoaDonServiec.GetTimNTT(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Có Ngày Thanh Toán Nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            List<HoaDonViewModel> list = HoaDonServiec.GetTimNTT(date3);
            for (HoaDonViewModel hoaDonViewModel : list) {
                defaultTableModel.addRow(new Object[]{
                    hoaDonViewModel.getMa(),
                    hoaDonViewModel.getUs().getTen(),
                    hoaDonViewModel.getKh().getTen(),
                    hoaDonViewModel.getTongTien(),
                    hoaDonViewModel.getNgayTao(),
                    hoaDonViewModel.getNgayThanhToan(),
                    hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hoaDonViewModel.getGhiChu(),});

            }

        }
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_NgayTT;
    private javax.swing.JComboBox<String> cbo_nam;
    private javax.swing.JComboBox<String> cbo_thang;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelGradiente panelGradiente1;
    private javax.swing.JTable tbl_HoaDonCT;
    private javax.swing.JTable tbl_hoadon;
    private swing.SearchText txt_tk;
    // End of variables declaration//GEN-END:variables
}
