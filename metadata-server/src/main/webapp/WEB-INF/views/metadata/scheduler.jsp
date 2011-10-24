<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Websiteschema Scheduler Management</title>

        <link rel="stylesheet" type="text/css" href="resources/css/Ext.ux.form.LovCombo.css">
        <script type="text/javascript" src="js/packages.js"></script>
        <script type="text/javascript" src="js/Ext.ux.form.LovCombo.js"></script>
        <script type="text/javascript" src="js/Ext.ux.ThemeCombo.js"></script>
        <script type="text/javascript" src="js/dwrproxy.js"></script>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/interface/SchedulerService.js"></script>
    </head>

    <body>

        <div id="gridpanel"></div>

        <script type="text/javascript">
            var start = 0;
            var pageSize = 20;
            Ext.onReady(function(){

                var proxy = new Ext.data.DWRProxy(SchedulerService.getSchedulers, true);
                var recordType = new Ext.data.Record.create(schedulerRecordType);
                var store=new Ext.data.Store({
                    proxy : proxy,
                    reader : new Ext.data.ListRangeReader(
                    {
                        id : 'id',
                        totalProperty : 'totalSize'
                    }, recordType
                ),
                    remoteSort: false

                });
                var type_store = new Ext.data.SimpleStore(
                {
                    fields :['name','value'],
                    data:[
                        ['开始停止次数',1],
                        ['CRONTAB',0]
                    ]
                });

                // the column model has information about grid columns
                // dataIndex maps the column to the specific data field in
                // the data store
                //var nm = new Ext.grid.RowNumberer();
                var fm = Ext.form;
                var sm = new Ext.grid.CheckboxSelectionModel();  // add checkbox column
                var cm = new Ext.grid.ColumnModel([
                    //nm,
                    sm,
                    {
                        header: 'ID',
                        dataIndex: 'id',
                        width: 50
                    },
                    {
                        header: '起始URL',
                        dataIndex: 'startURLId',
                        width: 300,
                        editor: new fm.TextField({
                            allowBlank: false,
                            readOnly : true
                        })
                    },
                    {
                        header: '任务配置',
                        dataIndex: 'jobId',
                        width: 100,
                        editor: new fm.TextField({
                            allowBlank: false,
                            readOnly : true
                        })
                    },
                    {
                        header: '调度信息',
                        dataIndex: 'schedule',
                        width: 100,
                        editor: new fm.TextField({
                            allowBlank: false
                        })
                    },
                    {
                        header: '类型',
                        dataIndex: 'scheduleType',
                        width: 100,
                        hidden : false,
                        editor: new fm.ComboBox({
                            store : type_store,
                            triggerAction: 'all',
                            allowBlank: false,
                            forceSelection: true,
                            mode: 'local',
                            displayField:'name',
                            valueField:'value'

                        }),
                        renderer: function(value,metadata,record){
                            var index = type_store.find('value',value);
                            if(index!=-1){
                                return type_store.getAt(index).data.name;
                            }
                            return value;
                        }
                    },
                    {
                        header: '创建时间',
                        dataIndex: 'createTime',
                        width: 200,
                        editor: new fm.DateField({
                            allowBlank: false,
                            readOnly : true,
                            format: 'Y-m-d H:i:s'
                        })
                    }
                ]);

                // by default columns are sortable
                cm.defaultSortable = false;

                // trigger the data store load
                store.load({params : {
                        start : start,
                        limit : pageSize
                    },
                    arg : []});

                
                var grid = new Ext.grid.EditorGridPanel({
                    //el:'topic-grid',
                    renderTo: 'gridpanel',
                    width: '100%',
                    autoHeight: true,
                    clicksToEdit:1,
                    autoScroll: true,
                    //title: '分页和排序列表',
                    store: store,
                    trackMouseOver: false,
                    loadMask: true,
                    enableHdMenu: true,
                    sm: sm,
                    cm: cm,
                    
                    // inline toolbars
                    tbar: [ {
                            text: '新建',
                            tooltip: '新建记录',
                            iconCls: 'icon-add',
                            handler: handleAdd
                        }, '-',
                        {
                            text: '修改',
                            tooltip: '修改记录',
                            iconCls: 'icon-edit',
                            handler: handleEdit
                        }, '-',
                        {
                            text: '删除',
                            tooltip: '删除记录',
                            iconCls: 'icon-delete',
                            handler: handleDelete
                        }
                    ],
                    bbar: new Ext.PagingToolbar({
                        height: '22',
                        pageSize: pageSize,
                        store: store,
                        displayInfo: true
                    })
                });

                // render it
                grid.render();
                function handleAdd(){
                    var p = new recordType();
                    grid.stopEditing();
                    p.set("jobId","0");
                    p.set("startURLId","0");
                    p.set("schedule","10/* * * * *")
                    p.set("scheduleType","0");
                    store.insert(0, p);
                    grid.startEditing(0, 0);
                    SchedulerService.insert(p.data);
                    store.reload();
                }

                function handleEdit(){

                    var mr = store.getModifiedRecords();
                    for(var i=0;i<mr.length;i++){
                        Ext.MessageBox.alert("是否要更改" + mr[i].data["id"]+ "的配置");
                        SchedulerService.update(mr[i].data);
                    }
                    
                }

                //删除数据
                function handleDelete(){
                    var selections = grid.selModel.getSelections();
                    for (var i = 0,len = selections.length; i < len; i++) {
                        SchedulerService.deleteScheduler(selections[i].data);
                    }
                    store.reload();
                }

            });
        </script>

    </body>
</html>