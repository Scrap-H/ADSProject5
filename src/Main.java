import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static List<Float> weight = new ArrayList<>();
    static List<Integer> Old_Solution = new ArrayList<>();
    static List<Integer> Current_Solution = new ArrayList<>();
    static float FinalFitness = 0;
    private static Random rand = new Random();


    public static List<Float> FileRead()
    {

    String file = "C:\\ADLProject5\\ADLProject5\\Brick2.csv";
    try{

        BufferedReader Buff = new BufferedReader(new FileReader(file));

        String Data;

        while((Data = Buff.readLine()) != null)
        {

            weight.add(Float.valueOf(Data));

        }

    }catch (IOException e ){
        throw new RuntimeException(e);
    }
    return weight;
    }


    public static void InitialSolution()
    {

        // CANGE MAX VALUE TO SIZE OF DATA SET
        for (int b = 0; b < 100; b++) {

            Current_Solution.add(rand.nextInt(2));

        }
    }

    public static float FitnessFunction()
    {

        float LoryA = 0;
        float LoryB = 0;
        float Fitness = 0;

    for(int i = 0 ; i < Current_Solution.size() ; i++)
    {
        if(Current_Solution.get(i) == 0)
        {
            LoryA = weight.get(i) + LoryA;
        }else if(Current_Solution.get(i) == 1)
        {
            LoryB = weight.get(i) + LoryB;
        }
    }

    if(LoryA > LoryB)
    {

        Fitness = LoryA - LoryB;

    }else
    {

        Fitness = LoryB - LoryA;

    }
        return Fitness;
    }


    public static List<Float> Small_Change(List<Float> Dataset)
    {
        int x = 2;
        while(x == 2)
        {


            int index1 = rand.nextInt(Current_Solution.size());
            int index2 = rand.nextInt(Current_Solution.size());

            if(Current_Solution.get(index1) != Current_Solution.get(index2))
            {

                int index;
                float weight;

                index = Current_Solution.get(index1);
                weight = Dataset.get(index1);

                Current_Solution.set(index1 , Current_Solution.get(index2) );  //make index1 = index 2
                Dataset.set(index1 , Dataset.get(index2));

                Current_Solution.set(index2 , index);//make index2 = index 1
                Dataset.set(index2 , weight);

                x = 1;

            }
        }
        return Dataset;
    }


    public static void Display(List<Integer> solution)
    {
        System.out.print("Solution : [ ");

        for(int i = 0 ; i < solution.size() ; i++)
        {
            System.out.print(solution.get(i) + " ");
        }

        System.out.print("] \n");
    }



    public static void Hill_Climbing(int itter)
    {

        List<Float> Dataset = new ArrayList<>();
        Dataset = weight;

        float New_Fitness;

        FinalFitness = FitnessFunction();

        Display(Current_Solution);
        System.out.println( "Solution Fitness Value : " + FinalFitness);

        for(int a = 0 ; a < itter ; a++ )
        {
            System.out.println("RMHC No : " + (a+1));

            Small_Change(Dataset);

            Display(Current_Solution);
            New_Fitness = FitnessFunction();
            System.out.println( "Solution Fitness Value : " + New_Fitness);

            if(New_Fitness < FinalFitness)
            {
                Old_Solution = Current_Solution;
                FinalFitness = New_Fitness;
            }

        }

    }

    public static void main(String[] args) {

       FileRead();
       InitialSolution();

        Hill_Climbing(50);


    }

}