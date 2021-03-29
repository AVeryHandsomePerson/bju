layui.use('form', function () {
    var form = layui.form;
    form.render("radio");
});
layui.use(['layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer
        , laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#date'
    });
    //监听提交
    form.on('submit(demo1)', function (data) {
        $.ajax({
            type: "POST",
            url: "/add_user",
            data: JSON.stringify(data.field),
            dataType: "text",
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                // console.log("===>"+data)
                if (data == 'true') {
                    layer.msg("录入成功");
                    $("#my_question")[0].reset();
                } else {
                    layer.msg("录入失败,信息可能已存在");
                }

            },
            error: function (data) {
                console.log(data);
                layer.msg("提交失败");
            }
        });
        return false;
    });
});


layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#test'
        , url: '/find_user'
        , page: true
        , cellMinWidth: 80
        , cols: [
            [
                {field: 'username', width: 120, title: '姓名'}
                , {field: 'sex', width: 120, title: '性别'}
                , {field: 'age', width: 120, title: '年龄'}
                , {field: 'identity', width: 120, title: '身份证号'}
                , {field: 'phone', width: 120, title: '电话'}
                , {field: 'site', width: 120, title: '常住地址'}
                , {field: 'emergency_contact', width: 120, title: '紧急联系人'}
                , {field: 'occupation_classify', width: 120, title: '职位分类'}
                , {field: 'duty', width: 120, title: '职务'}
                , {field: 'entry_time', width: 120, title: '入职日期'}
                , {field: 'remarks', width: 120, title: '备注'}
                , {field: 'bank_card', width: 120, title: '备注'}
                // , {field: 'edit', title: '操作', toolbar: '#barDemo'}
            ]
        ]
    });
});

// // 管理员访问
layui.use('table', function () {
    var table = layui.table;
    //监听单元格编辑
    table.on('edit(luyu_test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data); //所在行的所有相关数据
    });
});



layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#luyu_test'
        , url: '/find_user'
        , id: 'testReload'
        , limit: 20
        , page: true
        , cellMinWidth: 80
        , cols: [
            [
                {field: 'username', width: 120, title: '姓名', edit: 'text'}
                , {field: 'sex', width: 120, title: '性别', edit: 'text'}
                , {field: 'age', width: 120, title: '年龄', edit: 'text'}
                , {field: 'identity', width: 120, title: '身份证号', edit: 'text'}
                , {field: 'phone', width: 120, title: '电话', edit: 'text'}
                , {field: 'site', width: 120, title: '常住地址', edit: 'text'}
                , {field: 'emergency_contact', width: 120, title: '紧急联系人', edit: 'text'}
                , {field: 'occupation_classify', width: 120, title: '职位分类', edit: 'text'}
                , {field: 'duty', width: 120, title: '职务', edit: 'text'}
                , {field: 'entry_time', width: 120, title: '入职日期', edit: 'text'}
                , {field: 'remarks', width: 120, title: '备注', edit: 'text'}
                , {field: 'bank_card', width: 120, title: '银行卡号', edit: 'text'}
                , {field: 'edit', title: '操作', toolbar: '#barDemo'}
            ]
        ]
    });
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                var url = "/del_user"
                Eidt(data,url)
                layer.close(index);
            });
        }
        else if(obj.event === 'edit'){
                url = "/up_user"
                Eidt(data,url,table);
        }
    });
});


function queryType(){
    layui.use('table', function () {
        let table = layui.table;
        // let str=damo3.getValue('nameStr');
        var name = $('#find_name').val();
        queryTypeAll(table,name)
    })
}
function queryTypeAll(table,name) {
    table.render({
        elem: '#luyu_test'
        , url: '/find_user?'+"name="+name
        , id: 'testReload'
        , limit: 20
        , page: true
        , cellMinWidth: 80
        , cols: [
            [
                {field: 'username', width: 120, title: '姓名', edit: 'text'}
                , {field: 'sex', width: 120, title: '性别', edit: 'text'}
                , {field: 'age', width: 120, title: '年龄', edit: 'text'}
                , {field: 'identity', width: 120, title: '身份证号', edit: 'text'}
                , {field: 'phone', width: 120, title: '电话', edit: 'text'}
                , {field: 'site', width: 120, title: '常住地址', edit: 'text'}
                , {field: 'emergency_contact', width: 120, title: '紧急联系人', edit: 'text'}
                , {field: 'occupation_classify', width: 120, title: '职位分类', edit: 'text'}
                , {field: 'duty', width: 120, title: '职务', edit: 'text'}
                , {field: 'entry_time', width: 120, title: '入职日期', edit: 'text'}
                , {field: 'remarks', width: 120, title: '备注', edit: 'text'}
                , {field: 'bank_card', width: 120, title: '银行卡号', edit: 'text'}
                , {field: 'edit', title: '操作', toolbar: '#barDemo'}
            ]
        ]
    });
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                var url = "/del_user"
                Eidt(data,url)
                layer.close(index);
            });
        }
        else if(obj.event === 'edit'){
            url = "/up_user"
            Eidt(data,url,table);
        }
    });
}


// 编辑的方法
function  Eidt(data,url,table) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "text",
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            if(url==="/del_user"){
                layer.msg("删除成功");
            }else if(url === "/up_user"){
                layer.msg("更新成功");
                //更新成功后自动刷新
                table.reload('testReload', {

                })
            }

        },
        error: function (data) {
            if(url==="/del_user"){
                layer.msg("删除失败");
            }else if(url === "/up_user"){
                layer.msg("更新成功");
            }

        }
    })
}