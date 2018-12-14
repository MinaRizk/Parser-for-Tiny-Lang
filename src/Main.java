public class Main
{
    public static void main(String[] args) throws Exception
    {

       // Scanner scanner = new Scanner();
       // scanner.execute();

        TreeNode root = new TreeNode();

        Parser parser = new Parser("out.txt" );
        parser.readFile();
        root = parser.Program();

        root.setName("THE PROGRAM");
        System.out.println("done ");
    }

}
