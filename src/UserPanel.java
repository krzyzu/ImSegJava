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
    private int comboID;
    private JTextField kNeighTxtField;
    private JTextField thresholdTxtField;
    private JTextField colorTxtField;

    public UserPanel() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileChooserFilter = new FileNameExtensionFilter("DICOM", "dcr", "RA64","DCM","DCM30", "JPG", "JPEG");
        fileChooser.setFileFilter(fileChooserFilter);
        chooseFileButton = new JButton("Wybierz plik");
        chooseFileButton.addActionListener(v -> chooseFileButtonAction());
        segmentImageButton = new JButton("Dokonaj segmentacji");
        segmentImageButton.addActionListener(v -> segmentImageButtonAction());
        segTypeComboBox = new JComboBox();
//        segTypeComboBox.addActionListener(v -> segTypeComboAction());
        comboID = 0;
        kNeighTxtField = new JTextField(8);
        kNeighTxtField.setText("3");
        thresholdTxtField = new JTextField(8);
        colorTxtField = new JTextField(8);
        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);

        DefaultComboBoxModel segTypeComboModel = new DefaultComboBoxModel();
        segTypeComboModel.addElement("Rozrost regionow");
        segTypeComboModel.addElement("k-means");
        segTypeComboModel.addElement("Wododzialowy");
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
        add(new JLabel("Rodzaj segmentacji"), gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.insets = new Insets(0,0,0,10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(segTypeComboBox, gbc);

        gbc.weightx = 0.5;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ilosc clusterow"), gbc);

        gbc.weightx = 0.5;
        gbc.weighty = 0.1;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(kNeighTxtField, gbc);
/*
        if (comboID == 0) {
            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(new JLabel("Threshold"), gbc);

            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(thresholdTxtField, gbc);
        } else if (comboID == 1) {
            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(new JLabel("Ilosc k-sasiadow"), gbc);

            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(kNeighTxtField, gbc);
        } else if (comboID == 2) {
            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 1;
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(new JLabel("Color"), gbc);

            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.gridx = 0;
            gbc.insets = new Insets(0, 0, 0, 10);
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.LINE_END;
            add(colorTxtField, gbc);
        }*/
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
        if (id == 0) {
            String segParam = kNeighTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, segParam);
        }  else if (id == 1) {
            String segParam = kNeighTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, segParam);
        } else if (id == 2) {
            String segParam = kNeighTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, segParam);
        }
        if (formListener != null) {
            formListener.segmentEventOccured(segmentEvent);
        }
    }
    public void setUserListener(FormListener fl) {
        this.formListener = fl;
    }


/*
    private void segTypeComboAction() {
        this.comboID = segTypeComboBox.getSelectedIndex();
        layoutComponents();
    }
*/

}
