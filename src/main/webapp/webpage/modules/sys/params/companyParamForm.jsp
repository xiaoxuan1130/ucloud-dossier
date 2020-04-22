<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>公司参数</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
        function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
            }else{
                var index = jp.loading();
                jp.post("${ctx}/sys/companyParam/save",$('#inputForm').serialize(),function(data){
                    if(data.success){
                        jp.success(data.msg);
                    }else{
                        jp.error(data.msg);
                    }
                })
            }

        }
	</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="companyParamEntity" class="form-horizontal" cssStyle="width: 60%;margin: auto;">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   <tr>
			   <td  colspan="2" class="width-15 active"><label class="center">公司参数</label></td>
		   </tr>
			  <tr>
				 <td  class="width-15 active"><label class="pull-right">使用行业字段模版:</label></td>
				 <td class="width-35" >
					 <form:select path="industry"  class="form-control m-b" cssStyle="width: 200px;">
						 <form:option value="" label=""/>
						 <form:options items="${fns:getDictList('industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					 </form:select>
				 </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right">本位币:</label></td>
				  <td class="width-35" >
					  <form:select path="currency"  class="form-control m-b" cssStyle="width: 200px;">
						  <form:option value="" label=""/>
						  <form:options items="${fns:getDictList('currency')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					  </form:select>
				  </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right">客户公海领取规则:</label></td>
				  <td class="width-35" >
					  <form:select path="receiveRule"  class="form-control m-b" cssStyle="width: 200px;">
						  <form:option value="" label=""/>
						  <form:options items="${fns:getDictList('receive_rule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					  </form:select>
				  </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right">线索池领取规则:</label></td>
				  <td class="width-35" >
					  <form:select path="cluePoolReceiveRule"  class="form-control m-b" cssStyle="width: 200px;">
						  <form:option value="" label=""/>
						  <form:options items="${fns:getDictList('receive_rule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					  </form:select>
				  </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right"><font color="red">*</font>客户回收规则:</label></td>
				  <td class="width-35" >
					  客户<form:input path="recoveryRule"  class="required number" cssStyle="width: 50px"></form:input>天未进行跟进，系统自动收到客户公海
				  </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right"><font color="red">*</font>线索回收规则:</label></td>
				  <td class="width-35" >
					  线索<form:input path="clueRecoveryRule"  class="required number" cssStyle="width: 50px"></form:input>天未进行跟进，系统自动收到客户公海
				  </td>
			  </tr>
		      <tr>
				  <td  class="width-15 active"><label class="pull-right">系统消息是否开启:</label></td>
				  <td>
					  <form:radiobuttons path="openNotice" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks " />
				  </td>
			  </tr>
			  <tr>
				  <td  class="width-15 active"><label class="pull-right">收到消息是否弹出小窗口:</label></td>
				  <td>
					  <form:radiobuttons path="openWindow" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
				  </td>
			  </tr>
		   	  <tr>
				  <td colspan="2">
					  <button id="add" type="button" class="btn btn-primary" onclick="save()" style="margin-left: 46%">
						  <i class="glyphicon glyphicon-plus"></i> 确定
					  </button>
					  <button id="add" type="button" class="btn btn-primary" onclick="sync()" style="margin-left: 46%">
						  <i class="glyphicon glyphicon-plus"></i> 一键同步
					  </button>
				  </td>
			  </tr>
		</tbody>
		</table>
	</form:form>
</body>
</html>