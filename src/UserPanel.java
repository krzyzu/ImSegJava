import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class UserPanel extends JPanel {
    private JFileChooser fileChooser;
    private JButton chooseFileButton;

    public UserPanel() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileChooserFilter = new FileNameExtensionFilter("DICOM", "dcr", "RA64","DCM","DCM30");
        fileChooser.setFileFilter(fileChooserFilter);
        chooseFileButton = new JButton("Wybierz plik");
        chooseFileButton.addActionListener(v -> chooseFileButtonAction());
        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Panel Uzytkownika");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(chooseFileButton, gbc);
    }
    private void chooseFileButtonAction() {
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String dicomPath = fileChooser.getSelectedFile().getPath();
        }
    }

}
