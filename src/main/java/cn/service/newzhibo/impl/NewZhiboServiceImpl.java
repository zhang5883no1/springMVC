package cn.service.newzhibo.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.dao.newzhibo.NEWZhibo_CustomerMapper;
import cn.dao.newzhibo.NEWZhibo_CustomerTypeMapper;
import cn.entity.newzhibo.NEWZhibo_Customer;
import cn.entity.newzhibo.NEWZhibo_CustomerExample;
import cn.entity.newzhibo.NEWZhibo_CustomerExample.Criteria;
import cn.service.newzhibo.NewZhiboService;
  
@Service
public class NewZhiboServiceImpl implements NewZhiboService{

	@Autowired
	private NEWZhibo_CustomerMapper customerMapper;
	@Autowired
	private NEWZhibo_CustomerTypeMapper typeMapper;
	
	@Override
	public boolean validCustomerExist(NEWZhibo_Customer customer) {
		// TODO Auto-generated method stub  
		NEWZhibo_CustomerExample example=new NEWZhibo_CustomerExample();
		Criteria criteria=example.createCriteria();
		if(ValidateUtil.isEmpty(customer.getUsername())){
			criteria.andUsernameEqualTo(customer.getUsername());
		}
		List<NEWZhibo_Customer> list=customerMapper.selectByExample(example);
		return list.size()>0?true:false;
	}

}
