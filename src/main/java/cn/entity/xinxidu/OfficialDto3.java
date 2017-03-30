package cn.entity.xinxidu;


/**
 * @Author xidu-lyb
 * @Time   2015年6月25日下午1:13:38
 * 
 */
public class OfficialDto3 {
	
	private String     id;//编号
	private String     typeid;//类型编号
	private String  typeid2;//
	private String     sortrank;//
	private int     click;//点击量
	private String    title;//标题
	private String    shorttitle;//小标题
	private String    litpic;//
	private String     pubdate;//
	private String     senddate;//
	private String    keywords;//关键字
	private String  description;//
	private String body;
	private String redirecturl;
	
	public OfficialDto3() {

	}
	
	public OfficialDto3(String id, String typeid, String typeid2, String sortrank,
			int click, String title, String shorttitle, String litpic,
			String pubdate, String senddate, String keywords,
			String description, String body, String redirecturl) {
		this.id = id;
		this.typeid = typeid;
		this.typeid2 = typeid2;
		this.sortrank = sortrank;
		this.click = click;
		this.title = title;
		this.shorttitle = shorttitle;
		this.litpic = litpic;
		this.pubdate = pubdate;
		this.senddate = senddate;
		this.keywords = keywords;
		this.description = description;
		this.body = body;
		this.redirecturl = redirecturl;
	}

	public OfficialDto3(String id, String typeid, String typeid2, String sortrank,
			int click, String title, String shorttitle, String litpic,
			String pubdate, String senddate, String keywords, String description,String body) {
		this.id = id;
		this.typeid = typeid;
		this.typeid2 = typeid2;
		this.sortrank = sortrank;
		this.click = click;
		this.title = title;
		this.shorttitle = shorttitle;
		this.litpic = litpic;
		this.pubdate = pubdate;
		this.senddate = senddate;
		this.keywords = keywords;
		this.description = description;
		this.body = body;
	}

	public String getId() {
		return id;
	}
	public String getTypeid() {
		return typeid;
	}
	public String getTypeid2() {
		return typeid2;
	}
	public String getSortrank() {
		return sortrank;
	}
	public int getClick() {
		return click;
	}
	public String getTitle() {
		return title;
	}
	public String getShorttitle() {
		return shorttitle;
	}
	public String getLitpic() {
		return litpic;
	}
	public String getPubdate() {
		return pubdate;
	}
	
	public String getSenddate() {
		return senddate;
	}
	public String getKeywords() {
		return keywords;
	}
	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public void setTypeid2(String typeid2) {
		this.typeid2 = typeid2;
	}
	public void setSortrank(String sortrank) {
		this.sortrank = sortrank;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}
	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	public String getRedirecturl() {
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}

	@Override
	public String toString() {
		return "OfficialDto [id=" + id + ", typeid=" + typeid + ", typeid2="
				+ typeid2 + ", sortrank=" + sortrank + ", click=" + click
				+ ", title=" + title + ", shorttitle=" + shorttitle
				+ ", litpic=" + litpic + ", pubdate=" + pubdate + ", senddate="
				+ senddate + ", keywords=" + keywords + ", description="
				+ description + ", body=" + body + ", redirecturl="
				+ redirecturl + "]";
	}
	
}
