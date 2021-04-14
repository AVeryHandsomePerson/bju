import scala.beans.BeanProperty

/**
 * @author ljh
 * @date 2021/4/14 17:35
 * @version 1.0
 */


case class OrderDBEntity(
                          @BeanProperty orderId:Int,
                          @BeanProperty orderNo:String
                        )

object OrderDBEntity {
  def apply(): OrderDBEntity ={
    OrderDBEntity(
      10,
      "string"
    )
  }
}
