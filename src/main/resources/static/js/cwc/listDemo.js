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
var Input=antd.Input;






var ListDemo = React.createClass({
    getInitialState: function () {
        return {
            dataSource: [],
            trade: [],
            total: "",
            count: "",
            loading: false,
            currentPage: 1,
            tellbody:"",
            tellname:"ysc"
        }

    },
    componentDidMount: function () {
        this.setState({loading: false});
       
        $.ajax({
        	type : "POST",
            url: "../cwc/selectList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                this.setState({
                	dataSource : data,
                    count : data.totalCount,
                    loading : false,
                })
            }.bind(this)

        });
      

    },handTellClick:function(){
    	$.ajax({
	    	url:"../cwc/saveTalk",
	    	type:"post", 
	    	 dataType: "json",
	    	data:{
	    		userTalkDesc:this.state.tellbody,
	    		userName:"cwc"
	    	},async:true,
	    	success:function(data){
	    		console.log(data);
	    		this.setState({
                	dataSource : data,
                    loading : false,
                    tellbody:""
                })
	    	}.bind(this)
	    });
    },Tellchange:function(event){
    	this.setState({tellbody:event.target.value});
    },
    
    render: function () {
        let self = this;
        return (
        		
      
           <ul className="listBg" id="ul_rwardList">
           {
               this.state.dataSource.map(function (item) {
                   return (
                       <li className="tellBox" key={item.chatID}>
   
                      <Row type="flex" justify="center">
                  		<Col span="12">
                  		
                  		
                        <Row type="flex" justify="start">
                        	<Col span="12">
                        	<div className="fx1">评论人：{item.userName}||评论时间{item.createTime}</div>
                        	
                        	</Col>
                       </Row> 
                        
                       <Row type="flex" justify="center">
                        <Col span="20">
                        <div className="listContent">{item.userTalkDesc}</div>
                        </Col>
                        
                        </Row> 
                  		
                  		
                  		</Col>
                      </Row>
                       </li>
                   )})
           }
           <div>
           <Row type="flex" justify="center">
     		<Col span="12">
     		评论区
     		<Input type="textarea" placeholder="随便写" value={this.state.tellbody}  onChange={this.Tellchange}/>
     		</Col>
         </Row>
         
         <Row type="flex" justify="center">
  		<Col span="12">
  		评论区
  		<Button type="primary" onClick={this.handTellClick}>提交</Button>
  		</Col>
      </Row>
         
         
           
           </div>
       </ul>
           
         
                
           
        )
    }
});


ReactDOM.render(<ListDemo />, demo3);