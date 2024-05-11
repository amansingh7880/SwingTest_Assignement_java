package com.files;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShowData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	   private static DefaultTableModel model;
	

	/**
	 * Create the frame.
	 * @param akeys 
	 * @param allData 
	 */
	public ShowData(String[][] allData, Vector<String> akeys) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setSize(new Dimension(0, 100));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBounds(250, 10, 120, 35);
		panel.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		
		//contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		Object[] keys=akeys.toArray();
		
		System.out.println("length : "+keys.length);
		
		for(int i=0;i<keys.length;i++) System.out.println(keys[i]);
		
		System.out.println("data column length : "+allData[0].length);
		//table = new JTable(allData,akeys.toArray());
		model= new DefaultTableModel(allData,akeys.toArray());
		table=new JTable(model);
		
		table.setAutoCreateRowSorter(true);
		removeEmptyRows(table);
		JScrollPane jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jsp);
	}
	 private static void removeEmptyRows(JTable table) {
	        int rowCount = table.getRowCount();
	        for (int i = rowCount - 1; i >= 0; i--) {
	            boolean isEmpty = true;
	            for (int j = 0; j < table.getColumnCount(); j++) {
	                Object value = table.getValueAt(i, j);
	                if (value != null && !value.toString().isEmpty()) {
	                    isEmpty = false;
	                    break;
	                }
	            }
	            if (isEmpty) {
	                model.removeRow(i);
	            }
	        }
	    }
}
