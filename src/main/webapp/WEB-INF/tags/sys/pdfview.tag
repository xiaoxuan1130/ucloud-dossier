<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="url" type="java.lang.String" required="false" description="pdf地址"%>
<%@ attribute name="resId" type="java.lang.String" required="false" description="资源id"%>
<%@ attribute name="modelType" type="java.lang.String" required="false" description="模版类型"%>
<%@ attribute name="sysEmailModule" type="java.lang.String" required="false" description="所选择的模版的id"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="type=1时表示直接预览pdf,type=2是表示通过模版生成pdf后预览"%>

<a  id="view" class="btn btn-primary btn-rounded  btn-bordered btn-sm" onclick="view()">预览</a>

<script type="text/javascript">
    function view(){
        var type="${type}";
        if(type=='1'){
            var url="${url}";
            if(url==""){
                jp.error("附件不存在或没有配置");
            }else{
                jp.openViewDialog("预览","${ctx}/sysemail/sysEmail/viewPdf?url="+url,"800px","1000px");
            }
        }else{
            var resId="${resId}";
            var modelType="${modelType}";
            var moduleId=$("#sysEmailModuleId").val();
            var json={resId:resId,'modelType':modelType,'sysEmailModule.id':moduleId};
            jp.post("${ctx}/sysemail/sysEmail/getAttachUrl",json,function(data){
                var url=data;
                if(url==""){
                    jp.error("附件不存在或没有配置");
                }else{
                    jp.openViewDialog("预览","${ctx}/sysemail/sysEmail/viewPdf?url="+url,"800px","1000px");
                }
            })
        }
    }

</script>
