package cn.entity.xinxidu;

public class OfficDto2 {
	
	private String     id;//编号
	private String     typeid;//类型编号
	private String     sortrank;//
	private int     click;//点击量
	private String    title;//标题
	private String    shorttitle;//小标题
	private String    litpic;//
	private String     arcrank;//
	private String     pubdate;//
	private String     senddate;//
	private String    keywords;//关键字
	
	public OfficDto2() {
		
	}

	public OfficDto2(String id, String typeid, String sortrank, int click, String title,
			String shorttitle, String litpic, String arcrank, String pubdate,
			String senddate, String keywords) {
		this.id = id;
		this.typeid = typeid;
		this.sortrank = sortrank;
		this.click = click;
		this.title = title;
		this.shorttitle = shorttitle;
		this.litpic = litpic;
		this.arcrank = arcrank;
		this.pubdate = pubdate;
		this.senddate = senddate;
		this.keywords = keywords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getSortrank() {
		return sortrank;
	}

	public void setSortrank(String sortrank) {
		this.sortrank = sortrank;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getArcrank() {
		return arcrank;
	}

	public void setArcrank(String arcrank) {
		this.arcrank = arcrank;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "OfficDto2 [id=" + id + ", typeid=" + typeid + ", sortrank="
				+ sortrank + ", click=" + click + ", title=" + title
				+ ", shorttitle=" + shorttitle + ", litpic=" + litpic
				+ ", arcrank=" + arcrank + ", pubdate=" + pubdate
				+ ", senddate=" + senddate + ", keywords=" + keywords + "]";
	}

}
