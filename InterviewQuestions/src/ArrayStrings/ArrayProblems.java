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

	private void matrixCheck(int[][] matrix){
		if(matrix == null) return;

		boolean[] rowFlags = new boolean[matrix.length];
		boolean[] colFlags = new boolean[matrix[0].length];

		for(int r = 0; r < matrix.length; r++){
			for(int c = 0; c < matrix[0].length; c++){
				if(matrix[r][c] == 0){
					rowFlags[r] = true;
					colFlags[c] = true;
				}
			}
		}

		for(int r = 0; r < matrix.length; r++){
			for(int c = 0; c < matrix[0].length; c++){
				if(rowFlags[r]){
					matrix[r][c] = 0;
					continue;
				}
				if(colFlags[c]){
					matrix[r][c] = 0;
				}
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


		int M = 4;
		int[][] matrix = new int[M][N];
		count = 0;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				matrix[i][j] = count;
				count++;
			}
		}
		matrix[1][2] = 0;

		System.out.println();
		//matrix before manipulation
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}

		ap.matrixCheck(matrix);

		//image after rotation
		System.out.println();
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
