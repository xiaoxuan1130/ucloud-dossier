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
    </style>
</head>
<body>
<!-- 汇总统计信息 start -->
<section class="count-info">
    <h4>订单统计信息</h4>
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>订单编号</td>
            <td>${no}</td>
            <td>客户名称</td>
            <td>${customerInfo.name}</td>
        </tr>
        <tr>
            <td>订单状态</td>
            <td>${statusLabel}</td>
            <td>合同号</td>
            <td>${contract.contractNo}</td>
        </tr>
        <tr>
            <td>联系人</td>
            <td>${customerContact.name}</td>
            <td>联系人手机号</td>
            <td>${customerContact.mobile}</td>
        </tr>
        <tr>
            <td>交货日期</td>
            <td>${deliveryDateStr}</td>
            <td>交货地址</td>
            <td>${deliveryAddr}</td>
        </tr>
        <tr>
            <td>订单金额</td>
            <td>${orderPrice}</td>
            <td>销售负责人</td>
            <td>${sales.name}</td>
        </tr>
        <tr>
            <td>创建人</td>
            <td>${createBy.name}</td>
            <td>创建时间</td>
            <td>${createDateStr}</td>
        </tr>
    </table>
</section>
<!-- 汇总统计信息 end -->

<!-- 明细 start -->
<section class="detail">
    <h4>明细</h4>
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th><font color="red">*</font>产品名称</th>
            <th>产品编码</th>
            <th>产品描述</th>
            <th>规格</th>
            <th><font color="red">*</font>数量</th>
            <th><font color="red">*</font>标准售价(含税)</th>
            <th><font color="red">*</font>税率(%)</th>
            <th><font color="red">*</font>折扣(%)</th>
            <th><font color="red">*</font>单价</th>
            <th>销售单位</th>
            <th><font color="red">*</font>小计</th>
        </tr>
        <#list orderInfoList as item>
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.code}</td>
                <td>${item.product.remarks}</td>
                <td>${item.product.spec}</td>
                <td>${item.productNum}</td>
                <td>${item.product.standardPrice}</td>
                <td>${item.taxRate}</td>
                <td>${item.discount}</td>
                <td>${item.quotaPrice}</td>
                <td>${item.product.unit}</td>
                <td>${item.totalAmount}</td>
            </tr>
        </#list>
    </table>
</section>

<section class="total">
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>整体折扣</td>
            <td>${discountTypeLabel}</td>
            <td>${discountPrice}</td>
        </tr>
        <tr>
            <td>数量总计</td>
            <td colspan="2" class="td_total">${totalNum}
            </td>
        </tr>
        <tr>
            <td>税金总计</td>
            <td colspan="2" class="td_total">${totalTax}
            </td>
        </tr>
        <tr>
            <td> 不含税金合计</td>
            <td colspan="2" class="td_total">${totalPriceNoTax}
            </td>
        </tr>
        <tr>
            <td >含税金额合计</td>
            <td colspan="2" class="td_total">${totalPrice}
            </td>
        </tr>
    </table>
</section>

</body>
</html>