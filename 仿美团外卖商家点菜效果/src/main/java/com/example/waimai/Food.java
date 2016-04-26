package com.example.waimai;
/**
 * 食物类
 * @author Administrator
 *
 */
public class Food {
	//区分食物的类，相同属于同一个级别
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private String tittle;
	private String ipgUrl;
	public String getIpgUrl() {
		return ipgUrl;
	}
	public void setIpgUrl(String ipgUrl) {
		this.ipgUrl = ipgUrl;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getInSales() {
		return inSales;
	}
	public void setInSales(String inSales) {
		this.inSales = inSales;
	}
	public String getZan() {
		return zan;
	}
	public void setZan(String zan) {
		this.zan = zan;
	}
	public String getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(String remainingCount) {
		this.remainingCount = remainingCount;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	private String foodName;
	private String inSales;
	private String zan;
	private String remainingCount;

}
