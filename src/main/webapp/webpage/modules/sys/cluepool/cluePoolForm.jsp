<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>线索池管理</title>
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
                jp.post("${ctx}/sys/cluePool/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="cluePool" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>线索池名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" maxlength="50"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>部门：</label></td>
					<td class="width-35">
						<sys:treeselect id="office" name="office.id" value="${cluePool.office.id}" labelName="office.name" labelValue="${cluePool.office.name}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="form-control  required" allowClear="true" notAllowSelectParent="false"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>负责人：</label></td>
					<td class="width-35">
						<sys:userselect id="manager" name="manager.id" value="${cluePool.manager.id}" labelName="manager.name" labelValue="${cluePool.manager.name}"
							    cssClass="form-control required "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>