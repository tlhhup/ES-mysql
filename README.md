# ES-mysql
es和mysql学习


1. aggregation相当于数据库中的聚合函数

		"aggregations" : {
		    "<aggregation_name>" : {
		        "<aggregation_type>" : {
		            <aggregation_body>
		        }
		        [,"meta" : {  [<meta_data_body>] } ]?
		        [,"aggregations" : { [<sub_aggregation>]+ } ]?
		    }
		    [,"<aggregation_name_2>" : { ... } ]*
		}
2. 通过Logstash可以实现从数据库到ES的数据导入，通过定义`input`和`output`节点进行处理