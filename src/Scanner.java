import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Scanner
{
    static String data ;
    static int location = 0 ;
    enum State{START,INCOMMENT,INNUM,INID,INASSIGN,DONE};
/*
    public Scanner(String data)
    {
        this.data=data;
    }
*/
    public  void execute () throws Exception {
        // create the empty list of tokens
        List<Token_Type> List_of_Tokens = new ArrayList<Token_Type>();

        // read the program from file and remove the newlines

        // over here you need to put the file path to read from

        data = readFileAsString("program.txt");
        data = data.replaceAll("\r", " ").replaceAll("\n", " ");
        //data.replaceAll("\\r\\n", " ");
        //System.out.println(data);

        //start here cutting it to tokens
        while ( location < data.length()) {
            Start(   List_of_Tokens);
        }

        // print the token list to file
        //System.out.println(data);
        Token_Type.Print_List_Token_Type(List_of_Tokens );
        System.out.println("output file is created ");
    }
    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
    private static Boolean isSymbol(char c)
    {
        switch(c)
        {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
            case '<':
            case '(':
            case ')':
            case ';':
            case ':':
                return true;
            default:
                return false;
        }
    }
    private static Boolean isReserved(String s)
    {
        switch (s)
        {
            case "if":
            case "then":
            case "else":
            case "end":
            case "repeat":
            case "until":
            case "read":
            case "write":
                return true;
            default:
                return false;
        }
    }
    private  static  void Start(  List<Token_Type> List_of_Tokens    )
    {
        char ch;
        State currentState=State.START;
        String token="";
        while(currentState!=State.DONE)
        {
            if(location<data.length())
                ch=data.charAt(location);
            else if(location==data.length()&&token!="")
            {
                if(currentState==State.INID)
                {
                    if(isReserved(token))
                        List_of_Tokens.add(new Token_Type("Reserved Word",token));
                    else
                        List_of_Tokens.add(new Token_Type("identifier",token));
                }
                else if(currentState==State.INNUM)
                {
                    List_of_Tokens.add(new Token_Type("number",token));
                }
                break;
            }
            else
                break;
            switch (currentState)
            {
                case START:
                    if(ch==' ')
                        currentState=State.START;
                    else if (ch=='{')
                        currentState=State.INCOMMENT;
                    else if(isSymbol(ch))
                    {
                        if(ch==':')
                            currentState=State.INASSIGN;
                        else
                        {
                            currentState=State.DONE;
                            token=String.valueOf(ch);

                            List_of_Tokens.add(new Token_Type("Symbol",String.valueOf(ch)));
                        }

                    }
                    else if(Character.isDigit(ch)) {
                        token += String.valueOf(ch);
                        currentState = State.INNUM;
                    }
                    else if(Character.isLetter(ch)) {
                        token += String.valueOf(ch);
                        currentState = State.INID;
                    }
                    break;
                case INCOMMENT:
                    if(ch=='}')
                        currentState=State.DONE;
                    else
                        currentState=State.INCOMMENT;
                    break;
                case INNUM:
                    if(Character.isDigit(ch))
                    {
                        currentState=State.INNUM;
                        token+=String.valueOf(ch);
                    }
                    else
                    {
                        currentState=State.DONE;
                        location--;
                        List_of_Tokens.add(new Token_Type("number",token));
                    }

                    break;
                case INID:
                    if(Character.isLetter(ch))
                    {
                        currentState=State.INID;
                        token+=String.valueOf(ch);
                        /*
                        if(location==data.length()-1)
                        {
                            if(isReserved(token))
                                List_of_Tokens.add(new Token_Type("Reserved Word",token));
                            else
                                List_of_Tokens.add(new Token_Type("identifier",token));
                        }*/
                    }
                    else
                    {
                        currentState=State.DONE;
                        location--;
                        if(isReserved(token))
                            List_of_Tokens.add(new Token_Type("Reserved Word",token));
                        else
                            List_of_Tokens.add(new Token_Type("identifier",token));
                    }
                    break;
                case INASSIGN:
                    if(ch=='=')
                    {
                        currentState=State.DONE;
                        //location--;
                        token=":=";
                        List_of_Tokens.add(new Token_Type("assign",token));
                    }
                    else
                    {
                        currentState=State.DONE;
                        location--;
                        List_of_Tokens.add(new Token_Type("unknown",token));
                    }
                    break;
            }
            location++;
        }

        return ;
    }
    private  static  void InNum( char id ,  List<Token_Type> List_of_Tokens  )
    {
        return ;
    }
    private  static  void InId( char num , List<Token_Type> List_of_Tokens )

    {
        return ;
    }
    private  static  void InAssign(  char symbol   , List<Token_Type> List_of_Tokens  )
    {
            return ;

    }
    private  static  void SpecialSymbols( char symbol   , List<Token_Type> List_of_Tokens  )
    {
        // Token_Type temp = new Token_Type( "" ,":=" );
        // List_of_Tokens.add(temp);
        return ;
    }

}
