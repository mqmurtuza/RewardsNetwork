package com.rewardsnetwork.service

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class DressColdEventTest extends FunSuite {

    val testColdData =List(
      (Array(8,6,3,4,2,5,1,7),true, "Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house")
      , (Array(8,6,3,4,2,5,7),false,"Removing PJs, pants, socks, shirt, hat, jacket, fail")
      , (Array(6), false,"fail")
    )

  def SuccessOrFailure(value: Boolean) ={
    if(value) "SUCCESS" else "FAILURE"
  }

  testColdData.foreach { data =>
      test(s"test COLD " + SuccessOrFailure(data._2)+ " scenarios " + data._1.mkString(", ")) {
        val dressCommands = data._1
        val dressEvent = new DressEvent("COLD", dressCommands.toIterator, ListBuffer[Int](), ListBuffer[String]())
        while (dressEvent.dressCommandsCursor.hasNext) {
          val dressCommand = dressEvent.dressCommandsCursor.next()
          DressColdEvent.checkColdDressStackRules(dressEvent, dressCommand)
        }
        assert(!dressEvent.dressBuilderResponse.contains("fail") == data._2)
        assert(dressEvent.dressBuilderResponse.mkString(", ") === data._3)
      }
    }

  val testInvalidData = List(
    (Array(8,6,4,2,1,85677), 85677)
    ,(Array(8,6,85677), 85677)
    ,(Array(8,2,85677), 85677)
  )

  testInvalidData.foreach { data =>
    test(s"test COLD Invalid scenarios " + data._1.mkString(", ")) {
      val thrown = intercept[Exception] {
        val dressCommands = data._1
        val dressEvent = new DressEvent("COLD", dressCommands.toIterator, ListBuffer[Int](), ListBuffer[String]())
        while (dressEvent.dressCommandsCursor.hasNext) {
          val dressCommand = dressEvent.dressCommandsCursor.next()
          DressColdEvent.checkColdDressStackRules(dressEvent, dressCommand)
        }
      }
      assert(thrown.getMessage === s"DressCommand "+data._2+" is invalid")
    }
  }


}
