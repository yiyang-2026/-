package bean;

public class Trade {
	//出品商品id
	private String sellid;

	//購入者
	private String purchaser;
	//出品者
	private String seller;
	//写真
	private String image;
	//購入日時
	private String purchasedate;
	//発送日時
	private String shippingdate;
	//取引状況
	private String tradeSituation;
	//商品価格
	private int price;

	public String getSellid() {
		return sellid;
	}

	public void setSellid(String sellid) {
		this.sellid = sellid;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getShippingdate() {
		return shippingdate;
	}

	public void setShippingdate(String shippingdate) {
		this.shippingdate = shippingdate;
	}

	public String getTradeSituation() {
		return tradeSituation;
	}

	public void setTradeSituation(String tradeSituation) {
		this.tradeSituation = tradeSituation;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}