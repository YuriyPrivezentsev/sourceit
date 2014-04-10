package ua.com.sourceit.power;

import java.util.*;

/**
 * Created by yuriy on 06.04.14.
 */
public class PowerOfTwo {

    public static void main(String[] args) {
        long average;
        average = 0;
        int limit = 11;
        for (int i = 0; i < limit; i++ ){
            long start = System.currentTimeMillis();
            powerArrayIteratorToString((byte) 2, 2000);
            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("For plain array with iterator to String time is " + delta + "ms");
            if(i != 0) average += delta;
        }
        System.out.println("Average time " + (double)average/(double) 10 + "\n");

        average = 0;
        for (int i = 0; i < limit; i++ ){
            long start = System.currentTimeMillis();
            powerArray((byte)2,2000);
            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("For plain array time is " + delta + "ms");
            if(i != 0) average += delta;
        }
        System.out.println("Average time " + (double)average/(double) 10 + "\n");


        average = 0;
        for (int i = 0; i < limit; i++ ){
            if(i != 0) average += measureTime(ArrayList.class);
        }
        System.out.println("Average time " + (double)average/(double) 10 + "\n");

        average = 0;
        for (int i = 0; i < limit; i++ ){
            if(i != 0) average += measureTime(LinkedList.class);
        }
        System.out.println("Average time " + (double)average/(double) 10 + "\n");

    }

    private static long measureTime(Class<? extends List> collection) {
        long start = System.currentTimeMillis();
        power((byte)2,2000, collection);
        long end = System.currentTimeMillis();
        long delta = end - start;
        System.out.println("For " + collection + " time is " + delta + "ms");
        return delta;
    }


    private static String power(byte base, int power, Class<? extends List> collection){
        List<Byte> powerList = ListFactory.getList(collection);

        byte buffer;
        powerList.add((byte)1);
        for(int i = 1; i <= power; i++){
            buffer = 0;
            for (ListIterator<Byte> iterator = powerList.listIterator(); iterator.hasNext(); ) {
                byte next = iterator.next();
                next = (byte) (next * base + buffer);
                buffer = (byte) (next / 10);
                next %= 10;
                iterator.set(next);
            }
            if (buffer != 0){
                powerList.add(buffer);
            }
        }

        StringBuilder result = new StringBuilder();
        for(ListIterator<Byte> iterator = powerList.listIterator(powerList.size()); iterator.hasPrevious();){
            Byte previous = iterator.previous();
            result.append(previous);
        }
        return result.toString();
    }

    private static String powerArray(byte base, int power){
        StringBuffer result = new StringBuffer();
        List<Byte> powerList = powerWithArray(base, power);


        for (int i=0; i<powerList.size(); i++) {
            result.append(powerList.get(i));
        }

        return result.toString();
    }

    private static String powerArrayIteratorToString(byte base, int power){
        StringBuffer result = new StringBuffer();
        List<Byte> powerList = powerWithArray(base, power);


        for (Byte aPowerList : powerList) {
            result.append(aPowerList);
        }

        return result.toString();
    }

    private static List<Byte> powerWithArray(byte base, int power) {
        List<Byte> powerList = new ArrayList<>();
        powerList.add(0,base);
        byte buffer;
        for(int j = 1; j < power; j++){
            buffer = 0;
            for (int i = powerList.size() - 1; i >= 0; i--){
                byte next = powerList.get(i);
                next = (byte) (next * base + buffer);
                buffer = (byte) (next / 10);
                next %= 10;
                powerList.set(i,next);
                if(i == 0 && buffer >= 1){
                    powerList.add(i,buffer);
                }
            }
        }
        return powerList;
    }
}
