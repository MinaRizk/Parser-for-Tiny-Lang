import javax.swing.*;
import java.awt.*;

class testGraphDraw {
    //Here is some example syntax for the GraphDraw class
    public static void main(String[] args) {

        GraphDraw frame = new GraphDraw("PARSER GRAPH");


//        frame.setBackground(Color.darkGray);


        //Create a JPanel
        JPanel jp = new JPanel();
//        jp.setBackground(Color.white);
        //Create a scrollbar using JScrollPane and add panel into it's viewport
        //Set vertical and horizontal scrollbar always show

        //Create a JFrame with title ( AddScrollBarToJFrame )

        // frame.setBackground(Color.darkGray);

        jp.setPreferredSize(new Dimension(20000,80000));

        JScrollPane pane=new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(jp.getPreferredSize());
        frame.add(pane);
        //Add JScrollPane into JFrame
        //frame.add(scrollBar);






        //Set close operation for JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set JFrame size
        frame.setSize(10000, 40000);

        //Make JFrame visible. So we can see it.
        frame.setVisible(true);

        //So, if you want to add other component like JTextArea, just add them into JPanel.After that add
        //the JPanel into JScrollPane before add the JScrollPane into JFrame.







        int xlevel = 500;
        int ylevel = 100;

        frame.addNode("program", xlevel,ylevel);
        frame.addNode("if_statment", xlevel+100,ylevel+100);
        frame.addNode("h", 400,200);

        frame.addEdge(0,1);
        frame.addEdge(0,2);





    }






 /*
public static void main(String[] args) {

    GraphDraw frame = new GraphDraw("PARSER GRAPH");

    // make it easy to close the application
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // set the frame size (you'll usually want to call frame.pack())
    frame.setSize(new Dimension(1000, 1800));

    // center the frame
    //   frame.setLocationRelativeTo(null);

    // make it visible to the user
    frame.setVisible(true);


    //  frame.setBackground(Color.darkGray);
    //Create a JPanel
    JPanel jp = new JPanel();
    //     jp.setBackground(Color.white);
    //Create a scrollbar using JScrollPane and add panel into it's viewport
    //Set vertical and horizontal scrollbar always show


    // frame.setBackground(Color.darkGray);

    // scrollPane.setPreferredSize(new Dimension(20000,80000));

    JScrollPane pane=new JScrollPane(frame,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setPreferredSize(frame.getPreferredSize());
    frame.add(pane);
    //Add JScrollPane into JFrame
    //frame.add(scrollBar);






        //Set close operation for JFrame
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set JFrame size
       // frame.setSize(10000, 40000);

        //Make JFrame visible. So we can see it.
       // frame.setVisible(true);

        //So, if you want to add other component like JTextArea, just add them into JPanel.After that add
        //the JPanel into JScrollPane before add the JScrollPane into JFrame.







        int xlevel = 500;
        int ylevel = 100;

        frame.addNode("program", xlevel,ylevel);
        frame.addNode("if_statment", xlevel+100,ylevel+100);
        frame.addNode("h", 400,200);

        frame.addEdge(0,1);
        frame.addEdge(0,2);





    }

*/
}