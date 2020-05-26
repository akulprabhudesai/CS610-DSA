package DSA;

// AKUL ANANT PRABHUDESAI cs610 7193 prp

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lexicon_7193 {
	static int N = 0;
	static int[] hashtable = null;
	static char[] A = null;
	static int index = 0;
	static int length = 0;
	static boolean start = false;

	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.out.println("Please enter file name");
			System.exit(0);
		}
		try {
		String file = args[0];
		Scanner sc = new Scanner(new File(file));
		while (sc.hasNext()) {
			try {
				String current = sc.next();
				if (current.contains("14") && start == false) {

					int lengthT = sc.nextInt();
					start = true;
					length = 15 * lengthT;
					CreateTable(lengthT, length);
				} else if (current.contains("10")) {
					String word1 = sc.next();
					int status = Lexicon_7193.Insert(word1);
					if (status == -1) {
						char[] B = InsertFail(A, word1);
						OverFlow(B);
					}
				} else if (current.contains("11")) {
					String word = sc.next();
					int ind = 0;

					int sum = 0;
					for (int i = 0; i < word.length(); i++) {
						sum = sum + (int) word.charAt(i);
					}
					sum = sum % N;
					boolean success = true;
					int position = 0;
					for (int i = 0; i < N; i++) {
						position = (sum + (i * i)) % N;
						if (hashtable[position] == -1)
							continue;
						for (int j = hashtable[position]; A[j] != '\0'; j++) {
							if (A[j] == word.charAt(ind)) {
								ind++;
								success = true;
								continue;
							} else {
								success = false;
								break;
							}
						}
						if (success == true) {
							ind = 0;

							for (int j = hashtable[position]; A[j] != '\0'; j++) {
								if (A[j] == word.charAt(ind)) {
									A[j] = '*';
									ind++;
								}
							}
							hashtable[position] = -1;
							System.out.println(word + " deleted from slot " + position);
							System.out.println();
							break;
						} else {
							continue;
						}

					}
					ind = 0;
				}

				else if (current.contains("12")) {
					String word = sc.next();
					int sum = 0;
					if (N < 0) {
						System.out.println("Hash table is empty");
						continue;
					}
					for (int i = 0; i < word.length(); i++) {
						sum = sum + (int) word.charAt(i);
					}
					sum = sum % N;
					int position = 0;
					boolean success = true;
					for (int i = 0; i < N; i++) {
						position = (sum + (i * i)) % N;
						int k = hashtable[position];
						int j = 0;

						if (k == -1) {
							success = false;
							break;
						}
						while (A[k] != '\0') {
							try {
								if (A[k] == word.charAt(j)) {
									k++;
									j++;
									success = true;
									continue;
								} else {
									success = false;
									break;
								}
							} catch (Exception e) {
								success = false;
								continue;
							}

						}
						if (success == true) {
							System.out.println(word + " found at slot " + position);
							System.out.println();
							break;
						} else {
							success = false;
							continue;
						}

					}
					if (success == false) {
						System.out.println(word + " not found");
						System.out.println();
					}

				}

				else if (current.contains("13")) {
					if(A == null && hashtable == null) {
						continue;
					}
					System.out.print("    T");
					System.out.print("                   A: ");
					
					for (int i = 0; i < A.length; i++) {
						if (A[i] == '\0') {
							System.out.print("/");
							continue;
						} else if (A[i] == ' ') {

							continue;
						}
						System.out.print(A[i]);
					}
					System.out.println("");
					
					
					
					int temp = hashtable.length;
					int k = 0;
					while (temp > 0) {
						temp = temp / 10;
						k++;
					}
					String value = "%-" + k + "d";
					for (int i = 0; i < hashtable.length; i++) {

						System.out.printf(value, i);
						System.out.print(" : ");
						
						if (hashtable[i] == -1) {
							System.out.println();
							continue;
						}
						System.out.print(hashtable[i]);
						System.out.println();
						
					}
					System.out.println();
				} else if (current.contains("14") && start == true) {
					System.out.println("Hashtable already created");
				} else {
					String word = sc.nextLine();
					continue;
				}

			} catch (InputMismatchException e) {
				System.out.println("Length should be number of type int");
				System.exit(0);
               
			}catch (Exception e) {
				
				continue;
			} 

		}
		sc.close();
		try {
		int i = 0;
		for (int j = 0; j < hashtable.length; j++) {
			if (hashtable[j] != -1)
				i++;
		}
		System.out.println("Hash table has " + i + " positions filled");
		System.out.println("hash table has " + (hashtable.length - i) + " positions empty");
		}catch(Exception e) {
			System.out.println("Hashtable is not created, Please insert command to create hashtable and try again");
			System.exit(0);
		}
		} catch(Exception e) {
			System.out.println("Entered file is not available, Please try again");
			System.exit(0);
		}
	}

	public static int Insert(String word) {

		if (word.length() > (A.length - (index + 1))) {

			char[] C = new char[A.length];
			for (int i = 0; i < A.length; i++) {
				C[i] = A[i];
			}
			length = length + C.length + (15 * word.length());
			A = null;

			CreateArray(length);
			for (int i = 0; i < C.length; i++) {
				A[i] = C[i];
			}
		}
		int sum = 0;
		for (int i = 0; i < word.length(); i++) {
			sum = sum + (int) word.charAt(i);
		}
		sum = sum % N;
		int position = 0;
		boolean success = false;
		for (int i = 0; i < N; i++) {
			position = (sum + (i * i)) % N;
			if (hashtable[position] == -1) {

				hashtable[position] = index;
				for (int j = 0; j < word.length(); j++) {
					A[index] = word.charAt(j);
					index++;
				}
				A[index] = '\0';
				index++;
				success = true;
				break;
			} else {
				continue;
			}
		}
		if (success == true)
			return 0;
		return -1;
	}

	public static void CreateTable(int table, int array) {
		N = table;
		hashtable = new int[N];
		for (int i = 0; i < hashtable.length; i++) {
			hashtable[i] = -1;
		}
		CreateArray(array);
	}

	public static void CreateArray(int length) {
		A = new char[length];
		for (int i = 0; i < A.length; i++) {
			A[i] = ' ';
		}
	}

	public static int OverFlow(char[] B) {
        boolean flag = false;
		String word = "";
		for (int i = 0; i < B.length; i++) {
         
			if (B[i] != '\0' && B[i] != ' ' && B[i] != '*') {
				if(flag == true) {
					A[index] = '\0';
					index++;
					flag = false;
				}
				word = word + B[i];
			} else if (B[i] == ' ') {
				continue;
			} else if(B[i] == '*') {
				flag = true;
				putStart();			
				}
			else {
                if(word.equals("")) {
                continue;
                }
				int status = Insert(word);
				if (status == -1) {
					B = overInsert(B, word, i);
					i = -1;
				}
				word = "";
			}

			continue;
		}
		return 0;
	}

	public static char[] InsertFail(char[] C, String word) {
		char[] B = new char[C.length];
		for (int i = 0; i < C.length; i++) {
			B[i] = C[i];
		}
		index = 0;
		A = null;
		N = 2 * N;
		CreateTable(N, 15 * N);
		Insert(word);
		return B;
	}
	
	public static void putStart() {
		A[index] = '*';
		index++;
	}
	
	public static char[] overInsert(char[] B, String word, int k) {
		char[] D = new char[A.length + B.length];
		int temp = 0;
		for(int i = 0; i < A.length; i++) {
			D[i] = A[i];
			temp++;
		}
		temp++;
		for(int i = k+1; i < B.length; i++) {
			D[temp] = B[i];
			temp++;
		}
		A=null;
		N = 2 * N;
		index = 0;
		CreateTable(N, 15 * N);
		Insert(word);
		return D;
	}

}
