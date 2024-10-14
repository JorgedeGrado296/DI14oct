package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AñadirUsuarioForm extends JFrame {
    private JTextField txtCorreo;
    private JComboBox<String> cmbPais;
    private JComboBox<String> cmbPlataforma;
    private JLabel lblInfo;
    private JTable Correo;
    private DefaultTableModel tableModel;
    private JPanel panel1;
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel CE;
    private JLabel CO;
    private JLabel Plat;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPasswordField passwordField1;

    public AñadirUsuarioForm() {
        setTitle("Añadir Usuario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2));

        panelForm.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);

        panelForm.add(new JLabel("País:"));
        cmbPais = new JComboBox<>(new String[]{"España", "Francia", "Italia", "Alemania", "Portugal"});
        panelForm.add(cmbPais);

        panelForm.add(new JLabel("Plataforma:"));
        cmbPlataforma = new JComboBox<>(new String[]{"Escritorio", "Móvil", "Desconocido"});
        panelForm.add(cmbPlataforma);

        // Botones
        JButton btnGuardar = new JButton("Confirmar y Guardar");
        JButton btnVolver = new JButton("Volver Atrás");
        panelForm.add(btnGuardar);
        panelForm.add(btnVolver);

        add(panelForm, BorderLayout.NORTH);

        // Panel para la tabla
        String[] columnNames = {"Correo", "País", "Plataforma"};
        tableModel = new DefaultTableModel(columnNames, 0);
        Correo = new JTable(tableModel);
        add(new JScrollPane(Correo), BorderLayout.CENTER);

        // Label de información
        lblInfo = new JLabel(" ");
        add(lblInfo, BorderLayout.SOUTH);

        // Acción para el botón guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        // Acción para el botón volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
    }

    private void guardarUsuario() {
        String correo = txtCorreo.getText();
        String pais = (String) cmbPais.getSelectedItem();
        String plataforma = (String) cmbPlataforma.getSelectedItem();

        if (correo.isEmpty() || pais == null || plataforma == null) {
            lblInfo.setText("Faltan campos por completar.");
        } else {
            Usuario nuevoUsuario = new Usuario(correo, pais, plataforma);
            tableModel.addRow(new Object[]{nuevoUsuario.getCorreo(), nuevoUsuario.getPais(), nuevoUsuario.getPlataforma()});

            JOptionPane.showMessageDialog(this, "Usuario almacenado correctamente: " + nuevoUsuario.getCorreo());
            limpiarFormulario();
        }
    }

    private void limpiarFormulario() {
        txtCorreo.setText("");
        cmbPais.setSelectedIndex(0);
        cmbPlataforma.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        AñadirUsuarioForm form = new AñadirUsuarioForm();
        form.setVisible(true);
    }
}
