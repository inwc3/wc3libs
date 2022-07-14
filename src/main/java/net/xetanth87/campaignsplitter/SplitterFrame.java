package net.xetanth87.campaignsplitter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SplitterFrame extends JFrame {
	public static final String AUTHOR = "Xetanth87";
	public static final String APP_TITLE = "Campaign Splitter";
	private JTextField filePathField = new JTextField(30);
	private JButton browse = new JButton("Browse");
	private JButton split = new JButton("Split");
	private JCheckBox difficultySelectorCheckbox = new JCheckBox("Add difficulty selector", true);
	private boolean running = false;
	private Thread splitterThread = null;

	public SplitterFrame() {
		super(AUTHOR + "'s " + APP_TITLE);
		setLayout(new FlowLayout());
		add(new Label("Click \"" + browse.getText() + "\" to select a Warcraft III Custom Campaign file (\".w3n\")."));
		add(new Label("Click \"" + split.getText() + "\" to split the campaign (the campaign file will not be altered)."));
		add(new Label("A folder with the same name as the campaign will be created in the same location."));
		add(new Label("This folder will contain all extracted maps, merged with campaign data."));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		browse.addActionListener(new BrowseL());
		split.addActionListener(new SplitL());
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p.add(filePathField);
		p.add(browse);
		browse.setFocusPainted(false);
		browse.setBackground(new Color(100, 200, 255));
		p.add(split);
		split.setFocusPainted(false);
		split.setBackground(Color.ORANGE);
		add(p);
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p.add(difficultySelectorCheckbox);
		add(p);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//URL iconURL = getClass().getResource("image/Icon.png");
		//ImageIcon icon = new ImageIcon(iconURL);
		ImageIcon icon = new ImageIcon(getClass().getResource("Icon.png"));
		setIconImage(icon.getImage());
		setSize(520, 225);
		setResizable(false);
		setVisible(true);
	}

	class BrowseL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			try {
				File directory = new File(filePathField.getText());
				if (!directory.isDirectory())
					directory = directory.getParentFile();
				if (directory != null)
					c.setCurrentDirectory(directory);
			} catch (Exception ex) {
			}
			c.setFileFilter(new FileNameExtensionFilter("Warcraft III Campaign (*.w3n)", "w3n"));
			c.setAcceptAllFileFilterUsed(false);
			int rVal = c.showOpenDialog(SplitterFrame.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filePathField.setText(c.getSelectedFile().getAbsolutePath());
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
			}
		}
	}

	public void setButtonStates(boolean running)
	{
		this.running = running;
		browse.setEnabled(!running);
		filePathField.setEditable(!running);
		difficultySelectorCheckbox.setEnabled(!running);
		if (running) {
			browse.setBackground(Color.GRAY);
			split.setText("Stop");
			split.setBackground(Color.RED);
		}
		else {
			browse.setBackground(new Color(100, 200, 255));
			split.setText("Split");
			split.setBackground(Color.ORANGE);
		}
	}

	class CampaignSplitterThread extends Thread {
		public void run() {
			String filename = new File(filePathField.getText()).getName();
			try {
				CampaignSplitter.splitCampaign(filePathField.getText(), difficultySelectorCheckbox.isSelected());
				JOptionPane.showMessageDialog(null, "Campaign \"" + filename + "\" has been split successfully!", APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
			} catch (InterruptedException ex) {
			} catch (Exception ex) {
				if (!isInterrupted()) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error has been encountered when splitting \"" + filename + "\":\n" + ex.getMessage(), APP_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
			finally {
				setButtonStates(false);
			}
		}
	}

	class SplitL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (running) {
				setButtonStates(false);
				if (splitterThread != null)
					splitterThread.interrupt();
				XT87Utils.deleteNewFiles();
				JOptionPane.showMessageDialog(null, "The splitting has stopped!", APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				setButtonStates(true);
				splitterThread = new CampaignSplitterThread();
				splitterThread.start();
			}
		}
	}

	public static void main(String[] args) {
		new SplitterFrame();
	}
}