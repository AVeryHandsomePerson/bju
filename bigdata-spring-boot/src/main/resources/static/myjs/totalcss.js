layui.use('element', function () {
    let $ = layui.jquery
        , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    //触发事件
    let active = {
        tabChange: function () {
            //切换到指定Tab项
            element.tabChange('demo', '22'); //切换到：用户管理
        }
    };
    $('.site-demo-active').on('click', function () {
        let othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

});
//刷新
layui.use('form', function () {
    let form = layui.form;
    form.render("radio");
})
//删除更新数据
function find_de_up(data,obj){
    if (obj.event === 'detail') {
        layer.msg('ID：' + data.id + ' 的查看操作');
    } else if (obj.event === 'del') {
        layer.confirm('真的删除行么', function (index) {
            obj.del();
            var url = "/del" + "?username=" + data.username + "&sex=" + data.sex + "&dateTime=" + data.dateTime
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data),
                dataType: "text",
                contentType: 'application/json;charset=UTF-8',
                success: function (data) {
                    layer.msg("删除成功");
                },
                error: function (data) {
                    layer.msg("删除失败");
                }
            })
            layer.close(index);
        });
    } else if (obj.event === 'edit') {
        $.ajax({
            type: "POST",
            url: "/updata",
            data: JSON.stringify(data),
            dataType: "text",
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                layer.msg("提交成功");
            },
            error: function (data) {
                layer.msg("提交失败");
            }
        })
    }
}
//历史积分默认展示
layui.use('table', function () {
    let table = layui.table;
    table.render({
        elem: '#test'
        , url: '/find'
        , toolbar: '#ssDemo' //开启头部工具栏，并为其绑定左侧模板
        , id: 'testReload'
        , cellMinWidth: 80
        , page: true //开启分页
        , cols:integral_management_user
    });
    table.on('tool(demo)', function (obj) {
        let data = obj.data;
        find_de_up(data,obj)
    });

});
//动态获取下拉框分类
data1 = [{ name: '前厅', value: 1 },{ name: '后厨', value: 2 }]
damo1=xmSelect.render({
    el: '#demo1',
    tips:'员工所属区域',
    data: data1
})
//历史积分时间查询
function search() {
    let time_tmp = $("#time_range").val()
    //选择框
    let str=damo1.getValue('nameStr');
    layui.use('table', function () {
        let table = layui.table;
        table.render({
            elem: '#test'
            , url: '/find?timerange=' + time_tmp+'&occupation_classify='+str
            , cellMinWidth: 80
            , id: 'Reload'
            , toolbar: '#ssDemo' //开启头部工具栏，并为其绑定左侧模板
            , page: true
            , cols: integral_management_user
        });
        table.on('tool(demo)', function (obj) {
            let data = obj.data;
            find_de_up(data,obj)
        });
    });
}
var integral_management_user = [
    [
        {field: 'username', width: 120, title: '姓名'}
        , {field: 'data_number', title: '当天得分', width: 120, minWidth: 100,edit: 'text', sort: true}
        , {field: 'dateTime', width: 120, title: '日期', sort: true}
        , {field: 'cause', title: '备注',edit: 'text'}
        , {field: 'edit', title: '操作', toolbar: '#barDemo'}
    ]
]
var add_integral = [
    [
        {type: 'checkbox', fixed: 'left'}
        , {field: 'username', width: 120, title: '姓名'}
        , {field: 'data_number', title: '当天得分', width: 120, minWidth: 100, sort: true, edit: 'number'}
        , {field: 'dateTime', width: 120, title: '日期', sort: true, edit: 'text'}
        , {field: 'cause', title: '备注', edit: 'text'}
    ]
]
layui.use('laydate', function () {
    let laydate = layui.laydate;
    //日期范围
    laydate.render({
        elem: '#time_range'
        ,range: true
    });
    //日期范围
    laydate.render({
        elem: '#time_diagram'
        , range: true
    });
});
function add_user(data,table) {
    $.ajax({
        type:"POST",
        url:"/add_integral",
        data : JSON.stringify(data),
        dataType:"text",
        contentType: 'application/json;charset=UTF-8',
        success:function (data) {
            //更新成功后自动刷新
            table.reload('testReload')
            table.reload('sumIntegral')
            layer.msg("提交成功");
        },
        error: function (data) {
            console.log(data)
            layer.msg("提交失败");
        }
    })
}
var damo3 =xmSelect.render({
    el: '#demo3',
    tips:'员工所属区域',
    data: [
        {name: '前厅', value: 1},
        {name: '后厨', value: 2},
    ]
})
//查询用户信息和职责分类
layui.use('table', function () {
    let table = layui.table;
    let str='';
    queryTypeAll(table,str)
});
function queryType(){
    layui.use('table', function () {
        let table = layui.table;
        let str=damo3.getValue('nameStr');
        queryTypeAll(table,str)
    })
}
function queryTypeAll(table,str) {
    table.render({
        elem: '#entering'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , url: '/find_user_name?type='+str
        , cellMinWidth: 80
        , cols: add_integral
    });
    //头工具栏事件
    table.on('toolbar(demo)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                let data = checkStatus.data;
                add_user(data,table)
                break;
            case 'getCheckLength':
                let datas = checkStatus.data;
                layer.msg('提交成功');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
    });
}
function sx(table,table_name) {
    table.reload(table_name, {

    })
}