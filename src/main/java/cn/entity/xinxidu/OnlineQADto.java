package cn.entity.xinxidu;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class OnlineQADto {
	
	private int id;//编号
	private String qutime;//提问时间
	private String antime;//回答时间
	private String q;//问题
	private String qn;//提问人
	private String a;//答案
	private String an;//回答人
	private int status;//状态
	
	
	public OnlineQADto() {
	}
	public OnlineQADto(int id, String qutime, String antime, String q, String qn, String a, String an,
			int status) {
		this.id = id;
		this.qutime = qutime;
		this.antime = antime;
		this.q = q;
		this.qn = qn;
		this.a = a;
		this.an = an;
		this.status = status;
	}
	
	public JSONObject toJson(){
		JSONObject ob=new JSONObject();
		ob.accumulate("q", this.q);
		ob.accumulate("qn", this.qn);
		ob.accumulate("a", this.a);
		ob.accumulate("an", this.an);
		return ob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public String getQutime() {
		return qutime;
	}
	public void setQutime(String qutime) {
		this.qutime = qutime;
	}
	public String getAntime() {
		return antime;
	}
	public void setAntime(String antime) {
		this.antime = antime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OnlineQADto [id=" + id + ", qutime=" + qutime + ", antime=" + antime+ ", q=" + q +  ", qn=" + qn
				+ ", a=" + a +", an=" + an  +",status=" + status +"]";
	}
}
