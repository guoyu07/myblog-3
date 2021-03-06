<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-09-17
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>show</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugin/bootstrap.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugin/jq.js"></script>
    <script src="<%=request.getContextPath() %>/plugin/bootstrap.js"></script>
    <style type="text/css">
        .label-success {
            background-color: #5cb85c;
        }

        .label {
            display: inline;
            padding: .2em .6em .3em;
            font-size: 75%;
            font-weight: bold;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: .25em;
        }
    </style>
</head>
<body>
<div class="container"></div>
<div class="panel-footer navbar-fixed-bottom"></div>
<script>
    $(function () {
        var id = window.location.search.split('=')[1];
        $.ajax({
            url: "<%=request.getContextPath() %>/articleController/loadOne",
            data: {"id": id},
            success: function (result) {
                var article = result.article;
                var tagNames = result.tagNames;
                var htmlStr = "";
                htmlStr += "<div class=\"page-header row\">";
                htmlStr += "<h1 class='bg-primary'>" + article.title + "</h1>";
                htmlStr += "</div>";
                htmlStr += "<div class='row' id='labelList'>";
                for (let i = 0; i < tagNames.length; i++) {
                    if (!tagNames[i].name) continue;
                    htmlStr += "<span class=\"label label-success\">" + tagNames[i].name + "</span>&nbsp;";
                }
                htmlStr += "</div>";
                htmlStr += "<div class='row'>";
                htmlStr += "<p class=\"lead\">";
                htmlStr += article.content;
                htmlStr += "</p>";
                htmlStr += "<p>" + article.create_time + "</p>";
                htmlStr += " </div>";
                htmlStr += " <div class=\"row\">\n";
                if (result.enableDelete) {
                    //可删除状态
                    htmlStr += "<button class=\"btn btn-danger pull-right delete\">删除</button>";
                }
                htmlStr += "<button class=\"btn btn-success pull-right back\">返回</button>" +
                    //                    "<button class=\"btn btn-success pull-right criticism\">criticism</button>" +
                    "</div>";
                htmlStr += "<br/><br/><br/>";
                $("div.container").append(htmlStr);

                $("button.back").on("click", function () {
                    window.location.href = "<%=request.getContextPath() %>/index.jsp";
                });

                $("button.delete").on("click", function () {
                    if (confirm("Are you sure to delete ???")) {
                        $.ajax({
                            url: "<%=request.getContextPath() %>/articleController/delete",
                            data: {"id": id},
                            type: "post",
                            dataType: "json",
                            success: function (result) {
                                if (result.rs === "ok") {
                                    alert("delete ok!");
                                    window.location.href = "<%=request.getContextPath() %>index.jsp";
                                }
                            },
                            error: function () {
                                alert("why error");
                            }
                        })
                    }
                });

                $("#button.criticism").on("click", function () {
                    //弹出个框框
                    $.ajax({
                        url: "<%=request.getContextPath() %>/articleController/criticism",
                        data: {"title": "temp_title", "message": "temp_message"},
                        type: "post",
                        success: function (rs) {
                            if (rs.state === "ok") {
                                alert("评论成功");
                                //关闭那个弹出的框框
                            } else {
                                alert("评论失败");
                            }
                        }
                    })
                });
            },
            error: function () {
                // errro
            }
        });

    });

</script>

</body>
</html>
