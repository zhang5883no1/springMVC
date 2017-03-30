package cn.entity.xinxidu;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
	
	private int indexPage;//当前页
	private int pageRows;//每页显示数
	private int sumPage;//总页数
	private List list;//内容
	
	public Page() {
		
	}

	public Page(int indexPage, int pageRows, int sumPage,
			List list) {
		this.indexPage = indexPage;
		this.pageRows = pageRows;
		this.sumPage = sumPage;
		this.list = list;
	}

	public int getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
