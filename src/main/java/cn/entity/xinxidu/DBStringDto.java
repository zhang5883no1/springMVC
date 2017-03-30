package cn.entity.xinxidu;

public class DBStringDto {
	private int htmlIndexnum;

	public DBStringDto() {
		
	}

	public DBStringDto(int htmlIndexnum) {
		this.htmlIndexnum = htmlIndexnum;
	}

	public int getHtmlIndexnum() {
		return htmlIndexnum;
	}

	public void setHtmlIndexnum(int htmlIndexnum) {
		this.htmlIndexnum = htmlIndexnum;
	}

	@Override
	public String toString() {
		return "DBStringDto [htmlIndexnum=" + htmlIndexnum + "]";
	}
	
	
}
