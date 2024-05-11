package com.files;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Demo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable data;
	private JTextField SDate;
	private JButton btnNewButton;
	private JLabel lblEnterTheEnd;
	private JTextField EDate;
	String allData[][];
	String headings[];
	int row,column;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Demo() 
	{
		headings=new String[] {
				"Userid", "Tickets", "Price", "Valume", "Status", "Type", "Symbol", "CreatedAt", "UpdateAt", "New column", "New column"
			};
		
		row=column=0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500,200,750,550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 746, 513);
		contentPane.add(panel);
		panel.setLayout(null);
		
//		data = new JTable();
//		data.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
//		data.setBounds(10, 10, 726, 177);
//		panel.add(data);
//		
		JLabel lblNewLabel = new JLabel("Enter The Start Date");
		lblNewLabel.setBounds(40, 259, 106, 29);
		panel.add(lblNewLabel);
		
		SDate = new JTextField();
		SDate.setBounds(151, 259, 197, 29);
		panel.add(SDate);
		SDate.setColumns(10);
		
		btnNewButton = new JButton("Fetch data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date startDate=null,endDate=null;
				Vector<String> akeys=new Vector<String>();
				try 
				{
					startDate=sdf.parse(SDate.getText());
					endDate=sdf.parse(EDate.getText());
					
					System.out.println("Start Date : "+startDate);
					System.out.println("End Date : "+endDate);
					//JSonReader jsr=new JSonReader();
					JSONArray json = JSonReader.readJsonFromUrl("https://piptrade.org/webtest/testingdata.json");
				    //System.out.println(json.toString());
				    //System.out.println(json.getJSONArray("id"));
					
					allData=new String[json.length()][21];
			       JSONObject jsonObject = json.getJSONObject(0);
		            
		            // Extract keys (field names) from the JSON object
		            Iterator<String> keys = jsonObject.keys();
		           
		            while (keys.hasNext()) {
		                String fieldName = keys.next();
		                akeys.add(fieldName);
		            }
		        System.out.println("array length : "+json.length());
				    for(int i=0;i<json.length();i++)
				    {
				    	JSONObject row1=json.getJSONObject(i);
				    	
				    	Date dd=sdf.parse(row1.getString("createdAt"));
				    	//System.out.println("Date : "+dd);
				    	//System.out.println("data : "+row1.toString());
				    	//System.out.println("Checking data of : "+dd);
				    	if(dd.after(startDate) && dd.before(endDate))
				    	{
				    		//System.out.println("true");
				    		Iterator<String> keysIterator=akeys.iterator();
				    		while(keysIterator.hasNext())
				    		{
				    			//System.out.println("in loop");
				    			String key=keysIterator.next();
				    			//System.out.println("Key : "+key);
				    			//		"Userid", "Tickets", "Price", "Valume", "Status", "Type", "Symbol", "CreatedAt", "UpdateAt", "New column", "New column"
				    			//if(key.equals("user_id")|| key.equals("ticket")|| key.equals("symbol")|| key.equals("volume")|| key.equals("reason")|| key.equals("price")|| key.equals("createdAt")|| key.equals("updatedAt"))
				    			
				    			//System.out.println(row1);
				    			String data=row1.optString(key.toString());
				    			System.out.println("Length : "+data.length());
				    			if(!data.isBlank())
				    				allData[row][column++]=data;
				    			else
				    				allData[row][column++]="NULL";
				    			//System.out.println(allData[row][column++]);
				    			
				    		}
				    	}
				    	column=0;
				    	row++;
					}
//				    for(int r=0;r<allData.length;r++)
//				    {
//				    	for(int c=0;c<allData[r].length;c++)
//				    	{
//				    		
//				    		System.out.print(allData[r][c]+"\t");
//				    	}
//				    	System.out.println();
//				    }
			    } catch (ParseException | IOException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ShowData sd=new ShowData(allData,akeys);
				sd.setVisible(true);
			}
		});
		btnNewButton.setActionCommand("FetchData");
		btnNewButton.setBounds(144, 419, 85, 21);
		panel.add(btnNewButton);
		
		lblEnterTheEnd = new JLabel("Enter The End Date");
		lblEnterTheEnd.setBounds(40, 337, 106, 29);
		panel.add(lblEnterTheEnd);
		
		EDate = new JTextField();
		EDate.setColumns(10);
		EDate.setBounds(151, 337, 197, 29);
		panel.add(EDate);
		
		JLabel lblNewLabel_1 = new JLabel("Enter data in yyyy-mm-dd");
		lblNewLabel_1.setBounds(220, 298, 184, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter data in yyyy-mm-dd");
		lblNewLabel_1_1.setBounds(220, 376, 184, 29);
		panel.add(lblNewLabel_1_1);
		
				
	}
}
