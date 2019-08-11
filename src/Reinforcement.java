
import java.util.ArrayList;
import java.util.Random;

public class Reinforcement {





    double rewardTable[][] = {{-1,-1,-1,-1,0,-1},
            {-1,-1,-1,0,-1,100},
            {-1,-1,-1,0,-1,-1},
            {-1,0,0,-1,0,-1},
            {0, -1, -1, 0, -1, 100},
            {-1,0,-1,-1,0,100}
    };
    double qTable [][] = new double[6][6];

    public static void main(String args[]){
        double lambda = 0.8;
        boolean goalState = false;
        Reinforcement r = new Reinforcement();
        int nextState =0 ;
        int state = 1;
        int numberOfEpisodes = 100000;
        Random ran = new Random();

        for (int i = 0; i < numberOfEpisodes; i++) {


            state = ran.nextInt((5 -0)+ 1) + 0;
            //System.out.println(state);
            while (state !=5) {
                //Look at reward table for currentstate
                nextState = r.getRandomAction(state);
                //Update our qTable with the calculation
                r.qTable[state][nextState] =
                        r.rewardTable[state][nextState] + lambda * (r.getNonNegRewards(nextState));
                state = nextState;
            }

        }

        for(int i = 0; i< 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.println(r.qTable[i][j]);
            }

        }
    }

    public int getRandomAction(int currentstate){
        ArrayList<Integer> actions = new ArrayList<>();

        int selectedAction;
        for(int i = 0; i<6; i++){
            if (rewardTable[currentstate][i] != -1){
                actions.add(i);
            }
        }

        Random r = new Random();
        selectedAction = r.nextInt(( (actions.size()-1) - 0) + 1) + 0;

        return actions.get(selectedAction);
    }
    public double getNonNegRewards(int action){
       double max = 0;

        for (int i = 0; i< 6; i++){
            if(qTable[action][i] > max){
                max = qTable[action][i];
            }
        }

        return max;
    }
}
