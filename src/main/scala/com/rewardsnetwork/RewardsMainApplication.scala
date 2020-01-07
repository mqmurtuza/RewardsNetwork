package com.rewardsnetwork

import com.rewardsnetwork.service.DressEvent

object RewardsMainApplication extends App {
   try {
      DressEvent.getDressed(args)
   } catch {
      case e: Exception => println(s"The program failed due to [" + e.getMessage + "]")
   }
}
