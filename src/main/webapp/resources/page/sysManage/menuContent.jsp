<%--
  Created by IntelliJ IDEA.
  User: zzw
  Date: 2020/11/14
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <title>菜单管理</title>
</head>
<body>
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form id="searchFrm" class="layui-form" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单ID:</label>
            <div class="layui-input-inline">
                <input type="text" name="id" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">标题:</label>
            <div class="layui-input-inline">
                <input type="text" name="title" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 400px;">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-search"
                    id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="menuTable" lay-filter="menuTable"></table>
<div style="display: none;" id="menuToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>
</div>
<div id="menuBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">菜单ID:</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" lay-verify="required" autocomplete="off"
                           class="layui-input" id="id">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">标题:</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">图标:</label>
                <div class="layui-input-inline layui-icon">
                    <select name="icon">
                        <option value="&#xe609;">&#xe609;</option>

                        <option value="&#xe655;">&#xe655;</option>
                        <option value="&#xe634;">&#xe634;</option>
                        <option value="&#xe716;">&#xe716;</option>
                        <option value="&#xe630;">&#xe630;</option>

                        <option value="&#xe66f;">&#xe66f;</option>
                        <option value="&#xe61c;">&#xe61c;</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">链接:</label>
                <div class="layui-input-inline">
                    <input type="text" name="href" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">展开:</label>
                <div class="layui-input-inline">
                    <select name="spread">
                        <option value="0">关闭</option>
                        <option value="1">展开</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">父菜单:</label>
                <div class="layui-input-inline">
                    <select name="parentId" id="selectParentId">
                        <option value="0">作为父菜单</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置
                </button>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->

<script src="../../layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;

        var url;
        var mainIndex;

        var pData;
        selectPId();

        //绑定时间选择器
        laydate.render({
            elem: '#startTime'
        });
        laydate.render({
            elem: '#endTime'
        });

        var tableIns = table.render({
            elem: '#menuTable' // 渲染的目标对象
            , url: '/menu/menuList' // 数据接口
            , toolbar: '#menuToolBar' // 表格的工具条
            , cellMinWidth: 80 // 最小宽度
            , page: true // 开启分页
            , limit: 5 // 默认每页数据量
            , limits: [5, 10, 15, 20] // 可以选择的每页数据量
            , cols: [[
                {type: 'checkbox', fixed: 'left', width:'5%'}
                , {field: 'id', title: '菜单ID', align: 'center', width:'8%', sort: true}
                , {field: 'icon', title: '图标', align: 'center', width:'8%', style: 'font-family:layui-icon'}
                , {field: 'title', title: '标题', align: 'center', width:'16%'}
                , {field: 'href', title: '链接', align: 'center', width:'23%'}
                , {field: 'spread', title: '展开', align: 'center', width:'10%', templet: function (d) {
                            if (d.spread == '1') {
                                return '展开'
                            } else {
                                return '关闭'
                            }
                        }}
                , {field: 'parentId', title: '父菜单', align: 'center', width:'16%', templet: function (d) {
                        var t;
                        $.each(pData, function (index, item) {
                            if (item.id == d.parentId) {
                                t = item.title;
                                return true;
                            }
                        });
                        if (t === undefined) {
                            return "无";
                        } else {
                            return t;
                        }
                    }}
                , {fixed: 'right', title: '操作', align: 'center', width:'14%', toolbar: '#menuBar'}
            ]]
        });

        //监听事件 toolbar
        table.on('toolbar(menuTable)', function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddMenu();
                    break;
                case 'batchDelete':
                    batchDelete();
                    break;
            }
        });

        // 打开添加页面
        function openAddMenu() {
            mainIndex = layer.open({
                type: 1
                , title: '添加菜单'
                , content: $('#saveOrUpdateDiv')
                , area: ['800px', '400px']
                , success: function (index) {
                    // 成功打开后，清空表单数据
                    $('#dataFrm')[0].reset();
                    url = '/menu/addMenu';
                    $('#role').removeAttr("readonly");
                }
            });
        }

        // 批量删除
        function batchDelete() {
            // 得到选中的数据行
            var checkStatus = table.checkStatus('menuTable');
            var data = checkStatus.data;
            url = '/menu/deleteMenus';
            var params = "";
            $.each(data, function (index, elem) {
                if (index == 0) {
                    params += 'ids=' + elem.id;
                } else {
                    params += '&ids=' + elem.id;
                }
            });
            layer.confirm('真的要删除这些菜单信息吗？', function (index) {
                layer.close(index);
                var res = $.post(url, params, function () {
                    layer.msg(res.responseText);
                    tableIns.reload();
                });
            });
        }

        // 提交按钮的监听事件
        form.on('submit(doSubmit)', function (obj) {
            // 序列化表单数据
            var params = $('#dataFrm').serialize();

            var res = $.post(url, params, function (obj) {
                // 异步请求成功之后要做的事情
                layer.close(mainIndex);
                layer.msg(res.responseText);
                tableIns.reload();
            });
        });

        //监听工具条
        table.on('tool(menuTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    layer.close(index);
                    //向服务端发送删除指令
                    deleteMenu(data.id);
                });
            } else if (layEvent === 'edit') { //编辑
                openUpdateMenu(data);
            }
        });

        // 单个删除
        function deleteMenu(id) {
            url = '/menu/deleteMenu';
            var res = $.post(url, {'id': id}, function (obj) {
                // 提示删除成功与否
                layer.msg(res.responseText);
                // 刷新数据表格
                tableIns.reload();
            });
        }

        // 打开修改页面
        function openUpdateMenu(data) {
            mainIndex = layer.open({
                type: 1
                , title: '修改菜单'
                , content: $('#saveOrUpdateDiv')
                , area: ['800px', '400px']
                , success: function (index) {
                    // 填数据
                    form.val('dataFrm', data);
                    url = '/menu/editMenu';
                    $('#id').attr("readonly", "readonly");
                }
            });
        }

        // 下拉菜单里添加元素
        function selectPId() {
            $.post('/menu/pList', function (data) {
                pData = data.data;
                $.each(pData, function (index, item) {
                    $('#selectParentId').append(new Option(item.title, item.id));
                });
            });
        }

        // 查询
        $('#doSearch').click(function () {
            url = '/menu/getMenus';
            var params = $('#searchFrm').serialize();
            tableIns.reload({
                url: url + '?' + params
            });
        });

    });
</script>
</body>
</html>
