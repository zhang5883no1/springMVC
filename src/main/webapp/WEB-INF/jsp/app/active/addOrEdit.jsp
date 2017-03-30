<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>active list</title>

  <!-- TABLE STYLES-->
<link href="${appStaticContent}assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<!-- datepicker  -->
<link rel="stylesheet" type="text/css" href="${datepickerStaticContent}jquery.datetimepicker.css"/>
  
  <!-- editor -->
   <script type="text/javascript" charset="utf-8" src="${editorStaticContent}ueditor.config.js"></script>
   <script type="text/javascript" charset="utf-8" src="${editorStaticContent}ueditor.all.min.js"> </script>
   <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
   <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
   <script type="text/javascript" charset="utf-8" src="${editorStaticContent}lang/zh-cn/zh-cn.js"></script>
   
   <style type="text/css">  
        div{  
            width:100%;  
        }  
    </style>  
    
</head>
<body>
	<div id="page-inner">
		 <div class="row">
                   <div class="col-md-12">
                       <h1 class="page-header">
                          		热门活动 
                       </h1>
                   </div>
            </div> 
                <!-- /. ROW  -->
             <div class="row">
               <div class="col-lg-12">
                   <div class="panel panel-default">
                       <div class="panel-heading">
                          
                       </div>
                       <div class="panel-body">
                           <div class="row">
                               <div class="col-lg-6">
                                   <form role="form" action="saveOrUpdate" method="post" enctype="multipart/form-data" id="form">
                                   		<form:hidden path="dto.id"/>
                                       <div class="form-group">
                                           <label>活动标题</label>
                                           <form:input path="dto.title" placeholder="Enter text" class="form-control" />
                                       </div>
                                       <div class="form-group">
                                           <label>活动状态</label>
                                           <form:select path="dto.status" class="form-control">
                                               <form:option value="0">未开始</form:option>
                                               <form:option value="1">已开始</form:option>
                                               <form:option value="2">已结束</form:option>
                                           </form:select>
                                       </div>
                                       <div class="form-group">
                                           <label>活动参与日期</label>
                                           <form:input path="dto.activeTimeString" placeholder="Enter text" class="form-control"/>
                                       </div>
                                       <div class="form-group">
                                           <label>排序</label>
                                           <form:input path="dto.sortNo" placeholder="Enter text" class="form-control"/>
                                       </div>
                                       <div class="form-group">
                                           <label>活动类型</label>
                                            <form:select path="dto.type" class="form-control">
                                               <form:option value="1">链接</form:option>
                                               <form:option value="2">图文</form:option>
                                           </form:select>
                                       </div>
                                       <div class="form-group">
                                            <label>活动图片上传</label>
                                            <input type="file" name="file" value="${dto.picUrl }"/>
                                            <core:if test="${dto.picUrl!=null && dto.picUrl!='' }">
	                                            <img src="${dto.picUrl }" />
                                            </core:if>
                                        </div>
                                        <div class="form-group">
                                            <label>活动详情</label>
                                            <div style="display:none">
											   <form:textarea rows="3" class="form-control" path="dto.content" id="dtoContent"></form:textarea>
											</div>
											
											<script id="editor" type="text/plain" style="width:890px;height:500px;">
											     
                                            </script>
                                        </div>
                                       <input class="btn btn-default" id="dosubmit" value="提交" type="button"/>
                                       <button class="btn btn-default" type="button" id="back-btn">返回</button>
                                   </form>
                               </div>
                           </div>
                           <!-- /.row (nested) -->
                       </div>
                       <!-- /.panel-body -->
                   </div>
                   <!-- /.panel -->
               </div>
               <!-- /.col-lg-12 -->
           </div>
	</div>
	
	<!-- datepicker -->
	<script src="${datepickerStaticContent}jquery.js"></script>
	<script src="${datepickerStaticContent}build/jquery.datetimepicker.full.js"></script>
	<script>
    $( "#activeTimeString" ).datetimepicker({
        lang:"ch",           //语言选择中文
        format:"Y-m-d H:i",      //格式化日期
        timepicker:true,    //关闭时间选项
        yearStart:2000,     //设置最小年份
        yearEnd:2050,        //设置最大年份
        todayButton:true    //关闭选择今天按钮
  	});
    
    $("#back-btn").click(function(){
    	history.go(-1);
    });
  </script>
  
  <script type="text/javascript">  
  
    //实例化编辑器  
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例  
    var ue = UE.getEditor('editor'); 
	
	function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
	</script>
	
	
	<script type="text/javascript">
	   
	
	   $(function(){
            $("#editor").html($("#dtoContent").val());
			
			
			$("#dosubmit").click(function(){
				$("#dtoContent").val(UE.getEditor('editor').getContent());
				$("#form").submit();
			});
	   });
	</script>
</body>
</html>