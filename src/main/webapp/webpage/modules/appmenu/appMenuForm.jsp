<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>移动端菜单管理</title>
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
                jp.post("${ctx}/appmenu/appMenu/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="appMenu" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>对应菜单：</label></td>
					<td class="width-35">
						<sys:treeselect id="menu" name="menu.id" value="${appMenu.menu.id}" labelName="menu.name" labelValue="${appMenu.menu.name}"
										title="菜单" url="/sys/menu/treeData" allowClear="true" cssClass="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">排序值：</label></td>
					<td class="width-35">
						<form:input path="sort" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">图标地址：</label></td>
					<td class="width-35">
						<sys:fileUpload path="icon" value="${appMenu.icon}" target="qiniu" type="image" uploadPath="/" fileNumLimit="1" readonly="false"/></td>
					</td>
					<td class="width-15 active"><label class="pull-right">跳转地址：</label></td>
					<td class="width-35">
						<form:input path="url" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>类别：</label></td>
					<td class="width-35">
						<form:radiobuttons path="type" items="${fns:getDictList('app_menu_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</td>

				</tr>
			   <tr>
				   <td class="width-15 active"><label class="pull-right">备注：</label></td>
				   <td class="width-35">
					   <form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
				   </td>
			   </tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>