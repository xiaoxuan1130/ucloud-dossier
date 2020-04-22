<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            font-family: SimSun;
        }
        section{
            display:block;
            margin: 20px 10px;
        }
        .title{
            text-align: center;
        }
        .preface p{
            line-height: 30px;
        }
        .preface p.content{
            text-indent: 2em;
        }
        section > table{
            table-layout: fixed;
            width: 100%;
            margin: 20px 0px;
            text-align:center;
            word-wrap:break-word;
        }
        section table td{
            padding:5px 0px;
        }
        .unit{
            margin-left: 10%;
        }
        .top_2{
            margin-left: 3%;
        }
    </style>
</head>
<body>
<!-- 汇总统计信息 start -->
<section class="count-info">
    <h4>报价统计信息</h4>
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr><td colspan="8">报价单</td></tr>
        </thead>
        <tr>
            <td colspan="8">
                <span >报价单位:</span>
                <span>${companyName}</span>
                <span class="top_2">联系人:</span>
                <span>${customerContact.name}</span>
                <span  class="top_2">联系电话:</span>
                <span>${customerContact.mobile}</span>
            </td>
        </tr>
        <tr>
            <td>客户名称:</td>
            <td colspan="3">${customerInfo.name}</td>
            <td>报价日期:</td>
            <td colspan="3">${quoteDateStr}</td>
        </tr>
        <tr>
            <td colspan="8" style="text-align: left">请看一下报价作为参考,如有任何问题请与我们联络</td>
        </tr>
        <tr>
            <td>序号</td>
            <td>产品名称</td>
            <td>产品编码</td>
            <td>产品规格</td>
            <td>数量</td>
            <td>单价(元)</td>
            <td>金额(元)</td>
            <td>备注</td>
        </tr>
        <#list quotationInfoList as item>
            <tr>
                <td>${item_index+1}</td>
                <td>${item.product.name}</td>
                <td>${item.product.code}</td>
                <td>${item.product.spec}</td>
                <td>${item.productNum}</td>
                <td>${item.discountPrice}</td>
                <td>${item.discountTotalAmount}</td>
                <td></td>
            </tr>
        </#list>
        <tr>
            <td colspan="2">合计</td>
            <td>${quotePrice}</td>
            <td>合计人民币金额(大写)</td>
            <td colspan="4">${quotaPriceDX}</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="5">备注</td>
            <td>1</td>
            <td colspan="5" style="text-align: left">本报价单有效期限位10天,供货期7天以内</td>
        </tr>
        <tr>
            <td>2</td>
            <td colspan="5" style="text-align: left">交货地址:</td>
        </tr>
        <tr>
            <td>3</td>
            <td colspan="5" style="text-align: left">货运方式:</td>
        </tr>
        <tr>
            <td>4</td>
            <td colspan="5" style="text-align: left">付款方式:</td>
        </tr>
        <tr>
            <td>5</td>
            <td colspan="5" style="text-align: left">报价单内容请确认签名:</td>
        </tr>
        <tr>
            <td colspan="2">报价人</td>
            <td colspan="3"></td>
            <td colspan="1">审批</td>
            <td colspan="2"></td>
        </tr>
    </table>
</section>
<!-- 汇总统计信息 end -->


</body>
</html>