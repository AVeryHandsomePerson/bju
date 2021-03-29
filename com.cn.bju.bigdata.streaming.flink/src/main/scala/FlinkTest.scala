import org.apache.flink.streaming.api.windowing.assigners.{TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

case class WC(word: String, count: Int)

object FlinkTest {
  def main(args: Array[String]): Unit = {
    //    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //    val text: DataStream[String] = env.readTextFile("C:\\Users\\Administrator\\IdeaProjects\\untitled\\file\\a.txt")

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("10.30.0.238", 8000)
    //    val counts = text.flatMap ( _.toLowerCase.split("\\W+") filter ( _.nonEmpty ) )
    //      .map ( (_, 1) )
    //      .keyBy(_._1)
    //      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
    //      .sum(1)
    //
    //    counts.print()
    //      var sdf =

    //处理15秒范围内的数据
    //      text
    //        .flatMap ( _.toLowerCase.split("\\W+") filter ( _.nonEmpty ) )
    //        .map(x => (x,1))
    //        .keyBy(_._1)
    //        .window(TumblingProcessingTimeWindows.of(Time.milliseconds(15)))
    //        .sum(1)
    //        .print()

    //---------------------reduce
                text
                .flatMap ( _.toLowerCase.split("\\W+") filter ( _.nonEmpty ) )
                .map(x => (x,3))
                .keyBy(_._1)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(15)))

                .reduce((v1,v2) => (v1._1,v1._2 / v2._2))
                .setParallelism(1)
                .print()

    //    val someIntegers: DataStream[Long] = env.generateSequence(0, 1000)
    //
    //    val iteratedStream = someIntegers.iterate(
    //      iteration => {
    //        val minusOne = iteration.map( v => v - 1)
    //        val stillGreaterThanZero = minusOne.filter (_ > 0)
    //        val lessThanZero = minusOne.filter(_ <= 0)
    //        (stillGreaterThanZero, lessThanZero)
    //      }
    //    )
    //
    //    iteratedStream.print()


//    val value: DataStream[WC] = text
//      .flatMap(_.toLowerCase.split("\\W+") filter (_.nonEmpty))
//      .map(x => WC(x, 1))
//    value.keyBy(_.word)

    env.execute("Window Stream WordCount")

  }

}
