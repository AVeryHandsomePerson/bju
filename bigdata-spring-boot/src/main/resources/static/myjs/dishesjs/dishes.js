//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function () {
    var element = layui.element;

    //…
});
layui.use(['laydate', 'upload'], function () {
    var laydate = layui.laydate, upload = layui.upload;
    //日期范围
    // laydate.render({
    //     elem: '#time_total_range'
    //     ,range: true
    // });
    //年选择器
    laydate.render({
        elem: '#time_total_range'
        , type: 'year'
    });
    laydate.render({
        elem: '#time_graph_range'
        , type: 'year'
    });
    //指定允许上传的文件类型
    upload.render({
        elem: '#test3'
        , url: '/read_file' //改成您自己的上传接口
        , accept: 'file' //普通文件
        , done: function (res) {
            // console.log(res)
            table.render({
                elem: '#finds_dishes'
                , toolbar: '#addTableDishes' //开启头部工具栏，并为其绑定左侧模板
                , limit: 100 //开启头部工具栏，并为其绑定左侧模板
                , cols: [[ //标题栏
                    {field: 'name', title: '类型', width: 250, minWidth: 100, edit: 'text'}
                ]]
                , data: [res]
                , cellMinWidth: 80
            });
            layer.msg('上传成功');
        }
    });
});
var integral_management_dishes = [
    [
        {field: 'mon', title: '月份', width: 250, minWidth: 100}
        , {field: 'all_sales', title: '销售量', width: 250, minWidth: 100}
        , {field: 'all_sales_ratio', width: 250, title: '销售占比'}
        , {field: 'all_money', width: 250, title: '销售金额'}
        , {field: 'all_money_ratio', width: 250, title: '销售金额占比'}
        // , {field: 'edit', title: '操作', toolbar: '#barDemo'}
    ]
]
//动态获取下拉框的值
var typeData = ""
$.ajaxSetup({async: false});
$.get("/find_dishes_name",{dishes_type: bigDishes}, function (data) {
    typeData = data
})
var damo2 = xmSelect.render({
    el: '#demo2',
    tips: '菜品名称',
    filterable: true,
    searchTips: '搜索菜品',
    data: typeData
})
var dishes_graph = xmSelect.render({
    el: '#dishes_graph',
    tips: '菜品名称',
    filterable: true,
    searchTips: '搜索菜品',
    data: typeData
})

//历史积分默认展示
layui.use('table', function () {
    let table = layui.table;
    let time_tmp = $("#time_total_range").val()
    let str = damo2.getValue('nameStr');
    let type = bigDishes
    table.render({
        elem: '#find_dishes'
        , url: '/find_dishes' + '?timerange=' + time_tmp + '&dishes_type=' + type+ '&dishes_name=' + str
        , toolbar: '#sumJfDemo' //开启头部工具栏，并为其绑定左侧模板
        , id: 'find_dishes'
        , cellMinWidth: 80
        // , page: true //开启分页
        , cols: integral_management_dishes
    });
});
function find_search() {
    var time_tmp = $("#time_total_range").val()
    let type = bigDishes
    let str = damo2.getValue('nameStr');
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#find_dishes'
            , url: '/find_dishes' + '?timerange=' + time_tmp + '&dishes_type=' + type + '&dishes_name=' + str
            , toolbar: '#sumJfDemo' //开启头部工具栏，并为其绑定左侧模板
            , cellMinWidth: 80
            , id: 'find_dishes'
            , cols: integral_management_dishes
        });
    });
}
layui.use('table', function () {
    var table = layui.table;
    //展示已知数据
    table.render({
        elem: '#find_add_dishes'
        , toolbar: '#addTableDishes' //开启头部工具栏，并为其绑定左侧模板
        , limit: 100 //开启头部工具栏，并为其绑定左侧模板
        , cols: [[ //标题栏
            {field: 'dishes_type', title: '类型', width: 250, minWidth: 100, edit: 'text'}
            , {field: 'dishes_name', title: '菜名', width: 250, minWidth: 100, edit: 'text'}
            , {field: 'dishes_sales', width: 250, title: '销售量', edit: 'text'}
            , {field: 'dishes_money', width: 250, title: '销售金额', edit: 'text'}
            , {field: 'sales_ratio', width: 250, title: '销售量占比', edit: 'text'}
            , {field: 'money_ratio', width: 250, title: '销售金额占比', edit: 'text'}
        ]]
        , data: [{
            "dishes_type": ""
            , "dishes_name": ""
            , "dishes_sales": ""
            , "dishes_money": ""
            , "sales_ratio": ""
            , "money_ratio": ""
        }]
        , cellMinWidth: 80
    });
    // table.on('toolbar(demo)', function (obj) {


    // });
});
function xz() {
    var table = layui.table;
    var dataBak = [];
    var tableBak = table.cache.find_add_dishes;
    for (var i = 0; i < tableBak.length; i++) {
        dataBak.push(tableBak[i]);      //将之前的数组备份
    }
    dataBak.push({
        "dishes_type": ""
        , "dishes_name": ""
        , "dishes_sales": ""
        , "dishes_money": ""
        , "sales_ratio": ""
        , "money_ratio": ""
    });
    table.reload("find_add_dishes", {
        data: dataBak   // 将新数据重新载入表格
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
//图形展示
myChartMoney = echarts.init(document.getElementById('chartmain'));
myChartSales = echarts.init(document.getElementById('chartsales'));
myChartMoneyRatio = echarts.init(document.getElementById('chartmoneyratio'));
myChartSalesRatio = echarts.init(document.getElementById('chartsalesratio'));
dishesGraph('','')
function queryGraph() {
    timerange = $("#time_graph_range").val()
    dishes_name = dishes_graph.getValue('nameStr')
    dishesGraph(timerange,dishes_name)
}
function dishesGraph(time,name){
    $.get('/find_dishes_ratio', {timerange: time,dishes_name: name,dishes_type: bigDishes}, function (data) {
        // console.log(data)
        array_mon = []
        array_money = []
        array_sales = []
        array_money_ratio = []
        array_sales_ratio = []
        for (let i = 0; i < data.length; i++) {
            array_mon[i] = data[i].mon
            array_money[i] = data[i].all_money
            array_sales[i] = data[i].all_sales
            array_money_ratio[i] = data[i].all_money_ratio
            array_sales_ratio[i] = data[i].all_sales_ratio
        }
        myChartMoney.setOption({
            title: {
                text: "金额"
            },
            xAxis: {
                type: 'category',
                data: array_mon
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: array_money,
                type: 'line',
                itemStyle: {normal: {label: {show: true}}}
            }]
        });
        myChartSales.setOption({
            title: {
                text: "销量"
            },
            xAxis: {
                type: 'category',
                data: array_mon
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: array_sales,
                type: 'line',
                itemStyle: {normal: {label: {show: true}}}
            }]
        });
        myChartMoneyRatio.setOption({
            title: {
                text: "金额比"
            },
            xAxis: {
                type: 'category',
                data: array_mon
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: array_money_ratio,
                type: 'line',
                itemStyle: {normal: {label: {show: true}}}
            }]
        });
        myChartSalesRatio.setOption({
            title: {
                text: "销量比"
            },
            xAxis: {
                type: 'category',
                data: array_mon
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: array_sales_ratio,
                type: 'line',
                itemStyle: {normal: {label: {show: true}}}
            }]
        });
    })
}







// option &&

