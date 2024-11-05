package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Основний клас, що виконує операції з матрицею, включаючи транспонування
 * та обчислення суми специфічних елементів.
 */
public class MatrixTranspose {

    /**
     * Головний виконавчий метод.
     *
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            float[][] matrix = inputMatrix(in);

            // Виведення вхідної матриці
            System.out.println("Вхідна матриця має наступний вигляд:");
            printMatrix(matrix);

            // Транспонування матриці
            float[][] transposedMatrix = transpose(matrix);

            // Виведення транспонованої матриці
            System.out.println("Транспортована матриця має наступний вигляд:");
            printMatrix(transposedMatrix);

            // Обчислення сум специфічних елементів
            float[] sums = calculateSums(transposedMatrix);
            System.out.println("Сума найбільших елементів у парних стовпцях: " + sums[0]);
            System.out.println("Сума найменших елементів у непарних стовпцях: " + sums[1]);

        } catch (InputMismatchException e) {
            System.out.println("Помилка вводу: будь ласка, введіть числові значення.");
        } catch (NegativeArraySizeException e) {
            System.out.println("Помилка: розмір матриці повинен бути більше 0.");
        } catch (Exception e) {
            System.out.println("Сталася непередбачена помилка: " + e.getMessage());
        } finally {
            in.close();
        }
    }

    /**
     * Запитує користувача на введення розмірів та елементів матриці.
     *
     * @param in Сканер для вводу користувача.
     * @return Двовимірний масив, що представляє матрицю.
     */
    public static float[][] inputMatrix(Scanner in) {
        System.out.println("Введіть кількість рядків:");
        int rows = in.nextInt();
        System.out.println("Введіть кількість стовпців:");
        int columns = in.nextInt();

        if (rows <= 0 || columns <= 0) {
            throw new NegativeArraySizeException("Розмір матриці повинен бути більше 0.");
        }

        float[][] matrix = new float[rows][columns];
        System.out.println("Введіть елементи матриці:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("Елемент (%d, %d): ", i + 1, j + 1);
                matrix[i][j] = in.nextFloat();
            }
        }
        return matrix;
    }

    /**
     * Транспонує вхідну матрицю.
     *
     * @param matrix Вхідна матриця для транспонування.
     * @return Транспонована матриця.
     */
    public static float[][] transpose(float[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        float[][] transposedMatrix = new float[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    /**
     * Виводить матрицю на екран.
     *
     * @param matrix Матриця для виведення.
     */
    public static void printMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            for (float element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /**
     * Обчислює суму найбільших елементів у парних стовпцях та
     * найменших елементів у непарних стовпцях матриці.
     *
     * @param matrix Матриця для обчислення.
     * @return Масив, що містить суму найбільших елементів у парних стовпцях
     *         та суму найменших елементів у непарних стовпцях.
     */
    public static float[] calculateSums(float[][] matrix) {
        float maxSum = 0.0F;
        float minSum = 0.0F;

        for (int j = 0; j < matrix[0].length; j++) {
            if ((j+1) % 2 == 0) {
                float maxEl = Float.NEGATIVE_INFINITY;
                for (int i = 0; i < matrix.length; i++) {
                    maxEl = Math.max(maxEl, matrix[i][j]);
                }
                maxSum += maxEl;
            } else {
                float minEl = Float.POSITIVE_INFINITY;
                for (int i = 0; i < matrix.length; i++) {
                    minEl = Math.min(minEl, matrix[i][j]);
                }
                minSum += minEl;
            }
        }
        return new float[]{maxSum, minSum};
    }
}
