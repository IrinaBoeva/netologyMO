import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Молоко", "Хлеб", "Гречка"};
    static int[] prices = {50, 14, 80};

    static File saveFile = new File("basket.bin");

    public static void main(String[] args) throws FileNotFoundException {

        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromBinFile(saveFile);

        } else {
            basket = new Basket(products, prices);
        }

        while (true) {
            System.out.println("Список продуктов для покупателей:");
            for (int i = 0; i < products.length; i++) {
                System.out.println(i + 1 + "." + products[i] + " " + prices[i] + "руб/шт");
            }

            System.out.println("Выберите товар и укажите количество или введите `end`");
            String inputString = scanner.nextLine();
            if ("end".equals(inputString)) {

                break;
            }
            String[] parts = inputString.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveBin(saveFile);
        }
        basket.printCart();
    }
}
