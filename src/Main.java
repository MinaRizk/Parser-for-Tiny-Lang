import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main
{
    static int currentNodeNo=-1;
    //static int prevChildNo=currentNodeNo;
    static int ratio=15;
    //static ArrayList<int> listOfParents=new ArrayList();
    static ArrayList<ArrayList<Integer>> listOfParents =new ArrayList<ArrayList<Integer>>();
    static int noOfLists=0;

    public static void justTraverse(TreeNode root,GraphDraw frame,int xlevel,int ylevel,int parentNodeNo,int ratio)
    {
        frame.addNode(root.getName(),xlevel,ylevel);
        currentNodeNo++;
        if(parentNodeNo!=-1)
            frame.addEdge(parentNodeNo,currentNodeNo);
        ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
        parentNodeNo=currentNodeNo;
        int numberOfChilds=0;
        ratio=ratio-50;
        while (listIterator.hasNext())
        {
            numberOfChilds++;
            justTraverse(listIterator.next(),frame,(xlevel-2*ratio)+ratio*numberOfChilds,ylevel+100,parentNodeNo,ratio);
        }
    }
    public static void drawTree(TreeNode root,GraphDraw frame,int xlevel,int ylevel,int parentNodeNo,int ratio)
    {
        Boolean isHorizontal=false;
        if (root.getName()=="H")
        {
            isHorizontal=true;
            listOfParents.add(new ArrayList<Integer>());
            listOfParents.get(noOfLists).add(parentNodeNo);
            noOfLists++;
        }
        else
        {
            isHorizontal=false;
            //frame.addNode(root.getName()+" "+(currentNodeNo+1),xlevel,ylevel);
            frame.addNode(root.getName(),xlevel,ylevel);
            currentNodeNo++;
        }
        int desiredListNo=noOfLists-1;
        //if it is not the first node in the tree
        if(parentNodeNo!=-1&& isHorizontal==false)
        {
            frame.addEdge(parentNodeNo,currentNodeNo);
            //System.out.println("join "+parentNodeNo+" with "+currentNodeNo);
        }
        ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
        if(isHorizontal==false)
            parentNodeNo=currentNodeNo;
        /*else
            parentNodeNo=currentNodeNo-1;*/
        int numberOfChilds=0;
        ratio=ratio-70;
        if(ratio<50)
            ratio=200;
        while (listIterator.hasNext())
        {
            numberOfChilds++;
            if (isHorizontal==false)
                drawTree(listIterator.next(),frame,(xlevel-2*ratio)+ratio*numberOfChilds+15,ylevel+90,parentNodeNo,ratio);
            else
            {
                int lastParentIndex=listOfParents.get(desiredListNo).size()-1;
                parentNodeNo=listOfParents.get(desiredListNo).get(lastParentIndex);
                listOfParents.get(desiredListNo).add(currentNodeNo+1);
                int xl = (xlevel-2*ratio)+ratio*numberOfChilds;

                drawTree(listIterator.next(),frame,xl,ylevel+130,parentNodeNo,ratio);

                System.out.println(xl);
                //parentNodeNo=currentNodeNo;
            }
        }
    }
    public static void PreOrderTraverse(TreeNode root,GraphDraw frame,int xlevel,int ylevel,int parentnodeNo,Boolean parentIsNull)
    {
        int prevChildNo=currentNodeNo;
        ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
        int childNo=0;
        if(root.getName().equals("null"))
        {
            if(listIterator.hasNext())
            {
                if(listIterator.next().getName().equals("null"))
                {
                    listIterator.previous();
                    ListIterator<TreeNode> listIterator2=listIterator.next().getChildren().listIterator() ;
                    /*frame.addNode(listIterator.next().getName(),xlevel,ylevel);
                    currentNodeNo++;
                    prevChildNo=currentNodeNo;*/
                    PreOrderTraverse(listIterator2.next(),frame,xlevel,ylevel,parentnodeNo,parentIsNull);
                }
                else
                {
                    listIterator.previous();
                    while (listIterator.hasNext())
                    {
                        childNo++;

                        if(childNo==1)
                        {
                            prevChildNo=currentNodeNo;
                            PreOrderTraverse(listIterator.next(),frame,xlevel+100*(childNo-1),ylevel,parentnodeNo,true);
                        }

                        else
                        {
                            PreOrderTraverse(listIterator.next(),frame,xlevel+100*(childNo-1),ylevel,prevChildNo,true);
                            prevChildNo=currentNodeNo;
                        }

                    }
                }
            }
        }
        else
        {
            frame.addNode(root.getName(),xlevel,ylevel);
            currentNodeNo++;
            /*if(parentIsNull==true)
                prevChildNo=currentNodeNo;*/
            frame.addEdge(currentNodeNo,parentnodeNo);
            parentnodeNo=currentNodeNo;
            while (listIterator.hasNext())
            {
                childNo++;
                PreOrderTraverse(listIterator.next(),frame,xlevel+100*(childNo-1),ylevel+100,parentnodeNo,false);
            }
        }
        /*
        int childNo=0;
        if(root.getName().equals("null"))
        {
            ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
            while(listIterator.hasNext())
            {
                childNo++;
                if(childNo==1)
                {
                    frame.addNode(listIterator.next().getName(),xlevel,ylevel);
                    currentNodeNo++;
                    prevChildNo=currentNodeNo;
                    listIterator.previous();
                    PreOrderTraverse(listIterator.next(),frame,xlevel,ylevel,currentNodeNo,true);
                }
                else
                {
                    frame.addNode(listIterator.next().getName(),xlevel+100*(childNo-1),ylevel);
                    currentNodeNo++;
                    frame.addEdge(prevChildNo,currentNodeNo);
                    prevChildNo=currentNodeNo;
                    listIterator.previous();
                    PreOrderTraverse(listIterator.next(),frame,xlevel,ylevel,currentNodeNo,true);
                }
            }
        }
        else
        {
            ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
            while(listIterator.hasNext())
            {
                childNo++;
                frame.addNode(listIterator.next().getName(),(xlevel-200)+100*childNo,ylevel+100);
                currentNodeNo++;
                frame.addEdge(currentNodeNo-1,currentNodeNo);
                listIterator.previous();
                PreOrderTraverse(listIterator.next(),frame,(xlevel-200)+100*childNo,ylevel+100,currentNodeNo,false);
                if(parentIsNull==true)
                {

                }
                else
                {

                }
            }
        }*/
       /* if(!(root.getName().equals("null")))
        {
            frame.addNode(root.getName(), xlevel,ylevel);
            currentNodeNo++;
        }*/
       /*
        int childNo=0;
        ListIterator<TreeNode> listIterator=root.getChildren().listIterator() ;
        while(listIterator.hasNext())
        {

            childNo++;

            if(!(root.getName().equals("null")))
            {
                frame.addNode(listIterator.next().getName(), xlevel,ylevel);
                currentNodeNo++;
                //currentNodeNo++;
                frame.addEdge(parentnodeNo,currentNodeNo);
                PreOrderTraverse(listIterator.next(),frame,(xlevel-40*ratio/2)+20*ratio/2*childNo,ylevel+100,currentNodeNo);
            }
            else
            {
                if(childNo>1)
                {
                    frame.addNode(root.getName(), xlevel+100*(childNo-1),ylevel);
                    currentNodeNo++;
                    frame.addEdge(prevChildNo,currentNodeNo);
                    prevChildNo=currentNodeNo;
                    PreOrderTraverse(listIterator.next(),frame,xlevel+200*childNo,ylevel,currentNodeNo);
                }
                else
                {
                    frame.addNode(root.getName(), xlevel,ylevel);
                    currentNodeNo++;
                    prevChildNo=currentNodeNo;
                    PreOrderTraverse(listIterator.next(),frame,xlevel,ylevel,currentNodeNo);
                }
            }


        }*/

    }
    public static void main(String[] args) throws Exception
    {

        Scanner scanner = new Scanner();
        scanner.execute();

        GraphDraw frame = new GraphDraw("PARSER GRAPH");


//        frame.setBackground(Color.darkGray);


        //Create a JPanel
        JPanel jp = new JPanel();
//        jp.setBackground(Color.white);
        //Create a scrollbar using JScrollPane and add panel into it's viewport
        //Set vertical and horizontal scrollbar always show

        //Create a JFrame with title ( AddScrollBarToJFrame )

        // frame.setBackground(Color.darkGray);

        jp.setPreferredSize(new Dimension(500,500));

        //    JScrollPane scrollPane=new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //JScrollPane vertical = new JScrollPane();
/*        vertical.setValue( vertical.getMaximum() );
        //pane.setPreferredSize(jp.getPreferredSize());
        pane.setPreferredSize(new Dimension(450, 110));
        frame.add(pane);*/
        //Add JScrollPane into JFrame
        //frame.add(scrollBar);

/*        JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 500);
        JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);


        class MyAdjustmentListener implements AdjustmentListener {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                frame.repaint();
            }
        }

        hbar.addAdjustmentListener(new MyAdjustmentListener( ));
        vbar.addAdjustmentListener(new MyAdjustmentListener( ));

        frame.getContentPane().add(hbar, BorderLayout.SOUTH);
        frame.getContentPane().add(vbar, BorderLayout.EAST);
        //Set close operation for JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        JScrollPane tAreaScrollPane = new JScrollPane(jp);
        //tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(tAreaScrollPane);
        //Set JFrame size
        //frame.setSize(500, 1000);
        //to enable full screen
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        //Make JFrame visible. So we can see it.
        frame.setVisible(true);

        //So, if you want to add other component like JTextArea, just add them into JPanel.After that add
        //the JPanel into JScrollPane before add the JScrollPane into JFrame.






        int xlevel = 600;
        int ylevel = 100;
/*
        frame.addNode("program", xlevel,ylevel);
        frame.addNode("if_statment", xlevel+100,ylevel+100);
        frame.addNode("h", 400,200);

        frame.addEdge(0,1);
        frame.addEdge(0,2);*/
        TreeNode root = new TreeNode();

        Parser parser = new Parser("out.txt" );
        parser.readFile();
        root = parser.Program();
/*
        root.setName("THE PROGRAM");
        frame.addNode(root.getName(), xlevel,ylevel);
        frame.addNode(root.getChildren().get(0).getName(), xlevel+100,ylevel+100);
        frame.addEdge(0,1);*/

        //PreOrderTraverse(root,frame,xlevel,ylevel,0,true);
        //justTraverse(root,frame,xlevel,ylevel,-1,300);
        drawTree(root,frame,xlevel,ylevel,-1,310);

        System.out.println("done ");
    }

}
