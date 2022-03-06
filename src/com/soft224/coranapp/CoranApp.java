package com.soft224.coranapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class CoranApp{
    VideoPlayer videoPlayer;
    int vpX,vpY;
    public CoranApp(){

        ImageIcon imageIcon = new ImageIcon("fatiha.png");
        int width= imageIcon.getIconWidth();
        int height= imageIcon.getIconHeight();

        JFrame frame = new JFrame("Qur'an app");
        frame.setSize(width,height+70);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container plate = frame.getContentPane();

        JPanel controller=new JPanel();
        JButton pause=new JButton("||");
        JButton prev=new JButton("<<");
        JButton next=new JButton(">>");

        pause.setForeground(Color.white);
        prev.setForeground(Color.white);
        next.setForeground(Color.white);

        pause.setBackground(new Color(0,0,0, 0));
        prev.setBackground(new Color(0,0,0, 0));
        next.setBackground(new Color(0,0,0, 0));

        prev.setBounds(50,7,60,30);
        pause.setBounds(111,7,60,30);
        next.setBounds(171,7,60,30);

        controller.setBackground(new Color(0,0,0, 160));
        //controller.setSize(width-20,40);
        controller.setLayout(null);
        controller.add(prev);
        controller.add(pause);
        controller.add(next);

        vpX=frame.getX()+width;vpY=frame.getY()+height;
        vpX=vpX-210;vpY=vpY-180;
        plate.setLayout(null);
        JLabel screen = new JLabel("");
        //frame.setContentPane(plate);
        screen.setIcon(imageIcon);
        screen.setBackground(Color.white);
        screen.setBounds(0,0,width,height);
        controller.setBounds(15,height-5,width-30,40);
        plate.add(screen);plate.add(controller);//controller.setVisible(true);
        frame.setVisible(true);
        videoPlayer=new VideoPlayer(frame,false);

        videoPlayer.showLocation(vpX,vpY);
        File video=new File("01_1_1.mp4");
        var vPath=video.getAbsolutePath();
        //System.out.println("vpath "+vPath);
        videoPlayer.lunchVideo(vPath);
        /*final long[] videoLen = {0};
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               videoLen[0] = videoPlayer.getLength();
                //System.out.println("videoLen "+ videoLen[0]);
            }
        },2000);*/


        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var pTxt=pause.getText();
                System.out.println("current pos:"+videoPlayer.getCurrentPos());
                if (pTxt.equals("||")) {
                    videoPlayer.setPause();
                    pause.setText(">");
                } else {
                    videoPlayer.setPlay();
                    pause.setText("||");
                }

                pause.setBackground(new Color(0,0,0, 0));

            }
        });

        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                videoPlayer.goBackward();
                prev.setBackground(new Color(0,0,0, 0));

            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                videoPlayer.goForward();
                next.setBackground(new Color(0,0,0, 0));
            }
        });

        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent windowEvent) {
                System.out.println("frame coord: "+frame.getBounds());
            }
        });

        while(true){
            var cTime=videoPlayer.getCurrentPos();
            //System.out.println("FrameBounds "+frame.getBounds());
            if(cTime>=0.5) {
                vpX = frame.getX() + 2;
                vpY = frame.getY() + 10;
            }else{
                vpX=frame.getX()+width;vpY=frame.getY()+height;
                vpX=vpX-210;vpY=vpY-180;
            }
            videoPlayer.showLocation(vpX,vpY);
        }
    }
    public static void main(String[] arg){

        new CoranApp();
    }
}
