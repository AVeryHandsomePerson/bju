var damo2 =xmSelect.render({
    el: '#demo2',
    tips:'员工所属区域',
    data: [
        {name: '前厅', value: 1},
        {name: '后厨', value: 2},
    ]
})
//总积分表
layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#total_pa'
        , url: '/sum_find'
        , toolbar: '#sumJfDemo' //开启头部工具栏，并为其绑定左侧模板
        , id: 'sumIntegral'
        , limit: 20
        , page: true
        , cellMinWidth: 80
        , cols: [
            [
                  {field:'numbers', width:120, title: '序号', sort: true,type: 'numbers'}
                  ,{field: 'username', width: 120, title: '姓名'}
                  ,{field: 'total', width: 120, title: '总积分', sort: true}
                  ,{field: 'data_number', width: 120, title: '当天得分', sort: true}
                  ,{field: 'cause', width: 120, title: '备注'}
                  ,{field: 'dateTime', width: 120, title: '最后得分时间'}
            ]
        ]
    });
});
function total_search() {
    var time_tmp = $("#time_total_range").val()
    let str=damo2.getValue('nameStr');
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#total_pa'
            , url: '/sum_find?timerange='+time_tmp+'&occupation_classify='+str
            , toolbar: '#sumJfDemo' //开启头部工具栏，并为其绑定左侧模板
            , cellMinWidth: 80
            , limit: 20
            , page: true
            , cols: [
                [
                    {field:'numbers', width:120, title: '序号', sort: true,type: 'numbers'}
                    ,{field: 'username', width: 120, title: '姓名'}
                    ,{field: 'total', width: 120, title: '总积分', sort: true}
                    ,{field: 'data_number', width: 120, title: '当天得分', sort: true}
                    ,{field: 'cause', width: 120, title: '备注'}
                    ,{field: 'dateTime', width: 120, title: '最后得分时间'}
                ]
            ]
        });
    });
}
layui.use('laydate', function(){
    var laydate = layui.laydate;
    //日期范围
    laydate.render({
        elem: '#time_total_range'
        ,range: true
    });
});
// 删除
function f(url) {
    $.ajax({
        type:"POST",
        url:url,
        dataType:"text",
        contentType: 'application/json;charset=UTF-8',
        success:function (data) {
            let table = layui.table;
            table.reload('testReload')
            layer.msg("删除成功");
        },
        error: function (data) {
            layer.msg("删除失败");
        }
    })
}
function del(){
    var time_tmp = $("#time_range").val()
    var url = ''
    if (time_tmp == null){
        layer.confirm('确定删除所有数据',{
            btn: ['确定', '取消']
        },function () {
            url = "/deltimehistory"+"?dateTime="
            f(url)

        },function () {

        });
    }else {
        layer.confirm('确定删除'+time_tmp+'所有数据',{
            btn: ['确定', '取消']
        },function () {
            url = "/deltimehistory"+"?dateTime="+time_tmp
            f(url)
        },function () {
        });
    }
}