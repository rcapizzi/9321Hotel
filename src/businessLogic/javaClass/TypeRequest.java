package businessLogic.javaClass;

import java.util.ArrayList;
import java.util.List;

public class TypeRequest {
   private String typeName = "";
   private int numberRequested = 0;
   private List<Room> availableRooms = new ArrayList<Room>();

   public String getTypeName() {
      return typeName;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public int getNumberRequested() {
      return numberRequested;
   }

   public void setNumberRequested(int numberRequested) {
      this.numberRequested = numberRequested;
   }

   public List<Room> getAvailableRooms() {
      return availableRooms;
   }

   public void setAvailableRooms(List<Room> availableRooms) {
      this.availableRooms = availableRooms;
   }
}
