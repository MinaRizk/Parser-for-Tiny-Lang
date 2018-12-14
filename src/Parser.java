import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/*
* we will use Top-Down Parsing /recursive descent method. in this parser
*EBNF notation is designed to mirror the actual
code of a recursive-descent parser, so a
grammar should always be translated into
EBNF if recursive-descent is to be used.
*
**/
public class Parser
{
    private static int i=0;
    private LinkedList <String> tokens;//all tokens in file
    private LinkedList <String> syntax;//all syntax in file
    private String token;//each token alone
    private String syntaxx;// each syntaxx alone
    private String readPath;// the file to read the input from

    public Parser(String readPath  )
    {
        token=new String();
        tokens = new LinkedList<String>();
        syntax = new LinkedList<String>();
        this.readPath = new String(readPath);
    }

    public void readFile()
    {
        String line = null;
        try {
                FileReader fileReader = new FileReader(readPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (   (line = bufferedReader.readLine()) != null  )
                    {
                        String [] s = line.split(",");
                        if(s[1].equals("Reserved Word") || s[1].equals("Symbol") || s[1].equals("assign"))
                            {
                                tokens.addLast(s[0]);
                            }
                        else
                            {
                                tokens.addLast(s[1]);
                            }
                        syntax.addLast(s[0]);
                    }
                bufferedReader.close();
            }
        catch (FileNotFoundException ex)
                {
                    System.out.println("Unable to open file '" + readPath + "'");
                }
        catch (IOException ex)
                {
                    System.out.println("Error reading file '" + readPath + "'");
                }
    }

    //helper functions before the grammer rules

/*
* •  psedocode
procedure match (expectedToken);
begin
if token = expectedToken then
getToken;
else
error;
end if;
end match;
*
*
* */
    void match(String expectedToken)
    {
        if(token.equals(expectedToken))
        {
            getToken();//if it is matched then get the next token
        }
        else
        {
            System.out.println("wrong token");
        }
    }

    //getting the next token
    void getToken ()
    {
        if(i<tokens.size())
            {
                token=tokens.get(i);
                if(i>0)
                    syntaxx=syntax.get(i-1);
                i++;
            }
        else
            {
                if(i==tokens.size())
                    syntaxx=syntax.get(i-1);

            }

    }

    /*
     * the EBNF : program → stmt-sequence
     * the psedocode :
     *
function program : syntaxTree
var: tree :syntaxTree
begin
get token // because it is the first token
stmt-sequence()
return syntax tree
end program
     * */
    TreeNode Program()
    {
        TreeNode tree = new TreeNode();
       // tree.setName("The Program");
        getToken();
        tree=stmt_sequence();
        // tree.setName("The Program");
        return tree;
    }

    /*
     * BNF: stmt-sequence → stmt-sequence ; statement | statement
     * EBNF:stmt-sequence → statement { ; statement}
     * psedocode:
function stmt-sequence : syntaxTree
begin
stmt
while(token=:){
stmt
}
return syntax tree
end stmt_sequence
     *
     * */
    TreeNode  stmt_sequence()
    {
        TreeNode tree = new TreeNode();
      //LinkedList<TreeNode> children = new LinkedList<TreeNode>();
      //children.addLast(stmt());
        tree.setChild(stmt());
        while(token.equals(";"))
        {
            getToken();
            tree.setChild(stmt());
        }
        return tree;
    }

    /*
     *BNF/EBNF:statement→ if- stmt | repeat-stmt | assign-stmt | read-stmt | write-stmt
     * assign-stmt => identifier
     *psedocode:
     *
function stmt : syntaxTree
begin
switch(token)
case if tree = if()
.........
}
return syntax tree
end stmt
 * */
    TreeNode stmt()
    {
        TreeNode tree = new TreeNode();
        switch (token)
            {
                case "if":
                    tree= if_stmt();
                    break;
                case "repeat":
                    tree= repeat_stmt();
                    break;
                case "identifier":
                    tree= assign_stmt();
                    break;
                case "read":
                    tree = read_stmt();
                    break;
                case "write":
                    tree = write_stmt();
                    break;
            }
        return tree;
    }

    /*
     *
     *if –stm → if (exp) statement [else statement ]
     * */
    TreeNode if_stmt()
    {
        TreeNode tree = new TreeNode();
        TreeNode testChildtree = new TreeNode();
        TreeNode thenChildtree = new TreeNode();
        TreeNode elseChildtree = new TreeNode();
        match("if");
        tree.setName("if");
        testChildtree = exp();
        tree.setChild(testChildtree);
        match("then");
        thenChildtree = stmt_sequence();
        tree.setChild(thenChildtree);
        if (token.equals("else"))
            {
                match("else");
                elseChildtree = stmt();
                tree.setChild(elseChildtree);
            }
        match("end");
        return tree;
    }

    /*
     *repeat -stmt → repeat stmt-sequence until exp
     *
     * */
    TreeNode repeat_stmt()
    {
        TreeNode tree = new TreeNode();
        TreeNode bodychildtree = new TreeNode();
        TreeNode testchildtree = new TreeNode();
        match ("repeat");
        tree.setName("repeat");
        bodychildtree = stmt_sequence();
        match("until");
        testchildtree = exp();
        tree.setChild(bodychildtree);
        tree.setChild(testchildtree);
        return tree;
    }

    /*
     *
     * assign -stmt → identifier := exp
     *
     *
     * */
    TreeNode assign_stmt()
    {
        TreeNode tree = new TreeNode();
        TreeNode expchildtree = new TreeNode();
        match("identifier");
        tree.setName("assign \n ( " + syntaxx+" )");
        match(":=");
        expchildtree = exp();
        tree.setChild(expchildtree);
        return tree;
    }

    /*
     *read -stmt → read identifier
     *
     * */
    TreeNode read_stmt()
    {
        TreeNode tree = new TreeNode();
        match("read");
        match("identifier");
        tree.setName("read \n id = (" + syntaxx + ")");
        return tree;
    }

    /*
     *write-stmt → write exp
     *
     * */
    TreeNode write_stmt()
    {
        TreeNode tree = new TreeNode();
        TreeNode expchildtree = new TreeNode();
        match("write");
        tree.setName("write");
        expchildtree = exp();
        tree.setChild(expchildtree);
        return tree;
    }

    /*
     *
     *exp → simple-exp comparison-op simple-exp | simple-exp
     * */
    TreeNode exp()
    {
        TreeNode tree = new TreeNode();
        TreeNode leftoperandtree = new TreeNode();
        TreeNode rightoperandtree = new TreeNode();
        leftoperandtree=simp_exp();
        if(token.equals("<")||token.equals("="))
            {
                tree.setChild(leftoperandtree);
                switch(token)
                {
                    case "<":
                        match("<");
                        rightoperandtree=simp_exp();
                        tree.setName("op \n (<)");
                        break;

                    case "=":
                        match("=");
                        rightoperandtree=simp_exp();
                        tree.setName("op \n (=)");
                        break;
                }
                tree.setChild(rightoperandtree);
            }
        else
            {
                tree=leftoperandtree;
            }
        return tree;
    }

    /*
     *simple-exp → term { addop term }
     *
     * */
    TreeNode simp_exp()
    {
        TreeNode tree = new TreeNode();
        TreeNode termtree =new TreeNode();
        termtree=term();
        tree.setChild(termtree);
        while (add_op())
        {
            if(token.equals("+"))
            {
                match("+");
                tree.setName("op \n (+)");
            }
            else
            {
                match("-");
                tree.setName("op \n (-)");
            }
            termtree=term();
            tree.setChild(termtree);
        }
        return tree;
    }

    /*
     *term → factor {mulop factor}
     *procedure term;
begin
factor;
while token = * do
match (token);
factor ;
end while ;
end term ;
     * */
    TreeNode term()
    {
        TreeNode tree = new TreeNode();
        TreeNode factortree =new TreeNode();
        factortree=factor();
        tree.setChild(factortree);
        while (mul_op())
        {
            if(token.equals("*"))
            {
                match("*");
                tree.setName("op \n (*)");
            }
            else
            {
                match("/");
                tree.setName("op \n (/)");
            }
            factortree=factor();
            tree.setChild(factortree);
        }
        return tree;
    }

    /*
     *
     *factor → (exp) | number | identifier
     * */
    TreeNode factor ()
    {
        TreeNode tree = new TreeNode();
        switch (token)
        {
            case"(":
                match("(");
                tree = exp();
                match(")");
                break;
            case "number":
                match("number");
                tree.setName("const \n ("+ syntaxx+") ");
                break;
            default:
                match("identifier");
                tree.setName("id \n (" + syntaxx+") ");
                break;
        }
        return tree;
    }

    /*
     *addop → + | -
     *
     * */
    boolean add_op()
    {
        if(token.equals("+"))
        {
            return true;
        }
        else if(token.equals("-"))
        {
            return true;
        }
        else
            return false;
    }

    /*
     *mulop → * | /
     *
     * */
    boolean mul_op()
    {
        if(token.equals("*"))
        {
            return true;
        }
        if(token.equals("/"))
        {
            return true;
        }
        else
            return false;
    }
}
