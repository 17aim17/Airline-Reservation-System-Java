public class validation{

  public static boolean checkContact(String str)
  {
    if(str !=null && str.length()==10)
    {
      for(int i=0;i<str.length();i++)
      {
        int a =(int)str.charAt(i);
        if(a>=48 && a<=57)
            continue;
        else
          return false;

      }
      return true;
    }
    else
    {
      return false;
    }
  }


  public static boolean checkAadhar (String str)
  {
    if(str !=null && str.length()==12 )
    {
      for(int i=0;i<str.length();i++)
      {
        int a =(int)str.charAt(i);
        if(a>=48 && a<=57)
          continue;
        else
          return false;
      }
      return true;
    }
    else
    {
      return false;
    }
  }


  public static boolean checkAge( String str)
  {
    try{
      int age =Integer.parseInt(str);
      if(age>=3 && age<=90)
        {
          return true;
        }
      return false;

    }catch(Exception e)
    {
      return false;
    }
}


public static boolean checkEmail(String str)
{
  if( str!=null && str.length()>5 && str.indexOf("@")>1 && str.indexOf(".")>1  )
  {
      return true;
  }
  else{
      return false;
  }

}


  public static boolean checkString (String str)
  {
    int flag=0;
    if( str==null ||  str.equals("null") || str.equals(""))
    {
           return false;
    }
    else if(str.length()>3)
    {
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
              if (Character.isLetter(ch) || Character.isWhitespace(ch))
                  continue;
              else{
                 flag =1;
                 break;
                }
              }
         if(flag==1){ return false ;}
         return true;
    }
    else
    {
      return false;
    }


  }



}
