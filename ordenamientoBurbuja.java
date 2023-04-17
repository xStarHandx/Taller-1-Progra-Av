import java.util.Scanner;
public class ordenamientoBurbuja {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		

	}
	
	private static void burbuja(int[]vector){
		for(int i=0;i<vector.lenght;i++){
			for(int j=i+1;j<vector.lenght;j++){
				if(vector[i]<vector[j]){
					intercambiar(vector,i,j);
				}
			}
		}
	}

}
