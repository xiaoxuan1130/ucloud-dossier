<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<!-- _login_page_ --><!--登录超时标记 勿删-->
<html>

<head>
    <meta name="decorator" content="ani"/>
    <title>档案管理系统 登录</title>
    <style>

        body, h1, h2, h3, h4, h5, h6, p, ul {
            margin: 0
        }

        html, body, .main {
            width: 100%;
            height: calc(100% - 60px)
        }

        #canvas {
            position: fixed;
            top: 0;
            z-index: -5
        }

        .logo {
            margin-top: 13px;
            margin-right: 15px;
            float: left
        }

        .main {
            box-sizing: border-box
        }

        a {
            text-decoration: none
        }

        a:hover {
            text-decoration: none
        }

        ul {
            padding: 0
        }

        ul, li {
            list-style: none
        }

        .header {
            height: 60px;
            background-color: #000;
            color: #fff;
            padding: 0 70px;
            line-height: 60px
        }

        .header h1 {
            color: #fff;
            font-size: 22px
        }

        .header a {
            color: #fff
        }

        .float_ri {
            float: right
        }

        .content_lf, .content_ri {
            width: 20%
        }

        .float_lf {
            float: left
        }

        .text-center {
            text-align: center
        }

        .height600 {
            height: 600px
        }

        .clearFl {
            clear: both
        }

        .head_btn a {
            cursor: pointer;
            margin-left: 46px
        }

        .head_user {
            position: relative
        }

        .head_user img {
            position: absolute;
            top: 18px;
            left: -30px
        }

        .head_title {
            width: 20%
        }

        .head_list {
            width: 40%
        }

        .head_list li {
            float: left;
            width: 20%
        }

        .head_list a {
            display: block;
            width: 100%;
            height: 60px;
            text-align: center;
            cursor: pointer
        }

        .head_list a:hover {
            background: linear-gradient(to bottom, #001466, #000833)
        }

        .head_list .active {
            background: linear-gradient(to bottom, #001466, #000833)
        }

        .head_user {
            margin-right: 6%
        }

        body {
            background: url(../img/common/bg.png) center 60px no-repeat;
            background-color: #000833
        }

        .main {
            padding: 0 35px
        }

        .content {
            position: relative;
            padding-top: 25px;
            margin-bottom: 20px
        }

        .content {
            padding-top: 25px
        }

        .content_title {
            position: absolute;
            left: 0;
            right: 0;
            top: 25px;
            margin: auto;
            text-align: center;
            color: #fff;
            display: none
        }

        .content_title a {
            position: relative;
            left: 15%;
            z-index: 3;
            font-size: 16px;
            color: rgba(255, 255, 255, 0.7);
            cursor: pointer
        }

        .data-item {
            box-sizing: border-box;
            width: 100%;
            height: 140px;
            border: 1px solid #003580;
            box-shadow: 0px 0px 20px 1px #004d99 inset;
            margin-bottom: 22px;
            text-align: center;
            padding: 16px 0;
            color: #fff
        }

        .data-item h3 {
            font-size: 16px;
            color: #fff;
            margin-bottom: 10px
        }

        .data-item .data-show {
            color: #f5b91e;
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 15px
        }

        .data-item .data-show span {
            font-size: 28px;
            margin-left: 10px
        }

        .data-item .data-statistics {
            color: rgba(255, 255, 255, 0.7);
            font-size: 14px
        }

        .data-item .data-statistics p {
            float: left;
            width: 50%
        }

        .data-item2 {
            height: 300px
        }

        .data-item2 h4 {
            font-size: 16px;
            margin-bottom: 18px
        }

        .tab-btn {
            overflow: hidden
        }

        .tab-btn a {
            float: left;
            width: 80px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            color: #666;
            background-color: #fff;
            box-shadow: 0 0 4px 1px #999 inset;
            cursor: pointer
        }

        .tab-btn .active {
            color: #fff;
            background-color: #bf444c;
            box-shadow: 0px 2px 3px 1px rgba(255, 255, 255, 0.3) inset, 0px -2px 3px 1px rgba(0, 0, 0, 0.3) inset
        }

        .tab-btn-child {
            clear: both
        }

        .tab-btn-child a {
            display: block;
            width: 40px;
            height: 80px;
            text-align: center;
            line-height: 80px;
            color: #666;
            background-color: #fff;
            box-shadow: 0 0 5px 1px #999 inset;
            cursor: pointer
        }

        .tab-btn-child .active {
            color: #fff;
            background-color: #2551bb;
            box-shadow: 0px 2px 3px 1px rgba(255, 255, 255, 0.3) inset, 0px -2px 3px 1px rgba(0, 0, 0, 0.3) inset
        }

        .chinaMap {
            position: absolute;
            width: 900px;
            height: 700px;
            left: 0;
            right: 0;
            top: 30px;
            margin: auto;
            z-index: 2
        }

        .chinaMap .echartMap {
            width: 100%;
            height: 100%
        }

        .echarts3 {
            position: relative;
            z-index: 1
        }

        .foot {
            position: relative;
            z-index: 1
        }

        .footer {
            overflow: hidden
        }

        @media screen and (max-width: 1366px) {
            .main .chinaMap {
                width: 600px;
                height: 470px
            }

            .data-item {
                height: 120px;
                padding: 10px 0
            }

            .data-item h3 {
                font-size: 14px
            }

            .data-item .data-show {
                margin-bottom: 10px
            }

            .data-item .data-show span {
                font-size: 24px
            }

            .data-item .data-statistics {
                font-size: 12px
            }
        }

        html,
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            overflow: hidden;
            background: black;
        }

        canvas {
            position: relative;
            width: 100%;
            height: 100%;
        }

        * {
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            -webkit-tap-highlight-color: transparent;
        }

        footer {
            position: fixed;
            right: 0;
            top: 0;
            left: 0;
            padding: 10px 10px;
            text-align: right;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
            font-size: 14px;
            color: #fff;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        a {
            display: inline-block;
            margin-left: 2px;
            padding: 2px 4px;
            color: #343436;
            text-decoration: none;
            background-color: #fcd000;
            border-radius: 4px;
            opacity: 1;
            -webkit-transition: opacity 0.2s;
            transition: opacity 0.2s;
        }

        a:hover {
            opacity: 0.6;
        }

        html,
        body,
        section {
            width: 100%;
            height: 100%;
        }

        section {
            background-size: calc();
        }

        .box {
            position: fixed;
            width: 870px;
            height: 460px;
            left: 0;
            right: 0;
            top: -150px;
            bottom: 0;
            margin: auto;
            background-color: transparent;
            color: #fff;
            text-align: center;
        }

        .title {
            width: 750px;
            margin: 0 auto;
            margin-top: 50px;
            position: relative;
            overflow: hidden;
            margin-bottom: 50px;
        }

        .title h1 {
            float: left;
            height: 80px;
            line-height: 70px;
            margin: 0 auto;
            font-size: 44px;
            letter-spacing: 10px;
        }

        .title img {
            float: left;
            margin-right: 40px;
        }

        label {
            border-radius: 5px;
            border: 1px solid #007EFF;
        }

        label i {
            float: left;
            width: 28px;
            height: 40px;
            margin: -10px 0;
            border-right: 1px solid #e5e5e5;
        }

        label input {
            float: left;
            border: none;
            outline: none;
            height: 22px;
            color: #333;
            font-size: 16px;
            padding-left: 15px;
            -webkit-box-shadow: 0 0 0px 1000px white inset;
        }

        ::-webkit-input-placeholder {
            color: #999;
        }

        .user i {
            background: url(${ctxStatic}/common/images/firstpage/user.png) no-repeat center;
        }

        .pass i {
            background: url(${ctxStatic}/common/images/firstpage/pwd.png) no-repeat center;
        }

        .contents .user, .contents .pass {
            display: block;
            box-sizing: border-box;
            width: 300px;
            height: 42px;
            background-color: #fff;
            padding: 10px 0;
            margin: 0 auto;
            overflow: hidden;
        }

        .input_box {
            width: 300px;
            margin-bottom: 10px;
            text-align: left;
            margin: 20px auto;

        }

        .box_foot {
            width: 300px;
            text-align: left;
            margin: -40px auto;
        }

        .box_foot .rememberPass {
            margin-bottom: 5px;
        }

        .box_foot .btn {
            display: block;
            width: 300px;
            height: 40px;
            line-height: 40px;
            outline: none;
            border: none;
            background-color: #007eff;
            color: #fff;
            text-align: center;
            font-size: 18px;
            letter-spacing: 5px;
            cursor: pointer;
            border-radius: 5px;
        }

        .input_error {
            font-size: 14px;
            color: #ff4040;
            margin-top: 5px;
        }

        .contents {
            width: 50%;
            height: 360px;
            float: left;
            background:rgba(255,255,255,1);
            box-shadow:40px 4px 27px 0px rgba(0,6,52,0.69);

            /*margin-top: 50px;*/
        }

        .advertising {
            float: left;
            width: 50%;
            height: 360px;
            background:linear-gradient(0deg,rgba(0,126,255,1),rgba(1,13,210,1));

        }

        canvas {
            background-color: #141a48;
        }

        .main_cont {
            box-sizing: border-box;
            /*padding: 30px 0;*/
            border-radius: 8px;
            overflow: hidden;
            background-color: rgba(0, 0, 0, 0.1);
        }
        .chose_box{
            height: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom:1px dashed #BFBFBF;
        }
        .zh_style{
            color: #333333;
            font-size: 24px;
        }
        .grayBtn:hover{
            color: #B6B6B6;
            cursor:pointer;
            /*-webkit-transform: scale(0.7);*/
            /*-moz-transform:scale(0.7);*/
            /*-o-transform:scale(0.7);*/
            /*transform:scale(0.7);*/
        }
        .line{
            width:2px;
            height:21px;
            background:rgba(212,212,212,1);
        }
        .left_move{
            margin-right: 36px;
        }
        .right_move{
            margin-left: 36px;
        }
        .lbl{
            color: #333333;
        }
        .input_content{

        }
        .saom_dl{
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 50px auto;
            position: relative;
            min-height: 200px;
        }
        #successImg{
            display: none;
            position: absolute;
            left: 50%;
            transform: translateX(-50%) translateY(-50%);
            padding: 0;
            top: 50%;
        }
        #scanImg{
            position: absolute;
            left: 50%;
            transform: translateX(-50%) translateY(-50%);
            padding: 0;
            top: 50%;
        }
        .code-overly {
            background: rgba(0,0,0,.5);
            width: 200px;
            height: 200px;
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            padding: 0;
            display: none;
            top: 0;
        }
        .code-overly-text {
            text-align: center;
            margin: 60px 0 20px;
            color: #fff;
            font-size: 15px;
        }
        .refresh-btn {
            width: 70px;
            height: 26px;
            background: #d90000;
            color: #fff;
            text-align: center;
            line-height: 26px;
            margin: 0 auto;
        }
        .pwd_dl{
            display: block;
        }
        .p_sty{
            font-size:30px;
            font-family:Source Han Sans CN;
            font-weight:bold;
            color:rgba(255,255,255,1);
            margin-top: 15px;
        }
        .app_you{
            position: absolute;
            bottom: -27px;
            left: 35%;
            color: gray;
            font-size: 16px;
        }
        .codeImg{
            position: absolute;
            left: 46%;
            top: 39%;
            width: 40px;
            height: 40px;
            border-radius: 8px;
        }
    </style>
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
    <script type="text/javascript">
        var username;
        var password;

        $(document).ready(function () {
            username=$("#username").val();
            password=$("#password").val();

        }


        function login(){
            $.ajax({
                type:"post",
                url:'${ctx}/login?username='+username+'&password='+password,

                async:true,
                success:function(res){
                    if(res.code == 200){
                        sessionStorage.setItem('jwt',res.data.jwt)
                        location.href = 'content/index.html'
                    }
                },
                error:function(res){
                    $('#error').show()
                }
            });
        }
        $(document).ready(function () {
            $("#loginForm").validate({
                messages: {
                    username: {required: "请填写用户名."}, password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0) {
            alert('未登录或登录超时。请重新登录，谢谢！');
            top.location = "${ctx}";
        }
    </script>

</head>


<body>
<section>
    <canvas width="1280" height="947"></canvas>
    <div class="box">
        <div class="title">
            <img src="${ctxStatic}/common/images/firstpage/logo.png"/>
            <h1>档案管理系统</h1>
            <%--<sys:message content="${message}" showType="1"/>--%>
        </div>
        <form id="loginForm" role="form" action="${ctx}/login" method="post">
            <div class="main_cont">



                <div class="advertising">
                    <img width="368px" height="237px" style="margin-top: 25px;" src="${ctxStatic}/common/images/firstpage/ground.png"/>
                    <p class="p_sty">功能全面 方便实用 易于拓展</p>
                </div>

                <div class="contents">
                    <div class="chose_box">
                        <div class="zh_style left_move grayBtn" onclick="haBtn()">账号登录</div>
                        <div class="line"></div>
                        <div class="zh_style right_move grayBtn" onclick="saoMiaoBtn()">扫描登录</div>
                    </div>
                    <div class="input_content">
                        <div class="pwd_dl" id="dl_id">
                            <div class="input_box">
                                <label class="user">
                                    <i></i>
                                    <input id="username" name="username" type="text" autocomplete="off" placeholder="请输入账号"/>
                                </label>
                            </div>
                            <div class="input_box">
                                <label class="pass">
                                    <i></i>
                                    <input id="password" name="password" type="password" autocomplete="off" placeholder="请输入密码"/>
                                </label>
                                <div style="height: 30px;">
                                    <p id="error"  class="input_error">${message}</p>
                                </div>

                            </div>
                            <div class="box_foot">
                                <div class="rememberPass">
                                    <input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}
                                           class="ace"/>
                                    <span class="lbl"> 记住我</span>
                                </div>
                                <input type="submit" style="position: relative;z-index: 2;" class="btn"  value="登录">
                            </div>
                        </div>
                        <div class="saom_dl" id="saoM_id" style="margin-top: 15px;">
                            <img src="#" id="scanImg"/>
                            <img src="${ctxStatic}/common/images/firstpage/code-success1.png" id="successImg"/>
                            <p class="app_you">优管APP扫码登录</p>
                            <img src="${ctxStatic}/common/images/firstpage/code.png" class="codeImg">
                            <div class="code-overly" id="coverImg">
                                <p class="code-overly-text">二维码已失效</p>
                                <div class="refresh-btn" id="refreshBtn" onclick="refreshBtn()">刷新</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </form>
    </div>

</section>
<script>

    !function(n,r){"object"==typeof exports&&"undefined"!=typeof module?module.exports=r():"function"==typeof define&&define.amd?define(r):n.starlings=r()}(this,function(){"use strict";return function(n,r,t,o,e,u,i,f){var a=f.onSetup;void 0===a&&(a=null);var v=f.onRepeat;void 0===v&&(v=null);var c=f.modifier;void 0===c&&(c=null);var l=f.perspective;void 0===l&&(l=1);var d=f.pixelRatio;void 0===d&&(d=1);var m=f.triangles;void 0===m&&(m=!1);var s,p,y=r.length,w=function(n,r){var t=s.createShader(n);return s.shaderSource(t,r),s.compileShader(t),t},b=function(){for(var n=0;n<o.length;n+=1){for(var r=s.createBuffer(),e=o[n],u=e.data(0,0).length,i=new Float32Array(t*y*u),f=0;f<t;f+=1)for(var a=e.data(f,t),v=f*y*u,l=0;l<y;l+=1)for(var d=0;d<u;d+=1)null!==c&&e.name===c.attribute?i[v]=c.value(i[v],a,d,l):i[v]=a[d],v+=1;s.bindBuffer(s.ARRAY_BUFFER,r),s.bufferData(s.ARRAY_BUFFER,i,s.STATIC_DRAW);var m=s.getAttribLocation(p,o[n].name);s.enableVertexAttribArray(m),s.vertexAttribPointer(m,u,s.FLOAT,!1,!1,0,0)}},A=function(){e.push({name:"uMVP",type:"mat4"});for(var n=0;n<e.length;n+=1){var r=s.getUniformLocation(p,e[n].name);e[n].location=r}},F={float:function(n,r){return s.uniform1f(n,r)},vec2:function(n,r){return s.uniform2fv(n,r)},vec3:function(n,r){return s.uniform3fv(n,r)},vec4:function(n,r){return s.uniform4fv(n,r)},mat2:function(n,r){return s.uniformMatrix2fv(n,!1,r)},mat3:function(n,r){return s.uniformMatrix3fv(n,!1,r)},mat4:function(n,r){return s.uniformMatrix4fv(n,!1,r)}},g=function(){s.clear(16640),s.useProgram(p),null!==v&&v(s,p,e);for(var n=0;n<e.length;n+=1)F[e[n].type](e[n].location,e[n].value);s.drawArrays(m?s.TRIANGLES:s.POINTS,0,y*t),requestAnimationFrame(g)},h=function(){n.width=n.clientWidth*d,n.height=n.clientHeight*d;var r=s.drawingBufferWidth,t=s.drawingBufferHeight;s.viewport(0,0,r,t),e[e.length-1].value=[l/(r/t),0,0,0,0,l,0,0,0,0,-1,-1,0,0,1,1]};s=n.getContext("webgl"),p=s.createProgram(),s.attachShader(p,w(s.VERTEX_SHADER,u)),s.attachShader(p,w(s.FRAGMENT_SHADER,i)),s.linkProgram(p),A(),h(),b(),null!==a&&a(s),g(),window.addEventListener("resize",h,!1)}});


    "use strict";
    var interval = '';
    // Do you like rainbow waves?
    var rainbow = false;

    // Need more performance?
    var HD = true;

    var canvas = document.querySelector("canvas");
    var background = document.querySelector(".background");
    var bar = document.querySelector(".progress");

    var initialize = function initialize(vertices) {
        var pixelRatio = HD ? window.devicePixelRatio : 1;
        var rows = HD ? 90 : 90;
        var multiplier = rows * rows;
        var duration = 0.4;
        var geometry = [{ x: 0, y: 0, z: 0 }];
        var pointSize = (HD ? 6 : 2).toFixed(1);

        var step = 0.004;
        var size = 5;
        var attributes = [{
            name: "aPositionStart",
            data: function data(i, total) {
                return [size - (i % rows / rows + 0.5 / rows) * (size * 2), -1, (size - (Math.floor(i / rows) / rows + 0.5 / rows) * size * 2) * -1];
            }
        }, {
            name: "aControlPointOne",
            data: function data(i) {
                return [size - (i % rows / rows + 0.5 / rows) * (size * 2), -0.5 + getRandom(0.2), (size - (Math.floor(i / rows) / rows + 0.5 / rows) * size * 2) * -1];
            }
        }, {
            name: "aControlPointTwo",
            data: function data(i) {
                return [size - (i % rows / rows + 0.5 / rows) * (size * 2), -0.5 + getRandom(0.2), (size - (Math.floor(i / rows) / rows + 0.5 / rows) * size * 2) * -1];
            }
        }, {
            name: "aPositionEnd",
            data: function data(i) {
                return [size - (i % rows / rows + 0.5 / rows) * (size * 2), -1, (size - (Math.floor(i / rows) / rows + 0.5 / rows) * size * 2) * -1];
            }
        }, {
            name: "aOffset",
            data: function data(i) {
                return [i * ((1 - duration) / (multiplier - 1))];
            }
        }, {
            name: "aColor",
            data: function data(i, total) {
                return getHSL(rainbow ? i / total * 1.0 : 0.5 + i / total * 0.4, 0.5, 0.5);
            }
        }];

        var uniforms = [{
            name: "uProgress",
            type: "float",
            value: 0.8
        }];

        var vertexShader = "\n  attribute vec3 aPositionStart;\n  attribute vec3 aControlPointOne;\n  attribute vec3 aControlPointTwo;\n  attribute vec3 aPositionEnd;\n  attribute float aOffset;\n  attribute vec3 aColor;\n\n  uniform float uProgress;\n  uniform mat4 uMVP;\n\n  varying vec3 vColor;\n\n  vec3 bezier4(vec3 a, vec3 b, vec3 c, vec3 d, float t) {\n    return mix(mix(mix(a, b, t), mix(b, c, t), t), mix(mix(b, c, t), mix(c, d, t), t), t);\n  }\n\n  float easeInOutQuint(float t){\n    return t < 0.5 ? 16.0 * t * t * t * t * t : 1.0 + 16.0 * (--t) * t * t * t * t;\n  }\n\n  void main () {\n    float tProgress = easeInOutQuint(min(1.0, max(0.0, (uProgress - aOffset)) / " + duration + "));\n    vec3 newPosition = bezier4(aPositionStart, aControlPointOne, aControlPointTwo, aPositionEnd, tProgress);\n    gl_PointSize = " + pointSize + " + ((newPosition.y + 1.0) * 80.0);\n    gl_Position = uMVP * vec4(newPosition, 1.0);\n    vColor = aColor;\n  }\n";

        var fragmentShader = "\n  precision mediump float;\n\n  varying vec3 vColor;\n\n  void main() {\n     vec2 pc = 2.0 * gl_PointCoord - 1.0;\n     gl_FragColor = vec4(vColor, 1.0 - dot(pc, pc));\n  }\n";

        var onSetup = function onSetup(gl) {
            gl.blendFunc(gl.SRC_ALPHA, gl.ONE);
            gl.enable(gl.BLEND);
        };

        var onRepeat = function onRepeat() {
            rotateY(uniforms[uniforms.length - 1].value, 0.002);
            if (uniforms[0].value < 0) {
                uniforms[0].value = 1;
            }
            uniforms[0].value -= step;
        };

        // const diff = (a, b) => Math.abs(a - b);

        // const ratio = window.innerWidth / window.innerHeight;
        // const halfWidth = window.innerWidth / 2;
        // const halfHeight = window.innerHeight / 2;
        // window.addEventListener('mousemove', (e) => {
        //   uniforms[0].value = (((e.clientX - halfWidth) / halfWidth) * ratio).toFixed(4);
        //   uniforms[1].value = (((e.clientY - halfHeight) / halfHeight)).toFixed(4) * -1;
        // });

        var options = {
            onSetup: onSetup,
            onRepeat: onRepeat,
            pixelRatio: pixelRatio
        };

        starlings(canvas, geometry, multiplier, attributes, uniforms, vertexShader, fragmentShader, options);
    };

    var getRandom = function getRandom(value) {
        return Math.random() * value - value / 2;
    };

    var rotateY = function rotateY(matrix, angle) {
        var sin = Math.sin(angle);
        var cos = Math.cos(angle);
        var clone = JSON.parse(JSON.stringify(matrix));

        matrix[0] = clone[0] * cos - clone[8] * sin;
        matrix[1] = clone[1] * cos - clone[9] * sin;
        matrix[2] = clone[2] * cos - clone[10] * sin;
        matrix[3] = clone[3] * cos - clone[11] * sin;
        matrix[8] = clone[0] * sin + clone[8] * cos;
        matrix[9] = clone[1] * sin + clone[9] * cos;
        matrix[10] = clone[2] * sin + clone[10] * cos;
        matrix[11] = clone[3] * sin + clone[11] * cos;
    };

    var h2r = function h2r(p, q, t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1 / 6) return p + (q - p) * 6 * t;
        if (t < 1 / 2) return q;
        if (t < 2 / 3) return p + (q - p) * 6 * (2 / 3 - t);
        return p;
    };

    var getHSL = function getHSL(h, s, l) {
        h = (h % 1 + 1) % 1;
        s = Math.max(0, Math.min(1, s));
        l = Math.max(0, Math.min(1, l));
        if (s === 0) return [l, l, l];
        var p = l <= 0.5 ? l * (1 + s) : l + s - l * s;
        var q = 2 * l - p;
        return [h2r(q, p, h + 1 / 3), h2r(q, p, h), h2r(q, p, h - 1 / 3)];
    };
    function  haBtn() {
        var sm = document.getElementById('dl_id');
        sm.style.display = 'block'
        var dr = document.getElementById('saoM_id');
        dr.style.display='none'
    }
    function saoMiaoBtn() {
        var sm = document.getElementById('dl_id');
        sm.style.display = "none"
        var dr = document.getElementById('saoM_id');
        dr.style.display='block'
        getUuid();
    }

    function refreshBtn(){
        $("#coverImg").css('display',"none");
        $("#successImg").css('display',"none");
        $("#scanImg").css('display',"block");
        getUuid();
    }

    //调用app获取uuid的接口
    function getUuid(){
        const that = this;
        clearInterval(interval);
        $.ajax({
            url:'${ctx}/getuuid',
            type:'get',
            dataType:'json',
            success:function(data){
                var uuid=data.msg;
                //根据uuid获取二维码替换图片
                $("#scanImg").attr('src',"${ctx}/createCode?code="+uuid+"");
                getStatus(uuid);
                interval = setInterval(function () {
                    that.getStatus(uuid);
                }, 3000)
            }
        })
    }

    //获取app的扫描状态
    function getStatus(uuid){
        $.ajax({
            url:'${ctx}/getstatus?code='+uuid,
            type:'get',
            dataType:'json',
            success:function(data){
                if(data.success){
                    var state=data.msg;
                    if(state=='0'){

                    }else if(state=='1'){
                        $("#successImg").css('display',"block");
                        $("#scanImg").css('display',"none");

                    }else if(state=='2'){
                        //二维码失效
                        $("#coverImg").css('display',"block");
                        $("#successImg").css('display',"none");
                        $("#scanImg").css('display',"block");

                        clearInterval(interval)
                    }else{
                        var token=state;
                        loginByToken(token);
                        clearInterval(interval)
                    }
                }

            }
        });
    }

    function loginByToken(token){
        const that = this;
        $.ajax({
            type:"post",
            url:'${ctx}/login?token='+token+'&requestType=1',
            async:true,
            success:function(res){
                var msg=res.msg;
                if(msg!=undefined){
                    // alert(msg);
                   // $('body').after("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>")
                    jp.error(msg);
                    $("#coverImg").css('display',"none");
                    $("#successImg").css('display',"none");
                    $("#scanImg").css('display',"block");
                    that.getUuid();
                }else{
                    location.href = '${ctx}?login';
                }
                <%--location.href = '${ctx}?login'--%>
                //document.write(res);  //一句搞定
                // if(res.code == 200){
                //     sessionStorage.setItem('jwt',res.data.jwt)
                //     location.href = 'content/index.html'
                // }
            },
            error:function(res){
                $('#error').show()
            }
        });
    }
    initialize();
</script>
</body>
</html>