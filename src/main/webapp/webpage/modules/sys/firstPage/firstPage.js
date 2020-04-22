<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function(){
        $("#userId").val($(".id_hidden").val());
        $("#userName").val($(".name_hidden").val());
        $("#user1Id").val($(".id_hidden").val());
        $("#user1Name").val($(".name_hidden").val());
        $("#user2Id").val($(".id_hidden").val());
        $("#user2Name").val($(".name_hidden").val());
        query();
         initChart();
         queryPeriod();
         queryTarget1();
         queryAchievement();

    })

function query(){
    //var index = jp.loading();
    jp.post("${ctx}/statistics",$('#inputForm').serialize(),function(data){
        if(data.success){
            //jp.success(data.msg);
            $(".customerTotal").text(data.body.statistics.customerNum);
            $(".contactTotal").text(data.body.statistics.customerContactNum);
            $(".clueInfoTotal").text(data.body.statistics.clueInfoNum);
            $(".businessChanceTotal").text(data.body.statistics.businessChanceNum);
            $(".orderAmount").text(data.body.statistics.orderAmount);
            $(".receiveAmount").text(data.body.statistics.receiveAmount);
        }else{
            //alert(data);
            jp.error(data.msg);
        }
    })

}


function openNewWindow(url, name, type) {
    var officeId = $("#officeId").val();
    var userId = $("#userId").val();
    if (userId == $(".id_hidden").val()) {
        if ("customer" == type) {
            url = "${ctx}" + url + "?sales.id=" + userId;
        } else if("customerContact"==type){
            url = "${ctx}" + url + "?customerInfo.sales.id=" + userId;
        }else if("clueInfo"==type){
            url = "${ctx}" + url + "?sales.id=" + userId;
        }else if("businessChance"==type){
            url = "${ctx}" + url + "?user.id=" + userId;
        }else if("order"==type){
            url = "${ctx}" + url + "?sales.id=" + userId;
        }else if("paymentReceive"==type){
            url = "${ctx}" + url + "?contract.salesUser.id=" + userId;
        }
        jp.closeByName(name);
        jp.openTab(url, name, false);
        // top.refreshOpenedTab(url);
    }

}

var periodChart;//销售漏斗
var gradeChart;//业绩排名
var achievementChart;//绩效目标

function initChart(){
    /* myChart=echarts.init(document.getElementById('middle_chart'));
     myChart.setOption(option);*/

    achievementChart=echarts.init(document.getElementById('container1'));
    achievementChart.setOption(option1);

    periodChart = Highcharts.chart('container', option2);

    gradeChart=echarts.init(document.getElementById('main'));
    gradeChart.setOption(option3);
}

var option1= {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            restore: {},
            saveAsImage: {}
        },
        show : false
    },
    series: [
        {
            name: '业务指标',
            type: 'gauge',
            detail: {
                fontSize:5,
                formatter:'{value}%'
            },
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[0.2, '#FF7474'], [0.8, '#FFCD50'], [1, '#4BE78D']]
                }
            },
            data: [{value: 0, name: '完成率'}]
        }
    ]
};

var option2= {
    chart: {
        type: 'funnel',
        marginRight: 100
    },
    title: {
        text: '',
        x: -50
    },
    credits: {
        enabled: false
    },
    plotOptions: {
        series: {
            dataLabels: {
                enabled: true,
                crop : false,
                overflow: 'none',
                format: '<b>{point.name}</b> ({point.y:,.0f})',
                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                softConnector: true
            },
            neckWidth: '30%',
            neckHeight: '25%'
        }
    },
    legend: {
        enabled: false
    },
	series:[{
    	name:'销售漏斗',
		data:[
            ['初步接洽（3单）',5654],
            ['确定需求（2单）',4064],
            ['方案制定（1单）',1987],
            ['方案报价（3单）',976],
            ['商务判定（2单）',846],
            ['赢单（2单）',6500]
        ]
	}]
}

var option3 = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        show: false,
        splitLine:{show: false},//去除网格
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        }
    },
    yAxis: {
        type: 'category',
        data: [],
        splitLine: {show: false},
        // splitArea : {show : true},//保留网格区域颜色
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        offset: 10,
        nameTextStyle: {
            fontSize: 15
        }
    },
    series: [
        {
            name: '数量',
            type: 'bar',
            data: [],
            barWidth: 14,
            barGap: 10,
            smooth: true,
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    offset: [5, -2],
                    textStyle: {
                        color: '#F68300',
                        fontSize: 13
                    }
                }
            },
            itemStyle: {
                emphasis: {
                    barBorderRadius: 7
                },
                normal: {
                    barBorderRadius: 7,
                    color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
                }
            }
        }
    ]
};


function queryAchievement(){
    jp.post("${ctx}/sys/firstPage/statisticsAchievement",$('#inputForm0').serialize(), function (data) {
        var str1=data[0].achieveAmount
        var str2=data[0].targetAmount
		var rate=(parseFloat(str1)*100/parseFloat(str2)).toFixed(2);
        $(".target").text((parseFloat(str2)/10000).toFixed(2));
        $(".finished").text((parseFloat(str1)/10000).toFixed(2));
        achievementChart.setOption({
            series:[
				{
                    data:[{
                        value:rate,
                        name:"完成率"
                    }]
				}
            ]
        })

    })
}

function queryPeriod(){
    jp.post("${ctx}/sys/firstPage/statisticsPeriod",$('#inputForm1').serialize(), function (datas) {
        var data=[];
        $.each(datas, function(i, item){
        	var arr=[];
        	arr.push(item.businessChancePeriod.name+"("+item.count+"单)");
        	arr.push(item.amount);
            data.push(arr);
        });
        periodChart.series[0].update({
            data:data
        });
    });
}


function queryTarget1(){
    var queryTarget=$("#inputForm3 #queryTarget").val();
    jp.post("${ctx}/sys/firstPage/statisticsTarget",$('#inputForm3').serialize(), function (data) {
        var category=[];
        var barData=[];
        $.each(data, function(i, item){
            category.push(item.name);
            barData.push(item.count);
        });
        var title;
        if(queryTarget=='2'||queryTarget=='3'||queryTarget=='4'){
            title="数量";
        }else{
            title="金额";
        }
        gradeChart.setOption({
            yAxis:{
                data:category
            },
            series:[
                {
                    name:title,
                    data:barData
                }
            ]
        })

    })


}
</script>