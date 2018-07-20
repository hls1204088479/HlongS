package generator;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JCheckBox;

public class mainF extends JFrame {

	/**
	 * 
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private JSeparator Separator = null;
	private JButton generate = null;
	private JPanel submit = null;
	private JPanel contentPane;
	JCheckBox chckbxNewCheckBox = null;
	// 当前的高度 初始化的3个面板 80*(3-1)=160
	private int currentHeight = 160;
	// 生成按钮位置
	private int submitPlaneHeight = 250;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					mainF frame = new mainF();
					ImageIcon icon = new ImageIcon("./logo.png"); // xxx代表图片存放路径，2.png图片名称及格式
					frame.setTitle("generator 1.0.0");
					frame.setIconImage(icon.getImage());
					frame.setName("主面板");
					mainSon mainSon = new mainSon();
					mainSon.setBounds(0, 0, 580, 80);
					mainSon mainSon1 = new mainSon();
					mainSon1.setBounds(0, 80, 580, 80);
					mainSon mainSon2 = new mainSon();
					mainSon2.setBounds(0, 160, 580, 80);
					frame.getContentPane().add(mainSon);
					frame.getContentPane().add(mainSon1);
					frame.getContentPane().add(mainSon2);
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
	public mainF() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 200, 580, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		submit = new JPanel();
		submit.setName("生成栏");
		submit.setLayout(null);
		submit.setBounds(0, 250, 580, 80);
		Separator = new JSeparator();
		generate = new JButton("生成");
		// 生成按钮按下

		final JFrame myself = this;

		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<mainSon> mainSonList = getMainSonList(myself);
				String result = getResult(mainSonList);
				jsonAnalysis.setCilpBoard(result+"共"+globalData.sum+"件。");
				System.out.println("生成的结果:\n" + result+"共"+globalData.sum+"件。");
				globalData.sum=0;
			}

		});
		Separator.setBounds(0, 0, 580, 1);
		generate.setBounds(175, 10, 200, 50);
		submit.add(Separator);
		submit.add(generate);
		getContentPane().add(submit);

		JButton btnNewButton = new JButton("\u6E05\u9664");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear(myself);

			}
		});
		btnNewButton.setBounds(385, 11, 93, 50);
		submit.add(btnNewButton);
		chckbxNewCheckBox = new JCheckBox("\u6362\u884C\u6A21\u5F0F");
		chckbxNewCheckBox.setName("newLineMode");
		chckbxNewCheckBox.setBounds(54, 37, 103, 23);
		submit.add(chckbxNewCheckBox);
	}

	public void newLine(StringBuilder sb) {

		sb.append("\n").toString();
	}

	public void trimComma(StringBuilder sb) {
		if (sb.toString().endsWith(",")) {
			sb.delete(sb.length() - 1, sb.length());
		}
	}

	private String getResult(ArrayList<mainSon> mainSonList) {

		StringBuilder Result = new StringBuilder();
		ArrayList<item> numbersArray = getNumbersMap(mainSonList);
		for (item item : numbersArray) {
			String itemName = item.getItemName();
			
			Set<Entry<String, String>> entrySet = item.getNumberMapp()
					.entrySet();
		
			boolean isChilren= false;
			boolean isdelete = false;
			boolean selected = chckbxNewCheckBox.isSelected();
			if(itemName.contains("童装")){
				isChilren=true;
			}
			
			// 包含空格的 在 空格中间 插入 颜色
			if (itemName.contains(" ")) {
				Result.append(itemName.replace(" ", " " + item.getColor() + " "));

				if (selected) {
					isdelete = insertColorNumber(Result, entrySet, 1,
							itemName.replace(" ", " " + item.getColor() + " ")
									.length(),isChilren);
				} else {
					isdelete = insertColorNumber(Result, entrySet, 0,
							itemName.replace(" ", " " + item.getColor() + " ")
									.length(),isChilren);
				}

				// 不包含空格的 在前端插入 颜色
			} else {
				Result.append((item.getColor() + " " + itemName));
				
				if (selected) {
					isdelete = insertColorNumber(Result, entrySet, 1,
							(item.getColor() + " " + itemName).length(),isChilren);
				} else {
					isdelete = insertColorNumber(Result, entrySet, 0,
							(item.getColor() + " " + itemName).length(),isChilren);
				}
			}

			trimComma(Result);
			if (isdelete) {
				newLine(Result);
			}

		}
		return Result.toString();
	}

	private boolean insertColorNumber(StringBuilder Result,
			Set<Entry<String, String>> entrySet, int mode, int deleteLength, boolean isChilren) {
		int flag = 0;
		if (mode == 1) {
			newLine(Result);
		}
		for (Entry<String, String> entry : entrySet) {
			if (!entry.getValue().equals("")) {
				HashMap<String, String> reflex = globalData.getReflex();
				
				if(isChilren){
					Result.append(reflex.get(entry.getKey()) + "-" + entry.getValue() + "件");
				}else{
					Result.append(entry.getKey() + "-" + entry.getValue() + "件");
				}
				globalData.sum+=Integer.valueOf(entry.getValue());
				Result.append(",");
			} else {
				flag++;
			}

		}
		if (flag == 10 && mode == 1) {
			Result.delete(Result.length() - deleteLength - "\n".length(),
					Result.length());
			return false;
		} else if (flag == 10 && mode == 0) {
			Result.delete(Result.length() - deleteLength, Result.length());
			return false;
		}
		return true;
	}

	private ArrayList<item> getNumbersMap(ArrayList<mainSon> mainSonList) {

		ArrayList<item> result = new ArrayList<item>();
		for (mainSon mainSon : mainSonList) {
			item it = new item();
			Map<String, String> mappMap = new TreeMap<>(new comparator());
			Component[] components = mainSon.getComponents();
			for (Component component : components) {

				if (component.getName() == null) {
					continue;
				} else if (component.getName().equals("color")) {
					@SuppressWarnings("unchecked")
					JComboBox<String> color = (JComboBox<String>) component;
					it.setColor(color.getSelectedItem().toString());
				} else if (component.getName().equals("type")) {
					@SuppressWarnings("unchecked")
					JComboBox<String> itemName = (JComboBox<String>) component;
					it.setItemName(itemName.getSelectedItem().toString());
				} else {
					JTextField text = (JTextField) component;
					mappMap.put(text.getName(), text.getText());
				}
			}
			it.setNumberMapp(mappMap);
			result.add(it);
		}

		return result;
	}

	private void clear(JFrame myself) {
		ArrayList<mainSon> mainSonList = getMainSonList(myself);
		for (mainSon mainSon : mainSonList) {
			Component[] components = mainSon.getComponents();
			for (Component component : components) {
				if (component instanceof JTextField) {
					JTextField text = (JTextField) component;
					text.setText("");
				}
			}
		}
		System.out.println("已清空");

	}

	private ArrayList<mainSon> getMainSonList(JFrame myself) {
		ArrayList<mainSon> mainSonListTemp = new ArrayList<>();
		JRootPane jrp = (JRootPane) myself.getComponent(0);
		JLayeredPane jlp = (JLayeredPane) jrp.getComponent(1);
		JPanel jp = (JPanel) jlp.getComponent(0);
		Component[] components = jp.getComponents();
		for (int i = 1; i < components.length; i++) {
			mainSon ms = (mainSon) components[i];
			mainSonListTemp.add(ms);
		}
		return mainSonListTemp;
	}

	public JSeparator getSeparator() {
		return Separator;
	}

	public JButton getGenerate() {
		return generate;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
	}

	public JPanel getSubmit() {
		return submit;
	}

	public void setSubmit(JPanel submit) {
		this.submit = submit;
	}

	public int getSubmitPlaneHeight() {
		return submitPlaneHeight;
	}

	public void setSubmitPlaneHeight(int submitPlaneHeight) {
		this.submitPlaneHeight = submitPlaneHeight;
	}
}
