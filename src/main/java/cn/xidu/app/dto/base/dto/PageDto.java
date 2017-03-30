package cn.xidu.app.dto.base.dto;  

import java.util.ArrayList;
import java.util.List;
  
public class PageDto<T> {

	private int totalPage=1;
	private int currentPage=1;
	private int pageSize=15;
	private int totalCount=1;
	private String pageString;
	private List<T> resultList=new ArrayList<T>();
	
	public int getTotalPage() {
		return totalPage;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
		getPageString();
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartLimit(){
		return (currentPage-1)*pageSize;
	}
	
	public String getPageString(){
		String r="";
		r+="<div class=\"row\">";
		r+="<div class=\"col-sm-6\">";
		r+="<div aria-relevant=\"all\" aria-live=\"polite\" role=\"alert\" id=\"dataTables-example_info\" class=\"dataTables_info\">Showing "+((this.currentPage-1)*this.pageSize+1)+" to "+(this.currentPage*this.pageSize>this.totalCount?this.totalCount:this.currentPage*this.pageSize)+" of "+this.totalCount+" entries</div>";
		r+="</div>";
		r+="<div class=\"col-sm-6\">";
		r+="<div id=\"dataTables-example_paginate\" class=\"dataTables_paginate paging_simple_numbers\">";
		r+="<ul class=\"pagination\">";
		
		//前三页
		if(this.currentPage>1){
			r+="<li id=\"dataTables-example_previous\" tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button previous \">";
			r+="<a href=\"#\" data-page=\""+(this.currentPage-1)+"\">Previous</a>";
			r+="</li>";
			for(int i=3;i>0;i--){
				if(this.currentPage-i>0){
					r+="<li tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button \">";
					r+="<a href=\"#\" data-page=\""+(this.currentPage-i)+"\">"+(this.currentPage-i)+"</a>";
					r+="</li>";
				}
			}
		}else{
			r+="<li id=\"dataTables-example_previous\" tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button previous disabled\">";
			r+="<a href=\"#\">Previous</a>";
			r+="</li>";
		}
		
		//当前页
		r+="<li tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button active\">";
		r+="<a href=\"#\">"+this.currentPage+"</a>";
		r+="</li>";
		
		//后三页
		if(this.currentPage<this.totalPage){
			for(int i=1;i<3;i++){
				if(this.currentPage+i<=this.getTotalPage()){
					r+="<li tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button \">";
					r+="<a href=\"#\" data-page=\""+(this.currentPage+i)+"\">"+(this.currentPage+i)+"</a>";
					r+="</li>";
				}
			}
			r+="<li id=\"dataTables-example_next\" tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button next\">";
			r+="<a href=\"#\" data-page=\""+(this.currentPage+1)+"\">Next</a>";
			r+="</li>";
		}else{
			r+="<li id=\"dataTables-example_next\" tabindex=\"0\" aria-controls=\"dataTables-example\" class=\"paginate_button next disabled\">";
			r+="<a href=\"#\">Next</a>";
			r+="</li>";
		}
		
		r+="</ul>";
		r+="</div>";
		r+="</div>";
		r+="</div>";
		this.pageString=r;
		return r;
	}
}
