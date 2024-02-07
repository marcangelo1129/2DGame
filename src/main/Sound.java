/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

/**
 *
 * @author geps
 */
public class Sound {
    
    GamePanel gp;
    
    Random random = new Random();
    public Clip clip;
    public ArrayList<Clip> clipArray = new ArrayList<>();
    int musicTrack;
    boolean musicState;
    int clipCounter = 1;
    URL soundURL[] = new URL[30];
    
    public Sound(GamePanel gp)
    {
        this.gp = gp;
        soundURL[0] = getClass().getResource("/music/sound2.wav");
        soundURL[1] = getClass().getResource("/music/sound1.wav");
        soundURL[2] = getClass().getResource("/music/sound3.wav");
        soundURL[3] = getClass().getResource("/music/sound4.wav");
    }
//    public void setListener()
//    {
//        clip.addLineListener(e -> 
//        {
//            if (e.getType() == LineEvent.Type.STOP)
//            {
//                if (gp.GameState != "inGame")
//                    return;
//                if (musicTrack == 3)
//                    musicTrack = 1;
//                else
//                {
//                    musicTrack++;
//                }
//                playMusic();
//                setListener();
//            }
//        });
//    }
    public void playIntro()
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){}
    }
    public void playMusic()
    {
        musicTrack = random.nextInt(1,3);
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[musicTrack]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }
        catch(Exception e){}
        musicState = true;
    }
    
    public void musicUpdate()
    {
        if (musicState == false)
            return;
        if (clip.getFramePosition() >= clip.getFrameLength())
        {
            if (musicTrack == 3)
                musicTrack = 1;
            else
                musicTrack++;
            
            try
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[musicTrack]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
            catch(Exception e){}

        }
    }
    
    public void stopMusic()
    {
        musicState = false;
        if (clip != null)
            clip.stop();
    }
    
    
    /**
     * getClass().getResource("/loadout_equip.wav")
     * @param sound
     */
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
    }
    
    public void SFXGarbageCollection()
    {
        for (int i = clipArray.size() - 1; i > 0; i--)
        {
            if (clipArray.get(i) == null)
                return;
            Clip clipAssigned = clipArray.get(i);
            if (clipAssigned.isRunning() != true)
            {
                clipAssigned.close();
                clipArray.remove(clipAssigned);
            }
        }
    }
    
    public void loadoutSelectSound()
    {
        int choices = random.nextInt(1, 3);
        switch (choices)
        {
            case 1:
                playSFX(getClass().getResource("/weaponFX/wpn_loadout_select_1.wav"));
                break;
            case 2:
                playSFX(getClass().getResource("/weaponFX/wpn_loadout_select_2.wav"));
                break;
            case 3:
                playSFX(getClass().getResource("/weaponFX/wpn_loadout_select_3.wav"));
                break;
        }
    }
    
    public void playZombieAttack()
    {
        int choices = random.nextInt(1, 3);
        switch (choices)
        {
            case 1:
                playSFX(getClass().getResource("/enemy/zombie_attack1.wav"));
                break;
            case 2:
                playSFX(getClass().getResource("/enemy/zombie_attack2.wav"));
                break;
            case 3:
                playSFX(getClass().getResource("/enemy/zombie_attack3.wav"));
                break;
            default:
                //do nothing
        }
    }
    
    public void playZombieDeath()
    {
        int choices = random.nextInt(1, 4);
        switch (choices)
        {
            case 1:
                playSFX(getClass().getResource("/enemy/zombie_death1.wav"));
                break;
            case 2:
                playSFX(getClass().getResource("/enemy/zombie_death2.wav"));
                break;
            case 3:
                playSFX(getClass().getResource("/enemy/zombie_death3.wav"));
                break;
            case 4:
                playSFX(getClass().getResource("/enemy/zombie_death4.wav"));
                break;
            default:
                //do nothing
        }
    }
}
