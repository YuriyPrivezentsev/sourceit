package ua.com.sourceit.hanoi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuriy on 10.04.14.
 */
public class Hanoi {

    private static List<Deque<Integer>> towers;

    public void setUp(){
        towers = new ArrayList<>();
        Deque<Integer> tower = new LinkedList<>();
        for (int i = 7; i > 0; i--) {
            tower.push(i);
        }
        towers.add(null);
        towers.add(tower);
        towers.add(new LinkedList<Integer>());
        towers.add(new LinkedList<Integer>());
    }

    public void moveTower(int from, int to, boolean recursive){
        if(recursive){
            moveTowerRecursive(towers.get(from).size(),from,to);
        }
        else {
            moveTowerNonRecursive(from, to);
        }
    }

    private void moveTowerNonRecursive(int from, int to){
        Deque<Move> actionStack= new LinkedList<>();
        int source;
        int destination;
        int fullDepth = towers.get(from).size();
        Move move = new Move(fullDepth, from, to);
        int stepDepth;

        while (move != null) {
            source = move.getFrom();
            destination = move.getTo();
            stepDepth = move.getDepth();

            //Push into stack
            for (int depth = stepDepth; depth >= 1 ; depth--){
                int spareTower = getSpareTower(source, destination);
                move = new Move(depth, source, destination);
                destination = spareTower;
                push(actionStack, move);
            }

            //Popping from stack according to depth of recursion
            stepDepth = move.getDepth();
            for (int j = 0; j <= stepDepth && !actionStack.isEmpty(); j++) {
                move = actionStack.pop();
                moveRing(move.getFrom(), move.getTo());
            }
            if (stepDepth != 0) {
                source = getSpareTower(move.getFrom(), move.getTo());
                destination = move.getTo();
                move = new Move(move.getDepth()-1, source,destination);
            }
            else {
                move = null;
            }
        };


    }

    private void push(Deque<Move> actionStack, Move move) {
        actionStack.push(move);
    }

    private void moveTowerRecursive(int depth, int from, int to){
        int spareTower = getSpareTower(from, to);
        if (depth != 1) {
            moveTowerRecursive(depth - 1, from, spareTower);
            moveRing(from, to);
            moveTowerRecursive(depth - 1, spareTower, to);
        }
        else {
            moveRing(from,to);
        }
    }

    private void moveRing(int from, int to) {
        Integer ring = towers.get(from).pop();
        towers.get(to).push(ring);
        String message = "Move ring %d from %d tower to %d tower";
        System.out.println(String.format(message,ring,from,to));
    }

    public int getSpareTower(int from, int to) {
        return 6 - from - to;
    }

    private class Move {
        private int from;
        private int to;
        private int depth;

        private Move(int depth, int from, int to) {
            this.depth = depth;
            this.from = from;
            this.to = to;
        }

        public int getDepth() {
            return depth;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.setUp();
        hanoi.moveTower(1, 3, true);
        System.out.println();

        hanoi.setUp();
        hanoi.moveTower(1, 3, false);
        System.out.println();
    }
}
