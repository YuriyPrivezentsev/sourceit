package ua.com.sourceit.power;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuriy on 06.04.14.
 */
public class ListFactory {
    public static List<Byte> getList(Class<? extends List> listType){
        if(listType == ArrayList.class){
            return new ArrayList<>();
        }
        if(listType == LinkedList.class){
            return new LinkedList<>();
        }
        throw new IllegalArgumentException("Unsupported list type: " + listType);
    }
}
