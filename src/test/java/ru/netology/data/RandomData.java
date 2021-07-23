package ru.netology.data;

public class RandomData {
    public static String getString(int value) {
        // выбрал символ случайный из этой строки
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        // создаем StringBuffer размером AlphaNumericString
        StringBuilder sb = new StringBuilder(value);
        for (int i = 0; i < value; i++) {
            // генерируем случайное число между
            // 0 переменной длины AlphaNumericString
            int index = (int) (AlphaNumericString.length() * Math.random());
            // добавляем символ один за другим в конец sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}