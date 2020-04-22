<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="false" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="false" description="树结构数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="allowSearch" type="java.lang.Boolean" required="false" description="是否允许搜索"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<%@ attribute name="paramName" type="java.lang.String" required="false" description="默认查询参数名称"%>
<%@ attribute name="paramValueEleId" type="java.lang.String" required="false" description="默认查询参数值元素Id"%>
<script type="text/javascript">


    var selectCustomer${id}=function(){
        var param=$("#${paramName}Id").val();
        top.layer.open({
            type: 2,
            area: ['850px', '600px'],
            title:"选择用户",
            maxmin: true, //开启最大化最小化按钮
            content: "${ctx}/sys/user/userList?officeId="+param ,
            btn: ['确定', '关闭'],
            yes: function(index, layero){
                var iframeWin=layero.find("iframe")[0].contentWindow;
                $("#${id}Id").val(iframeWin.userid);
                $("#${id}Name").val(iframeWin.username);
                top.layer.close(index);
            },
            cancel: function(index){
            }
        });
    }

    var DelButton${id}=function(){
        // 是否限制选择，如果限制，设置为disabled
        if ($("#${id}Button").hasClass("disabled")){
            return true;
        }
        // 清除
        $("#${id}Id").val("");
        $("#${id}Name").val("");
        $("#${id}Name").focus();
	}


</script>


<input id="${id}Id" name="${name}" class="${cssClass} form-control" type="hidden" value="${value}" <c:if test="${not empty paramValueEleId}">data-parent-id="${paramValueEleId}Id"</c:if>/>
<div class="input-group" style="width:100%">
	<input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'}  type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		   class="${cssClass}" style="${cssStyle}"/>
	<span class="input-group-btn">
	       		 <button type="button"  onclick="selectCustomer${id}()" id="${id}Button" class="btn <c:if test="${fn:contains(cssClass, 'input-sm')}"> btn-sm </c:if><c:if test="${fn:contains(cssClass, 'input-lg')}"> btn-lg </c:if>  btn-primary ${disabled} ${hideBtn ? 'hide' : ''}"><i class="fa fa-search"></i>
	             </button>
	             <c:if test="${allowClear}">
					 <button type="button" onclick="DelButton${id}()"  class="close" data-dismiss="alert" style="position: absolute; top: 5px; right: 53px; z-index: 999; display: block;">×</button>

				 </c:if>

       		 </span>

</div>
<label id="${id}Name-error" class="error" for="${id}Name" style="display:none"></label>

