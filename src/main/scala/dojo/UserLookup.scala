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
  
  private def userNameLookup(function: (User) => Boolean) = {
    val userList = dataSource.findUsers(function)
    val scalaUserBuffer = userList.asScala
    val scalaNameBuffer = scalaUserBuffer.map(_.getName())
    scalaNameBuffer.asJava
  }

  /*
      Are there standard JavaConversions to make it easier to work with Java collections?
   */
  def namesYoungerThan(age: Int):List[String] = {
    userNameLookup(_.getAge() < age)
  }

  
  def allFemale(): List[String] = {
    userNameLookup(!_.isMale)
  }

  def allEligible() = {
    dataSource.findUsers(
        (user: User) => {
          user.isMale() && user.getAge() > 34
        })
  }

}
