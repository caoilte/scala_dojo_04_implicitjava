package dojo

import java.util.{ ArrayList, List }
import com.google.common.base.Predicate

class UserLookup(dataSource :DataSource) extends JUserLookup {

  import ImplicitJava.funcToPred
  import scala.collection.JavaConverters._

  
  /*
     Once this is passing by using the predicate class
     can you do it in the Scala way by passing in a function?
     Is there a way to imlicit[ly] convert a function to a Predicate?
   */
  def olderThan(age :Int): List[User] = {
    dataSource.findUsers(
      (user: User) => user.getAge() > age)
  }

  /*
      Are there standard JavaConversions to make it easier to work with Java collections?
   */
  def namesYoungerThan(age: Int):List[String] = {
    val userList = dataSource.findUsers(
        (user: User) => user.getAge() < age)
    val scalaUserBuffer = userList.asScala
    val scalaNameBuffer = scalaUserBuffer.map(_.getName())
    scalaNameBuffer.asJava
  }

  
  def allFemale(): List[String] = new ArrayList[String]()

  def allEligible() = new ArrayList[User]()

}
