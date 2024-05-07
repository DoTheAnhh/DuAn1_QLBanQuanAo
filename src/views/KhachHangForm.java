/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.KhachHang;
import services.IHoaDonServiec;
import services.IKhachHangService;
import services.imp.HoaDonServiec;
import services.imp.khahangsvImpl;
import viewmodels.KhachHangViewMD;

/**
 *
 * @author Admin
 */
public class KhachHangForm extends javax.swing.JFrame {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    List<KhachHangViewMD> listKhachHang;
    private IKhachHangService KH;
    private IHoaDonServiec hoaDonServiec;
    String Ma;

    public KhachHangForm(String MaHD) {
        initComponents();
        setLocationRelativeTo(null);
        KH = new khahangsvImpl();
        hoaDonServiec = new HoaDonServiec();
        listKhachHang = KH.getall();

        Ma = MaHD;
        showTable(listKhachHang);

    }

    public void showTable(List<KhachHangViewMD> list) {
        defaultTableModel = (DefaultTableModel) tb_khachHang.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHangViewMD khachHang01 : list) {
            defaultTableModel.addRow(khachHang01.toDataRow());
        }
    }

    private KhachHang getData() {
        KhachHang cv = new KhachHang();
        cv.setTen(txt_Ten1.getText());
        cv.setTendem(txt_TenDem.getText());
        cv.setHo(txt_Ho.getText());
        int gt;
        if (rd_nam.isSelected()) {
            gt = 0;
        } else {
            gt = 1;
        }
        cv.setGioitinh(gt);
        cv.setNgaysinh(date_ngaysinh.getDate());
        cv.setSdt(txt_sdt.getText());
        cv.setEmail(txt_email.getText());

        return cv;
    }

    public int layid() {
        Integer row = tb_khachHang.getSelectedRow();
        int id = (int) tb_khachHang.getValueAt(row, 0);
        return id;

    }

    public boolean check() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_Ten1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên!");
            return false;
        }
        if (p.matcher(txt_Ten1.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên của bạn không được nhập số");
            return false;
        } else if (txt_Ten1.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return false;
        }
        if (txt_sdt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập SĐT!");
            return false;
        }
        try {
            if (!txt_sdt.getText().matches(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại của bạn chưa đúng định dạng");
                return false;
            }
        } catch (Exception e) {
        }
        if (KH.kiemtrasdt(txt_sdt.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại của bạn đã tồn tại");
            return false;
        }
        if (txt_email.getText().equals("")) {
            return true;
        } else {
            try {
                if (!txt_email.getText().matches(mail)) {
                    JOptionPane.showMessageDialog(this, "Email của bạn chưa đúng định dạng");
                    return false;
                }
            } catch (Exception e) {
            }
        }
        if (KH.kiemtra(txt_email.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại");
            return false;
        }

        return true;

    }

    public boolean check2() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_Ho.getText().equals("") & txt_TenDem.getText().equals("")) {
            return true;
        } else {
            if (p.matcher(txt_Ho.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Họ của bạn không được nhập số");
                return false;
            }
            if (txt_Ho.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
                return false;
            }
            if (p.matcher(txt_TenDem.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Tên đệm của bạn không được nhập số");
                return false;
            }
            if (txt_TenDem.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Tên Đệm không được quá 30 kí tự");
                return false;
            }
        }

        return true;

    }

    public boolean check3() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_Ten1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên!");
            return false;
        }
        if (p.matcher(txt_Ten1.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên của bạn không được nhập số");
            return false;
        }
        if (txt_Ten1.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return false;
        }
        if (txt_sdt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập SĐT!");
            return false;
        }
        try {
            if (!txt_sdt.getText().matches(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại của bạn chưa đúng định dạng");
                return false;
            }
        } catch (Exception e) {
        }

        if (txt_email.getText().equals("")) {
            return true;
        } else {
            try {

                if (!txt_email.getText().matches(mail)) {
                    JOptionPane.showMessageDialog(this, "Email của bạn chưa đúng định dạng");
                    return false;
                }

            } catch (Exception e) {
            }
        }

        return true;
    }

    public boolean check4() {
        Pattern p = Pattern.compile("^[0-9]+$");

        if (txt_Ho.getText() == null & txt_TenDem.getText() == null) {
            return true;
        } else {

            if (p.matcher(txt_Ho.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Họ của bạn không được nhập số");
                return false;
            }
            if (txt_Ho.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
                return false;
            }
            if (p.matcher(txt_TenDem.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Tên đệm của bạn không được nhập số");
                return false;
            }
            if (txt_TenDem.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Tên Đệm không được quá 30 kí tự");
                return false;

            }
        }

        return true;
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_khachHang = new javax.swing.JTable();
        txt_Ho = new swing.MyTextField();
        txt_Ten1 = new swing.MyTextField();
        date_ngaysinh = new com.toedter.calendar.JDateChooser();
        txt_sdt = new swing.MyTextField();
        txt_TenDem = new swing.MyTextField();
        txt_email = new swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rd_nam = new javax.swing.JRadioButton();
        rd_nu = new javax.swing.JRadioButton();
        Btn_capNhat = new swing.MyButton();
        btn_them = new swing.MyButton();
        btn_chon = new swing.MyButton();
        txt_timKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_khachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên Khách Hàng", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Điểm Thưởng"
            }
        ));
        tb_khachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tb_khachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_khachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_khachHang);

        date_ngaysinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        date_ngaysinh.setDateFormatString("dd/MM/yyyy");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tên đệm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Họ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ngày sinh");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("E-mail");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Giới tính");

        buttonGroup1.add(rd_nam);
        rd_nam.setSelected(true);
        rd_nam.setText("Nam");

        buttonGroup1.add(rd_nu);
        rd_nu.setText("Nữ");

        Btn_capNhat.setBackground(new java.awt.Color(125, 224, 237));
        Btn_capNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        Btn_capNhat.setText("Cập nhật");
        Btn_capNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_capNhatActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(125, 224, 237));
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_chon.setBackground(new java.awt.Color(125, 224, 237));
        btn_chon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        btn_chon.setText("Chọn");
        btn_chon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonActionPerformed(evt);
            }
        });

        txt_timKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timKiemCaretUpdate(evt);
            }
        });
        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Ho, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Ten1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rd_nam)
                                .addGap(18, 18, 18)
                                .addComponent(rd_nu))
                            .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_chon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(date_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ten1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Ho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_nam)
                    .addComponent(rd_nu)
                    .addComponent(Btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_chon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_timKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_capNhatActionPerformed
        int row = tb_khachHang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "cần chọn khách hàng để cập nhật");
            return;
        }
        if (check3() && check4()) {

            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?") == JOptionPane.YES_OPTION) {
                int id = layid();

                JOptionPane.showMessageDialog(this, KH.update(id, getData()));
                listKhachHang = KH.getall();
                showTable(listKhachHang);
            }
        }
    }//GEN-LAST:event_Btn_capNhatActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
         if (check() && check2()) {
            JOptionPane.showMessageDialog(this, KH.add(getData()));
            listKhachHang = KH.getall();
            showTable(listKhachHang);
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_chonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonActionPerformed
        int rowKh = tb_khachHang.getSelectedRow();
        if (rowKh < 0) {
          Integer updateHoaDonKh = hoaDonServiec.updateHoaDonKhachHang(19, Ma);
        dispose();
        }
        Integer updateHoaDonKh = hoaDonServiec.updateHoaDonKhachHang(Integer.parseInt(tb_khachHang.getValueAt(rowKh, 0).toString()), Ma);
        dispose();
    }//GEN-LAST:event_btn_chonActionPerformed

    private void txt_timKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timKiemCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemCaretUpdate

    private void tb_khachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_khachHangMouseClicked
        int id = layid();

        int row = tb_khachHang.getSelectedRow();
        KhachHangViewMD kh = listKhachHang.get(row);
        txt_Ten1.setText(kh.getTen());
        txt_TenDem.setText(kh.getTendem());
        txt_Ho.setText(kh.getHo());
        String gt = (tb_khachHang.getValueAt(row, 2).toString());
        if (gt == "Nam") {
            rd_nam.setSelected(true);
        } else {
            rd_nu.setSelected(true);
        }
        date_ngaysinh.setDate((Date) tb_khachHang.getValueAt(row, 3));
        txt_sdt.setText(kh.getSdt());
        txt_email.setText(kh.getEmail());
    }//GEN-LAST:event_tb_khachHangMouseClicked

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemActionPerformed

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
       defaultTableModel = (DefaultTableModel) tb_khachHang.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHangViewMD khachHang01 : KH.GetTK(txt_timKiem.getText())) {
            defaultTableModel.addRow(khachHang01.toDataRow());
        }
    }//GEN-LAST:event_txt_timKiemKeyReleased

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(KhachHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(KhachHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(KhachHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(KhachHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new KhachHangForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton Btn_capNhat;
    private swing.MyButton btn_chon;
    private swing.MyButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser date_ngaysinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private javax.swing.JTable tb_khachHang;
    private swing.MyTextField txt_Ho;
    private swing.MyTextField txt_Ten1;
    private swing.MyTextField txt_TenDem;
    private swing.MyTextField txt_email;
    private swing.MyTextField txt_sdt;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
