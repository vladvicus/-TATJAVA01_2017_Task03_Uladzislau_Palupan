package com.epam.catalog.view;

import com.epam.catalog.controller.Controller;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public final static String DATAFILE = Paths.get("data/units.txt").toAbsolutePath().toString();
	public static void main(String[] args) {
		int flag;
		Controller first = new Controller();
		List<?> result = new ArrayList<>();
		System.out.println("HELLO, MY DEAR CLIENT!!! YOU ARE INSIDE THE CATALOG !!!");
		do {
			System.out.println();
			System.out.println("Input   1-To input command\n" + "\t0-To exit");

			flag = inputInt();
			switch (flag) {
			case 1:
				do {
					Scanner sc1 = new Scanner(System.in);
					System.out.println("Input command with parameter(or copy/paste from the list) or 'q' for exit");
					if (sc1.hasNextLine()) {
						String message = sc1.nextLine();
						if (message.equals("q")) {
							System.out.println("Bye!!");
							break;
						}

						result = first.executeTask(message);

					}

				} while (result == null);
				if (result.isEmpty()) {
					System.out.println("Empty search result!!!Reenter the command with new parameter!!!");
				}
				showCollection(result);
				System.out.println();
			case 0:
				System.out.println("Thank YOU!");
				break;
			}
		} while (flag != 0);

	}

	private static void showCollection(List<?> books) {
		System.out.println("Result of the Command-------------------");
		for (Object oneBook : books) {
			System.out.println(oneBook);
		}
	}

	static int inputInt() {
		int x = 0;
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			System.out.println("You input not an integer number");
			System.out.println("Input an integer number");

			sc = new Scanner(System.in);
		}
		x = sc.nextInt();

		return x;
	}
}
