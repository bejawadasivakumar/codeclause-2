import java.awt.EventQueue;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Font;
public class frame1 extends JFrame {
	 
	private JPanel contentPane;
	private JTextField txtfield;
	static HashMap<String,Integer> entity = new HashMap<String,Integer>();
	String[] words;
    float totalwords=0;
    float score=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		File fs = null;
		BufferedReader os = null;
		String temp;
		String[] list;
		try {
			fs = new File("C:\\Users\\SIVAKUMAR BEJAWADA\\eclipse-workspace\\sentimentAnalysis\\Data.txt");
			os = new BufferedReader(new FileReader(fs));
			while((temp = os.readLine()) != null) {
				list=temp.split("	");
				
				entity.put(list[0],Integer.parseInt(list[1]));
			}
			}catch(Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 frame = new frame1();
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
	public frame1() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setFont(new Font("Times New Roman", Font.BOLD, 16));
		setForeground(new Color(255, 0, 255));
		setTitle("Sentimental Analysis");
		setBackground(new Color(199, 21, 133));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setForeground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("Enter text");
		label1.setForeground(new Color(72, 61, 139));
		label1.setFont(new Font("Arial Black", Font.BOLD, 20));
		label1.setBounds(108, 59, 116, 38);
		contentPane.add(label1);
		JLabel label2 = new JLabel("");
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label2.setForeground(new Color(0, 0, 139));
		label2.setBounds(138, 219, 329, 66);
		contentPane.add(label2);
		JButton button1 = new JButton("Check");
		button1.setForeground(new Color(0, 0, 255));
		button1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		button1.setBackground(new Color(0, 255, 255));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s="";
				String feedback="";
				float sentimentavg;
				s=txtfield.getText();
				s=s.toLowerCase();
				score=0;
				totalwords=0;
				words=s.split(" ");
				for(int i=0;i<words.length;i++) {
					if(entity.containsKey(words[i])) {
						totalwords+=1;
						score = score+entity.get(words[i]);
					}
				}
				if(totalwords==0) {
					totalwords=1;
				}
				sentimentavg=Math.round(score/(totalwords));
				if(sentimentavg<=-3) 
					feedback="Very Negative";
				else if(sentimentavg==-2) 
					feedback="Negative";
				else if(sentimentavg==-1 ) 
					feedback="Partially Negative";
				else if(sentimentavg==0) 
					feedback="Neutral";
				else if(sentimentavg==1) 
					feedback="Partially Positive";
				else if(sentimentavg==2 ) 
					feedback="Positive";
				else if(sentimentavg>=3) 
					feedback="Very Positive";
				label2.setText("Analysis : "+feedback+" ( "+sentimentavg+")");
				contentPane.add(label2);
			}
		});
		button1.setBounds(164, 151, 99, 32);
		contentPane.add(button1);
		
		txtfield = new JTextField();
		txtfield.setBackground(new Color(255, 255, 240));
		txtfield.setForeground(new Color(128, 0, 0));
		txtfield.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtfield.setBounds(234, 65, 212, 32);
		contentPane.add(txtfield);
		txtfield.setColumns(10);
		
		JButton button2 = new JButton("Clear");
		button2.setForeground(new Color(0, 0, 255));
		button2.setBackground(new Color(0, 255, 255));
		button2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfield.setText("");
				label2.setText("");
				contentPane.add(label2);
			}
		});
		button2.setBounds(347, 151, 99, 32);
		contentPane.add(button2);
		
		JLabel lblNewLabel = new JLabel("       SENTIMENTAL ANALYSIS");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 23));
		lblNewLabel.setBounds(60, 0, 436, 38);
		contentPane.add(lblNewLabel);
		
		
	}
}