<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<%@ include file="/webpage/include/echarts.jsp"%>
	<meta name="decorator" content="ani"/>
	<style>
		.td_info span{
			cursor: pointer;
			color: dodgerblue;
		}
		#div{
			margin: 0 auto ;
			height: 1000px;
			width: 90%;
		}
		#top{
			height: 20%;
			width: 100%;
		}
		#middle{
			height: 40%;
			width: 100%;
		}
		#middle_left{
			border: 1px solid;
			height: 100%;
			width: 49%;
			float: left;

		}
		#middle_left_query{
			height: 15%;
			width: 100%;
		}
		#middle_left_chart{
			height: 85%;
			width: 100%;
		}
		#middle_right{
			margin-left: 10px;
			height: 100%;
			width: 50%;
			float: left;
			border: 1px solid ;
		}
		#middle_query{
			height: 15%;
			width: 100%;
		}
		#middle_chart{
			height: 85%;
			width: 100%;
		}
		#buttom{
			margin-top: 20px;
			height: 40%;
			border: 1px solid;
			width: 100%;
		}
		#buttom_query{
			height: 15%;
			width: 70%;
			margin: 0 auto;
		}
		#buttom_chart{
			height: 85%;
			width: 70%;
			margin: 0 auto;
		}


		.box{
			width: 100%;
			height: 295px;
			margin: 10px auto;
		}

		.item{
			overflow: hidden;
		}

		.item>span{
			float: left;
			width: 120px;
			text-align: center;
			font-size:14px;
			color: #666;
		}

		.item>div{
			float: left;
		}

		.item p{
			height: 100px;
			margin: 0 auto;
			text-align: center;
			color: #fff;
			max-height: 50px;
			position: relative;
		}

		.lftriangle,.rgtriangle{
			position: absolute;
			width:0;
			height:0;
			top:0;
		}

		.lftriangle{
			left: 0;
			border-top:20px solid transparent;
			border-bottom:20px solid transparent;
			border-left:20px solid #fff;
			/*transform: rotate(-135deg);*/
		}

		.rgtriangle{
			right: 0;
			border-top:20px solid transparent;
			border-bottom:20px solid transparent;
			border-right:20px solid #fff;
			/*transform: rotate(-45deg);*/
		}

		.middle_query_title{
			width: 100%;
			height: 20px;
		}

		.middle_query_title div{
			width: 100px;
			margin: 0 auto;
			font-size: 18px;
			font-weight: bold;+
		}

		.lastTri{
			width: 200px;
			height: 50px !important;
			line-height: 50px  !important;
		}

		.lastTri .lftriangle{
			border-width: 110px !important;
			transform: rotate(-17deg);
			top: -40px;
		}

		.lastTri .rgtriangle{
			border-width: 110px !important;
			transform: rotate(17deg);
			top: -40px;
		}


	</style>
	<script>
		var myChart;//销售漏斗
		var gradeChart;//业绩排名
		var achievementChart;//绩效目标
        var option = {
            title: {
                text: '销售漏斗图',
                left:'left'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : ￥{c}万"
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid:{
                top:'25%',//距上边距
                left:'25%',//距离左边距
                right:'25%',//距离右边距
                bottom:'25%',//距离下边距

            },
            series: [{
                name: '销售漏斗图',
                type: 'funnel',
                left: '0',
                top: '15%',
                //x2: 80,

                width: '100%',
                // height: {totalHeight} - y - y2,
                min: 0,
                max: 100,
                minSize: '0%',
                maxSize: '100%',
                sort: 'descending',
                gap: 2,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    },
                    emphasis: {
                        textStyle: {
                            fontSize: 14
                        }
                    }
                },
                labelLine: {
                    normal: {
                        length: 10,
                        lineStyle: {
                            width: 1,
                            type: 'solid'
                        }
                    }
                },
                itemStyle: {
                    normal: {

                        borderColor: '#fff',
                        borderWidth: 1
                    }
                },
                data: [
                ]

            }]
        };


        var option1 = {
            title: {
                text: '业绩目标',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:['目标','完成']
            },
            series: [
                {
                    name:'商机赢单金额',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:335, name:'目标'},
                        {value:310, name:'完成'},
                    ]
                }
            ]
        };


        var option2 = {
            title: {
                text: '业绩排名',
                left: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            },
            yAxis: {
                type: 'category',
                data: [],
                splitLine: {show: false},
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                offset: 10,
                nameTextStyle: {
                    fontSize: 15
                }
            },
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    data: [],
                    barWidth: 15,
                    barGap: 0,
                    barCategoryGap:5,
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            offset: [5, -2],
                            textStyle: {
                                color: '#F68300',
                                fontSize: 13
                            }
                        }
                    },
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: 7
                        },
                        normal: {
                            barBorderRadius: 7,
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 1, 0,
                                [
                                    {offset: 0, color: '#3977E6'},
                                    {offset: 1, color: '#37BBF8'}

                                ]
                            )
                        }
                    }
                }
            ]
        };



        function initChart(){
           /* myChart=echarts.init(document.getElementById('middle_chart'));
            myChart.setOption(option);*/

            gradeChart=echarts.init(document.getElementById('buttom_chart'));
			gradeChart.setOption(option2);

			achievementChart=echarts.init(document.getElementById('middle_left_chart'));
            achievementChart.setOption(option1);
		}

        $(function(){
            $("#userId").val($(".id_hidden").val());
            $("#userName").val($(".name_hidden").val());
            $("#user1Id").val($(".id_hidden").val());
            $("#user1Name").val($(".name_hidden").val());
            $("#user2Id").val($(".id_hidden").val());
            $("#user2Name").val($(".name_hidden").val());
		    query();
		    initChart();
            queryPeriod1();
		    queryTarget1();
            queryAchievement();

		})

		function query(){
            //var index = jp.loading();
            jp.post("${ctx}/statistics",$('#inputForm').serialize(),function(data){
                if(data.success){
                    //jp.success(data.msg);
                    $(".customerTotal").text(data.body.statistics.customerNum);
                    $(".contactTotal").text(data.body.statistics.customerContactNum);
                    $(".clueInfoTotal").text(data.body.statistics.clueInfoNum);
                    $(".businessChanceTotal").text(data.body.statistics.businessChanceNum);
                    $(".orderAmount").text(data.body.statistics.orderAmount);
					$(".receiveAmount").text(data.body.statistics.receiveAmount);
                }else{
                    //alert(data);
                    jp.error(data.msg);
                }
            })

		}

        function queryAchievement(){
            jp.post("${ctx}/sys/firstPage/statisticsAchievement",$('#inputForm0').serialize(), function (data) {
                var legend=["目标","完成"];
                var datas=[];
                var str1={value:data[0].achieveAmount,name:'完成'}
                var str2={value:data[0].targetAmount,name:'目标'}
                datas.push(str1);
                datas.push(str2);
                achievementChart.setOption({
                    series:[
                        {
                            name:data[0].label,
                            data:datas
                        }
                    ]
                })

            })


        }

        /**
		 * 获取销售漏斗
         */
        function queryPeriod1(){
            jp.post("${ctx}/sys/firstPage/statisticsPeriod",$('#inputForm1').serialize(), function (datas) {
                var data=[];
                $.each(datas, function(i, item){
                    var str=  {
                        value: item.amount
                        , name: item.businessChancePeriod.name+"("+item.count+"单)"

                    }
                    data.push(str);
                });
                var color = ['#f80','#fd545c','#3bb4c1','#f08','#1c4b82','#f17e7e','#f8b739',"#7fe7cc",'#bf9fee','#fcd307','#1c4b82','#f17e7e',]
                var val = []

                for(var i=0;i<data.length;i++){
                    val.push(data[i].value)
                }
                var max = Math.max.apply(Math,val)


                let template = "<div class='item'><span>{{name}}</span><div>"+
                    "<p><i class='lftriangle'></i><i class='rgtriangle'></i><span>￥{{value}}万元</span></p></div></div>"

                let str =""

                for(var i=0;i<data.length;i++){
                    var per = parseFloat(data[i].value/max).toFixed(2)
                    data[i].percentage = per>0.25?per:0.25
                    var strs = template.replace('{{name}}',data[i].name).replace('{{value}}',data[i].value/10000)
                    str+=strs
                }

                document.querySelector('.box').innerHTML= str
                let el = document.querySelectorAll('.item')
                let item = document.querySelectorAll('item')
                let baseHeight = $('.box').height() - 50


                // for(let i=0;i<el.length;i++){
                //     $(el[i]).height(parseInt(baseHeight/el.length))
                //     $(el[i]).css('lineHeight',$(el[i]).height()+'px')
                //     let pEl = $(el[i]).find('p');
                //     pEl.width($(el[i]).find('div').width()*data[i].percentage)
                //     pEl.css('backgroundColor',color[i])
                //     pEl.height(($(el[i]).height()-8)+'px')
                //     pEl.css('lineHeight',(pEl.height())+'px')
                //     pEl.css('marginTop',($(el[i]).height()-pEl.height())/2+'px')
                //
                // }
                //
                // let p = $(el[el.length-1]).find('p')
                // $('.lftriangle,.rgtriangle').css('borderWidth',p.height()*1+'px')
                // p.width(p.height()*4)


                for(let i=0;i<el.length;i++){
                    $(el[i]).height(parseInt(baseHeight/el.length))
                    $(el[i]).css('lineHeight',$(el[i]).height()+'px')
                    $(el[i]).find('div')[0].style.width = ($(el[i]).width()-120)+'px'
                    let pEl = $(el[i]).find('p');
                    if(i!=el.length-1){
                        pEl.width($(el[i]).find('div').width()*data[i].percentage)
                    }
                    pEl.css('backgroundColor',color[i])
                    pEl.height(($(el[i]).height()-8)+'px')
                    pEl.css('lineHeight',(pEl.height())+'px')
                    pEl.css('marginTop',($(el[i]).height()-pEl.height())/2+'px')

                }

                let p = $(el[el.length-1]).find('p')
                let iEl =$('.lftriangle,.rgtriangle')
                iEl.css('borderWidth',p.height()*1+'px')
                p.addClass('lastTri')

                $(el[el.length-1]).height(56+'px')
                $(el[el.length-1]).css('liheHeight',55+'px')



            })


        }


        function queryPeriod(){
            jp.post("${ctx}/sys/firstPage/statisticsPeriod",$('#inputForm1').serialize(), function (data) {
                var legends=[];
                var datas=[];
                var colors=['palevioletred','dodgerblue','limegreen','indianred','orange']
                $.each(data, function(i, item){
                    legends.push(item.businessChancePeriod.name+"("+item.count+"单)");
					var str=  {
					    value: item.amount
						, name: item.businessChancePeriod.name+"("+item.count+"单)"
						,itemStyle: {
							normal: {
								color: colors[i]
							}
					    }
					}
					datas.push(str);
                });
                myChart.setOption({
					legend:{
                       /* left: 'left',
                        orient: 'vertical',
                        top: '15%',
						right:'15%',*/
					    data:legends
					},
                    series:[{
                        data:datas
					}]
				})

            })


		}

        function queryTarget1(){
            var queryTarget=$("#inputForm3 #queryTarget").val();
            jp.post("${ctx}/sys/firstPage/statisticsTarget",$('#inputForm3').serialize(), function (data) {
                var category=[];
                var barData=[];
                $.each(data, function(i, item){
                    var str=[];
                    category.push(item.name);
                    barData.push(item.count);
                });
                var title;
                if(queryTarget=='2'||queryTarget=='3'||queryTarget=='4'){
                    title="数量";
                }else{
                    title="金额";
                }
                gradeChart.setOption({
                    yAxis:{
                        data:category
					},
					series:[
						{
						    name:title,
						    data:barData
						}
					]
                })

            })


        }

        function openNewWindow(url, name, type) {
            var officeId = $("#officeId").val();
            var userId = $("#userId").val();
            if (userId == $(".id_hidden").val()) {
                if ("customer" == type) {
                    url = "${ctx}" + url + "?sales.id=" + userId;
                } else if("customerContact"==type){
                    url = "${ctx}" + url + "?customerInfo.sales.id=" + userId;
				}else if("clueInfo"==type){
                    url = "${ctx}" + url + "?sales.id=" + userId;
				}else if("businessChance"==type){
                    url = "${ctx}" + url + "?user.id=" + userId;
				}else if("order"==type){
                    url = "${ctx}" + url + "?sales.id=" + userId;
				}else if("paymentReceive"==type){
					url = "${ctx}" + url + "?contract.salesUser.id=" + userId;
				}
                jp.closeByName(name);
                jp.openTab(url, name, false);
                // top.refreshOpenedTab(url);
            }

        }

	</script>
	<script type="text/javascript">

        var selectCustomer=function(){
            top.layer.open({
                type: 2,
                area: ['850px', '600px'],
                title:"选择用户",
                maxmin: true, //开启最大化最小化按钮
                content: "${ctx}/sys/user/userList" ,
                btn: ['确定', '关闭'],
                yes: function(index, layero){
                    var userid = layero.find("iframe")[0].contentWindow.userid;
                    var username = layero.find("iframe")[0].contentWindow.username;
                    $(".userid").val(userid);
                    $(".username").val(username);
                    top.layer.close(index);
                },
                cancel: function(index){
                }
            });
        }
	</script>
