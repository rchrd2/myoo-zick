package tactu5;

/*
 * TACTU5 by Alessandro Capozzo  
 * ALPHA 0.2
 * www.abstract-codex.net
 */

//////////////////////////////////////
//  interface for clusters          //
//  and sequences                   //
///////////////////////////////////////
public interface T5Containers  {
   
       void addNote(Note inote);
       public void addNotes (Note[] inote);
       int getContainerNum();
       Note getNote(int index);
     
   
   }