package sv.edu.udb.vistas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.dao.RevistaDAO;
import sv.edu.udb.entidades.Revista;
import sv.edu.udb.util.Log4JUtil;

public class RevistaForm extends javax.swing.JFrame {
    private Logger log = Log4JUtil.getLogger(RevistaForm.class);
    private Revista revista;
    private FrmGestionRevistas listarRevistas;

    public RevistaForm(FrmGestionRevistas listarRevistas) {
        initComponents();
        this.listarRevistas = listarRevistas;
    }

    public boolean validarDatos() {
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Título no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            txtTitulo.requestFocus();
            return false;
        }

        if (txtEditorial.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Editorial no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            txtEditorial.requestFocus();
            return false;
        }

        if (txtPeriocidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Periodicidad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            txtPeriocidad.requestFocus();
            return false;
        }

        if (txtFechaPublicidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Fecha de Publicación no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            txtFechaPublicidad.requestFocus();
            return false;
        }

        if ((int) txtStock.getValue() <= 0) {
            JOptionPane.showMessageDialog(this, "El campo Stock no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            txtStock.requestFocus();
            return false;
        }

        return true;
    }

    public void llenarDatosFormulario(Revista revista) {
        this.revista = revista;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        txtTitulo.setText(revista.getTitulo());
        txtEditorial.setText(revista.getEditorial());
        txtPeriocidad.setText(revista.getPeriodicidad());
        txtFechaPublicidad.setText(formatoFecha.format(revista.getFechaPublicacion()));
        txtStock.setValue(revista.getStock());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtEditorial = new javax.swing.JTextField();
        txtPeriocidad = new javax.swing.JTextField();
        txtFechaPublicidad = new javax.swing.JTextField();
        txtStock = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHeader.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblHeader.setText("REGISTRAR REVISTA");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Titulo:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Editorial:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Periocidad:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fecha de publicación:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Stock:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar ");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addComponent(txtEditorial)
                        .addComponent(txtPeriocidad)
                        .addComponent(txtFechaPublicidad))
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPeriocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaPublicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        
        if (!validarDatos()) {
            return;
        }

        if (btnGuardar.getText().equals("Guardar")) {

            Revista revista = new Revista();

            revista.setTitulo(this.txtTitulo.getText());
            revista.setEditorial(this.txtEditorial.getText());
            revista.setPeriodicidad(this.txtPeriocidad.getText());
            Date fechaPublicacion;
            try {
                fechaPublicacion = formatoFecha.parse(this.txtFechaPublicidad.getText());
                revista.setFechaPublicacion(fechaPublicacion);
            } catch (ParseException ex) {
                log.error("Ocurrió un error al formatear la fecha: ", ex);
            }
            
            revista.setStock((int) this.txtStock.getValue());

            RevistaDAO revistaDAO = new RevistaDAO();

            try {
                int result = revistaDAO.crear(revista);

                if (result > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Los datos se han ingresado correctamente!!",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.listarRevistas.setListaRevistasActual(new ArrayList<>());
                    this.listarRevistas.cargarRevistas();

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ocurrió un error inesperado al guardar los datos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                log.error("Ocurrió el siguiente error: ", ex);
            }
        } else if (btnGuardar.getText().equals("Modificar")) {
            revista.setTitulo(this.txtTitulo.getText());
            revista.setEditorial(this.txtEditorial.getText());
            revista.setPeriodicidad(this.txtPeriocidad.getText());
            
            Date fechaPublicacion;
            try {
                fechaPublicacion = formatoFecha.parse(this.txtFechaPublicidad.getText());
                revista.setFechaPublicacion(fechaPublicacion);
            } catch (ParseException ex) {
                log.error("Ocurrió un error al formatear la fecha: ", ex);
            }
            revista.setStock((int) this.txtStock.getValue());

            RevistaDAO revistaDAO = new RevistaDAO();

            try {
                int result = revistaDAO.modificar(revista);

                if (result > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Los datos se han modificado correctamente!!",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    this.listarRevistas.setListaRevistasActual(new ArrayList<>());
                    this.listarRevistas.cargarRevistas();

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ocurrió un error inesperado al modificar los datos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                log.error("Ocurrió el siguiente error: ", ex.getMessage());
                JOptionPane.showMessageDialog(this, "Ocurrió un error al modificar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(RevistaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RevistaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RevistaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RevistaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RevistaForm(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel lblHeader;
    public javax.swing.JTextField txtEditorial;
    public javax.swing.JTextField txtFechaPublicidad;
    public javax.swing.JTextField txtPeriocidad;
    public javax.swing.JSpinner txtStock;
    public javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
