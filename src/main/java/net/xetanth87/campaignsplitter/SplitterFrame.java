package net.xetanth87.campaignsplitter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SplitterFrame extends JFrame {
	public static final String AUTHOR = "Xetanth87";
	public static final String APP_TITLE = "Campaign Splitter";
	private JTextField filename = new JTextField();
	private JButton browse = new JButton("Browse");
	private JButton split = new JButton("Split");

	public SplitterFrame() {
		super(AUTHOR +"'s " + APP_TITLE);
		setLayout(new FlowLayout());
//		add(new Label("Select a Warcraft III Custom Campaign file (\".w3n\")."));
//		add(new Label("A folder with the same name as the file will be created in the same location."));
//		add(new Label("This folder will contain all extracted maps, merged with campaign data."));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		browse.addActionListener(new BrowseL());
		split.addActionListener(new SplitL());
		p.add(browse);
		p.add(split);
		Container cp = getContentPane();
		cp.add(p, BorderLayout.SOUTH);
		filename.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(filename);
		cp.add(p, BorderLayout.NORTH);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(SplitterFrame.class.getResource("").getPath() + "Icon.png");
		URL iconURL = SplitterFrame.class.getResource("Icon.png");
		System.out.println(iconURL.getPath());
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconURL));
		setSize(360, 100);
		setVisible(true);
	}

	class BrowseL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			c.setFileFilter(new FileNameExtensionFilter("Warcraft III Campaign", "w3n"));
			int rVal = c.showOpenDialog(SplitterFrame.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename.setText(c.getSelectedFile().getAbsolutePath());
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
			}
		}
	}

	class SplitL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				CampaignSplitter.splitCampaign(filename.getText());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SplitterFrame();
	}
}