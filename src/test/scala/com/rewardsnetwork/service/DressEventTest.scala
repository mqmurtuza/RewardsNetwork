package com.rewardsnetwork.service

import org.scalatest.FunSuite

class DressEventTest extends FunSuite {

  def SuccessOrFailure(value: Boolean) ={
    if(value) "SUCCESS" else "FAILURE"
  }

    val testHotData = List(
      (Array("HOT","8,6,4,2,1,7"), true, "Removing PJs, shorts, shirt, sunglasses, sandals, leaving house")
      , (Array("HOT","8,6,6"), false, "Removing PJs, shorts, fail")
      , (Array("HOT","8,6,3"), false, "Removing PJs, shorts, fail")
    )

    testHotData.foreach { data =>
      test(s"test HOT " + SuccessOrFailure(data._2)+  " scenarios " + data._1.mkString(", ")) {
        val dressEvent = DressEvent.getDressed(data._1)
        assert(!dressEvent.dressBuilderResponse.contains("fail") == data._2)
        assert(dressEvent.dressBuilderResponse.mkString(", ") === data._3)
      }
    }

    val testColdData = List(
      (Array("COLD", "8,6,3,4,2,5,1,7"), true, "Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house")
      , (Array("COLD", "8,6,3,4,2,5,7"), false, "Removing PJs, pants, socks, shirt, hat, jacket, fail")
      , (Array("COLD", "6"), false, "fail")
    )
    testColdData.foreach { data =>
      test(s"test COLD " + SuccessOrFailure(data._2)+  " scenarios " + data._1.mkString(", ")) {
        val dressEvent = DressEvent.getDressed(data._1)
        assert(!dressEvent.dressBuilderResponse.contains("fail") == data._2)
        assert(dressEvent.dressBuilderResponse.mkString(", ") === data._3)
      }
    }

    val testInvalidData = List(
      (Array("COLD", "8,6,3,4,2,5,14567,7"), 14567)
     , (Array("HOT", "8,6,4,2,1,85677"), 85677)
      ,(Array("HOT", "8,6,85677"), 85677)
      ,(Array("HOT", "8,2,85677"), 85677)
    )

    testInvalidData.foreach { data =>
      test(s"test Invalid scenarios " + data._1.mkString(", ")) {
        val thrown = intercept[Exception] {
          DressEvent.getDressed(data._1)
        }
        assert(thrown.getMessage === s"The "+data._2+" is invalid dressCommand")
      }
    }

  val testDataInCorrectArguments = List(
    (Array("COLD"), "The arguments are invalid")
    , (Array("HOTTIE", "8,6,7"), "Usage: Invalid <temperature Type> HOT|COLD")
    , (Array(""), "The arguments are invalid")
  )

  testDataInCorrectArguments.foreach { data =>
    test(s"test Invalid scenarios " + data._1.mkString(", ")) {
      val thrown = intercept[RuntimeException] {
        DressEvent.getDressed(data._1)
      }
      assert(thrown.getMessage === data._2)
    }
  }
}
