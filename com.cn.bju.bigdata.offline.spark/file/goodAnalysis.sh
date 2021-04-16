#! /bin/bash
dt=$(date -d '-1 day' '+%Y%m%d')
sh importHive.sh $dt
if [ $? -eq 0 ]; then
  echo '数据同步success'+$dt
else
  echo '数据同步fail'+$dt
  exit 1
fi
echo '执行中间表'
spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class middle.OrdersMiddle com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo success
else
  echo '执行中间表fail'
  exit 1
fi
spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class shop.GoodsAnalysis com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo '商品分析success'
else
  echo '商品分析fail'
fi

spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class shop.OneGoodsAnalysis com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo '单品分析success'
else
  echo '单品分析fail'
fi
spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class shop.DealAnlaysis com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo '交易分析success'
else
  echo '交易分析fail'
fi

spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class shop.ClintAnalysis com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo '客户分析success'
else
  echo '客户分析fail'
fi

spark-submit \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--queue users \
--executor-cores 2 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class shop.WarehouseAnalysis com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar ${dt}
if [ $? -eq 0 ]; then
  echo '仓库分析success'
else
  echo '仓库分析fail'
fi
