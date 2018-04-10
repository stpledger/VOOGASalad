package data;

import com.sun.org.apache.bcel.internal.generic.DMUL;
import com.sun.org.apache.regexp.internal.RESyntaxException;

import javax.swing.event.TreeExpansionEvent;

public class Level {
    /*

            *******************NOTICE THIS**********************
            Class is a Place Holder Class The Real Level Class
            Is in Authoring!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

     */
    private String levelName;
    private int levelNum;
    private String gameName;


    public Level(String levelname, String gamename, int levelnum)
    {
        this.levelName=levelname;
        this.gameName=gamename;
        this.levelNum=levelnum;
    }

    private String getLevelName() {
        return levelName;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public String getGameName() {
        return gameName;
    }
}
