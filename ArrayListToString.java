package com.example.store;
import java.util.ArrayList;
public class ArrayListToString {

    public String aToS(ArrayList<Integer> a){
        StringBuilder SB= new StringBuilder();
            for(int item : a){
                if(SB.length()>0){
                    SB.append(", ");
                }
                SB.append(String.valueOf(item));
            }
            String S= SB.toString();

            return S;
    }
    
}
