package sv.edu.udb.vistas;

import javax.swing.*;
import java.awt.*;

public class LibroForm extends JFrame {

    public LibroForm() {

        setTitle("Registro de Libro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel lblCodigoID = new JLabel("Código ID:");
        JTextField txtCodigoID = new JTextField(15);

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(15);

        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField(15);

        JLabel lblPaginas = new JLabel("Páginas:");
        JTextField txtPaginas = new JTextField(15);

        JLabel lblEditorial = new JLabel("Editorial:");
        JTextField txtEditorial = new JTextField(15);

        JLabel lblISBN = new JLabel("ISBN:");
        JTextField txtISBN = new JTextField(15);

        JLabel lblAnioPublicacion = new JLabel("Año de Publicación:");
        JTextField txtAnioPublicacion = new JTextField(15);

        JLabel lblStock = new JLabel("Stock:");
        JTextField txtStock = new JTextField(15);

        JButton btnGuardar = new JButton("Guardar");



        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblCodigoID, gbc);
        gbc.gridx = 1;
        add(txtCodigoID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblTitulo, gbc);
        gbc.gridx = 1;
        add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblAutor, gbc);
        gbc.gridx = 1;
        add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPaginas, gbc);
        gbc.gridx = 1;
        add(txtPaginas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblEditorial, gbc);
        gbc.gridx = 1;
        add(txtEditorial, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblISBN, gbc);
        gbc.gridx = 1;
        add(txtISBN, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblAnioPublicacion, gbc);
        gbc.gridx = 1;
        add(txtAnioPublicacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblStock, gbc);
        gbc.gridx = 1;
        add(txtStock, gbc);


        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weighty = 0;
        add(new JLabel(), gbc);


        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        add(btnGuardar, gbc);


        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new LibroForm());
    }
}


