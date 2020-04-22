<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>档案管理员管理</title>
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
                jp.post("${ctx}/dossiermanager/dossierManager/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="dossierManager" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属公司：</label></td>
					<td class="width-35">
						<sys:treeselect id="belongCompany" name="belongCompany.id" value="${dossierManager.belongCompany.id}" labelName="belongCompany.name" labelValue="${dossierManager.belongCompany.name}"
										title="部门" url="/sys/office/treeData?type=2" cssClass="form-control" allowClear="true" notAllowSelectParent="false"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>管理员id：</label></td>
					<td class="width-35">
						<sys:onlyuserselect id="manager" name="manager.id" cssClass="form-control" value="${dossierManager.manager.id}"
											allowClear="true" labelName="manager.name" labelValue="${dossierManager.manager.name}" title="用户" paramName="belongCompany"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>