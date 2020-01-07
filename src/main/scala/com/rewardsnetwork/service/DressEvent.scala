package com.rewardsnetwork.service

import scala.collection.mutable.ListBuffer

case class DressEvent(temperatureType: String, dressCommandsCursor: Iterator[Int], inputDressCommandBuilder: ListBuffer[Int], dressBuilderResponse: ListBuffer[String])

object DressEvent {

  def getDressed(args: Array[String]): DressEvent = {
    checkForInvalidArguments(args)
    val temperatureType = args(0)
    val dressCommands = DressEvent.splitArray(args(1))
    val dressEvent = new DressEvent(temperatureType, dressCommands.toIterator, ListBuffer[Int](), ListBuffer[String]())

    //This function is responsible to process each input dress command and apply appropriate rules based on HOT or COLD season
    processDressEventCommands(temperatureType, dressEvent)


    val allClothesOnOrNot = isAllClothesPutOnOrNot(temperatureType, dressEvent.inputDressCommandBuilder.length)

    def SUCCESS = println("SUCCESS")

    def FAILURE = println("FAILURE")

    if (!dressEvent.dressBuilderResponse.contains("fail") && allClothesOnOrNot) SUCCESS else FAILURE

    println(s"INPUT: " + dressEvent.temperatureType + "," + dressCommands.mkString(", "))
    println(s"OUTPUT: " + dressEvent.dressBuilderResponse.mkString(", "))
    dressEvent
  }

  private def processDressEventCommands(temperatureType: String, dressEvent: DressEvent) = {
    while (dressEvent.dressCommandsCursor.hasNext) {
      val dressCommand = dressEvent.dressCommandsCursor.next()
      temperatureType match {
        case "HOT" => DressHotEvent.checkHotDressStackRules(dressEvent, dressCommand)
        case "COLD" => DressColdEvent.checkColdDressStackRules(dressEvent, dressCommand)
      }
    }
  }

  val validHotAllClothes = 6
  val validColdAllClothes = 8

  protected def isAllClothesPutOnOrNot(tempType: String, lengthOfDressCommands: Int) = {
    tempType match {
      case "HOT" => if (lengthOfDressCommands == validHotAllClothes) true else false
      case "COLD" => if (lengthOfDressCommands == validColdAllClothes) true else false
    }
  }

  protected def checkForInvalidArguments(args: Array[String]) = {
      if(args == null || args.length < 2 )
        printInvalidStatements("The arguments are invalid")
      else {
        val firstParameter = args(0)
        firstParameter match {
          case _ if firstParameter == "HOT" || firstParameter == "COLD" =>
          case _ => printInvalidStatements("Usage: Invalid <temperature Type> HOT|COLD")
        }
      }

  }

  private def printInvalidStatements(message: String) = {
    System.err.println(s"Usage: RewardsMainApplication <temperature Type> HOT|COLD <dressingCommands> 1,2,3,4,5,6,7,8 | 8,2,5 |...> " + "\n valid arguments HOT 8,4,6,1,2,7")
    throw new RuntimeException(message)
  }

  private def splitArray(inputString: String): Array[Int] = {
    inputString.split(",").flatMap(str => scala.util.Try(str.toInt).toOption)
  }
}
