package cn.service.xxdzhibo;  

import cn.entity.xxdzhibo.XXD_Customer;
  
public interface XxdZhiboService {

	public boolean validCustomerExist(XXD_Customer customer);
	
	public XXD_Customer add(XXD_Customer customer);
}
