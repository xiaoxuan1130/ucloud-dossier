<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {

		});
		function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
			}else{
                jp.loading();
                jp.post("${ctx}/coderule/codeRule/save",$('#inputForm').serialize(),function(data){
                    if(data.success){
                        jp.getParent().refresh();
                        var dialogIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(dialogIndex);
                        jp.success(data.msg)

                    }else{
                        jp.error(data.msg);
                    }
                })
			}

        }
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="codeRule" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>代码：</label></td>
					<td class="width-35">
						<form:select path="code"  class="form-control m-b" >
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('code_rule')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35" colspan="3">
						<form:radiobuttons path="status" items="${fns:getDictList('enabled')}"   itemLabel="label" cssClass="i-checks" itemValue="value" htmlEscape="false" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>前缀：</label></td>
					<td class="width-35">
						<form:input path="prefix" htmlEscape="false" maxlength="16"    class="form-control required "/>
					</td>
					<td class="width-15 active"><label class="pull-right">中间日期：</label></td>
					<td class="width-35">
						<form:select path="middleDate" class="form-control ">
							<form:option value=" " label=""/>
							<form:options items="${fns:getDictList('middle_date')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>尾部流水位数：</label></td>
					<td class="width-35">
						<form:input path="lastSerialBit" htmlEscape="false" maxlength="2"    class="form-control required isIntGtZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>尾部流水号起始值：</label></td>
					<td class="width-35">
						<form:input path="lastSerialStartNo" htmlEscape="false" maxlength="9999999"    class="form-control required isIntGtZero"/>
					</td>
				</tr>
				<tr>

					<td class="width-15 active"><label class="pull-right">示例：</label></td>
					<td class="width-35" colspan="3">
						<form:input path="example" htmlEscape="false"    class="form-control "/>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>