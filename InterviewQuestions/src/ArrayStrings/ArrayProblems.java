package ArrayStrings;

public class ArrayProblems {

	private void rotateMatrix(int[][] image){
		if(image == null || image.length != image[0].length) return;
		
		int temp = 0;
		int N = image.length;
		for(int l = 0; l < N/2; l++){
			for(int i = 0; i < (N - 1 - 2 * l); i++){
				temp = image[l][l + i];
				image[l][l + i] = image[N - 1 - l - i][l];
				image[N - 1 - l - i][l] = image[N - 1 - l][N - 1 - l - i];
				image[N - 1 - l][N - 1 - l - i] = image[l + i][N - 1 - l];
				image[l + i][N - 1 - l] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		int N = 5;
		int[][] image = new int[N][N];
		int count = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				image[i][j] = count;
				count++;
			}
		}
		
		//iamge before rotation
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				System.out.print(image[i][j] + "\t");
			}
			System.out.println();
		}
		
		ap.rotateMatrix(image);
		
		//image after rotation
		System.out.println();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				System.out.print(image[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
