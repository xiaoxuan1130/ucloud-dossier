
/**
 * 通用/扩展JS
 * @author gongtao
 * @version 2018/01/10 9:53
 **/
(function ($) {

    String.prototype.startWith=function(str){
        var reg=new RegExp("^"+str);
        return reg.test(this);
    };

    $.ajaxSetup({
        error: function (XMLHttpRequest, textStatus, errorThrow) {
            if (XMLHttpRequest.status === 401) {
                //超时未认证跳转到登录页面
                var top = getTopWindow(); //获取当前页面的顶层窗口对象
                //layer.alert('登录超时, 请重新登录.');
                top.location.href = XMLHttpRequest.getResponseHeader("CAS-Login-Url");
            } else if (XMLHttpRequest.status === 403) {
                var json = JSON.parse(XMLHttpRequest.responseText);
                if (json && json.status === 2002) {
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-rim', //加上边框
                        area: ['600px', '250px'], //宽高
                        content: ctx + '/webpage/common/wait_approve_tip.jsp'
                    });
                } else {
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-rim', //加上边框
                        area: ['600px', '250px'], //宽高
                        content: ctx + '/webpage/common/apply_vip_tip.jsp'
                    });
                }
                return false;
            }else if (XMLHttpRequest.status === 405){
                var data = JSON.parse(XMLHttpRequest.responseText);
                if (data.message === '-1'){
                    layer.confirm('<p class="my-dialog lg-font">您还未申请收款账号，赶快去申请吧！</p><p class="my-dialog md-font">进行供需交易需要先开通收款账号哟！</p>', {
                        skin: 'demo-class demo-class2',
                        title:'温馨提示！',
                        btnAlign: 'c',
                        btn: ['前往申请'] //按钮
                    }, function(){
                        window.open(ctxFront + '/account/apply');
                    });
                }else if (data.message === '01' || data.message === '02'){
                    layer.confirm('<p class="my-dialog lg-font">您的收款账号还在审核中，请再稍稍等待~</p>', {
                        skin: 'demo-class',
                        title:'温馨提示！',
                        btn: [],
                        time: 5000
                    });
                }else{
                    layer.confirm('<p class="my-dialog lg-font">您的收款账号申请被驳回，去我的云制造里看看吧！</p>', {
                        skin: 'demo-class',
                        title:'温馨提示！',
                        btn: [],
                        time: 5000
                    });
                }
            }else {
                console.error(XMLHttpRequest.responseText);
                return false;
            }
        },
    });

    /**
     * 在页面中任何嵌套层次的窗口中获取顶层窗口
     * @return 当前页面的顶层窗口对象
     */
    function getTopWindow() {
        var p = window;
        while (p !== p.parent) {
            p = p.parent;
        }
        return p;
    }

    /**
     * form表单转为Json对象
     */
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                this.value = this.value ? this.value.replace(/^\s+|\s+$/g, "") : null;
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };

    /**
     * 简单模板渲染，仅支持obj为简单对象
     * @param tpl
     * @param obj
     * @returns {*}
     */
    $.template = function (tpl, obj) {
        var template = tpl;
        if (obj instanceof Object) {
            for (var key in obj) {
                var pattern = new RegExp('{{' + key + '}}', 'g');
                template = template.replace(pattern, obj[key] === null ? "" : obj[key]);
            }
        } else {
            return null;
        }
        return template;
    };

    /**
     * 获取地址栏参数对象
     * @returns {{}}
     */
    $.getReqObj = function () {
        var url = window.location.search; //获取url中"?"符后的字串
        var theRequest = {};
        if (url.indexOf("?") !== -1) {
            var str = url.substr(1);
            var strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
            }
        }
        return theRequest;
    };

    /** 通用三级节点插件
     * @author gongtao
     * config = {
     *     url : "",  //后端请求地址，后端返回值格式为 {data : [{code: "",id:"",name:""},{code: "",id:"",name:""}] }
     *     first : "",  //第一个节点id
     *     fDefault: "", //第一个节点默认值
     *     second: "",  //第二个节点id
     *     sDefault: "",  //第二个节点默认值
     *     third: "",  //第三个节点id
     *     tDefault: "",  //第三个节点默认值
     *     parentId : "",  //初始父节点值
     *     noDataCreate: true|false //无数据时是否创建节点 default:true
     * }
     * @param config
     * @version 2018/1/25 13:47
     * @return
     */
    $.initThirdNode = function (config) {
        var url = config.url;
        var $firstId = $('#' + config.first);
        var $secondId = $('#' + config.second);
        var $thirdId = $('#' + config.third);
        var parentId = config.parentId;
        var create = config.noDataCreate === 'undefined' ? true : config.noDataCreate;
        initFirstNode();
        if (config.fDefault) {
            initSecondNode();
        }
        if (config.sDefault) {
            initThirdNode();
        }
        $firstId.on("change", initSecondNode);
        $secondId.on("change", initThirdNode);

        /**
         * 一级节点数据初始化
         */
        function initFirstNode() {
            nodeInit($firstId, parentId, config.fDefault);
        }

        /**
         * 二级节点初始化
         */
        function initSecondNode(event) {
            var $default = $("<option value=''>请选择</option>");
            //判断是一级节点的 change 事件的绑定调用还是初始化调用
            if (event) {
                //重置二级节点数据
                var parentId = $firstId.find("option:selected").attr("data-id");
                if (parentId) {
                    //已选择数据则加载二级节点数据
                    nodeInit($secondId, parentId);
                } else {
                    //未选择任何数据则清空二级节点数据
                    $secondId.empty();
                    $secondId.append($default.clone());
                }
            } else {
                //默认初始化
                nodeInit($secondId, config.fDefault, config.sDefault);
            }
            //清空三级节点数据
            $thirdId.empty();
            $thirdId.append($default);
        }

        /**
         * 三级节点初始化
         */
        function initThirdNode(event) {
            var $default = $("<option value=''>请选择</option>");
            //判断是二级节点 change 事件的绑定调用还是初始化调用
            if (event) {
                //重置三级节点数据
                var parentId = $secondId.find("option:selected").attr("data-id");
                if (parentId) {
                    //已选择数据则加载三级节点数据
                    nodeInit($thirdId, parentId);
                } else {
                    //未选择任何数据则清空三级节点数据
                    $thirdId.empty();
                    $thirdId.append($default.clone());
                }
            } else {
                //默认初始化
                nodeInit($thirdId, config.sDefault, config.tDefault);
            }
        }

        /**
         * 节点初始化
         * @param nextSelectNode
         * @param parentId
         * @param defaultValue
         */
        function nodeInit(nextSelectNode, parentId, defaultValue) {
            $.get(url, {parentId: parentId}, function (data) {
                //如果无数据则不创建节点
                if (!create && !data.data){
                    return;
                }
                var $node = nextSelectNode;
                $node.empty();
                var $default = $("<option value=''>请选择</option>");
                $node.append($default);
                var array = data.data;
                for (var i = 0, len = array.length; i < len; i++) {
                    var obj = array[i];
                    var $option = $("<option value='" + obj.code + "' data-id='" + obj.id + "'>" + obj.name + "</option>");
                    if (obj.code === defaultValue) {
                        $option.attr("selected", "selected");
                    }
                    $node.append($option);
                }
            });
        }
    };

    /**
     * 日期转换
     * @param longTypeDate
     * @returns {string}
     */
    $.formatDate = function (longTypeDate) {
        var datetimeType = "";
        var date = new Date();
        date.setTime(longTypeDate);
        datetimeType += date.getFullYear();   //年
        datetimeType += "-" + getMonth(date); //月
        datetimeType += "-" + getDay(date);   //日
        return datetimeType;
    };

    /**
     * 日期时间转换
     * @param longTypeDate
     * @returns {string}
     */
    $.formatDatetime = function (longTypeDate) {
        var datetimeType = "";
        var date = new Date();
        date.setTime(longTypeDate);
        datetimeType += date.getFullYear();   //年
        datetimeType += "-" + getMonth(date); //月
        datetimeType += "-" + getDay(date);   //日
        datetimeType += "&nbsp;&nbsp;" + getHours(date);   //时
        datetimeType += ":" + getMinutes(date);      //分
        datetimeType += ":" + getSeconds(date);      //分
        return datetimeType;
    };


    //返回 01-12 的月份值
    function getMonth(date) {
        var month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    //返回01-30的日期
    function getDay(date) {
        var day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

    //返回小时
    function getHours(date) {
        var hours = date.getHours();
        if (hours < 10) {
            hours = "0" + hours;
        }
        return hours;
    }

    //返回分
    function getMinutes(date) {
        var minute = date.getMinutes();
        if (minute < 10) {
            minute = "0" + minute;
        }
        return minute;
    }

    //返回秒
    function getSeconds(date) {
        var second = date.getSeconds();
        if (second < 10) {
            second = "0" + second;
        }
        return second;
    }
})(jQuery);