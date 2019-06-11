import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton helloBtn;
    private JButton byeBtn;
    private StringListener textListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        helloBtn = new JButton("Hello");
        byeBtn = new JButton("Goodbye");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloBtn);
        add(byeBtn);

        helloBtn.addActionListener(this);
        byeBtn.addActionListener(this);
    }

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if (clicked == helloBtn) {
            if (textListener != null) {
                textListener.textEmitted("Hello\n");
            }
        }
        if (clicked == byeBtn) {
            if (textListener != null) {
                textListener.textEmitted("Goodbye\n");
            }
        }
    }
}

