package me.koji;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final List<String> ALLOW_INPUTS = List.of("+", "-", "*", "/");

    public static void main(final String[] args) {
        System.out.println("This is a simple calculator made in Java. If you want to quit type \"quit\" or \"exit\".");

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Please select an action to proceed. Available: (%s)"
                        .formatted(ALLOW_INPUTS.stream().reduce("", (t, c) -> (t + (!t.isEmpty() ? ", " : "") + c)))
                );

                final String action = scanner
                        .nextLine();

                if (action.equalsIgnoreCase("quit") || action.equalsIgnoreCase("exit")) {
                    System.out.println("Quitting calculator...");
                    break;
                }

                if (!ALLOW_INPUTS.contains(action))
                    throw new IllegalArgumentException(action);

                System.out.println("Input a number.");
                final int number1 = scanner.nextInt();

                System.out.println("Input a second number.");
                final int number2 = scanner.nextInt();

                final int result = switch (action) {
                    case "+" -> number1 + number2;
                    case "-" -> number1 - number2;
                    case "*" -> number1 * number2;
                    case "/" -> number1 / number2;
                    default -> throw new IllegalStateException();
                };

                System.out.println("The final result is: %s".formatted(result));
                scanner.nextLine();
            } catch (final InputMismatchException exception) {
                System.out.println("The input isn't a valid number, please try again.");

                scanner.nextLine();
            } catch (final IllegalArgumentException exception) {
                System.out.println("Invalid actions, make sure you selected one of the available actions.");
            } catch (final NoSuchElementException exception) {
                System.out.println("You must input a action.");
            } catch (final Exception exception) {
                System.out.println("Sorry, something got wrong please try again.");
            }
        }
    }
}