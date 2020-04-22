<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>选择用户</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/webpage/include/treeview.jsp" %>
	<script type="text/javascript">


		var userTree;

		// 初始化
		$(document).ready(function(){
            userTree = $.fn.zTree.init($("#userTree"), setting, userNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		
		var userNodes=[
	            <c:forEach items="${userList}" var="user">
	            {id:"${user.id}",
	             pId:"0",
	             name:"${user.name}"},
	            </c:forEach>];

		var userid='';
		var username='';
        //点击选择项回调
        function treeOnClick(event, treeId, treeNode, clickFlag){
            $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			//if($.inArray(String(treeNode.id), ids)<0){
				userid=treeNode.id;
				username=treeNode.name;
			//}
        };

	</script>
</head>
<body>
	
	<div id="assignRole" class="row wrapper wrapper-content">
		<div class="col-sm-4">
			<p>待选人员：</p>
			<div id="userTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
