<%@ page contentType="text/html;charset=UTF-8" %>
<script>
function editMonth1Formatter(value,row,index){
    debugger;
	return [
		'<input type="text" id="month1'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',1,'+index+')"/>'
	].join("");
}
function editMonth2Formatter(value,row,index){
	return [
		'<input type="text" id="month2'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',2,'+index+')"/>'
	].join("");
}
function editMonth3Formatter(value,row,index){
	return [
		'<input type="text" id="month3'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',3,'+index+')"/>'
	].join("");
}
function editMonth4Formatter(value,row,index){
	return [
		'<input type="text" id="month4'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',4,'+index+')"/>'
	].join("");
}
function editMonth5Formatter(value,row,index){
	return [
		'<input type="text" id="month5'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',5,'+index+')"/>'
	].join("");
}
function editMonth6Formatter(value,row,index){
	return [
		'<input type="text" id="month6'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',6,'+index+')"/>'
	].join("");
}
function editMonth7Formatter(value,row,index){
	return [
		'<input type="text" id="month7'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',7,'+index+')"/>'
	].join("");
}
function editMonth8Formatter(value,row,index){
	return [
		'<input type="text" id="month8'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',8,'+index+')"/>'
	].join("");
}
function editMonth9Formatter(value,row,index){
	return [
		'<input type="text" id="month9'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',9,'+index+')"/>'
	].join("");
}
function editMonth10Formatter(value,row,index){
	return [
		'<input type="text" id="month10'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',10,'+index+')"/>'
	].join("");
}
function editMonth11Formatter(value,row,index){
	return [
		'<input type="text" id="month11'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',11,'+index+')"/>'
	].join("");
}
function editMonth12Formatter(value,row,index){
	return [
		'<input type="text" id="month12'+row.id+index+'" class="form-control edit1" data='+value+' value='+value
		+' onblur="addTarget(\''+row.id+'\',12,'+index+')"/>'
	].join("");
}

