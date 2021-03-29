spark-submit  \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--executor-cores 1 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class middle.OrdersMiddle /export/servers/bju/com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar 20210322

spark-submit  \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--executor-cores 1 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class common.CommonAnalysis /export/servers/bju/com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar 20210322

spark-submit  \
--master yarn \
--driver-memory 2g \
--executor-memory 3g \
--num-executors 3 \
--executor-cores 1 \
--jars /export/servers/bju/mysql-connector-java.jar \
--class goods.GoodsAnalysis /export/servers/bju/com.cn.bju.bigdata.offline.spark-1.0-SNAPSHOT.jar 20210322


