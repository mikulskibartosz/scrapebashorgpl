package name.mikulskibartosz.bashorgpl

import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Failure, Success}

class ParseArgumentsSpec extends FlatSpec with Matchers {
  "ParseArguments" should "return the number of pages if the first argument is a positive number" in {
    //given
    val arguments = Array("7")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldEqual Success(new NumberOfPages(7))
  }

  it should "ignore redundant parameters" in {
    //given
    val arguments = Array("7", "13", "something", "else")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldEqual Success(new NumberOfPages(7))
  }

  it should "return a Failure if the first argument is not a number" in {
    //given
    val arguments = Array("text", "13", "something", "else")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldBe a[Failure[_]]
    val exception = result.failed.get
    exception shouldBe an[IllegalArgumentException]
    exception should have message "Number of pages must be a positive integer."
  }

  it should "return a Failure if the first argument is 0" in {
    //given
    val arguments = Array("0", "13", "something", "else")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldBe a[Failure[_]]
    val exception = result.failed.get
    exception shouldBe an[IllegalArgumentException]
    exception should have message "Number of pages must be a positive integer."
  }

  it should "return a Failure if the first argument is a negative integer." in {
    //given
    val arguments = Array("-1", "13", "something", "else")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldBe a[Failure[_]]
    val exception = result.failed.get
    exception shouldBe an[IllegalArgumentException]
    exception should have message "Number of pages must be a positive integer."
  }

  it should "return a Failure if the first argument is a real number." in {
    //given
    val arguments = Array("3.14", "13", "something", "else")

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldBe a[Failure[_]]
    val exception = result.failed.get
    exception shouldBe an[IllegalArgumentException]
    exception should have message "Number of pages must be a positive integer."
  }

  it should "return a Failure if there are no input arguments" in {
    //given
    val arguments = Array[String]()

    //when
    val result = ParseArguments(arguments)

    //then
    result shouldBe a[Failure[_]]
    val exception = result.failed.get
    exception shouldBe an[IllegalArgumentException]
    exception should have message "Number of pages should be passed in the application arguments."
  }
}
