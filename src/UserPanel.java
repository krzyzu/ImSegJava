import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UserPanel extends JPanel {
    private JFileChooser fileChooser;
    private JButton chooseFileButton;
    private JButton segmentImageButton;
    private FormListener formListener;
    private FormEvent formEvent;
    private SegmentEvent segmentEvent;
    private JComboBox segTypeComboBox;
    private SegmentableImage segmentableImage;

    public UserPanel() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileChooserFilter = new FileNameExtensionFilter("DICOM", "dcr", "RA64","DCM","DCM30", "JPG", "JPEG");
        fileChooser.setFileFilter(fileChooserFilter);
        chooseFileButton = new JButton("Wybierz plik");
        chooseFileButton.addActionListener(v -> chooseFileButtonAction());
        segmentImageButton = new JButton("Dokonaj segmentacji");
        segmentImageButton.addActionListener(v -> segmentImageButtonAction());
        segTypeComboBox = new JComboBox();
        segmentableImage = null;
        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);

        DefaultComboBoxModel segTypeComboModel = new DefaultComboBoxModel();
        segTypeComboModel.addElement("Segmentacja k-means");
        segTypeComboModel.addElement("Segmentacja na kolor");
        segTypeComboModel.addElement("Progowanie");
        segTypeComboBox.setModel(segTypeComboModel);
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

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.insets = new Insets(0,0,0,10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(segTypeComboBox, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.insets = new Insets(0,0,0,10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(segmentImageButton, gbc);
    }
    private void chooseFileButtonAction() {
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String dicomPath = fileChooser.getSelectedFile().getPath();
            formEvent = new FormEvent(this, dicomPath);
            if (formListener != null) {
                formListener.formEventOccured(formEvent);
            }
        }
    }

    private void segmentImageButtonAction() {
        int id = segTypeComboBox.getSelectedIndex();
        segmentEvent = new SegmentEvent(this, id);
        if (formListener != null) {
            formListener.segmentEventOccured(segmentEvent);
        }
    }
    public void setUserListener(FormListener fl) {
        this.formListener = fl;
    }
}
