
  
import java.util.Date;  
  
public class Result {  
  
    private String hitID;  
    private int type;  
  
    public Result() {  
        
    }  
  
    public Result(String hitID, int type) {  
        this.hitID = hitID;  
        this.type = type;  
    }  
  
    public String getHitID() {  
        return hitID;  
    }  
  
    public void setHitID(String hitID) {  
        this.hitID = hitID;  
    }  
  
    public int getType() {  
        return type;  
    }  
  
    public void setType(int type) {  
        this.type = type;  
    }  
  
}  
