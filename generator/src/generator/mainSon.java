package generator;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 
 * @author Administrator 宽度=580 高度=80
 *
 */
public class mainSon extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container Parent = null; // getParent();
	private Container grandpa = null; // getParent().getParent();
	private Container Ancestors = null; // getParent().getParent().getParent().getParent();
	globalData gd=new jsonAnalysis().getGlobalData("./config.json");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Create the panel.
	 */
	public mainSon() {
		setLayout(null);
		setVisible(true);

		textField_9 = new JTextField();
		textField_9.setName("XS");
		textField_9.setBounds(210, 10, 36, 21);
		add(textField_9);
		textField_9.setColumns(10);

		JLabel lblS = new JLabel("S");
		lblS.setBounds(265, 7, 36, 27);
		add(lblS);

		textField = new JTextField();
		textField.setName("S");
		textField.setBounds(280, 10, 36, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblM = new JLabel("M");
		lblM.setBounds(335, 7, 36, 27);
		add(lblM);

		textField_1 = new JTextField();
		textField_1.setName("M");
		textField_1.setBounds(350, 10, 36, 21);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("L");
		lblNewLabel.setBounds(395, 7, 36, 27);
		add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setName("L");
		textField_2.setBounds(411, 10, 36, 21);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("XL");
		lblNewLabel_1.setBounds(457, 7, 36, 27);
		add(lblNewLabel_1);

		textField_3 = new JTextField();
		textField_3.setName("XL");
		textField_3.setBounds(474, 10, 36, 21);
		add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("2XL");
		lblNewLabel_2.setBounds(180, 41, 36, 27);
		add(lblNewLabel_2);

		textField_4 = new JTextField();
		textField_4.setName("2XL");
		textField_4.setBounds(202, 44, 36, 21);
		add(textField_4);
		textField_4.setColumns(10);

		JLabel lblxl = new JLabel("3XL");
		lblxl.setBounds(245, 41, 36, 27);
		add(lblxl);

		textField_5 = new JTextField();
		textField_5.setName("3XL");
		textField_5.setBounds(270, 44, 36, 21);
		add(textField_5);
		textField_5.setColumns(10);

		JLabel lblxl_1 = new JLabel("4XL");
		lblxl_1.setBounds(315, 41, 36, 27);
		add(lblxl_1);

		textField_6 = new JTextField();
		textField_6.setName("4XL");
		textField_6.setBounds(338, 44, 36, 21);
		add(textField_6);
		textField_6.setColumns(10);

		JLabel lblxl_2 = new JLabel("5XL");
		lblxl_2.setBounds(384, 41, 36, 27);
		add(lblxl_2);

		textField_7 = new JTextField();
		textField_7.setName("5XL");
		textField_7.setBounds(411, 44, 36, 21);
		add(textField_7);
		textField_7.setColumns(10);

		JLabel lblxl_3 = new JLabel("6XL");
		lblxl_3.setBounds(450, 41, 36, 27);
		add(lblxl_3);

		textField_8 = new JTextField();
		textField_8.setName("6XL");
		textField_8.setBounds(474, 44, 36, 21);
		add(textField_8);
		textField_8.setColumns(10);

		/**
		 * 减少一个mainSon
		 */
		JButton btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JButton source = (JButton) e.getSource();
				initParent(source);
				mainF mainPlane = (mainF) Ancestors;

				mainSon mainSon = (generator.mainSon) source.getParent();
				System.out.println("删除面板：" + mainSon);

				// 因为删除掉了1个，所以下1次生成的位置-80
				mainPlane.setCurrentHeight(mainPlane.getCurrentHeight() - 80);

				Parent.remove(mainSon);
				Parent.repaint();
				Component[] components = Parent.getComponents();
				// 剩下的列表往前移动
				for (Component component : components) {
					if (component.getY() > mainSon.getY()) {
						component.setBounds(component.getX(),
								component.getY() - 80, component.getWidth(),
								component.getHeight());
					}
				}

				Ancestors.setBounds(Ancestors.getX(), Ancestors.getY(), 580,
						Ancestors.getHeight() - 80);

				Parent.revalidate();
				Ancestors.revalidate();

			}
		});

		final JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainSon m = new mainSon();
				// 设置新建按钮的父，祖父，祖宗 面板
				initParent(m);
				mainF mainPlane = (mainF) Ancestors;
				// 距离上一次面板的 摆放的距离+80
				mainPlane.setCurrentHeight(mainPlane.getCurrentHeight() + 80);
				m.setBounds(0, mainPlane.getCurrentHeight(), 580, 80);
				// 添加一个面板
				Ancestors.add(m);
				System.out.println("添加面板:" + m);
				// 主面板的高度增加
				Ancestors.setBounds(Ancestors.getX(), Ancestors.getY(), 580,
						m.Ancestors.getHeight() + 80);
				// 生成的按钮面板 需要往下移动80
				JPanel submitPlane = mainPlane.getSubmit();
				submitPlane.setBounds(submitPlane.getX(),
						submitPlane.getY() + 80, submitPlane.getWidth(),
						submitPlane.getHeight());
				// 重绘
				Parent.revalidate();
				Ancestors.revalidate();
			}
		});
		btnNewButton_1.setBounds(520, 41, 50, 23);
		add(btnNewButton_1);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setName("color");
		comboBox.setBounds(10, 10, 100, 25);
		add(comboBox);

		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setName("type");
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedItem = comboBox_1.getSelectedItem().toString();
				int itemCount = comboBox.getItemCount();
				if(itemCount!=0){
				for (int i = 0; i < itemCount; i++) {
					comboBox.removeItemAt(0);
				}
				
				}
				HashMap<String,ArrayList<String>> itemmapper = gd.getItemmapper();
				ArrayList<String> selectedStyle = itemmapper.get(selectedItem);
				for (String color : selectedStyle) {
					comboBox.addItem(color);
				}
			}
		});
		comboBox_1.setBounds(10, 43, 160, 25);
		add(comboBox_1);

		btnNewButton.setBounds(520, 9, 50, 23);
		add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("XS");
		lblNewLabel_3.setBounds(180, 7, 36, 27);
		add(lblNewLabel_3);
		initItem(comboBox,comboBox_1);
	}
	private void initItem(JComboBox<String> cb1,JComboBox<String> cb2){
		
		ArrayList<String> itemNames = gd.getItemNames();
		for (String item : itemNames) {
			cb2.addItem(item);
		}
		
		
	}
	
	private void initParent(Component obj) {
		if (obj == null) {
			return;
		}
		if (Parent == null) {
			Parent = getParent();
			grandpa = getParent().getParent();
			Ancestors = getParent().getParent().getParent().getParent();
		}

	}

	private void initParent(mainSon obj) {
		if (obj == null) {
			return;
		}
		if (Parent == null) {
			Parent = getParent();
			grandpa = getParent().getParent();
			Ancestors = getParent().getParent().getParent().getParent();
		}
		obj.Parent = Parent;
		obj.grandpa = grandpa;
		obj.Ancestors = Ancestors;
	}
	@Override
	public String toString() {
		return "mainSon [Parent=" + Parent + ", grandpa=" + grandpa
				+ ", Ancestors=" + Ancestors + ", gd=" + gd + ", textField="
				+ textField + ", textField_1=" + textField_1 + ", textField_2="
				+ textField_2 + ", textField_3=" + textField_3
				+ ", textField_4=" + textField_4 + ", textField_5="
				+ textField_5 + ", textField_6=" + textField_6
				+ ", textField_7=" + textField_7 + ", textField_8="
				+ textField_8 + ", textField_9=" + textField_9 + "]";
	}
}
