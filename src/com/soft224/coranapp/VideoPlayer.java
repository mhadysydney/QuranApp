package com.soft224.coranapp;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class VideoPlayer extends JDialog {
    private EmbeddedMediaPlayerComponent player;
    public VideoPlayer(JFrame parent,boolean modal){
        super(parent,modal);
        player=new EmbeddedMediaPlayerComponent();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(200,170);
        this.setResizable(false);
        this.setContentPane(player);
        this.setUndecorated(true);
    }

    public void lunchVideo(String url){
        this.setVisible(true);
        player.mediaPlayer().media().play(url);
        player.mediaPlayer().menu().activate();

    }

    public void showLocation(int x,int y){
        this.setLocation(x,y);
    }

    public void setPlay(){
        player.mediaPlayer().controls().play();
    }

    public void setPause(){
        player.mediaPlayer().controls().pause();
    }

    public void goForward(){
        player.mediaPlayer().controls().skipTime(1000);
    }

    public void goBackward(){
        player.mediaPlayer().controls().skipTime(-1000);
    }
}
