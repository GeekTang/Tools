package igeektang.com.log.view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class LogViewWindow {

	private static int currentPage = 1;

	private JFrame frame;
	private JTable table;
	private JLabel pageLable;

	private final static int PAGE_SIZE = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogViewWindow window = new LogViewWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogViewWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table = getTable(currentPage, PAGE_SIZE);

		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		final JButton next = new JButton("next");
		final JButton prev = new JButton("prev");

	    pageLable = new JLabel("Page: " + currentPage);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle rect = scrollPane.getVisibleRect();
				if (e.getSource() == next) {
					currentPage++;

				} else if (e.getSource() == prev) {
					currentPage--;
					if (currentPage < 1) {
						currentPage = 1;
					}
				}
				table = getTable(currentPage, PAGE_SIZE);
				pageLable.setText("Page: " + currentPage);
				scrollPane.setViewportView(table);
				scrollPane.scrollRectToVisible(rect);
			}
		};

		next.addActionListener(al);
		prev.addActionListener(al);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(prev);
		buttonPanel.add(next);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		frame.getContentPane().add(pageLable, BorderLayout.SOUTH);
	}

	private JTable getTable(int currentPage, int pageSize) {
		String[] colNames = new String[] { "col 0", "col 1", "col 2", "col 3" };

		String[][] data = new String[PAGE_SIZE][4];
		for (int i = 0; i < PAGE_SIZE; i++) {
			for (int j = 0; j < 4; j++) {
				data[i][j] = "r:" + (i + pageSize * (currentPage - 1)) + " c:"
						+ j;
			}
		}

		return new JTable(data, colNames);

	}

}
