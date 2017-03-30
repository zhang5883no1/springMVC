package cn.entity.xinxidu;

public class DoubleOfficialDto {
	private String     id;//编号
	private String     channel;//类型编号
	private String  addtime;//
	private int     clickNum;//点击量
	private String    title;//标题
	private String    shortTitle;//小标题
	private String    img;//
	private String  description;//
	private String content;
	private String url;
	private String attchs;
	public DoubleOfficialDto() {
		
	}
	
	public DoubleOfficialDto(String id, String channel, String addtime,
			int clickNum, String title, String shortTitle, String img,
			String description, String content, String url, String attchs) {
		this.id = id;
		this.channel = channel;
		this.addtime = addtime;
		this.clickNum = clickNum;
		this.title = title;
		this.shortTitle = shortTitle;
		this.img = img;
		this.description = description;
		this.content = content;
		this.url = url;
		this.attchs = attchs;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public int getClickNum() {
		return clickNum;
	}
	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAttchs() {
		return attchs;
	}

	public void setAttchs(String attchs) {
		this.attchs = attchs;
	}
	
}
