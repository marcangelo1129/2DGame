/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author geps
 */
public class Sound {
    
    public Clip clip;
    public ArrayList<Clip> clipArray = new ArrayList<>();
    int clipCounter = 1;
    URL soundURL[] = new URL[30];
    
    public Sound()
    {
        soundURL[0] = getClass().getResource("/music/sound1.wav");
        
    }
    public void setFile(int i)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e)
        {
            
        }
    }
    public void play()
    {
        clip.start();
        
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
    
    public void playSFX (URL sound)
    {
        SFXGarbageCollection();
        try
        {
            clipArray.add(AudioSystem.getClip());
            int clipArrayNum = clipArray.size() - 1;
            clipArray.get(clipArrayNum).open(AudioSystem.getAudioInputStream(sound));
            clipArray.get(clipArrayNum).start();
        } catch(Exception e) {e.printStackTrace();}
        System.out.println(clipArray.size());
    }
    
    public void SFXGarbageCollection()
    {
        for (int i = 0; i < clipArray.size(); i++)
        {
            if (clipArray.get(i).isRunning() != true)
            {
                clipArray.get(i).close();
                clipArray.remove(i);
            }
        }
    }
}
