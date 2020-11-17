package manning.fp


  case class Error(valErr:String)

  //  Effects
  object ScalaFPEffectDemo extends App{
    /* // Option[A] Model the effect of optionality
    def getCustomerDetailFromDB(id:Long):Option[Customer] = {.....}
       - it would be Some[Customer] or None , it provide optional effects
          value might be present or absent                  */
    def getCustomerPhoneNumber(customerId:Long):Either[Error,String] ={
      if ((customerId) != 0)
        Right("+91717770540")
      else Left(Error(" customer not found"))
    }

    def dialCustomer(phoneNumber:String):Either[Error,Boolean] ={
      if (phoneNumber.startsWith("+91")) Right(true) else Left(Error("Not India Number"))
    }
    def checkCustomerDialed(customerId:Long) = {
      getCustomerPhoneNumber(customerId).flatMap(number => (dialCustomer(number)))
    }


    println(checkCustomerDialed(0))
    println(checkCustomerDialed(100).getOrElse())


    def checkCustomerDialedForComprehensive(customerId:Long) = {
      for {
        number <- getCustomerPhoneNumber(customerId)
        dial <- dialCustomer(number)
      } yield dial
    }

    println(checkCustomerDialedForComprehensive(0))
    println(checkCustomerDialedForComprehensive(100).getOrElse())




    /*  // Either[A,B]    Model of effect two either left or right
    def verifyEmailId(emailId:String):Either[Error,Unit]
       - it would be used in validation , it would return either fail or success value
     */


    /* List[A] - it is list effect, either it will effect zero, one or More.
     def getAvailableAgents(skill:List[String]):List[Agents]
     */


    // There above basic type of provides effect in diffrent scenarios also like Try , Either, Option amd List
  }