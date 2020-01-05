import java.util.Scanner;

// 0100110101

public class solvelfsr {

    public static int zeroOrOne(char c) {
        return c == '1' ? 1 : 0;
    }

    public static int[][] makeX(int n, String key) {
        int[][] X = new int[n][n];
        int a = n - 1;
        for (int i = 0; i < n; i++) {
            a++;
            int t = a;
            for (int j = 0; j < n; j++) {
                X[j][i] = zeroOrOne(key.charAt(--t));
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(X[i][j] + " ");
//            }
//            System.out.println();
//        }

        int[][] AugmentedInverted = inverse(X, n);
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                X[i][j - n] = AugmentedInverted[i][j];
            }
        }

                System.out.println("lmao rip");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                System.out.print(AugmentedInverted[i][j] + " ");
            }
            System.out.println();
        }

        return X;
    }


    static int[][] inverse(int[][] matrix, int n) {
        int[][] augmented = new int[n][n * 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented[i][j] = matrix[i][j];
            }
            augmented[i][i + n] = 1;
        }
//        System.out.println("lmao rip");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < 2 * n; j++) {
//                System.out.print(augmented[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (augmented[j][i] == 1) {
                    if (j > i) {
                        //swap rows in matrix:
                        swap(augmented, i, j, n);
                        j=i;
                    }
                    //xor with row that has 1:
                    for (int k = j + 1; k < n; k++) {
                        if (augmented[k][i] == 1) {
                            for (int l = 0; l < 2 * n; l++) {
                                if (augmented[k][l] == augmented[j][l])
                                    augmented[k][l] = 0;
                                else
                                    augmented[k][l] = 1;
                            }
                        }
                    }
                    System.out.println("after xor");
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < 2 * n; y++) {
                            System.out.print(augmented[x][y] + " ");
                        }
                        System.out.println();
                    }
                    break;
                }
            }
        }
//        System.out.println("BEFORE");
//                            for (int x = 0; x < n; x++) {
//                        for (int y = 0; y < 2 * n; y++) {
//                            System.out.print(augmented[x][y] + " ");
//                        }
//                        System.out.println();
//                    }


        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (augmented[j][i] == 1) {
                    //xor with row that has 1:
                    for (int l = 0; l < 2 * n; l++) {
                        if (augmented[j][l] == augmented[i][l])
                            augmented[j][l] = 0;
                        else
                            augmented[j][l] = 1;
                    }
                }
            }
        }

//        System.out.println("AUG");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < 2 * n; j++) {
//                System.out.print(augmented[i][j] + " ");
//            }
//            System.out.println();
//        }

        return augmented;
    }

    static void swap(int[][] matrix, int i, int j, int n) {
        for (int k = 0; k < 2 * n; k++) {
            int t = matrix[i][k];
            matrix[i][k] = matrix[j][k];
            matrix[j][k] = t;
        }

    }


    public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int r1, int c1, int c2) {
        int[][] product = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return product;
    }


    public static int[][] makeY(int n, String key) {
        int[][] Y = new int[n][n];
        int a = n;
        for (int i = 0; i < n; i++) {
            a++;
            int t = a;
            for (int j = 0; j < n; j++) {
                Y[j][i] = zeroOrOne(key.charAt(--t));
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(Y[i][j] + " ");
//            }
//            System.out.println();
//        }

        return Y;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String knownPartOfKey = sc.nextLine();

        int[][] X = makeX(n, knownPartOfKey);
//        System.out.println("Y:");
        int[][] Y = makeY(n, knownPartOfKey);

        int[][] prod = multiplyMatrices(Y, X, n, n, n);

//        System.out.println("PRODUCT");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(prod[i][j] + " ");
//            }
//            System.out.println();
//        }
        String tapVec = "";
        for (int i = 0; i < n; i++) {
            int x = prod[0][i] % 2;
            tapVec += x;
        }


        StringBuilder sb = new StringBuilder();
        String initContent = (knownPartOfKey.substring(0, n));
        sb.append(initContent);
        sb = sb.reverse();
        // INITIAL CONTENT OF REGISTER IS first n BITS OF KEY REVERSED
        System.out.println(sb);
        System.out.println(tapVec);

    }
}


//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i; j >= 0; j--) {
//                if (augmented[i][j] == 1) {
//                    if (j < i) {
//                        //swap rows in matrix:
//                        swap(augmented, i, j, n);
//                    }
//                    //xor with row that has 1:
//                    for (int k = n-1; k >= 0; k--) {
//                        if (augmented[k][i] == 1) {
//                            for (int l = 0; l < 2 * n; l++) {
//                                if (augmented[k][l] == augmented[i][l])
//                                    augmented[k][l] = 0;
//                                else
//                                    augmented[k][l] = 1;
//                            }
//
//                        }
//                    }
//                    break;
//                }
//            }
//        }