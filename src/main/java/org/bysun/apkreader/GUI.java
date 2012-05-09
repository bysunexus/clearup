package org.bysun.apkreader;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JTextField;
public class GUI {

	private JFrame frame;
	private JFileChooser chooser;

	private JButton chooserBtn;
	private JButton processBtn;
	private JButton closeBtn;
	private JTextField filePathField;
	
	private File chooserPath;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		JFrame.setDefaultLookAndFeelDecorated(true);  
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		
		MyListener btnListener = new MyListener();
		Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
		
		chooserBtn = new JButton("...");
		chooserBtn.setToolTipText("ѡ����apk�ļ���·��");
		chooserBtn.setBorder(btnBorder);
		chooserBtn.setBounds(202, 10, 30, 20);		
		chooserBtn.addActionListener(btnListener);
		frame.getContentPane().add(chooserBtn);
		
		
		processBtn = new JButton("start");
		processBtn.setToolTipText("��ʼ����,ע�⽫��ɾ���ϰ汾��apk,ֻ�������°汾.");
		processBtn.setBorder(btnBorder);
		processBtn.setBounds(48, 42, 60, 20);
		processBtn.addActionListener(btnListener);
		processBtn.setEnabled(false);
		frame.getContentPane().add(processBtn);
		
		closeBtn = new JButton("close");
		closeBtn.setToolTipText("�رձ�����.");
		closeBtn.setBorder(btnBorder);
		closeBtn.setBounds(129, 42, 60, 20);
		closeBtn.addActionListener(btnListener);
		frame.getContentPane().add(closeBtn);
		
		filePathField = new JTextField();
		filePathField.setBounds(10, 9, 190, 23);
		filePathField.setColumns(1000);
		filePathField.setBorder(btnBorder);
		filePathField.setEditable(false);
		frame.getContentPane().add(filePathField);
		
		
		chooser = new JFileChooser();
		// ����ѡ���ļ���
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	private class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// ѡ���ļ���ť�¼�
			if(e.getSource().equals(chooserBtn)){
				int state = chooser.showOpenDialog(null);
				if(state == JFileChooser.CANCEL_OPTION){
					return;
				}else{
					chooserPath = chooser.getSelectedFile();
					filePathField.setText(chooserPath.getAbsolutePath());
					processBtn.setEnabled(true);
				}
			}
			
			if(e.getSource().equals(processBtn)){
				try {
					Main.process(chooserPath.getAbsolutePath());
				} catch (IOException e1) {
					
				}
			}
			
			if(e.getSource().equals(closeBtn)){
				frame.dispose();
				System.exit(0);
			}
		}
	}
}
