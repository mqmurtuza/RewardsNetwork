package com.rewardsnetwork.service

object DressHotEvent {
  val FAIL  = "fail"
  val INVALID = "Invalid"

  def dressHotResponse(dressHot: Any): String = dressHot match {
    case 1 => "sandals"
    case 2 => "sunglasses"
    case 3 => FAIL
    case 4 => "shirt"
    case 5 => FAIL
    case 6 => "shorts"
    case 7 => "leaving house"
    case 8 => "Removing PJs"
    case _ => INVALID
  }

  def checkHotDressStackRules(dressEvent: DressEvent, dressCommand: Int) = {
    dressEvent.inputDressCommandBuilder += dressCommand
    val dressResp = dressHotResponse(dressCommand)
    dressResp match {
      case INVALID => throw new Exception(s"DressCommand " + dressCommand + " is invalid")
      case _ => {
        val last = dressEvent.inputDressCommandBuilder.last
        if (dressEvent.inputDressCommandBuilder.head != 8)
          dressEvent.dressBuilderResponse += FAIL
        else if (last == 2 && !dressEvent.inputDressCommandBuilder.contains(6))
          dressEvent.dressBuilderResponse += FAIL
        else if (last == 1 && !dressEvent.inputDressCommandBuilder.contains(4))
          dressEvent.dressBuilderResponse += FAIL
        else if (dressEvent.inputDressCommandBuilder.filter(_ == last).length > 1)
          dressEvent.dressBuilderResponse += FAIL
        else if (!dressEvent.dressCommandsCursor.hasNext && dressEvent.inputDressCommandBuilder.length == 6 && dressEvent.inputDressCommandBuilder.last != 7)
          dressEvent.dressBuilderResponse += FAIL
        else if (!dressEvent.dressCommandsCursor.hasNext && dressEvent.inputDressCommandBuilder.length < 6)
          dressEvent.dressBuilderResponse += FAIL
        else
          dressEvent.dressBuilderResponse += dressResp
        }
    }
  }
}
