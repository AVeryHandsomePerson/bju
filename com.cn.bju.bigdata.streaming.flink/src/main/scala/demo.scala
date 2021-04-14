/**
 * @author ljh
 * @date 2021/4/14 17:48
 * @version 1.0
 */
object demo {
  def main(args: Array[String]): Unit = {
    val entity = OrderDBEntity(1,"2")
    val entity1 = OrderDBEntity(3,"3")


    println(entity1.orderId)
    println(entity1.orderNo)
  }
}
