/**
 * Created by 彦祖 on 2017/7/13.
 */
function avc(){
	alert("dy");
	
}
avc();
var Form = antd.Form;
var Table = antd.Table;
var Column = Table.Column;
var Button = antd.Button;
var Modal = antd.Modal;
var Icon = antd.Icon;
var message = antd.message;
var Spin = antd.Spin;
var DatePicker = antd.DatePicker;
var RangePicker = DatePicker.RangePicker;
var Select = antd.Select;
var Option = Select.Option;
var FormItem = Form.Item;
var Row = antd.Row;
var Col = antd.Col;
var moment = moment;


const columns = [ {
	  title: '活动id',
	  dataIndex: 'activityId',
	}, {
	  title: '活动名称',
	  dataIndex: 'activityName',
	}, {
	  title: '区域描述',
	  dataIndex: 'activityDesc',
	}, {
	  title: '创建人',
	  dataIndex: 'createName',
	}, {
	  title: '电话',
	  dataIndex: 'telephone',
	}, {
	  title: '时间',
	  dataIndex: 'createDate',
	}, {
	  title: '时间',
	  dataIndex: 'endDate',
	}, {
	  title: '时间',
	  dataIndex: 'startDate',
	}, {
	  title: '领域',
	  dataIndex: 'orgRange',
	},{
		  title: '境界',
		  dataIndex: 'orgLevel',
		}
	];

	const data = [];
	for (let i = 0; i < 46; i++) {
	  data.push({
	    key: i,
	    orgLevel:'省分',
	    activityDailyId: `${i}`,
	    activityDailyName: '活动${i}',
	    activityTheme:'存话费送流量',
	    activityCycle:'一次性',
	    parentActivity:'执行活动',
	    starttime:'2018-1-0',
	    endtime:'2018-1-1',
	    stateDesc:'暂存',
	    createUserName:'ysc'
	  });
	}


var TableDemo = React.createClass({
    getInitialState: function () {
        return {
            data: [],
            trade: [],
            total: "",
            count: "",
            loading: false,
            currentPage: 1,
        }

    },
    componentDidMount: function () {
        this.setState({loading: false});
        let tmpObj = {
            "trade" : "",
            "startDate" : "",
            "endDate" : "",
            "page" : 1,
            "pageSize" : 10,
        };
        $.ajax({
        	type : "POST",
            url: "../cwc/list",
            dataType: "json",
            data:{"jsonData": JSON.stringify(tmpObj)},
            success: function (data) {
                console.log(data);
                this.setState({
                    data : data.list,
                    count : data.totalCount,
                    loading : false,
                })
            }.bind(this)

        });
        $.ajax({
        	type : "POST",
            url: "../cwc/list",
            dataType: "json",
            success: function (data) {
                this.setState({trade:data})
            }.bind(this)
        });

    },
    search: function (e) {
        e.preventDefault();
        this.setState({loading: true});

        console.info(this.props.form.getFieldsValue());
        //点击查询从第一页显示
        let tmpObj = {
            "trade" : this.props.form.getFieldValue('trade') ? this.props.form.getFieldValue('trade') : "",
            "startDate" : this.props.form.getFieldValue('startDate') ? this.props.form.getFieldValue('startDate')[0].format("YYYY-MM-DD") : "",
            "endDate" : this.props.form.getFieldValue('startDate') ? this.props.form.getFieldValue('startDate')[1].format("YYYY-MM-DD") : "",
            "page" : 1,
        };
        $.ajax({
        	type : "POST",
            url: "../cwc/list",
            dataType: "json",
            data: {jsonData : JSON.stringify(tmpObj)},
            success: function (data) {
                console.log(data);
                this.setState({
                    data: data.jsonArray,
                    count: data.count,
                    loading: false,
                    currentPage:1,
                })
            }.bind(this)

        });
    },
    changePage: function (page,pageSize) {
        let tmpObj = {
            "page" : page,
            "trade" : this.props.form.getFieldValue('trade') ? this.props.form.getFieldValue('trade') : "",
            "startDate" : this.props.form.getFieldValue('startDate') ? this.props.form.getFieldValue('startDate')[0].format("YYYY-MM-DD") : "",
            "endDate" : this.props.form.getFieldValue('startDate') ? this.props.form.getFieldValue('startDate')[1].format("YYYY-MM-DD") : "",
        };
        $.ajax({
        	type : "POST",
            url: "../cwc/list",
            dataType: "json",
            data: {jsonData : JSON.stringify(tmpObj)},
            success: function (data) {
                console.log(data);
                this.setState({
                    data : data.jsonArray,
                    count : data.count,
                    currentPage : page
                })
            }.bind(this)

        });
    },
    render: function () {
        let self = this;
        const { getFieldDecorator } = this.props.form;
        var tradeNameOption = (trade)=>{
            return <Option key={trade.tradetype}>{trade.tradename}</Option>
        };
        const formItemLayout = {
            labelCol: { span: 4 },
            wrapperCol: { span: 20 },
        };
        return (
            <Form onSubmit={this.search}>
                <Row gutter={24}>
                    <Col span={7} offset={2}>
                        
                    </Col>
                    <Col span={7}>
                        <FormItem {...formItemLayout} label="开始时间">
                            {getFieldDecorator('startDate')(
                                <RangePicker size="large" />
                            )}
                        </FormItem>
                    </Col>
                    <Col span={6} offset={2}>
                        <FormItem>
                            <Button htmlType="submit" >查询</Button>
                            <Button onClick={()=>this.props.form.resetFields()} >清空</Button>
                            <Button type="primary" onClick={()=>location.href="../antd/form"}>新增</Button>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span={20} offset={2}>
                        <Table  loading={this.state.loading} size="middle" columns={columns} dataSource={this.state.data} >
                           
                        </Table>
                    </Col>
                </Row>
            </Form>
        )
    }
});

var TableDemoCreate = Form.create({})(TableDemo);
console.log(TableDemoCreate);

ReactDOM.render(<TableDemoCreate />, demo2);