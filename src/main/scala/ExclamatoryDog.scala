/**
  * Created by Denis on 23-Mar-17.
  */
trait ExclamatoryDog extends Friendly{
  override def greet(): String = super.greet() + "!!!"
}
