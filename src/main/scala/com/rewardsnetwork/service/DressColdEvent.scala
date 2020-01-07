package com.rewardsnetwork.service

object DressColdEvent {

  val FAIL = "fail"
  val INVALID = "Invalid"

  def dressColdResponse(dressCold: Any): String = dressCold match {
    case 1 => "boots"
    case 2 => "hat"
    case 3 => "socks"
    case 4 => "shirt"
    case 5 => "jacket"
    case 6 => "pants"
    case 7 => "leaving house"
    case 8 => "Removing PJs"
    case _ => INVALID
  }

  def checkColdDressStackRules(dressEvent: DressEvent, dressCommand: Int) = {
    dressEvent.inputDressCommandBuilder += dressCommand
    val dressResp = dressColdResponse(dressCommand)
    dressResp match {
      case INVALID => throw new Exception(s"The " + dressCommand + " is invalid dressCommand")
      case _ => {
        val last = dressEvent.inputDressCommandBuilder.last
        if (dressEvent.inputDressCommandBuilder.head != 8 )
          dressEvent.dressBuilderResponse += FAIL
        else if (last == 1 && !(dressEvent.inputDressCommandBuilder.contains(6) || dressEvent.inputDressCommandBuilder.contains(3)))
          dressEvent.dressBuilderResponse += FAIL
        else if ((last == 2 || last == 5) && !dressEvent.inputDressCommandBuilder.contains(4))
          dressEvent.dressBuilderResponse += FAIL
        else if (dressEvent.inputDressCommandBuilder.filter(_ == last).length > 1)
          dressEvent.dressBuilderResponse += FAIL
        else if (!dressEvent.dressCommandsCursor.hasNext && dressEvent.inputDressCommandBuilder.length == 8 && dressEvent.inputDressCommandBuilder.last != 7)
          dressEvent.dressBuilderResponse += FAIL
        else if (!dressEvent.dressCommandsCursor.hasNext && dressEvent.inputDressCommandBuilder.length < 8)
          dressEvent.dressBuilderResponse += FAIL
        else
          dressEvent.dressBuilderResponse += dressResp
      }
    }
  }

}
