package cn.service.xxdzhibo.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.base.util.ValidateUtil;
import cn.dao.xxdzhibo.XXD_CustomerMapper;
import cn.dao.xxdzhibo.XXD_CustomerTypeMapper;
import cn.entity.xxdzhibo.XXD_Customer;
import cn.entity.xxdzhibo.XXD_CustomerExample;
import cn.entity.xxdzhibo.XXD_CustomerExample.Criteria;
import cn.entity.xxdzhibo.XXD_CustomerType;
import cn.service.xxdzhibo.XxdZhiboService;
  
@Service
public class XxdZhiboServiceImpl implements XxdZhiboService{

	@Autowired
	private XXD_CustomerMapper customerMapper;
	@Autowired
	private XXD_CustomerTypeMapper typeMapper;
	
	@Override
	public boolean validCustomerExist(XXD_Customer customer) {
		// TODO Auto-generated method stub 
		XXD_CustomerExample example=new XXD_CustomerExample();
		Criteria criteria=example.createCriteria();
		if(ValidateUtil.isEmpty(customer.getUsername())){
			criteria.andUsernameEqualTo(customer.getUsername());
		}
		List<XXD_Customer> list=customerMapper.selectByExample(example);
		return list.size()>0?true:false;
	}

	@Override
	public XXD_Customer add(XXD_Customer customer) {
		// TODO Auto-generated method stub  
		int index=customerMapper.insert(customer);
		if(index==1){
			XXD_CustomerExample example=new XXD_CustomerExample();
			example.createCriteria().andUsernameEqualTo(customer.getUsername());
			customer=customerMapper.selectByExample(example).get(0);
			
			XXD_CustomerType type=new XXD_CustomerType().initParam();
			type.setCustomerId(customer.getId());
			typeMapper.insert(type);
		}
		return customer;
	}


}
