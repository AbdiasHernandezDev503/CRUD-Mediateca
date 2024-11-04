/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sv.edu.udb.vistas;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.dao.CdAudioDAO;
import sv.edu.udb.entidades.CdAudio;
import sv.edu.udb.util.Log4JUtil;

/**
 *
 * @author HP
 */
public class FrmGestionCdAudio extends javax.swing.JFrame {

    private Logger log = Log4JUtil.getLogger(FrmGestionCdAudio.class);
    private CdAudioDAO CdAudioDAO = new CdAudioDAO();
    private List<CdAudio> listaCdAudioActual = new ArrayList<>();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public FrmGestionCdAudio() {
        initComponents();
        bntEliminar.setEnabled(tbCdAudios.getSelectedRow() != -1);
        btnModificar.setEnabled(tbCdAudios.getSelectedRow() != -1);
        setResizable(false);
        cargarCdAudios();
    }

    public void cargarCdAudios() {
        String[] columnas = {"Código", "Título", "Artista", "Género", "Duración", "Canciones", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbCdAudios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean seleccionoFila = tbCdAudios.getSelectedRow() != -1;
                btnModificar.setEnabled(seleccionoFila);
                bntEliminar.setEnabled(seleccionoFila);
            }
        });

        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar datos nuevos

        try {
            if (listaCdAudioActual == null || listaCdAudioActual.isEmpty()) {
                listaCdAudioActual = CdAudioDAO.listar(); // Carga completa desde la base de datos
            }

            for (CdAudio cdAudio : listaCdAudioActual) { // Utiliza listaCdAudioActual como esté (con búsqueda o completo)
                Object[] fila = {
                    cdAudio.getCodigoId(),
                    cdAudio.getTitulo(),
                    cdAudio.getArtista(),
                    cdAudio.getGenero(),
                    cdAudio.getDuracion(),
                    cdAudio.getCanciones(),
                    cdAudio.getStock()
                };
                modeloTabla.addRow(fila);
            }

            tbCdAudios.setModel(modeloTabla); // Actualiza la tabla con el modelo

        } catch (Exception ex) {
            log.error("Error inesperado al listar los CdAudios: ", ex);
        }
    }
    
    public void limpiarLista() {
        listaCdAudioActual.clear();
    }

    public void buscarCdAudio() {
        try {
            CdAudio cdAudioSearch = new CdAudio();
            cdAudioSearch.setTitulo(this.txtTitulo.getText());
            cdAudioSearch.setArtista(this.txtArtista.getText());
            cdAudioSearch.setGenero(this.txtGenero.getText());

            listaCdAudioActual = CdAudioDAO.buscar(cdAudioSearch);

            cargarCdAudios();
        } catch (SQLException ex) {
            log.error("Ocurrió un error al buscar los CdAudios.", ex);
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCdAudios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtArtista = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiarFiltros = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bntNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        bntEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Listado de Cd Audio Registrados");

        tbCdAudios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Audio", "Codigo", "Artista", "Genero", "Duracion", "Canciones", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCdAudios);

        jLabel3.setText("Titulo:");

        jLabel4.setText("Artista:");

        jLabel5.setText("Genero:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiarFiltros.setText("Limpiar filtros");
        btnLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtArtista, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtTitulo)
                            .addComponent(txtGenero)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiarFiltros))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Busqueda de Cd Audio");

        bntNuevo.setText("Nuevo");
        bntNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setActionCommand("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        bntEliminar.setText("Eliminar");
        bntEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(632, 632, 632))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(bntNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(bntEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntNuevo)
                    .addComponent(btnModificar)
                    .addComponent(bntEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tbCdAudios.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Crear un objeto Libro con los datos de la fila seleccionada
            CdAudio cdAudioSeleccionado = new CdAudio();
            cdAudioSeleccionado.setCodigoId((String) modeloTabla.getValueAt(filaSeleccionada, 0));
            cdAudioSeleccionado.setTitulo((String) modeloTabla.getValueAt(filaSeleccionada, 1));
            cdAudioSeleccionado.setArtista((String) modeloTabla.getValueAt(filaSeleccionada, 2));
            cdAudioSeleccionado.setGenero((String) modeloTabla.getValueAt(filaSeleccionada, 3));
            cdAudioSeleccionado.setDuracion((LocalTime) modeloTabla.getValueAt(filaSeleccionada, 4));
            cdAudioSeleccionado.setCanciones(Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 5).toString()));
            cdAudioSeleccionado.setStock(Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 6).toString()));

            CdAudioForm cdAudioForm = new CdAudioForm(this);

            cdAudioForm.setVisible(true);
            cdAudioForm.setTitle("Modificar CdAudio");
            cdAudioForm.lblHeader.setText("Modificar CdAudio Seleccionado: " + cdAudioSeleccionado.getCodigoId());
            cdAudioForm.btnGuardar.setText("Modificar");
            cdAudioForm.llenarDatosFormulario(cdAudioSeleccionado);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int filaSeleccionada = tbCdAudios.getSelectedRow();

        if (filaSeleccionada != -1) {
            String codigoId = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

            // Mostrar un cuadro de diálogo de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este CdAudio?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    int result = CdAudioDAO.eliminar(codigoId);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "El CdAudio ha sido eliminado exitosamente.");
                        modeloTabla.removeRow(filaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el CdAudio. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    log.error("Ocurrio un error al eliminar el registro: ", ex.getMessage());
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar el CdAudio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un CdAudio para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void bntNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNuevoActionPerformed
        CdAudioForm cdAudioForm = new CdAudioForm(this);

        cdAudioForm.setVisible(true);
    }//GEN-LAST:event_bntNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscarCdAudio();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltrosActionPerformed
        txtArtista.setText("");
        txtTitulo.setText("");
        txtGenero.setText("");

        listaCdAudioActual = new ArrayList<>();
        cargarCdAudios();
    }//GEN-LAST:event_btnLimpiarFiltrosActionPerformed

    private void bntEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarActionPerformed
        int filaSeleccionada = tbCdAudios.getSelectedRow();

        if (filaSeleccionada != -1) {
            String codigoId = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este CD Audio?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    int result = CdAudioDAO.eliminar(codigoId);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "El CD Audio ha sido eliminado exitosamente.");
                        modeloTabla.removeRow(filaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el CD Audio. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    log.error("Ocurrio un error al eliminar el registro: ", ex.getMessage());
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un registro para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bntEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGestionCdAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGestionCdAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGestionCdAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGestionCdAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGestionCdAudio().setVisible(true);
            }
        });
    }

    public List<CdAudio> getListaCdAudiosActual() {
        return listaCdAudioActual;
    }

    public void setListaCdAudioActual(List<CdAudio> listaCdAudiosActual) {
        this.listaCdAudioActual = listaCdAudioActual;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEliminar;
    private javax.swing.JButton bntNuevo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCdAudios;
    private javax.swing.JTextField txtArtista;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
