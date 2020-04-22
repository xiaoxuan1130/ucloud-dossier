<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/echarts.jsp"%>
	<script type="text/javascript" src="${ctxStatic}/plugin/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plugin/highcharts/funnel.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plugin/highcharts/highcharts-more.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plugin/highcharts/export-data.js"></script>
	<%@ include file="firstPage.js"%>
	<style>
		*{
			padding: 0;margin: 0;
		}
		body{
			background-color: #f5f5f5
		}
		.demo{width:60%;margin:50px auto 10px auto;padding:10px;position: relative;}
		/*.main_con{*/
			/*min-width: 1600px;*/
		/*}*/
		.content{
			display: flex;
			flex-direction: row;
			justify-content: center;
			margin-top: .4625rem;
		}
		.div_s{
			width: 100%;
			height: 33rem;
			background-color: #fff;
			margin-left: .625rem;
			position: relative;
		}
		.line{
			width: .2125rem;
			height: .75rem;
			background-color: blue;
			margin-top: .75rem;
			margin-left: 1.25rem;
			display: inline-block;
		}
		.xs{
			margin-top: .75rem;
			margin-left: .625rem;
			font-size: 1.55rem;
		}
		.seach{
			width: 100%;
			height: 50px;
			margin: .625rem auto;
			display: flex;
			flex-direction: row;
			justify-content: space-between;
           /* display: none;*/
			position: relative;
		}
		.seach_x{
			width: 35.875rem;
			height: 1.875rem;
			margin-top: .625rem;
			margin-left: 1.25rem;
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			/*display: none;*/
		}
		.time_select{
			width: 11.25rem;
			height: 2rem;
		}
		.input_t{
			width: 11.25rem;
			height: 1.475rem;
			outline:none;
		}
		.input_ty{
			width: 8.25rem;
			height: 1.475rem;
			outline:none;
		}
		/*.btn{*/
			/*width: 3.125rem;*/
			/*height: 1.575rem;*/
			/*background-color: #3899D9;*/
			/*border-radius: .125rem;*/
			/*text-align: center;*/
			/*line-height: 1.575rem;*/
			/*color: #fff;*/
		/*}*/
		.main{
			display: flex;
			flex-direction: row;
			justify-content: space-around;
			margin-top: 50px;
		}
		.d1{
			width: 23.875rem;
			height: 15.625rem;
			margin-top: 1.25rem;
		}
		.d2{
			width: 100%;
			height: 3.125rem;
			background-color: #f2f2f2;
			margin: 1.25rem auto;
			display: flex;
			align-content: center;
			justify-content: space-around;
		}
		.d3{
			width: 100%;
			height: 3.125rem;
			display: flex;
			align-content: center;
		}
		.img{
			width: 2.3rem;
			height: 2.3rem;
			border-radius: 50%;
			margin-left: .5rem;
			margin-top: .3125rem;
		}
		.txt{
			text-align: center;
			line-height: 3.125rem;
			margin-left: .5rem;
			font-size: 1.55rem;
		}
		.rigin_txt{
			text-align: center;
			line-height: 2.5rem;
			margin-right: 1.25rem;
		}
		.main_s{
			width: 20%;
			height: 20px;
			background-color: #fff;
			position: absolute;
			top:270px;
			left: 360px;
		}
		.tar_box{
			width: 400px;
			height: 100px;
			margin: -65px auto;
			display: flex;
			flex-direction: row;
			justify-content: space-around;
		}
		.bo_d{
			width: 40%;
			height: 100px;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
		/*.time_style{*/
			/*position: absolute;*/
			/*top: 13px;*/
			/*left: -40px;*/
			/*font-size: 18px;*/
		/*}*/
		/*.dep_style{*/
			/*position: absolute;*/
			/*top: 13px;*/
			/*left: 130px;*/
			/*font-size: 18px;*/
		/*}*/
		/*.dep_input{*/
			/*margin-left: 50px;*/
			/*margin-top: 10px;*/
		/*}*/
		/*.user_style{*/
			/*position: absolute;*/
			/*top: 13px;*/
			/*left: 370px;*/
			/*font-size: 18px;*/
		/*}*/
		/*.user_input{*/
			/*margin-left: 50px;*/
			/*margin-top: 10px;*/
		/*}*/
		/*.btn_sy{*/
			/*position: relative;*/
		/*}*/
		/*.btn_query{*/
			/*position: absolute;*/
			/*right: -70px;*/
			/*top: 12px;*/
			/*width: 50px;*/
			/*height: 30px;*/
		/*}*/
		/*.user_style_ye{*/
			/*position: absolute;*/
			/*top: 13px;*/
			/*left: 270px;*/
			/*font-size: 18px;*/
		/*}*/
		/*.user_style_zb{*/
			/*position: absolute;*/
			/*top: 13px;*/
			/*left: 420px;*/
			/*font-size: 18px;*/
		/*}*/
	</style>
</head>
<div class="main_con">
	<div class="content">
		<div class="div_s">
			<div class="line"></div><span class="xs">销售简报</span>
			<div class="seach">
				<form:form id="inputForm" modelAttribute="queryEntity" class="form-horizontal">
					<table  class="table ">
						<tr>
							<td style="width: 7%"><label class="pull-right" style="margin-top: 5px;">时间:</label></td>
							<td style="width: 15%" >
								<form:select path="queryDate" class="form-control required" cssStyle="width: 150px;">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td style="width: 7%"><label class="pull-right" style="margin-top: 5px;">部门:</label></td>
							<td style="width: 23%" >
								<sys:treeselect id="office" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
							</td>
							<td style="width: 7%"><label class="pull-right" style="margin-top: 5px;">用户:</label></td>
							<td style="width: 23%" >
								<sys:onlyuserselect id="user" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
													allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
								<input class="id_hidden" value="${fns:getUser().id}" type="hidden">
								<input class="name_hidden" value="${fns:getUser().name}" type="hidden">
							</td>
							<td><input type="button" value="查询" onclick="query()" style="margin-top: 2px;width: 50px;height: 30px;"></td>
						</tr>
					</table>
				</form:form>
			</div>


			<div class="main">
				<div class="d1">
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/kehu.png" class="img">
							<span class="txt">新增客户</span>
						</div>
						<div>
							<span id="kehu" class="rigin_txt customerTotal" onclick="openNewWindow('/customerinfo/customerInfo','客户列表', 'customer')"></span>
						</div>
					</div>
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/shangji.png" class="img">
							<span class="txt" >新增商机</span>
						</div>
						<div>
							<span id="shangji" class="rigin_txt businessChanceTotal" onclick="openNewWindow('/businesschance/businessChance','商机列表', 'businessChance')"></span>
						</div>
					</div>
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/hetong.png" class="img">
							<span class="txt">新增合同</span>
						</div>
						<div>
							<span id="hetong" class="rigin_txt contactTotal" onclick="openNewWindow('/customermanage/contact/customerContact','联系人列表', 'customerContact')"></span>
						</div>
					</div>
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/huikuan.png" class="img">
							<span class="txt">新增回款</span>
						</div>
						<div>
							<span id="huikuan" class="rigin_txt receiveAmount"  onclick="openNewWindow('/paymentreceive/paymentReceive','回款列表', 'paymentReceive')"></span>
						</div>
					</div>
				</div>
				<div class="d1">
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/lianxiren.png" class="img">
							<span class="txt">新增联系人</span>
						</div>
						<div>
							<span id="lianxiren" class="rigin_txt contactTotal" onclick="openNewWindow('/customermanage/contact/customerContact','联系人列表', 'customerContact')"></span>
						</div>
					</div>
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/bianhua.png" class="img">
							<span class="txt">新增线索</span>
						</div>
						<div>
							<span id="bianhua" class="rigin_txt clueInfoTotal" onclick="openNewWindow('/clueinfo/clueInfo','线索列表', 'clueInfo')"></span>
						</div>
					</div>
					<div class="d2">
						<div class="d3">
							<img src="${ctxStatic}/common/images/firstpage/jilu.png" class="img">
							<span class="txt">新增订单交易额</span>
						</div>
						<div>
							<span id="jilu" class="rigin_txt orderAmount" onclick="openNewWindow('/order/order','订单列表', 'order')"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="div_s">
			<div class="line"></div><span class="xs">业绩目标</span>
			<div class="seach">
				<form:form id="inputForm0" modelAttribute="queryEntity" class="form-horizontal">
					<table style="margin-top: 10px;">
						<tr>
							<td style="width: 5%"><label class="pull-right" style="margin-right: 5px;">时间:</label></td>
							<td class="width-15" >
								<form:select path="queryAchievementDate" class="form-control required" cssStyle="width: 120px;">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td style="width: 7%"><label class="pull-right" style="margin-right: 5px;">部门:</label></td>
							<td class="width-15" style="width: 18%">
								<sys:treeselect id="office1" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
							</td>
							<td style="width: 7%"><label class="pull-right" style="margin-right: 5px;">用户:</label></td>
							<td class="width-15" style="width: 18%">
								<sys:onlyuserselect id="user1" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
													allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
							</td>
							<td style="width: 7%"><label class="pull-right" style="margin-right: 5px;">指标:</label></td>
							<td class="width-15">
								<form:select path="queryTarget" class="form-control ">
									<form:option value="1" label="赢单金额"/>
									<form:option value="2" label="销售订单"/>
								</form:select>
							</td>
							<td class="width-15" style="padding-left: 20px;"><input type="button" style="margin-top: 2px;width: 50px;height: 30px;" value="查询" onclick="queryAchievement()"></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="container1" style="width: 80%; height: 70%;margin: 0 auto;"></div>
			<div class="tar_box">
				<div class="bo_d">
					<div style="font-size:14px;color:#666">业绩目标（万元）</div>
					<div style="font-size:26px;color:#333;font-weight: bold" class="target"></div>
				</div>
				<div class="bo_d">
					<div style="font-size:14px;color:#666">完成业绩（万元）</div>
					<div style="font-size:26px;color:#333;font-weight: bold" class="finished"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="div_s">
			<div class="line"></div><span class="xs">销售漏斗</span>
			<div class="seach">
				<form:form id="inputForm1" modelAttribute="queryEntity" class="form-horizontal">
					<table style="margin-top: 10px;">
						<tr>
							<td style="width: 5%"><label class="pull-right" style="margin-right: 10px;">部门:</label></td>
							<td class="width-15" >
								<sys:treeselect id="office2" name="office.id" value="${queryEntity.office.id}" labelName="office.name" labelValue="${queryEntity.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
							</td>
							<td style="width: 5%"><label class="pull-right" style="margin-right: 10px;">用户:</label></td>
							<td class="width-15">
								<sys:onlyuserselect id="user2" name="user.id" cssClass="form-control" value="${queryEntity.user.id}"
													allowClear="true" labelName="user.name" labelValue="${queryEntity.user.name}" title="用户" paramName="office"/>
							</td>
							<td class="width-15" style="padding-left: 20px;"><input type="button" style="margin-top: 2px;width: 50px;height: 30px;" value="查询" onclick="queryPeriod()"></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div class="demo">
				<div id="container" style="min-width: 410px; max-width: 220px; height: 230px; margin: 0 auto"></div>
			</div>
		</div>
		<div class="div_s">
			<div class="line"></div><span class="xs">业绩排名</span>
			<div class="seach">
				<form:form id="inputForm3" modelAttribute="queryEntity" class="form-horizontal">
					<table style="margin-top: 10px;">
						<tr>
							<td style="width: 10%"><label class="pull-right" style="margin-right: 8px;">时间:</label></td>
							<td class="width-15" >
								<form:select path="queryTargetDate" class="form-control required" cssStyle="width: 160px;">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('first_page_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td style="width: 10%"><label class="pull-right" style="margin-right: 8px;">范围:</label></td>
							<td class="width-15" style="width: 280px;">
								<form:select path="queryScope" class="form-control ">
									<form:option value="1" label="个人"/>
									<form:option value="2" label="公司"/>
									<form:option value="3" label="部门"/>
								</form:select>
							</td>
							<td style="width: 10%"><label class="pull-right" style="margin-right: 8px;">指标:</label></td>
							<td class="width-15" style="width: 280px;">
								<form:select path="queryTarget" class="form-control">
									<form:option value="2" label="商机数"/>
									<form:option value="1" label="销售额"/>
									<form:option value="3" label="线索数"/>
									<form:option value="4" label="客户数"/>
									<form:option value="5" label="合同金额"/>
								</form:select>
							</td>
							<td class="width-15" style="padding-left: 20px;"><input type="button" style="margin-top: 2px;width: 50px;height: 30px;" value="查询" onclick="queryTarget1()"></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="main" style="width:90%;height:300px;"></div>
		</div>
	</div>
</div>


</body>
</html>