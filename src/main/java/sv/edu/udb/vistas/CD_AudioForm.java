package sv.edu.udb.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CD_AudioForm extends JFrame {

    public CD_AudioForm() {

        setTitle("Registro para CD Audio");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(20);

        JLabel lblArtista = new JLabel("Artista:");
        JTextField txtArtista = new JTextField(20);

        JLabel lblGenero = new JLabel("Género:");
        JTextField txtGenero = new JTextField(20);

        JLabel lblDuracion = new JLabel("Duración:");
        JTextField txtDuracion = new JTextField(20);

        JLabel lblCanciones = new JLabel("Canciones:");
        JTextField txtCanciones = new JTextField(20);

        JLabel lblStock = new JLabel("Stock:");
        JTextField txtStock = new JTextField(20);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnSalir = new JButton("Salir");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitulo, gbc);
        gbc.gridx = 1;
        add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblArtista, gbc);
        gbc.gridx = 1;
        add(txtArtista, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblGenero, gbc);
        gbc.gridx = 1;
        add(txtGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblDuracion, gbc);
        gbc.gridx = 1;
        add(txtDuracion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblCanciones, gbc);
        gbc.gridx = 1;
        add(txtCanciones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblStock, gbc);
        gbc.gridx = 1;
        add(txtStock, gbc);

        // Espacio en blanco
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weighty = 0;
        add(new JLabel(), gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnSalir);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        add(panelBotones, gbc);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CD_AudioForm());
    }
}

