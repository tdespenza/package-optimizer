package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

import java.util.Scanner;

public class Client {
    public static void main(final String[] args) throws APIException {
        System.out.println("Welcome To Package Optimizer!\n");
        System.out.println("Please note that any items with a price or weight over 100 will be discarded from packaging.\n");

        String filePath = null;
        if (args.length == 0) {
            final Scanner scanner = new Scanner(System.in);

            while (filePath == null || "".equals(filePath.trim())) {
                System.out.println("\nEnter a correct absolute file path: ");
                filePath = scanner.nextLine();
            }
        } else {
            filePath = args[0];
        }

        System.out.println(Packer.pack(filePath));
    }
}
