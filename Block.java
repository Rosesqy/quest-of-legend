/*Block class */

import java.util.*;
import java.io.*;

public class Block extends Cell{
    public Block(){
       this.label = "I";
    }

    @Override
    public boolean access(){
        return false;
    }
    
}