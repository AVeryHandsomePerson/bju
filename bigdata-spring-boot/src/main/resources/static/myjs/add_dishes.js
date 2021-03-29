//动态获取下拉框的值
var typeData = ""
$.ajaxSetup({async: false});
$.get("/find_dishes_type", function (data) {
    typeData = data
})
var damo2 = xmSelect.render({
    el: '#demo2',
    tips: '菜品分类',
    data: typeData
})
layui.use(['upload', 'table'], function () {
    upload = layui.upload, table = layui.table;
    //指定允许上传的文件类型
    upload.render({
        elem: '#test3'
        , url: '/read_file' //改成您自己的上传接口
        , accept: 'file' //普通文件
        , done: function (res) {
            table.render({
                elem: '#find_add_dishes'
                , toolbar: '#addTableDishes' //开启头部工具栏，并为其绑定左侧模板
                , limit: 1000 //开启头部工具栏，并为其绑定左侧模板
                , cols: [[ //标题栏
                    {field: 'dishes_name', title: '菜品', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'specI', title: '规格', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'dishes_type', title: '分类', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'dishes_sales', title: '销量', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'dishes_sales_times', title: '销售次数', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'sales_times_ratio', title: '销售次数占比', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'dishes_money', title: '销售金额', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'discounts_money', title: '优惠金额', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'after_discount_money', title: '折后金额', width: 250, minWidth: 100, edit: 'text'},
                    {field: 'money_ratio', title: '折后金额占比', width: 250, minWidth: 100, edit: 'text'}
                ]]
                , data: res.list
                , cellMinWidth: 80
            });
            layer.msg('上传成功');
        }
    });
});
typeDishes('')
//条件搜索
function total_search() {
    let str = damo2.getValue('nameStr');
    typeDishes(str)
}
//菜品所属表格展示
function typeDishes(str) {
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#find_add_mapping'
            , url: '/find_dishes_mapping?type=' + str
            , id: 'typeDishes'
            , toolbar: '#sumJfDemo' //开启头部工具栏，并为其绑定左侧模板
            , cellMinWidth: 80
            , limit: 20
            , page: true
            , cols: [
                [
                    {field: 'dishes_name', title: '菜品', width: 250, minWidth: 100},
                    {field: 'dishes_type', title: '分类', width: 250, minWidth: 100}
                ]
            ]
        });
    })
}

function addDishe() {
    var table = layui.table;
    var tableBak = table.cache.find_add_dishes;
    $.get("/add_dishes", {date: tableBak, maplength: tableBak.length}, function (data) {
        if (data) {
            layer.msg("提交成功");
        } else {
            layer.msg("提交失败");
        }
    })
}

//弹窗
layui.use('layer', function () { //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    //触发事件
    var active = {
        offset: function (othis) {
            var type = othis.data('type')
            layer.open({
                type: 2
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + type //防止重复弹出
                , content: ['/add_dishes_mapping_index']
                , btn: ['确定', '取消']
                , area: ['390px', '260px']
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
                , yes: function (index, layero) {
                    var body = layer.getChildFrame('body', index); //得到iframe页的body内容
                    var dishes_name = body.find("#dishes_name").val();
                    var dishes_type = body.find("#dishes_type").val();
                    data = {"dishes_name": dishes_name, "dishes_type": dishes_type}
                    $.ajax({
                        type: "POST",
                        url: "/add_dishes_map",
                        data: JSON.stringify(data),
                        dataType: "text",
                        contentType: 'application/json;charset=UTF-8',
                        success: function (data) {
                            console.log(data)
                            //更新成功后自动刷新
                            layer.msg("提交成功");
                        },
                        error: function (data) {
                            console.log(data)
                            layer.msg("提交失败");
                        }
                    })
                    layer.msg('确定')
                    layer.closeAll();
                }, btn2: function () {
                    layer.msg('取消')
                }
            });
        }
    };
    $('#layerDemo .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});
