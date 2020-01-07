package com.rewardsnetwork.service

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class DressHotEventTest extends FunSuite {

    val testHotData = List(
      (Array(8,6,4,2,1,7),true, "Removing PJs, shorts, shirt, sunglasses, sandals, leaving house")
      , (Array(8,6,6),false,"Removing PJs, shorts, fail")
      , (Array(8,6,3), false,"Removing PJs, shorts, fail")
    )

  def SuccessOrFailure(value: Boolean) ={
    if(value) "SUCCESS" else "FAILURE"
  }

  testHotData.foreach { data =>
      test(s"test HOT " + SuccessOrFailure(data._2)+ " scenarios " + data._1.mkString(", ")) {
        val dressCommands = data._1
        val dressEvent = new DressEvent("HOT", dressCommands.toIterator, ListBuffer[Int](), ListBuffer[String]())
        while (dressEvent.dressCommandsCursor.hasNext) {
          val dressCommand = dressEvent.dressCommandsCursor.next()
          DressHotEvent.checkHotDressStackRules(dressEvent, dressCommand)
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
    test(s"test Invalid scenarios " + data._1.mkString(", ")) {
      val thrown = intercept[Exception] {
        val dressCommands = data._1
        val dressEvent = new DressEvent("HOT", dressCommands.toIterator, ListBuffer[Int](), ListBuffer[String]())
        while (dressEvent.dressCommandsCursor.hasNext) {
          val dressCommand = dressEvent.dressCommandsCursor.next()
          DressHotEvent.checkHotDressStackRules(dressEvent, dressCommand)
        }
      }
      assert(thrown.getMessage === s"The "+data._2+" is invalid dressCommand")
    }
  }


}
