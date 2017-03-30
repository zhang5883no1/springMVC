<%@page import="cn.base.util.WXUtil"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String appId="wx625145187a33aec7";
	String appSecret="9617a6f5e383fdf664751b29440c9fe7";
	//String localUrl=request.getRequestURL().toString();
	String localUrl = "http://app.service.xiduoil.com/page/wx/page";
    if (request.getQueryString() != null){
       localUrl += "?" + request.getQueryString();
    }
	Map<String, String> map=WXUtil.initJSSDK(appId, appSecret, localUrl);
%> 

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	var shared_index=0;
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '<%=appId%>', // 必填，公众号的唯一标识
	    timestamp: <%=map.get("timestamp")%>, 
	    nonceStr: '<%=map.get("nonceStr")%>', 
	    signature: '<%=map.get("signature")%>',
	    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage']
	});
	
	
	wx.ready(function(){
		wx.onMenuShareTimeline({
		    title: '红包测试', // 分享标题
		    link: 'http://m.xxidu.com/wxhb/', // 分享链接
		    imgUrl: '', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		        if(shared_index==2){
		        	return;
		        }
		        shared_index++;
		        $(".pop").show();
		        $(".popBg").show();
		        $(".popBox").show();
		        $(".share").hide();
		        $(".shared_txt").html("还需分享"+(parseInt(3)-parseInt(shared_index))+"次");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareAppMessage({
		    title: '红包测试', // 分享标题
		    desc: '红包测试', // 分享描述
		    link: 'http://m.xxidu.com/wxhb/', // 分享链接
		    imgUrl: '', // 分享图标
		    type: 'link', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    	 if(shared_index==2){
		        	return;
		        }
		    	shared_index++;
		        $(".pop").show();
		        $(".popBg").show();
		        $(".popBox").show();
		        $(".share").hide();
		        $(".shared_txt").html("还需分享"+(parseInt(3)-parseInt(shared_index))+"次");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});

	});
</script>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0 user-scalable=no"/>
	<meta name="format-detection" content="telephone=no,email=no" />
	<link rel="stylesheet" href="http://m.xxidu.com/wxhb/css/style.css">
</head>
<body>
<div id="wrap">
	<div class="allTop">
		<img src="http://m.xxidu.com/wxhb/images/allTop.jpg" class="allTopImg">
		<img src="http://m.xxidu.com/wxhb/images/head.jpg" class="head">
	</div>
	<div class="allMid">
		<b>微信红包<img src="http://m.xxidu.com/wxhb/images/pin.jpg"></b>
		<p>恭喜发财，大吉大利！</p>
		<h3><b class="numberRun">112.00</b><i>元</i></h3>
		<a href="javascript:;"><img src="http://m.xxidu.com/wxhb/images/btn.jpg"></a>
	</div>
	<h1 class="title">共1000个红包</h1>
	<ul class="list">
		<li class="red">
			<dl>
				<dt><img src="http://m.xxidu.com/wxhb/images/head.jpg" ></dt>
				<dd>
					<h5>杨明</h5>
					<time>09:09</time>
				</dd>
			</dl>
			<p>领取了6元现金</p>
		</li>
		<li>
			<dl>
				<dt><img src="http://m.xxidu.com/wxhb/images/head.jpg" ></dt>
				<dd>
					<h5>杨明</h5>
					<time>09:09</time>
				</dd>
			</dl>
			<p>领取了5元现金</p>
		</li>
		<li>
			<dl>
				<dt><img src="http://m.xxidu.com/wxhb/images/head.jpg" ></dt>
				<dd>
					<h5>杨明</h5>
					<time>09:09</time>
				</dd>
			</dl>
			<p>领取了200元现金</p>
		</li>
		<li>
			<dl>
				<dt><img src="http://m.xxidu.com/wxhb/images/head.jpg" ></dt>
				<dd>
					<h5>杨明</h5>
					<time>09:09</time>
				</dd>
			</dl>
			<p>领取了500元现金</p>
		</li>
	</ul>
</div>
<div class="pop">
	<div class="popBg"></div>
    	<div class="popBox">
    		<p>选择菜单<span style="color:#ed3d28">“发送给朋友”</span><br />分享到任意微信群即可马上领取<br/><span class="shared_txt"></span></p>
    		<a href="javascript:;">去分享领取</a>
    	</div>
    	<img src="http://m.xxidu.com/wxhb/images/share.png" class="share">
</div>
<script type="text/javascript" src="http://m.xxidu.com/wxhb/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".allMid a").click(function(){
		$(".pop").show();
		$(".popBox").show();
		$(".share").show();
	})
	$(".popBox a").click(function(){
		$(".popBox").hide();
	})
	$(".popBg").click(function(){
		$(".pop").hide();
	})
	$('.popBg').bind("touchmove",function(e){
	            e.preventDefault();
	        });
})
</script>
<script>
(function($) {
	$.fn.numberAnimate = function(setting) {
		var defaults = {
		speed : 1000,//动画速度
		num : "", //初始化值
		iniAnimate : true, //是否要初始化动画效果
		symbol : '',//默认的分割符号，千，万，千万
		dot : 0 //保留几位小数点
	}
	//如果setting为空，就取default的值
	var setting = $.extend(defaults, setting);
	//如果对象有多个，提示出错
	if($(this).length > 1){
		alert("just only one obj!");
		return;
	}
	//如果未设置初始化值。提示出错
	if(setting.num == ""){
		alert("must set a num!");
		return;
	}
	var nHtml = '<div class="mt-number-animate-dom" data-num="{{num}}">\
	<span class="mt-number-animate-span">0</span>\
	<span class="mt-number-animate-span">1</span>\
	<span class="mt-number-animate-span">2</span>\
	<span class="mt-number-animate-span">3</span>\
	<span class="mt-number-animate-span">4</span>\
	<span class="mt-number-animate-span">5</span>\
	<span class="mt-number-animate-span">6</span>\
	<span class="mt-number-animate-span">7</span>\
	<span class="mt-number-animate-span">8</span>\
	<span class="mt-number-animate-span">9</span>\
	<span class="mt-number-animate-span">.</span>\
	</div>';
	//数字处理
	var numToArr = function(num){
		num = parseFloat(num).toFixed(setting.dot);
		if(typeof(num) == 'number'){
		var arrStr = num.toString().split("");  
	}else{
		var arrStr = num.split("");
	}
	//console.log(arrStr);
	return arrStr;
	}
	//设置DOM symbol:分割符号
	var setNumDom = function(arrStr){
	var shtml = '<div class="mt-number-animate">';
	for(var i=0,len=arrStr.length; i<len; i++){
		if(i != 0 && (len-i)%3 == 0 && setting.symbol != "" && arrStr[i]!="."){
			shtml += '<div class="mt-number-animate-dot">'+setting.symbol+'</div>'+nHtml.replace("{{num}}",arrStr[i]);
		}else{
			shtml += nHtml.replace("{{num}}",arrStr[i]);
		}
	}
	shtml += '</div>';
	return shtml;
	}
	//执行动画
	var runAnimate = function($parent){
	$parent.find(".mt-number-animate-dom").each(function() {
		var num = $(this).attr("data-num");
		num = (num=="."?10:num);
		var spanHei = $(this).height()/11; //11为元素个数
		var thisTop = -num*spanHei+"px";
		if(num == 0){
			thisTop = 0;
		}
		if(thisTop != $(this).css("top")){
			if(setting.iniAnimate){
			//HTML5不支持
			if(!window.applicationCache){
				$(this).animate({
					top : thisTop
				}, setting.speed);
			}else{
				$(this).css({
				'transform':'translateY('+thisTop+')',
				'-ms-transform':'translateY('+thisTop+')',   /* IE 9 */
				'-moz-transform':'translateY('+thisTop+')',  /* Firefox */
				'-webkit-transform':'translateY('+thisTop+')', /* Safari 和 Chrome */
				'-o-transform':'translateY('+thisTop+')',
				'-ms-transition':setting.speed/1000+'s',
				'-moz-transition':setting.speed/1000+'s',
				'-webkit-transition':setting.speed/1000+'s',
				'-o-transition':setting.speed/1000+'s',
				'transition':setting.speed/1000+'s'
				}); 
			}
		}else{
			setting.iniAnimate = true;
			$(this).css({
			top : thisTop
				});
			}
		}
	});
}
//初始化
var init = function($parent){
//初始化
$parent.html(setNumDom(numToArr(setting.num)));
	runAnimate($parent);
};
//重置参数
this.resetData = function(num){
	var newArr = numToArr(num);
	var $dom = $(this).find(".mt-number-animate-dom");
	if($dom.length < newArr.length){
		$(this).html(setNumDom(numToArr(num)));
	}else{
		$dom.each(function(index, el) {
		$(this).attr("data-num",newArr[index]);
		});
	}
	runAnimate($(this));
}
//init
init($(this));
	return this;
}
})(jQuery);
$(function(){
	var numRun3 = $(".numberRun").numberAnimate({num:'112.00', dot:2, speed:1000});
});
</script>
</body>
</html>