</head>
<body class="bg-white">

		<div id="div">
			<div id="top">
				<form:form id="inputForm" modelAttribute="queryEntity" class="form-horizontal">
					<table  class="table ">
						<thead>
							<tr>
								<th colspan="7" style="text-align: center;font-size: 18px;">销售简报</th>
							</tr>
						</thead>
						<tr>
							<td style="width: 5%"><label class="pull-right">时间:</label></td>
							<td class="width-15" >
								<form:select path="queryDate" class="form-control required" cssStyle="width: 200px;">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td style="width: 5%"><label class="pull-right">部门:</label></td>
							<td class="width-15" >
								<sys:treeselect id="office" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
							</td>
							<td style="width: 5%"><label class="pull-right">用户:</label></td>
							<td class="width-15">
								<sys:onlyuserselect id="user" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
													allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
								<input class="id_hidden" value="${fns:getUser().id}" type="hidden">
								<input class="name_hidden" value="${fns:getUser().name}" type="hidden">
							</td>
							<td><input type="button" value="查询" onclick="query()"></td>
						</tr>
					</table>
				</form:form>
				<table class="table table-bordered">
					<tr>
						<td class="width-15">新增线索</td>
						<td class="width-15">新增商机</td>
						<td class="width-15">新增客户数</td>
						<td class="width-15">新增联系人</td>
						<td class="width-15">新增拜访</td>
						<td class="width-15">新增订单交易额</td>
						<td class="width-15">新增客户回款金额</td>
					</tr>
					<tr class="td_info">
						<td><span class="clueInfoTotal" onclick="openNewWindow('/clueinfo/clueInfo','线索列表', 'clueInfo')"></span></td>
						<td><span class="businessChanceTotal" onclick="openNewWindow('/businesschance/businessChance','商机列表', 'businessChance')"></span></td>
						<td><span class="customerTotal" onclick="openNewWindow('/customerinfo/customerInfo','客户列表', 'customer')"></span></td>
						<td><span class="contactTotal" onclick="openNewWindow('/customermanage/contact/customerContact','联系人列表', 'customerContact')"></span></td>
						<td>-</td>
						<td><span class="orderAmount" onclick="openNewWindow('/order/order','订单列表', 'order')"></span></td>
						<td><span class="receiveAmount" onclick="openNewWindow('/paymentreceive/paymentReceive','回款列表', 'paymentReceive')"></span></td>
					</tr>
				</table>
			</div>
			<div id="middle">
				<div id="middle_left">
					<div id="middle_left_query">
						<form:form id="inputForm0" modelAttribute="queryEntity" class="form-horizontal">
							<table style="margin-top: 10px;">
								<tr>
									<td style="width: 5%"><label class="pull-right">时间:</label></td>
									<td class="width-15" >
										<form:select path="queryAchievementDate" class="form-control required" cssStyle="width: 200px;">
											<form:option value="" label=""/>
											<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
										</form:select>
									</td>
									<td style="width: 5%"><label class="pull-right">部门:</label></td>
									<td class="width-15" >
										<sys:treeselect id="office1" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
														title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
									</td>
									<td style="width: 5%"><label class="pull-right">用户:</label></td>
									<td class="width-15">
										<sys:onlyuserselect id="user1" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
															allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
									</td>
									<td style="width: 5%"><label class="pull-right">指标:</label></td>
									<td class="width-15">
										<form:select path="queryTarget" class="form-control ">
											<form:option value="1" label="赢单金额"/>
											<form:option value="2" label="销售订单"/>
										</form:select>
									</td>
									<td class="width-15" style="padding-left: 20px;"><input type="button" value="查询" onclick="queryAchievement()"></td>
								</tr>
							</table>
						</form:form>
					</div>
					<div id="middle_left_chart">

					</div>
				</div>
				<div id="middle_right">
					<div id="middle_query">
						<form:form id="inputForm1" modelAttribute="queryEntity" class="form-horizontal">
							<table style="margin-top: 10px;">
								<tr>
									<td style="width: 5%"><label class="pull-right">部门:</label></td>
									<td class="width-15" >
										<sys:treeselect id="office2" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
														title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
									</td>
									<td style="width: 5%"><label class="pull-right">用户:</label></td>
									<td class="width-15">
										<sys:onlyuserselect id="user2" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
															allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
									</td>
									<td class="width-15" style="padding-left: 20px;"><input type="button" value="查询" onclick="queryPeriod1()"></td>
								</tr>
							</table>
						</form:form>
					</div>
					<div class="middle_query_title"><div>销售漏斗</div></div>
					<div id="box" class="box">
						<div class="item">
							<span>苹果</span>
							<div>
								<p></p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="buttom">

				<div id="buttom_query">
					<form:form id="inputForm3" modelAttribute="queryEntity" class="form-horizontal">
						<table style="margin-top: 10px;">
							<tr>
								<td style="width: 5%"><label class="pull-right">时间:</label></td>
								<td class="width-15" >
									<form:select path="queryTargetDate" class="form-control required" cssStyle="width: 200px;">
										<form:option value="" label=""/>
										<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
								</td>
								<td style="width: 5%"><label class="pull-right">范围:</label></td>
								<td class="width-15" >
									<form:select path="queryScope" class="form-control ">
										<form:option value="1" label="个人"/>
										<form:option value="2" label="公司"/>
										<form:option value="3" label="部门"/>
									</form:select>
								</td>
								<td style="width: 5%"><label class="pull-right">指标:</label></td>
								<td class="width-15">
									<form:select path="queryTarget" class="form-control ">
										<form:option value="2" label="商机数"/>
										<form:option value="1" label="销售额"/>
										<form:option value="3" label="线索数"/>
										<form:option value="4" label="客户数"/>
										<form:option value="5" label="合同金额"/>
									</form:select>
								</td>
								<td class="width-15" style="padding-left: 20px;"><input type="button" value="查询" onclick="queryTarget1()"></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div id="buttom_chart"></div>
			</div>
		</div>


</body>
</html>