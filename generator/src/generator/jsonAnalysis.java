package generator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class jsonAnalysis {

	public static void main(String[] args) {
		new jsonAnalysis().getGlobalData("./config.json");
	}

	public globalData getGlobalData(String fileName) {
		globalData gd = new globalData();
		File file = new File(fileName);
		String json = txt2String(file);
		JsonElement root = new JsonParser().parse(json);
		JsonArray rootArray = root.getAsJsonArray();
		for (JsonElement itemElement : rootArray) {
			JsonObject itemJsonArray = itemElement.getAsJsonObject();
			setGlobalItemNames(itemJsonArray, gd);
			setGlobalItemMapper(itemJsonArray, gd);
		}
		deleteMapp(gd);
	
		return gd;
	}
	/**
	 * 删除映射的列表
	 * @param gd
	 */
	private void deleteMapp(globalData gd) {
		ArrayList<String> itemNames = gd.getItemNames();
		for (int i = 0; i < itemNames.size(); i++) {
			if(itemNames.get(i).contains("mapp")){
				itemNames.remove(i);
			}
		}
	}

	private void setGlobalItemMapper(JsonObject itemJsonArray, globalData gd) {
		Set<Entry<String, JsonElement>> entrySet = itemJsonArray.entrySet();
		HashMap<String, ArrayList<String>> itemmapper = gd.getItemmapper();
		for (Entry<String, JsonElement> entry : entrySet) {
			JsonArray array = entry.getValue().getAsJsonArray();
			ArrayList<String> subItem = getSubItem(array);
			if (entry.getKey().contains("mapp")) {
				setGlobalReflex(globalData.getReflex(), subItem);
			} else {
				itemmapper.put(entry.getKey(), subItem);
			}

		}

	}

	private void setGlobalReflex(HashMap<String, String> reflex,
			ArrayList<String> subItem) {
		for (String string : subItem) {
			String[] split = string.split("@");
			reflex.put(split[0], split[1]);
		}
		

	}

	public ArrayList<String> getSubItem(JsonArray array) {

		ArrayList<String> arrayList = new ArrayList<String>();
		for (JsonElement jsonElement : array) {
			arrayList.add(jsonElement.getAsString());
		}
		return arrayList;
	}

	public void setGlobalItemNames(JsonObject jsonObj, globalData gd) {
		Set<String> keySet = jsonObj.keySet();
		ArrayList<String> itemNames = gd.getItemNames();
		for (String name : keySet) {

			itemNames.add(name);
		}
	}

	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static void setCilpBoard(String content) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable trandata = new StringSelection(content);
		clipboard.setContents(trandata, null);
	}

}
