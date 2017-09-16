<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript">
        $(document).ready(function(){
            $("#interface-desc-tab").addClass("active");
        });
    </script>

</head>
<body>
    <div>
        <h4><small>简要说明</small></h4>
        <div>
            <p>ucmp后台服务通过hessian协议提供，类似 String addAddress(String jsonParam).输入参数和返回结果均为json格式字符串。</p>
            <pre>
输入参数：
{
  "countryCode" : "CN",
  "langCode" : "sc",
  "mediaCode" : null,
  "systemCode" : "club",
  ......
}


 countryCode 地区编码(客户所使用的地区版本，必填)，只能为("CN","TW","HK", "MC")  CN:大陆, HK:香港, TW:台湾,MC:澳门
 langCode 语言编码(必填),只能为("sc","tc","en") sc:简体中文, tc:繁体中文, en:英文
 mediaCode  媒体Code(必填),只能为("baidu", "sina", "163", "sohu","PC.OWT","MOBILE.OWT","CMB","BAIDU","QING.BAIDU",
 	"ZHIDA.BAIDU","PC.MEMBER","MOBILE.MEMBER","IOS.APP","ANDROID.APP","WENXIN",null)
 	PC.OWT：pc端官网， MOBILE.OWT：手机端官网,CMB：招商银行,BAIDU：百度,QING.BAIDU：百度轻应用,ZHIDA.BAIDU：百度直达号,
 	PC.MEMBER：pc端会员,MOBILE.MEMBER：手机端会员,IOS.APP：ios应用,ANDROID.APP：安卓应用,WENXIN：WENXIN
 systemCode 渠道Code(必填), 只能为("club", "wx", "syt","PC.OWT","MOBILE.OWT","PC.MEMBER","MOBILE.MEMBER",
 	"IOS.APP","ANDROID.APP",null)club:会员系统, wx:微信, syt:速运通,PC.OWT：pc端官网， MOBILE.OWT：手机端官网,
 	PC.MEMBER：pc端会员,MOBILE.MEMBER：手机端会员,IOS.APP：ios应用,ANDROID.APP：安卓应用
            </pre>

            <pre>
返回：
{
  "status" : "1",
  "errorCode" : "",
  "message" : ""
  ......
}

status 调用是否成功，"1" 代表成功，"0" 代表失败.
errorCode 错误编码
message 错误信息
            </pre>
        </div>
    </div>
</body>
</html>