//id:这条数据的id  type :1,2,3:代表的是第几个目标值
function addTarget(id, type, index)
{
	  var writevalue=$("#month"+type+id+index).val(); //获取改变后的输入框的值
	  if(writevalue==''){
		  writevalue=0;
	  }
	  var oldvalue = $("#month"+type+id+index).attr("data"); //获取输入框原本的值
	  if(!(writevalue==oldvalue)){//通过判断输入框的值是否改变，是否写入jsontarget改变的json数据
//		  var data = '{id:'+id+', month'+type+':'+writevalue+'}';
//		  var dataa = '{performanceObjectiveMonth.id:'+id+', performanceObjectiveMonth.month'+type+':'+writevalue+'}';
//		  var str = '{"id": '+id+' "huangxiaojian","age":"23"}'//单引号写在{}外，每个属性名都必须用双引号，否则会抛出异常。
//			  JSON.parse(datae)
//		  alert(datae);
//		  alert(JSON.stringify(data));
//		  alert(JSON.parse(datae));
		  var submitdata = '{"id":"'+id+'", "month'+type+'":"'+writevalue+'"}';
		  $.ajax({
		      type: "post",  
	          url:'${ctx}/sys/performanceObjectiveMonth/save',
//	          data:dataa,
	          data:JSON.parse(submitdata),
//	          contentType : "application/json", 
	          dataType : "json",
//	          async:true,
	          success:function (res) {
	        	  debugger;
	  		  	oldvalue=writevalue;
			  	$("#month"+type+id+index).attr("data",writevalue);
			  	$("#month"+type+id+index).val(writevalue);
//			  	alert(writevalue);
	          		console.log(res);
//	          	 bootbox.alert({size: "small",message:res.msg}); 
//	          	 $("#table").bootstrapTable('refresh');//保存成功后刷新表格
//	          	 jsontarget=[];//保存完成后清空修改的目标值
	          }
	      });
		  
//		  //新方案更新月份值
//		  data = '{id:'+id+', type:'+type+', newvalue:'+writevalue+'}';
//			$.ajax({
//				type: "POST",
//		        url:'${ctx}/sys/performanceObjectiveMonth/updateMonth',
//		    	data: {"id":id,"type":type, "newvalue":writevalue},
////		        data: JSON.stringify(data),
//				dataType:'json',
//				cache: false,
//				success: function(data){
//				  	console.log(data);
//					 if(data.success){
//				  		  	oldvalue=writevalue;
//						  	$("#month"+type+id+index).attr("data",writevalue);
//						  	$("#month"+type+id+index).val(writevalue);
//					 }else{
//					 }
//					 
//					 
//				}
//			});

//		  	bootbox.alert(writevalue);
//		  	writeJson(id,type,writevalue);
//		 	console.log(jsontarget);
	  }	  
}
$(document).ready(function() {
	//zTree初始化
	var extId = "";
	if ("false" === "${fns:getUser().admin}"){
		extId = "${fns:getUser().company.id}";
	}
	$.getJSON("${ctx}/sys/office/bootstrapTreeData?extId=" + extId,function(data){
		$('#jstree').treeview({
			data: data,
			levels: 5,
            onNodeSelected: function(event, treeNode) {
            	var id = treeNode.id == '0' ? '' :treeNode.id;
				if(treeNode.level == 1){//level=1 代表公司
					$("#companyId").val(id);
					$("#companyName").val(treeNode.text);
					$("#officeId").val("");
					$("#officeName").val("");
				}else{
					$("#companyId").val("");
					$("#companyName").val("");
					$("#officeId").val(id);
					$("#officeName").val(treeNode.text);
				}
				
				$('#performanceObjectiveMonthTable').bootstrapTable('refresh');
            },
         });
	});
	

	function editFormatter(value,row,index){
		return [
			'<input type="text" id="1plan'+row.id+'" class="form-control edit1" data='+value+' value='+value
			+' onblur="teeest(\''+row.id+'\')"/>'
		].join("");
	}
	$('#performanceObjectiveMonthTableaa').bootstrapTable({
        url: "${ctx}/sys/performanceObjectiveMonth/data",
        toolbar: '#toolbar',
        clickEdit: true,
        showToggle: true,
        pagination: true,       //显示分页条
        showColumns: true,
        showPaginationSwitch: true,     //显示切换分页按钮
        showRefresh: true,      //显示刷新按钮
        //clickToSelect: true,  //点击row选中radio或CheckBox
        columns: [{
            checkbox: true
        }, 
		,{
	        field: 'month1',
	        title: '一月',
	        sortable: true,
	        sortName: 'month1'
	       
	    }
		,{
	        field: 'month2',
	        title: '二月',
	        sortable: true,
	        sortName: 'month2'
	       
	    }
		,{
	        field: 'month3',
	        title: '三月',
	        sortable: true,
	        sortName: 'month3'
	       
	    } ],
        /**
         * @param {点击列的 field 名称} field
         * @param {点击列的 value 值} value
         * @param {点击列的整行数据} row
         * @param {td 元素} $element
         */
        onClickCell: function(field, value, row, $element) {
            $element.attr('contenteditable', true);
            $element.blur(function() {
                let index = $element.parent().data('index');
                let tdValue = $element.html();
                alert(tdValue);
                //saveData(index, field, tdValue);
            })
        }
    });
	$("td").each(function () {
        var value = $(this).text();
        alert(value);
        //$(this).html("<input value='" + value + "'>");
    });
	$('#performanceObjectiveMonthTable').bootstrapTable({
		 
		  //请求方法
               method: 'post',
               //类型json
               dataType: "json",
               contentType: "application/x-www-form-urlencoded",
               //显示检索按钮
	           showSearch: true,
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: pageSize,
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/sys/performanceObjectiveMonth/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   		edit(row.id);
                   }else if($el.data("item") == "view"){
                       view(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该业绩目标详情表记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/sys/performanceObjectiveMonth/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#performanceObjectiveMonthTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
            	   $el.attr('contenteditable', true);
               },
               	onShowSearch: function () {
			$("#search-collapse").slideToggle();
		},
               columns: [
			{
		        field: 'user.name',
		        title: '用户'
		       
		    }
			,{
		        field: 'yearTarget',
		        title: '汇总',
		        sortable: true,
		        sortName: 'yearTarget'
		       
		    }
			,{
		        field: 'month1',
		        title: '一月',
		        sortable: true,
		        sortName: 'month1',
		        formatter:editMonth1Formatter
		       
		    }
			,{
		        field: 'month2',
		        title: '二月',
		        sortable: true,
		        sortName: 'month2',
		        formatter:editMonth2Formatter
		       
		    }
			,{
		        field: 'month3',
		        title: '三月',
		        sortable: true,
		        sortName: 'month3',
		        formatter:editMonth3Formatter
		       
		    }
			,{
		        field: 'month4',
		        title: '四月',
		        sortable: true,
		        sortName: 'month4',
		        formatter:editMonth4Formatter
		       
		    }
			,{
		        field: 'month5',
		        title: '五月',
		        sortable: true,
		        sortName: 'month5',
		        formatter:editMonth5Formatter
		       
		    }
			,{
		        field: 'month6',
		        title: '六月',
		        sortable: true,
		        sortName: 'month6',
		        formatter:editMonth6Formatter
		       
		    }
			,{
		        field: 'month7',
		        title: '七月',
		        sortable: true,
		        sortName: 'month7',
		        formatter:editMonth7Formatter
		       
		    }
			,{
		        field: 'month8',
		        title: '八月',
		        sortable: true,
		        sortName: 'month8',
		        formatter:editMonth8Formatter
		       
		    }
			,{
		        field: 'month9',
		        title: '九月',
		        sortable: true,
		        sortName: 'month9',
		        formatter:editMonth9Formatter
		       
		    }
			,{
		        field: 'month10',
		        title: '十月',
		        sortable: true,
		        sortName: 'month10',
		        formatter:editMonth10Formatter
		       
		    }
			,{
		        field: 'month11',
		        title: '十一月',
		        sortable: true,
		        sortName: 'month11',
		        formatter:editMonth11Formatter
		       
		    }
			,{
		        field: 'month12',
		        title: '十二月',
		        sortable: true,
		        sortName: 'month12',
		        formatter:editMonth12Formatter
		       
		    }
		     ],
	        onClickCell: function(field, value, row, $element) {
	            $element.attr('contenteditable', true);
	            $element.blur(function() {
	                let index = $element.parent().data('index');
	                let tdValue = $element.html();
	
	            })
	        }
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#performanceObjectiveMonthTable').bootstrapTable("toggleView");
		}
	  
	  $('#performanceObjectiveMonthTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#performanceObjectiveMonthTable').bootstrapTable('getSelections').length);
            $('#view,#edit').prop('disabled', $('#performanceObjectiveMonthTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 2,
                area: [500, 200],
                auto: true,
			    title:"导入数据",
			    content: "${ctx}/tag/importExcel" ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					 jp.downloadFile('${ctx}/sys/performanceObjectiveMonth/import/template');
				  },
			    btn2: function(index, layero){
				        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						iframeWin.contentWindow.importExcel('${ctx}/sys/performanceObjectiveMonth/import', function (data) {
							if(data.success){
								jp.success(data.msg);
								refresh();
							}else{
								jp.error(data.msg);
							}
						});//调用保存事件
						jp.close(index);
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		    
		
	  $("#export").click(function(){//导出Excel文件
			jp.downloadFile('${ctx}/sys/performanceObjectiveMonth/export');
	  });

		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#performanceObjectiveMonthTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#performanceObjectiveMonthTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#performanceObjectiveMonthTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该业绩目标详情表记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/sys/performanceObjectiveMonth/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#performanceObjectiveMonthTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function refresh(){
  	$('#performanceObjectiveMonthTable').bootstrapTable('refresh');
  }
  function add(){
		jp.go("${ctx}/sys/performanceObjectiveMonth/form/add");
	}

  function edit(id){
	  if(id == undefined){
		  id = getIdSelections();
	  }
	  jp.go("${ctx}/sys/performanceObjectiveMonth/form/edit?id=" + id);
  }

  function view(id) {
      if(id == undefined){
          id = getIdSelections();
      }
      jp.go("${ctx}/sys/performanceObjectiveMonth/form/view?id=" + id);
  }
  
</script>