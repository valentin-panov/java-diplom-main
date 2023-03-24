package ru.netology.graphics;

import ru.netology.graphics.image.*;
import ru.netology.graphics.server.GServer;

public class Main {
    private static final char[] WIN_COLOR = new char[]{'#', '$', '@', '%', '*', '+', '-', '\''};
    private static final char[] DEFAULT_COLOR = new char[]{'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера
        TextColorSchema schema = new ColorConverter(DEFAULT_COLOR);
        converter.setTextColorSchema(schema);
        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

       // Или то же, но с выводом на экран:
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
