package net.xetanth87.campaignsplitter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Enumeration;

public class SplitterFrame extends JFrame {
	public static final String AUTHOR = "Xetanth87";
	public static final String APP_TITLE = "Campaign Splitter";
	private JTextField filePathField = new JTextField(30);
	private JButton browse = new JButton("Browse");
	private JButton split = new JButton("Split");
	private boolean running = false;
	private Thread splitterThread = null;
	private JProgressBar bar;
	private JTextArea taskOutput;
	private XT87Utils.TriOption difficultySelectorOption = XT87Utils.TriOption.DEFAULT;
	private ButtonGroup difficultySelectorGroup;

	public SplitterFrame() {
		super(AUTHOR + "'s " + APP_TITLE);
		setSize(600, 500);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new Label("Click \"" + browse.getText() + "\" to select a Warcraft III Custom Campaign file (\"*.w3n\").", Label.CENTER));
		panel.add(new Label("Click \"" + split.getText() + "\" to split the campaign (the campaign file will not be altered).", Label.CENTER));
		panel.add(new Label("A folder with the same name as the campaign will be created in the same location.", Label.CENTER));
		panel.add(new Label("This folder will contain all extracted maps, merged with campaign data.", Label.CENTER));
		add(panel);
		panel = new JPanel();
		panel.add(new JLabel("Add Difficulty Selector?"));
		difficultySelectorGroup = new ButtonGroup();
		JRadioButton radioButton;
		for (XT87Utils.TriOption option : XT87Utils.TriOption.values()) {
			radioButton = new JRadioButton(XT87Utils.beautifyEnum(option));
			radioButton.setFocusPainted(false);
			radioButton.setActionCommand(option.toString());
			if (option == difficultySelectorOption)
				radioButton.setSelected(true);
			String tooltipText = "";
			switch (option) {
				case NO:
					tooltipText = "Don't add a Difficulty Selector at the start of each map.";
					break;
				case DEFAULT:
					tooltipText = "Add a Difficulty Selector at the start of each map, only if the campaign has the \"Variable Difficulty\" setting. (Recommended)";
					break;
				case YES:
					tooltipText = "Add a Difficulty Selector at the start of each map.";
					break;
			}
			radioButton.setToolTipText(tooltipText);
			radioButton.addActionListener(new DifficultySelectorOptionL(option));
			panel.add(radioButton);
			difficultySelectorGroup.add(radioButton);
		}
		add(panel);

		browse.addActionListener(new BrowseL());
		split.addActionListener(new SplitL());
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(filePathField);
		panel.add(browse);
		browse.setFocusPainted(false);
		browse.setBackground(new Color(100, 200, 255));
		panel.add(split);
		split.setFocusPainted(false);
		split.setBackground(Color.ORANGE);
		add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel = new JPanel();
		bar = new JProgressBar();
		bar.setValue(0);
		bar.setMaximum(100);
		bar.setStringPainted(true);
		bar.setPreferredSize(new Dimension((int) (0.9f * getWidth()), bar.getPreferredSize().height));

		panel.add(bar);
		add(panel);

		panel = new JPanel();
		taskOutput = new JTextArea(12, 50);
		taskOutput.setMargin(new Insets(5,5,5,5));
		taskOutput.setEditable(false);
		panel.add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		add(panel);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(getClass().getResource("Icon.png"));
		setIconImage(icon.getImage());
		setVisible(true);
	}

	class DifficultySelectorOptionL implements ActionListener {
		private XT87Utils.TriOption option;

		public DifficultySelectorOptionL(XT87Utils.TriOption option) {
			this.option = option;
		}

		public void actionPerformed(ActionEvent e) {
			difficultySelectorOption = option;
		}
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
		Enumeration<AbstractButton> e = difficultySelectorGroup.getElements();
		while (e.hasMoreElements()) {
			e.nextElement().setEnabled(!running);
		}
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

	private static String formatDuration(Duration duration) {
		long s = duration.getSeconds();
		return String.format("%d:%02d:%02d", s/3600, (s%3600)/60, (s%60));
	}

	private class FrameCampaignSplitter extends CampaignSplitter {
		public FrameCampaignSplitter(File campFile, XT87Utils.TriOption difficultySelectorOption) throws IOException {
			super(campFile, difficultySelectorOption);
		}

		@Override
		public void InitializeProgressBar(int i) {
			super.InitializeProgressBar(i);
			bar.setMaximum(i);
			bar.setValue(0);
		}

		@Override
		public void SetValueProgressBar(int i) {
			super.SetValueProgressBar(i);
			bar.setValue(i);
		}

		@Override
		public void IncrementValueProgressBar(int i) {
			super.IncrementValueProgressBar(i);
			if (initializedProgressBar)
				bar.setValue(bar.getValue() + i);
		}

		@Override
		public void CompleteProgressBar() {
			super.CompleteProgressBar();
			bar.setValue(bar.getMaximum());
		}
	}

	private static class AreaPrintStream extends PrintStream {
		private SplitterFrame frame;
		private String prefix;

		public AreaPrintStream(OutputStream out, SplitterFrame frame, String prefix) {
			super(out, true);
			this.frame = frame;
			this.prefix = prefix;
		}

		@Override
		public void println(String s)
		{
			super.println(s);
			frame.taskOutput.append(prefix + s + "\n");
		}
	}

	class CampaignSplitterThread extends Thread {
		public final Instant startTime;

		CampaignSplitterThread() {
			this.startTime = Instant.now();
		}

		public void run() {
			File file = new File(filePathField.getText());
			try {
				CampaignSplitter cs = new FrameCampaignSplitter(file, difficultySelectorOption);
				cs.splitCampaign();
				String timeSpan = formatDuration(Duration.between(startTime, Instant.now()));
				JOptionPane.showMessageDialog(null, "Campaign \"" + file.getName() + "\" has been split successfully! (" + timeSpan + ")", APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
			} catch (InterruptedException ex) {
			} catch (Exception ex) {
				try {
					new CampaignSplitter(file, XT87Utils.TriOption.NO).removeTemporaryFiles();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!isInterrupted()) {
					ex.printStackTrace();
					String timeSpan = formatDuration(Duration.between(startTime, Instant.now()));
					JOptionPane.showMessageDialog(null, "An error has been encountered when splitting \"" + file.getName() + "\"(" + timeSpan + "):\n" + ex.getMessage(), APP_TITLE, JOptionPane.ERROR_MESSAGE);
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
				taskOutput.setText("");
				setButtonStates(true);
				splitterThread = new CampaignSplitterThread();
				splitterThread.start();
			}
		}
	}

	public static void main(String[] args) {
		SplitterFrame frame = new SplitterFrame();
		PrintStream origOut = System.out;
		PrintStream areaOut = new AreaPrintStream(origOut, frame, "");
		System.setOut(areaOut);
		PrintStream origErr = System.err;
		PrintStream areaErr = new AreaPrintStream(origErr, frame, "ERR: ");
		System.setErr(areaErr);
	}
}