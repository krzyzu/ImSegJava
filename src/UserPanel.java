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

    public UserPanel() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileChooserFilter = new FileNameExtensionFilter("DICOM", "dcr", "RA64","DCM","DCM30", "JPG", "JPEG");
        fileChooser.setFileFilter(fileChooserFilter);
        chooseFileButton = new JButton("Wybierz obraz");
        chooseFileButton.addActionListener(v -> chooseFileButtonAction());
        segmentImageButton = new JButton("Dokonaj segmentacji");
        segmentImageButton.addActionListener(v -> segmentImageButtonAction());
        segTypeComboBox = new JComboBox();
//        segTypeComboBox.addActionListener(v -> segTypeComboAction());
        comboID = 0;
        kNeighTxtField = new JTextField(5);
        kNeighTxtField.setMinimumSize(new Dimension(5,10));
        kNeighTxtField.setMaximumSize(new Dimension(5,10));
        kNeighTxtField.setText("3");
        thresholdTxtField = new JTextField(5);
        thresholdTxtField.setMinimumSize(new Dimension(5,10));
        thresholdTxtField.setMaximumSize(new Dimension(5,10));
        thresholdTxtField.setText("100");
        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);

        DefaultComboBoxModel segTypeComboModel = new DefaultComboBoxModel();
        segTypeComboModel.addElement("Segmentacja na kolor");
        segTypeComboModel.addElement("Segmentacja k-means");
        segTypeComboModel.addElement("Progowanie met. Otsu");
        segTypeComboBox.setModel(segTypeComboModel);
        Border innerBorder = BorderFactory.createTitledBorder("Panel Uzytkownika");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.weightx = 1;
        //gbc.weighty = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(0,0,0,20);
        gbc.anchor = GridBagConstraints.CENTER;
        add(chooseFileButton, gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;
        gbc.insets = new Insets(20,0,20,0);
        gbc.gridx = 0;
        gbc.gridy ++;
        //gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Rodzaj segmentacji"), gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;
        gbc.insets = new Insets(5,0,20,0);
        gbc.gridx = 0;
        gbc.gridy ++;
        //gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(segTypeComboBox, gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;

        gbc.insets = new Insets(10,0,5,0);
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.gridwidth = 1;
        //gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Ilosc klas(k-means)"), gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Prog(kolor)"), gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;

        gbc.insets = new Insets(5,0,5,0);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        //gbc.insets = new Insets(0, 0, 0, 10);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(kNeighTxtField, gbc);


        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        //gbc.insets = new Insets(0, 0, 0, 10);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(thresholdTxtField, gbc);

        //gbc.weightx = 0.2;
        //gbc.weighty = 0.2;

        gbc.insets = new Insets(20,0,20,0);
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        //gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
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
            String segParam = thresholdTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, segParam);
        }  else if (id == 1) {
            String segParam = kNeighTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, segParam);
        } else if (id == 2) {
            String segParam = kNeighTxtField.getText();
            segmentEvent = new SegmentEvent(this, id, "");
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
