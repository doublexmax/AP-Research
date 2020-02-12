import java.lang.*;
import java.util.*;

public class Main {
  public static Random rand = new Random();
  public static String real;
  public static ArrayList<Integer> finaled = new ArrayList<Integer>();
  public static HashMap<Integer, Integer> keys = new HashMap<>();
  public static void main(String[] args) throws InterruptedException {
    int length = 5;
    //Enable these two lines when determining absolute threshold
	  //SecondBlock(5);
    //System.exit(0);
	  set();

	while (!threeTimes(keys)){
		test(length);
		System.out.print("\033[H\033[2J");
		Thread.sleep(2000);
		System.out.println("New Trial");
		Thread.sleep(2000);
		System.out.println("List length: "+Integer.toString(length));
    	print();
		if (run(length)==(length+1)){
			int temp=keys.get(length);
			keys.replace(length, temp+1);
			length++;
		}
		else
			length--;
		finaled.clear();
	}
	System.out.println("Test complete. Final score: " +Integer.toString(length-1));
  }
  public static int run(int length) throws InterruptedException{
	  int temp = length;
	  Scanner n = new Scanner(System.in);
	  for (int i=0; i<length; i++){
			String letter = n.nextLine();
			System.out.print("\033[H\033[2J");
			if (letter.equals(Integer.toString(finaled.get(i)))==false){
				System.out.println("Incorrect letter.");
				Thread.sleep(500);
				return (temp-1);
			}
		}
		return temp+1;
  }
  public static boolean threeTimes(HashMap<Integer, Integer> map){
	  for (int i=3; i<16; i++)
	  	if (map.get(i)==3)
			return true;
	  return false;
  }
  public static void set(){
	  for (int i=3; i<16; i++)
		  keys.put(i, 0);
  }
  public static void test(int length){
	int temp = length;
	for (int i=0; i<temp; i++){
		int maybe=(int)(Math.random()*((9-0)+1)+0);
		if (occurences(maybe)>2 || ascencion(i, maybe) || descencion(i, maybe) || repetition(i, maybe) || repeat(i, maybe)) 
		    i--;
		else
		    finaled.add(maybe);
	}
	StringBuilder strBuilder = new StringBuilder();
	for (int i = 0; i < finaled.size(); i++) {
		strBuilder.append(finaled.get(i));
	}
	real = strBuilder.toString();
  }
  public static void print() throws InterruptedException {
	  Thread.sleep(2000);
		System.out.print("\033[H\033[2J");
	  for (int j=0; j<finaled.size(); j++){
        System.out.println(finaled.get(j));
		Thread.sleep(1000);
		System.out.print("\033[H\033[2J");
	}
	System.out.println("?????");
	Thread.sleep(1500);
	System.out.print("\033[H\033[2J");
  }
  public static int occurences(int item) {
	  int result=0;
	  for (int i=0; i<finaled.size(); i++)
		  if (item==(finaled.get(i)))
			result++;
		return result;
  }
  public static boolean ascencion(int i, int number){
	  if (i<=1)
	  	return false;
		if ((number-2)==(finaled.get(i-2)) && (number-1)==finaled.get(i-1))
			return true;
	  return false;
  }
  public static boolean descencion(int i, int number){
	  if (i<=1)
	  	return false;
	  if ((number+2)==finaled.get(i-2) && (number+1)==finaled.get(i-1))
	  	return true;
	  return false;
  }
  public static boolean repetition(int i, int number){
	  if (i>2){
		  if (finaled.get(i-3)==finaled.get(i-1) && finaled.get(i-2)==number)
		  	return true;
	  }
	  return false;
  }
  public static boolean repeat(int i, int number){
	  if (i<1)
	  	return false;
	  if (finaled.get(i-1)==number){
		  return true;
	  }
	  return false;
  }
  public static void SecondBlock(int length) throws InterruptedException{
    System.out.print("\033[H\033[2J");
	  HashMap<Integer, Integer> map = new HashMap<>();
	  HashMap<Integer, Integer> correct = new HashMap<>();
    boolean randomTrue;
	  for (int i=length-1; i<length+4;i++){
		  map.put(i, 0);
		  correct.put(i, 0);
	  }
	  for (int j=0; j<40; j++){
		  randomTrue=true;
		  int choice=0;
		  while (randomTrue){
			choice = (int)(Math.random()*(length+3-(length-1))+(length-1));
			if(map.get(choice)!=8){
				map.replace(choice, map.get(choice)+1);
        randomTrue=false;
			}
		  }
		  test(choice);
		  System.out.print("\033[H\033[2J");
			Thread.sleep(2000);
			System.out.println("New Trial");
			Thread.sleep(2000);
			System.out.println("List length: "+Integer.toString(choice));
			Thread.sleep(2000);
			print();
			if (run(choice)==(choice+1)){
				System.out.println("Correct.");
				correct.replace(choice, correct.get(choice)+1);
			}
      finaled.clear();
	  }
  System.out.println("Trial Complete. Thank you for participating.");
	Thread.sleep(250);
	System.out.println("Please do not restart the program as results are printing.");
	for (int n : correct.keySet()){
	  System.out.println(Integer.toString(correct.get(n))+" correct out of 8 at list length "+Integer.toString(n)+".");
	}
}
}
