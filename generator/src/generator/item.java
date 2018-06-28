package generator;

import java.util.Map;

public class item {

	private String itemName;
	private String color;
	private Map<String, String> numberMapp;
	@Override
	public String toString() {
		return "item [itemName=" + itemName + ", color=" + color
				+ ", numberMapp=" + numberMapp + "]";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Map<String, String> getNumberMapp() {
		return numberMapp;
	}

	public void setNumberMapp(Map<String, String> numberMapp) {
		this.numberMapp = numberMapp;
	}
}
