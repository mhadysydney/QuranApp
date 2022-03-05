package com.soft224.coranapp;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class CoranApp{
    VideoPlayer videoPlayer;
    private JLabel screen;

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

        int vpX=frame.getX()+width,vpY=frame.getY()+height;

        plate.setLayout(null);
        screen=new JLabel("");
        //frame.setContentPane(plate);
        screen.setIcon(imageIcon);
        screen.setBackground(Color.white);
        screen.setBounds(0,0,width,height);
        controller.setBounds(15,height-5,width-30,40);
        plate.add(screen);plate.add(controller);//controller.setVisible(true);
        frame.setVisible(true);
        videoPlayer=new VideoPlayer(frame,false);

        videoPlayer.showLocation(vpX-210,vpY-180);
        File video=new File("01_1_1.mp4");
        var vPath=video.getAbsolutePath();
        System.out.println("vpath "+vPath);
        videoPlayer.lunchVideo(vPath);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var pTxt=pause.getText();

                if (pTxt.equals("||")) {
                    videoPlayer.setPause();
                } else {
                    videoPlayer.setPlay();
                }
                pause.setText(pTxt.equals("||") ? ">":"||");
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
    }
    public static void main(String[] arg){

        new CoranApp();
    }
}
