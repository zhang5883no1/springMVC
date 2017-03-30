<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>banner list</title>

  <!-- TABLE STYLES-->
<link href="${appStaticContent}assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	 Banner
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div role="grid" class="dataTables_wrapper form-inline" id="dataTables-example_wrapper">
                                	<form action="query" method="post" id="form">
	                                	<div class="row">
	                                		<div class="col-sm-6">
	                                			<div class="dataTables_length" id="dataTables-example_length">
	                                				<label>
	                                					状态:<form:select path="queryDto.deletFlag"  aria-controls="dataTables-example" class="form-control input-sm">
	                                						<form:option value="">未选择</form:option>
	                                						<form:option value="0">显示</form:option>
	                                						<form:option value="1">不显示</form:option>
	                                					</form:select> 
	                                					&nbsp;&nbsp;
	                                					
	                                				</label>
	                                			</div>
	                                		</div>
	                                		<div class="col-sm-6">
	                                			<div id="dataTables-example_filter" class="dataTables_filter">
	                                				<label>
	                                					<a class="btn btn-default" href="#" id="search-btn">查询</a>
	                                					<a class="btn btn-default" href="#" id="add-btn">新增</a>
	                                				</label>
	                                			</div>
	                                		</div>
	                                	</div>
                                	</form>
                                	<table id="dataTables-example" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="dataTables-example_info">
	                                    <thead>
	                                        <tr role="row">
	                                        <th class="sorting_asc" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" style="width: 275px;" aria-sort="ascending" aria-label="Rendering engine: activate to sort column ascending">标题</th>
	                                        <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" style="width: 405px;" aria-label="Browser: activate to sort column ascending">创建时间</th>
	                                        <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" style="width: 368px;" aria-label="Platform(s): activate to sort column ascending">是否要在APP上显示</th>
	                                        <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" style="width: 368px;" aria-label="Platform(s): activate to sort column ascending"></th>
	                                    </thead>
	                                    <tbody>
	                                    <core:forEach items="${queryDto.resultList}" var="result" varStatus="status">
	                                    		<tr class="gradeA 
	                                    			<core:if test="${status.index%2==1 }">
			                                    		odd
			                                    	</core:if>
	                                    			<core:if test="${status.index%2==0 }">
	                                    				even
	                                    			</core:if>
	                                    		">
		                                            <td class="sorting_1">${result.name }</td>
		                                            <td class=" ">${result.createDate }</td>
		                                            <td class=" ">
			                                             <core:if test="${result.deleteFlag==0 }">显示 </core:if>
			                                             <core:if test="${result.deleteFlag==1 }">不显示</core:if>
		                                            </td>
		                                            <td class=" "><a class="btn btn-default update-btn" href="#" id="${result.id }">修改</a></td>
		                                        </tr>
	                                    	
	                                    </core:forEach>
	                                   </tbody>
                                	</table>
                                	${queryDto.pageString}
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
    </div>
    
    <script type="text/javascript">
    	$(function(){ 
    		//查询
    		$("#search-btn").click(function(){
    			$("#form").submit();
    		});
    		
    		//新增
			$("#add-btn").click(function(){
    			location.href="addOrEdit?id=";
    		});
    		
    		//修改
			$(".update-btn").click(function(){
				location.href="addOrEdit?id="+$(this).attr("id");
    		});
    		
    	});
    </script>
</body>
</html>