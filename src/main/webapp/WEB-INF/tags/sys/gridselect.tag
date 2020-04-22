<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Value）"%>
<%@ attribute name="fieldLabels" type="java.lang.String" required="true" description="表格Th里显示的名字"%>
<%@ attribute name="fieldKeys" type="java.lang.String" required="true" description="表格Td里显示的值"%>
<%@ attribute name="searchLabels" type="java.lang.String" required="true" description="检索框标签"%>
<%@ attribute name="searchKeys" type="java.lang.String" required="true" description="检索框key"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据地址"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="isMultiSelected" type="java.lang.Boolean" required="false" description="是否允许多选"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="allowInput" type="java.lang.String" required="false" description="是否可编辑"%>
<%@ attribute name="paramTip" type="java.lang.String" required="false" description="默认查询参数提示名称"%>
<%@ attribute name="paramName" type="java.lang.String" required="false" description="默认查询参数名称"%>
<%@ attribute name="paramValue" type="java.lang.String" required="false" description="默认查询参数名称"%>
<%@ attribute name="paramValueEleId" type="java.lang.String" required="false" description="默认查询参数值元素Id"%>
<%@ attribute name="confirmEvent" type="java.lang.String" required="false" description="确认回调函数"%>

	<input id="${id}Id" name="${name}"  type="hidden" value="${value}" <c:if test="${not empty paramValueEleId}">data-parent-id="${paramValueEleId}Id"</c:if> >
	<div class="input-group" style="width: 100%">
		<input id="${id}Name"  name="${labelName}" ${allowInput?'':'readonly="readonly"'}  type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		class="${cssClass}" style="${cssStyle}"/>
       		 <span class="input-group-btn">
	       		 <button type="button"  id="${id}Button" class="btn <c:if test="${fn:contains(cssClass, 'input-sm')}"> btn-sm </c:if><c:if test="${fn:contains(cssClass, 'input-lg')}"> btn-lg </c:if>  btn-primary ${disabled} ${hideBtn ? 'hide' : ''}"><i class="fa fa-search"></i>
	             </button> 
	               <button type="button" id="${id}DelButton" class="close" data-dismiss="alert" style="position: absolute; top: 5px; right: 53px; z-index: 999; display: block;">×</button>
       		 </span>
       		
    </div>
	 <label id="${id}Name-error" class="error" for="${id}Name" style="display:none"></label>
<script type="text/javascript">
$(document).ready(function(){
	$("#${id}Button, #${id}Name").click(function(){
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		<%--if ("${changeFunName}"){--%>
			<%--$("#${id}Id").on("change", eval("${changeFunName}"));--%>
        <%--}--%>
		var eleValue;
		if ($("#${paramValueEleId}Id").length !== 0){
            eleValue = $("#${paramValueEleId}Id").val();
        }
        var url = "${ctx}/tag/gridselect?url="+encodeURIComponent("${url}")+"&fieldLabels="+encodeURIComponent("${fieldLabels}")+"&fieldKeys="+encodeURIComponent("${fieldKeys}")+"&searchLabels="+encodeURIComponent("${searchLabels}")+"&searchKeys="+encodeURIComponent("${searchKeys}")+"&isMultiSelected=${isMultiSelected? true:false}";
        if ("${paramName}"){
            if (!eleValue && !"${paramValue}"){
				var paramTip = "${paramTip}";
				if(!paramTip){
					top.layer.msg("请先选择父项！");
				}else{
					top.layer.msg("请先选择" + paramTip + "！");
				}
                return;
            }
            url += "&${paramName}=";
            url += eleValue ? eleValue : "${paramValue}";
        }
		top.layer.open({
		    type: 2,  
		    area: ['800px', '500px'],
		    title:"${title}",
		    auto:true,
		    name:'friend',
		    content: url,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var iframeWin = layero.find('iframe')[0].contentWindow; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		    	 var items = iframeWin.getSelections();
		    	 if(items == ""){
			    	jp.warning("必须选择一条数据!");
			    	return;
		    	 }
		    	 var ids = [];
		    	 var names = [];
		    	 for(var i=0; i<items.length; i++){
		    		 var item = items[i];
		    		 ids.push(item.id);
		    		 names.push(item${fn:substring(labelName, fns:lastIndexOf(labelName, '.'), fn:length(labelName))})
				 }
		    	 var newIds = ids.join(",");
		    	 var oldIds = $("#${id}Id").val();
		    	 if (oldIds !== '' && oldIds !== newIds){
					 var eles = $('[data-parent-id]');
					 var prevEle = $("#${id}Id");
					 var len = eles.length;
					 for (var j = 0; j < len; j++){
					 	var $item = $(eles[j]);
					 	var parentId = $item.attr('data-parent-id');
					 	var id = $item.attr('id');
					 	if (parentId === '${id}Id' || (prevEle && prevEle.attr('id') === parentId)){
							$item.val('');
							$("#" + id.substr(0, id.length - 2) + "Name").val('');
						}
					 	prevEle = $item;
					 }
				 }
		    	 $("#${id}Id").val(ids.join(","));
		    	 $("#${id}Name").val(names.join(","));
		    	 //硬编码，增加特定格式的回调函数
		    	 var callback = "${confirmEvent}";
		    	 callback && eval(callback);

				// 自定义事件
				var myEvent = new CustomEvent('itemSelected', {
					detail: {
						data: items,
						id : '${id}'
					}
				});
				// 随后在对应的元素上触发该事件，页面中需要注册相关的EventListener才能获取到数据，通过id来区分当前正在操作的元素
				//example:
				// 		window.addEventListener('itemSelected', function(event){
				//			console.log('数据：', event.detail.data);
				//			console.log('id：', event.detail.id);
				//		});
				if(window.dispatchEvent) {
					window.dispatchEvent(myEvent);
				} else {
					window.fireEvent(myEvent);
				}

				 top.layer.close(index);//关闭对话框。
			  },
			  cancel: function(index){ 
		       }
		}); 
	})
	$("#${id}DelButton").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		// 清除	
		$("#${id}Id").val("");
		$("#${id}Name").val("");
		$("#${id}Name").focus();
		var eles = $('[data-parent-id]');
		var prevEle = $("#${id}Id");
		var len = eles.length;
		for (var j = 0; j < len; j++){
			var $item = $(eles[j]);
			var parentId = $item.attr('data-parent-id');
			var id = $item.attr('id');
			if (parentId === '${id}Id' || (prevEle && prevEle.attr('id') === parentId)){
				$item.val('');
				$("#" + id.substr(0, id.length - 2) + "Name").val('');
			}
			prevEle = $item;
		}
	});

	function funExists(funName){
		try{
			if(typeof eval(funName)=="undefined"){return false;}
			if(typeof eval(funName)=="function"){return true;}
		}catch(e){
			return false;
		}
	}


})
</script>
