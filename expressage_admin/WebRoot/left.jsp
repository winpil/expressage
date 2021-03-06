<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>快递快滴管理平台</title>
        <script src="js/jquery/jquery-1.4.2.min.js"></script>
        <script src="js/jquery/ui/jquery.ui.core.js"></script>
        <script src="js/jquery/ui/jquery.ui.widget.js"></script>
        <script src="js/jquery/ui/jquery.ui.accordion.js"></script>

        <link href="style/frame/ZB.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            var menuConfig;
            $(function() {
                menuConfig=parent.getMenuConfig();
                
                initLeftMenu();
            });

            //更新导航文字标题
            function setNavTitle(title){
                parent.setNavTitle(title);
            }

            function updateLeftMenu(parentId){
                
                var leftMenuConfig;
                var menuHtml="";
                for(var i=0;i<menuConfig.length;i++){
                    if(menuConfig[i][0]==parentId){
                        leftMenuConfig=menuConfig[i][3];
                        break;
                    }
                }
                for(var i=0;i<leftMenuConfig.length;i++){
                    var submenu=leftMenuConfig[i][3];
                    if(!submenu)continue;
                    menuHtml=menuHtml+"<div class=\"MIS_LeftTitle\"><a href=\"#\">"+leftMenuConfig[i][1]+"</a></div>";
                    menuHtml=menuHtml+"<div ><ul class=\"MIS_LeftNav\">";
                    if(submenu.length==0){
                        menuHtml=menuHtml+ "<li><a href=\"${pageContext.request.contextPath}"+leftMenuConfig[i][2]+"\" onclick=\"setNavTitle('"+ leftMenuConfig[i][1]+"')\" target=\"centerpageid\">"+leftMenuConfig[i][1]+"</a></li>";
                    }else
                    {
                        for(var j=0;j<submenu.length;j++){
                            if(submenu[j].length>0){
                                menuHtml=menuHtml+ "<li><a href=\"${pageContext.request.contextPath}"+submenu[j][2]+"\" onclick=\"setNavTitle('"+ leftMenuConfig[i][1]+"->"+submenu[j][1]+"')\" target=\"centerpageid\">"+submenu[j][1]+"</a></li>";
                            }
                        }
                    }
                    menuHtml=menuHtml+"</ul></div>";
                }

                $("#accordionContainer").html("");
                $("#accordionContainer").html("<div id=\"accordion\" class=\"MIS_LeftM\">"+menuHtml+"</div>");
                $("#accordion").accordion({
                    autoHeight: false
                    // height:300
                    //navigation: true


                });
            }
            function initLeftMenu(){
                for(var i=0;i<menuConfig.length;i++){
                    if(menuConfig[i][1]==undefined||menuConfig[i][1]=="退出"||menuConfig[i].length==0){
                            //删除空菜单和“退出”菜单
                           //alert("splice");
                            menuConfig.splice(i,1);
                            i--;
                        }

                }
                updateLeftMenu(menuConfig[0][0]);
            }


            function switchSysBar(){
                var arrowImgSrc=$("#arrowImg").attr("src");
                //当前状态为展开，现在点击后需要折叠起来
                if(arrowImgSrc.indexOf("02")>-1){
                    $("#accordionContainer").css("display", "none");
                    $("#ShrinkLeftArrow").css("left","3px");
                    $("#arrowImg").attr("src","style/frame/images/arrow_01.gif");
                    parent.switchSysBar("10,*");
                }else{// 当前状态为折叠，现在点击后需要展开还原
                    $("#accordionContainer").css("display", "");
                    $("#ShrinkLeftArrow").css("left","161px");
                    $("#arrowImg").attr("src","style/frame/images/arrow_02.gif");
                    parent.switchSysBar("168,*");
                }
            }



        </script>

    </head>

    <body class="MIS_LeftBox" id="LeftBox" scroll=no >


        <div id="accordionContainer">
            <div id="accordion" class="MIS_LeftM">
            </div>
        </div>
        <div class="MIS_ShrinkLeftBox">
            <div id="ShrinkLeftArrow">
                <div id="ShrinkLeftArrowContent">
                    <a href="javascript:switchSysBar()"><img id="arrowImg" src="style/frame/images/arrow_02.gif" width="7" height="28" /></a>
                </div>
            </div>
        </div>
    </body>
</html>