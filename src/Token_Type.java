import java.io.*;
import java.util.Iterator;
import java.util.List;

public class Token_Type

{
    private String Type ;
    private String Type_Equivalent ;
    //private int Type_Equivalent_value ;

    public  Token_Type
            ( String Type_x , String Type_Equivalent_x
            //, int Type_Equivalent_value_x
    )
    {
        Type = Type_x;
        Type_Equivalent = Type_Equivalent_x;
        //Type_Equivalent_value= Type_Equivalent_value_x ;
    }
    public static   void Print_List_Token_Type (  List<Token_Type> List_of_Tokens )
            throws IOException {

        Iterator itr = List_of_Tokens.iterator();
        //PrintWriter out = new PrintWriter("output.txt");
        // checking the next element availabilty
        File newTextFile = new File("out.txt");

        FileWriter fw = new FileWriter(newTextFile);


        do {
            //  moving cursor to next element
            Token_Type i = (Token_Type) itr.next();

            // getting even elements one by one
            // System.out.println(i.Type + ','+ i.Type_Equivalent );

            //String value =i.Type + ','+ i.Type_Equivalent+"\n";

            String str =  i.Type_Equivalent + ','+i.Type  + "\n";
            fw.write(str);
        }
        while (itr.hasNext());

        fw.close();
        return ;
    }
}
