package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean notCheckedOut = true;
        Scanner scanner = new Scanner(System.in);

        List<Product> productList = FileManager.readProducts();
        List<Product> cart = new ArrayList<>();

        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Online Store Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. Search by SKU");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    displayProducts(productList);
                    break;

                case 2:
                    searchProduct(productList, cart, scanner);
                    break;

                case 3:
                    cartMenu(cart, scanner);
                    break;

                case 4:
                    checkout(cart);
                    break;

                case 5:
                    running = false;
                    System.out.println("Thanks for shopping!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        Product product = new Product("Av5699", "Noise Blocking Headphones", 79.99, "Audio Video");

        FileManager.writeProduct(product);


    }
    public static void displayProducts(List<Product> products)
    {
        System.out.println("\nAvailable Products:");
        System.out.println("\nSKU | Product Name | Price | Department |");

        for(Product product : products)
        {
            System.out.printf("%s | %s | $%.2f | %s%n",
                    product.getsKU(),
                    product.getItem(),
                    product.getPrice(),
                    product.getDepartment());
        }
    }
    public static void searchProduct(List<Product> products,
                                     List<Product> cart,
                                     Scanner scanner)
    {
        System.out.print("Enter SKU: ");
        String sku = scanner.nextLine();

        boolean found = false;

        for(Product product : products)
        {
            if(product.getsKU().equalsIgnoreCase(sku))
            {
                found = true;

                System.out.println("\nProduct Found:");
                System.out.printf("%s | %s | $%.2f%n",
                        product.getsKU(),
                        product.getItem(),
                        product.getPrice());

                System.out.println("Add to cart? (Y/N)");
                String answer = scanner.nextLine();

                if(answer.equalsIgnoreCase("Y"))
                {
                    cart.add(product);
                    System.out.println("Added to cart!");
                }
            }
        }

        if(!found)
        {
            System.out.println("SKU not found.");
        }
    }
    public static void cartMenu(List<Product> cart,
                                Scanner scanner)
    {
        if(cart.isEmpty())
        {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n=== Cart ===");

        for(int i = 0; i < cart.size(); i++)
        {
            Product product = cart.get(i);

            System.out.printf("%d. %s - $%.2f%n",
                    i + 1,
                    product.getItem(),
                    product.getPrice());
        }

        System.out.println("Remove an item?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1)
        {
            System.out.print("Enter item number: ");
            int itemNumber = scanner.nextInt();
            scanner.nextLine();

            if(itemNumber > 0 && itemNumber <= cart.size())
            {
                Product removed = cart.remove(itemNumber - 1);

                System.out.println(
                        removed.getItem() + " removed from cart."
                );
            }
        }
    }
    public static void checkout(List<Product> cart)
    {
        if(cart.isEmpty())
        {
            System.out.println("Cart is empty.");
            return;
        }

        double total = 0;

        System.out.println("\n=== Checkout ===");

        for(Product product : cart)
        {
            System.out.printf("%s - $%.2f%n",
                    product.getItem(),
                    product.getPrice());

            total += product.getPrice();
        }

        System.out.printf("Total: $%.2f%n", total);

        System.out.println("Checkout complete!");
    }
}