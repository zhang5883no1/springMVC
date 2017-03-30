package cn.service.huitongapp;

import java.util.List;

import cn.entity.huitongapp.OfficialDto;
import cn.entity.huitongapp.OnlineQADto;
import cn.entity.qdm0020530.DEDE_ADDONARTICLE;
import cn.entity.qdm0020530.DEDE_ARCHIVES;
import cn.entity.qdm0020530.DEDE_ARCHIVESExample;
import cn.entity.xdchats.ZXDY;
import cn.entity.xdchats.ZXDYExample;
import cn.entity.xinxidu.Freecms_Info;
import cn.entity.xinxidu.Freecms_InfoExample;
import cn.entity.xinxidu.OfficialDto3;


public interface HuiTongService {

	/**
	 * 查询archives和article两表
	 * @param archivesExample DEDE_ARCHIVES类的查询类
	 * @return List<OfficialDto>
	 */
	
	public List<OfficialDto> query_archives_article(DEDE_ARCHIVESExample archivesExample);
	
	/**
	 * 根据aid查询article表
	 * @param aid 主键
	 * @return String
	 */
	public String query_article(int aid);
	
	/**
	 * 查询archives表
	 * @param archivesExample DEDE_ARCHIVES类的查询类
	 * @return List<OfficialDto>
	 */
	public List<OfficialDto> query_archives(DEDE_ARCHIVESExample archivesExample);
	
	/**
	 * 查询xd_zxdy表
	 * @param zxdyExample ZXDY类的查询类
	 * @return List<OnlineQADto>
	 */
	public List<OnlineQADto> query_zxdy(ZXDYExample zxdyExample);
	
	/**
	 * 得到要查询的DEDE_ARCHIVES类的总页数
	 * @param archivesExample DEDE_ARCHIVES类的查询类
	 * @return Integer
	 */
	public int count_official(DEDE_ARCHIVESExample archivesExample);
	
	/**
	 * 得到要查询的ZXDY类的总页数
	 * @param zxdyExample ZXDY类的查询类
	 * @return Integer
	 */
	public int count_zxdy(ZXDYExample zxdyExample);
	
	
	/**
	 * 根据id查询一个特定的DEDE_ARCHIVES类，并转换成接口要求的类的集合(注：此集合只有一个元素)
	 * @param id 主键的值
	 * @return List<OfficialDto>
	 */
	public List<OfficialDto> query_archives_detail(int id);
	
	/**
	 * 根据id查询一个特定的ZXDY类，并转换成接口要求的类的集合(注：此集合只有一个元素)
	 * @param id 主键的值
	 * @return List<OfficialDto>
	 */
	public List<OnlineQADto> query_zxdy_detai(long id);
	
	
	/**
	 * 传来一个ZXDY对象并添加到数据库中
	 * @param zxdy
	 * @return Integer 
	 */
	public int insert_zxdy(long id, String content);
	
	
	/**
	 * 将List<DEDE_ARCHIVES>的集合转变成List<OfficialDto>的集合
	 * @param archives_list
	 * @return List<OfficialDto>
	 */
	public List<OfficialDto> arch_to_offic(List<DEDE_ARCHIVES> archives_list);
	
	/**
	 * 将List<DEDE_ADDONARTICLE>的集合转变成List<String>的集合
	 * @param article_list
	 * @return List<String>
	 */
	public List<String> arti_to_str(List<DEDE_ADDONARTICLE> article_list);
	
	
	/**
	 * 将List<DEDE_ARCHIVES>和List<DEDE_ADDONARTICLE>两个集合合并转变成List<OfficialDto>的集合
	 * @param archives_list
	 * @return List<OfficialDto>
	 */
	public List<OfficialDto> arch_arti_to_offic(List<DEDE_ARCHIVES> archives_list, List<DEDE_ADDONARTICLE> article_list);
	
	/**
	 * 将List<ZXDY>集合转变成List<OnlineQADto>集合
	 * @param zxdy
	 * @return List<OnlineQADto>
	 */
	public List<OnlineQADto> zxdy_to_online(List<ZXDY> zxdy);
	
	public List<OnlineQADto> zxdy_to_online_answer(List<ZXDY> zxdy);
	
	/**
	 * 将List<Freecms_Info>集合转变成List<OfficialDto3>的集合
	 * @param freecms 传递过来的List<Freecms_Info>集合的参数
	 * @returnList<OfficialDto>
	 */
	public List<OfficialDto3> free_to_offic(List<Freecms_Info> freecms);
	
	/**
	 * 根据Freecms_InfoExample查询类查询出Freecms_InfoExample的结果集
	 * @param freecmsExample
	 * @return
	 */
	public List<Freecms_Info> query_freecms(Freecms_InfoExample freecmsExample);
	
	/**
	 * 得到要查询的Freecms类的总页数
	 * @param Freecms_InfoExample Freecms类的查询类
	 * @return Integer
	 */
	public int count_freecms(Freecms_InfoExample freecmsExample);
	
	/**
	 * 根据id查询一个特定的Freecms_Info类
	 * @param id 主键的值
	 * @return Freecms_Info
	 */
	public Freecms_Info query_freecms_detail(String id);
}
