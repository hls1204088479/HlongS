


$(function(){
	
	var expressCompany;//快递公司
	var logisticsNumber;//物流单号
	var oDiv;
	var API_key="20c0e3e6-248e-4a9c-872a-878dea228014";
	var Application_level_message;
	var flag=true;  //只发一次请求flag
	var json;
	var Traces;
	var flag2=true; //只创建一次div
	var area;
	var text;
	var shopSelect="<div><select id='shopSelect'></select></div>";
	
	
	$("#loginErrorWrapId").after(shopSelect);
	$(AccountList).each(function(index,text){
		
			var lastIndex=localStorage.getItem("index");
			
			if(lastIndex==index){
					var list=text.split(",")
					$("#shopSelect").append("<option value="+list[0]+">【上次登陆】"+list[0]+"</option>")
			}else{
				var list=text.split(",")
				$("#shopSelect").append("<option value="+list[0]+">"+list[0]+"</option>")
			}
			
			
		
		
	});
	
	$("#loginBtnId").click(function(){
			var index=$('option:selected','#shopSelect').index();
			localStorage.setItem("index",index);
		
	});
	
		$("#shopSelect").change(function(){
			var index=$('option:selected','#shopSelect').index();
			var list=AccountList[index].split(",");
			$("#usernameId").val(list[1]);
			$("#passwordId").val(list[2]);
			$("#loginBtnId").attr("data-click",true);
			$("#loginBtnId").attr("class","pdd-btn l-c-c-c-btn");
			
		});
	$(".g-r-a-c-refund-info").children().each(function(i,n){
		
		//var span=""; 
		var button="<span>&nbsp;</span><button id='insertExcel'>插入表格</button>"; 
		var span="<span>插入成功</span>";
		var input="<div id='logisticsdiv'><input id='logistics'></input></div>";
		var select="<select id='select'><option value='zh'>追回</option><option value='qszh'>显示签收，未收到货。追回</option></select>";
		if(i==6){  //  6是 物流单号
				
				
				
				
				$(n).children().after(button);   
				$(n).children("#insertExcel").after(input);
				$("#logistics").after(select);
				
				$("#insertExcel").click(function(){
					//获取订单号
					$(".g-r-a-c-refund-order").children().each(function(i,n){
						if(i==0){
							text=$(n).children().text();
												
					}
					})
					
					//发送ajax
						$.ajax({
							url:"http://mms.pinduoduo.com/express_base/mms/track/"+text+"?"+"source=MMS_TRACK_STATUS",
							type:"GET",
							success:function(obj){
								var isSuccess=obj.success;
								
								if(isSuccess!=false){
								var Traces=obj.result.traces;
								
								for(i=0;i<Traces.length;i++){
								if(i==Traces.length-1){
									
									if(Traces.length-1==0){
										var str="";
									}else{
										var str=Traces[i-1].info;
									}
									
									
									var begin=str.lastIndexOf("【");
									
									if(begin==-1){
											area="not";
											var name=$(".g-r-a-c-r-o-a-t-left").children().text();
											var tel=$(".g-r-a-c-r-o-a-t-right").text();
											var addr=$(".g-r-a-c-r-o-a-ads").children().text();
											var status=$("#select option:selected").val();
											
											logisticsNumber=$(n).children("i").text();
											if($(n).children("i").text()==""){
												logisticsNumber=$("#logistics").val();
												alert(logisticsNumber);
											}
											//alert(name+","+tel+","+addr);
											
											var data={
												address:"姓名："+name+"，电话："+tel+"，地址："+addr,
												logisticsNumber:logisticsNumber,
												status:status,
												area:area
											}
											
											
											$.ajax({
											url:"http://127.0.0.1:8080/expressQuery/insert",
											type:"POST",
											data:data,
											success:function(obj){
												if(arguments[2].status!=200){
													$("#insertExcel").text("插入失败,服务器未打开");
													return;
												}
												
												if(obj=='101'){
													$("#insertExcel").text("插入失败,请关闭文件后重试");
												}else if(obj=='404'){
													$("#insertExcel").text("插入失败,文件未找到");
												}else{
													$("#insertExcel").remove();
													$(n).children("span").after(span);   
												}
												
											},
											error:function(){
											$("#insertExcel").text("插入失败,请关闭文件后重试");
											
											}
										})
											
										}else{
											var end=str.lastIndexOf("】");
											area=str.substring(begin+1,end);
											var name=$(".g-r-a-c-r-o-a-t-left").children().text();
											var tel=$(".g-r-a-c-r-o-a-t-right").text();
											var addr=$(".g-r-a-c-r-o-a-ads").children().text();
											var status=$("#select option:selected").val();
											
											logisticsNumber=$(n).children("i").text();
											if($(n).children("i").text()==""){
												logisticsNumber=$("#logistics").val();
												alert(logisticsNumber);
											}
											//alert(name+","+tel+","+addr);
											
											var data={
											address:"姓名："+name+"，电话："+tel+"，地址："+addr,
											logisticsNumber:logisticsNumber,
											status:status,
											area:area
										}
										
										
										$.ajax({
										url:"http://127.0.0.1:8080/expressQuery/insert",
										type:"POST",
										data:data,
										success:function(obj){
											if(arguments[2].status!=200){
												$("#insertExcel").text("插入失败,服务器未打开");
													return;
												}
											if(obj=='101'){
												$("#insertExcel").text("插入失败,请关闭文件后重试");
											}else if(obj=='404'){
												$("#insertExcel").text("插入失败,文件未找到");
											}else{
												$("#insertExcel").remove();
												$(n).children("span").after(span);   
											}
											
										},
										error:function(){
										$("#insertExcel").text("插入失败,请关闭文件后重试");
												
										}
									})
										
									}
								
									
								}
								
							}
							
						}else{
							//未查询到
								area='not';
								var name=$(".g-r-a-c-r-o-a-t-left").children().text();
								var tel=$(".g-r-a-c-r-o-a-t-right").text();
								var addr=$(".g-r-a-c-r-o-a-ads").children().text();
								var status=$("#select option:selected").val();
								
								logisticsNumber=$(n).children("i").text();
								if($(n).children("i").text()==""){
									logisticsNumber=$("#logistics").val();
									alert(logisticsNumber);
								}
								//alert(name+","+tel+","+addr);
								
								var data={
								address:"姓名："+name+"，电话："+tel+"，地址："+addr,
								logisticsNumber:logisticsNumber,
								status:status,
								area:area
							}
							
							
							$.ajax({
							url:"http://127.0.0.1:8080/expressQuery/insert",
							type:"POST",
							data:data,
							success:function(obj){
								if(arguments[2].status!=200){
									$("#insertExcel").text("插入失败,服务器未打开");
									return;
									}
								if(obj=='101'){
									$("#insertExcel").text("插入失败,请关闭文件后重试");
								}else if(obj=='404'){
									$("#insertExcel").text("插入失败,文件未找到");
								}else{
									$("#insertExcel").remove();
									$(n).children("span").after(span);   
								}
								
							},
							error:function(){
							$("#insertExcel").text("插入失败,请关闭文件后重试");
									
							}
						})
						}
						}
				})
					
				
				})
				$(n).children("i").click(function(){
					$(oDiv).show();
					$(n).children().unbind('mouseout');
				})
				$(n).children().mouseout(function(){
					$(oDiv).hide();
				})
				
				$(n).children("i").mouseover(function(e){
				
				$(oDiv).show();
				if(flag2){
					logisticsNumber=$(n).children("i").text();
					expressCompany=logisticsNumber.substring(0,2);
					var oEvent=e||event;
					oDiv=document.createElement('div');
					oDiv.style.left=oEvent.clientX+100+'px';  // 指定创建的DIV在文档中距离左侧的位置
					oDiv.style.top=(oEvent.clientY-200)+'px';  // 指定创建的DIV在文档中距离顶部的位置
					oDiv.style.border='1px solid #8B7D6B'; // 设置边框
					oDiv.style.position='absolute'; // 为新创建的DIV指定<a href="https://www.baidu.com/s?wd=%E7%BB%9D%E5%AF%B9%E5%AE%9A%E4%BD%8D&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YYmhwbnvc1ujfdPARdn1wb0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnWcsnH6kPHT4rjnvrjDsPj0vr0" target="_blank" class="baidu-highlight">绝对定位</a>
					oDiv.style.width='400px'; // 指定宽度
					oDiv.style.height='600px'; // 指定高度
					oDiv.style.background='white';
					//oDiv.style.overflow-y='scroll';
					$(oDiv).css('overflow-y','scroll');
					
					
				}
				
				
				$(oDiv).click(function(){
					$(this).hide();
						$(n).children().mouseout(function(){
					$(oDiv).hide();
				})
					
				})
				
				
				
				if(expressCompany=='98'){
					expressCompany="EMS";

				}
				if(expressCompany=='33'){
					expressCompany="STO";

				}
				if(expressCompany=='38'){
					expressCompany="YD";

				}


				Application_level_message="{'ShipperCode':'" + expressCompany + "','LogisticCode':'" + logisticsNumber + "'}";
				
				var md5App=hex_md5(Application_level_message+API_key);
				var b64=new Base64();
				var b64App=b64.encode(md5App);
				var DataSign=escape(b64App);
				var RequestData=escape(Application_level_message);
				var data={
					expressCompany:expressCompany,
					logisticsNumber:logisticsNumber
				};
				//expressCompany=EMS&logisticsNumber=9891021409401
				if(flag){
					//获取订单号
					$(".g-r-a-c-refund-order").children().each(function(i,n){
						if(i==0){
							text=$(n).children().text();
						
					}
					})
					$.ajax({
					url:"http://mms.pinduoduo.com/express_base/mms/track/"+text+"?"+"source=MMS_TRACK_STATUS",
					type:"GET",
				//	data:data,
				//	dataType:'jsonp',
				//	jsonpCallback:"showData", //指定回调函数名称
					success:function(obj){
						var Traces=obj.result.traces;
						for(i=0;i<Traces.length;i++){
							$(oDiv).css('border-style','solid');
							$(oDiv).css('border-width','1px');
							
							$(oDiv).append("<div ><font color='red' size='2px' border='1' > "+Traces[i].time+"</font></div>");
							$(oDiv).append("<div ><font  size='2px' border='1' >"+Traces[i].info+"</font></div>");
							
						}
							document.body.appendChild(oDiv);
						flag2=false;
					}
				})
				}
			
				
			flag=false;
			});


		}



	});


	
})