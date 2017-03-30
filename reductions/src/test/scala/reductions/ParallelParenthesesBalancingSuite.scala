package reductions

import java.util.concurrent._
import scala.collection._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import common._

import ParallelParenthesesBalancing._

@RunWith(classOf[JUnitRunner])
class ParallelParenthesesBalancingSuite extends FunSuite {

  test("balance should work for empty string") {
    def check(input: String, expected: Boolean) =
      assert(balance(input.toArray) == expected,
        s"balance($input) should be $expected")

    check("", true)
  }

  test("balance should work for string of length 1") {
    def check(input: String, expected: Boolean) =
      assert(balance(input.toArray) == expected,
        s"balance($input) should be $expected")

    check("(", false)
    check(")", false)
    check(".", true)
  }

  test("balance should work for string of length 2") {
    def check(input: String, expected: Boolean) =
      assert(balance(input.toArray) == expected,
        s"balance($input) should be $expected")

    check("()", true)
    check(")(", false)
    check("((", false)
    check("))", false)
    check(".)", false)
    check(".(", false)
    check("(.", false)
    check(").", false)
  }

  test("it should work for an empty string") {
    assert(parBalance("".toArray, 1))
    assert(parBalance("".toArray, 10))
  }

  test("it should work for a string of length 1") {
    assert(!parBalance("(".toArray, 1))
    assert(!parBalance("(".toArray, 10))
    assert(!parBalance(")".toArray, 1))
    assert(!parBalance(")".toArray, 10))
    assert(parBalance("!".toArray, 1))
    assert(parBalance("!".toArray, 10))
  }

  test("it should work for string of length 2") {
    assert(parBalance("(())".toArray, 1))
    assert(parBalance("(())".toArray, 2))
    assert(parBalance("(())".toArray, 4))
    assert(parBalance("(())".toArray, 8))

    assert(!parBalance("))((".toArray, 1))
    assert(!parBalance("))((".toArray, 2))
    assert(!parBalance("))((".toArray, 4))
    assert(!parBalance("))((".toArray, 8))


  }

  test("others tests"){
    assert(parBalance("cia()0".toArray, 2))
    assert(!parBalance("c)(a".toArray, 2))
    assert(parBalance("ciao".toArray, 2))
    assert(!parBalance("(aoo(".toArray, 2))
    assert(!parBalance("(moo))".toArray, 2))
    assert(!parBalance("(()))".toArray, 2))
  }




}