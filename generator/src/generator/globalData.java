package generator;

import java.util.ArrayList;
import java.util.HashMap;

public class globalData {
	// ��ѡ�����
	private ArrayList<String> itemNames = new ArrayList<String>();
	// ���ÿ�ѡ�����Ʋ��Ҷ�Ӧ�Ŀ�ʽ
	private HashMap<String, ArrayList<String>> itemmapper = new HashMap<String, ArrayList<String>>();

	//ͯװ�����ӳ��
	public static  HashMap<String,String> reflex = new HashMap<>();
	
	//����
	public static  int sum = 0;
	@Override
	public String toString() {
		return "globalData [itemNames=" + itemNames + ", itemmapper="
				+ itemmapper + "]";
	}

	public HashMap<String, ArrayList<String>> getItemmapper() {
		return itemmapper;
	}

	public void setItemmapper(HashMap<String, ArrayList<String>> itemmapper) {
		this.itemmapper = itemmapper;
	}

	public ArrayList<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(ArrayList<String> itemNames) {
		this.itemNames = itemNames;
	}
	public static HashMap<String, String> getReflex() {
		return reflex;
	}

	public static void setReflex(HashMap<String, String> reflex) {
		globalData.reflex = reflex;
	}

}
