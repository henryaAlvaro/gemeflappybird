import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class flappybird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class flappybird extends Actor
{
    private double g = 1;
    private int y = 200;
    private boolean haspressed = false;
    private boolean isalive = true;
    private boolean isacroos = false;
    private boolean hasaddscore = false;
    public flappybird(){
        GreenfootImage image = getImage();
        image.scale(50,40);
    }
    public void act()
    {
        // jika tekan spasi kordinay y akan berkurang dan terbang ke atas
        if(spacePressed()){
            g = -2;
        }
        g += 0.1; //Nilai g meningkat 0.1 setiap saat
        y += g; //Nilai y tidak berubah dengan kecepatan konstan
        setLocation(getX(), (int)(y));
        //jika menabrak pipa maka flappibird mati 
        if(isTouchpipe()){
            isalive = false;
        }
        //pemeberian sauara pada saat gameover
        if(isTouchpipe()){
            isalive = false;
        }
        //setelah jatuh atau menabrak pipa maka flappybird hilang
        if(!isalive){
            getWorld().addObject(new Gameover(), 300, 200);
            getWorld().removeObject(this);
        }
        //pemberian skor setelah melewati pipa dan pemberian suara
        if(!hasaddscore && isacroos && isalive){
            Greenfoot.playSound("score.mp3");
            Score.add(1);
        }
        hasaddscore = isacroos;
    }
    public boolean spacePressed(){
        boolean pressed = false;
        if(Greenfoot.isKeyDown("space")){
            if(!haspressed){//pemberian suara
               Greenfoot.playSound("flay.mp3");
               pressed = true;
        }
        haspressed = true;
    }else{
        haspressed = false;
    }
    return pressed;
    
    }
    //pemeberian suara ketika menabrak pipa dan jatuh
    public boolean isTouchpipe(){
        isacroos = false;
        for(pipe pipe : getWorld().getObjects(pipe.class)){
            if(Math.abs(pipe.getX() - getX()) < 69){
            if(Math.abs(pipe.getY() + 30 - getY()) > 37){
                Greenfoot.playSound("peng.mp3");
                isalive = false;
            }
            isacroos = true;
        }
    }
    return !isalive;
}   
}

