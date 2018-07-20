package generator;

import java.util.ArrayList;
import java.util.HashMap;

public class globalData {
	// 可选择的项
	private ArrayList<String> itemNames = new ArrayList<String>();
	// 利用可选项名称查找对应的款式
	private HashMap<String, ArrayList<String>> itemmapper = new HashMap<String, ArrayList<String>>();

	//童装尺码的映射
	public static  HashMap<String,String> reflex = new HashMap<>();
	
	//总数
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
