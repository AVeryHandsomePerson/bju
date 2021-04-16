package com.cn.bju.realtime.etl.process

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.cn.bju.common.bean.CanalRowData
import com.cn.bju.realtime.etl.`trait`.MysqlBaseETL
import com.cn.bju.realtime.etl.util.GlobalConfigUtil
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * @author ljh
 * @version 1.0
 */
class OrderDataETL(env: StreamExecutionEnvironment) extends MysqlBaseETL(env){




  /**
   * 根据业务抽取出来process方法，因为所有的ETL都有操作方法
   */
  override def process(): Unit = {
    /**
     * 实现步骤
     * 1：从kafka中消费出来订单数据，过滤出来订单表的数据
     * 2：将RowData转换成OrderDBEntity对象
     * 3：将OrderDBEntity对象转换成Json字符串
     * 4：将转换后的json字符串写入到kafka集群
     */
    //1：从kafka中消费出来订单数据，过滤出来订单表的数据
    val orderDataStream: DataStream[CanalRowData] = getKafkaDataStream().filter(_.getTableName == "itcast_orders")

    //2：将RowData转换成OrderDBEntity对象
//    val orderDBEntityDataStream: DataStream[OrderDBEntity] = orderDataStream.map(rowData => {
//      OrderDBEntity(rowData)
//    })

    //3：将OrderDBEntity对象转换成Json字符串
//    val orderDBEntityJsonDataStream: DataStream[String] = orderDBEntityDataStream.map(orderDBEntity => {
//      //将样例类转换成json字符串
//      JSON.toJSONString(orderDBEntity, SerializerFeature.DisableCircularReferenceDetect)
//    })

    //打印测试
//    orderDBEntityJsonDataStream.printToErr("订单数据>>>")

    //4：将转换后的json字符串写入到kafka集群
//    orderDBEntityJsonDataStream.addSink(kafkaProducer(GlobalConfigUtil.`output.topic.order`))


  }
}
