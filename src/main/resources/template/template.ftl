<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        body{
            font-family: SimSun;
        }
    </style>
</head>
<body>
<#include "template_2.ftl">
<table>
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
        <th width="10"></th>
    </tr>
    <#list orderList as item>
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
</body>
</html>