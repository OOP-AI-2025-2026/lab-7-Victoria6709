package ua.opnu;
import java.util.Arrays;
import java.util.function.Predicate;
import ua.opnu.Student;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 17, 19, 20};
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        int[] primes = filter(test, isPrime);
        System.out.println("Прості числа: " + Arrays.toString(primes));

        Student[] students = {
                new Student("Іван", "Біленко", "AI-241", new int[]{75, 80, 90}),
                new Student("Олександр", "Ковальчук", "AI-243", new int[]{55, 60, 70}),
                new Student("Петро", "Шевченко", "AI-244", new int[]{40, 50, 45}),
                new Student("Марія", "Іваненко", "AI-245", new int[]{95, 85, 100})
        };

        Predicate<Student> hasDebts = Student::hasDebts;
        Student[] filteredStudents = filterStudents(students, hasDebts);
        System.out.println("Студенти з заборгованостями:");
        for (Student s : filteredStudents) {
            System.out.println(s);
        }

        int[] numbers = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> divisibleByThree = n -> n % 3 == 0;
        int[] filteredNumbers = filter(numbers, isEven, divisibleByThree);
        System.out.println("Числа > 20, парні і діляться на 3: " + Arrays.toString(filteredNumbers));

        Consumer<Student> printFullName = student ->
                System.out.println(student.getLastName() + " " + student.getFirstName());
        System.out.println("\nСписок студентів (ПРІЗВИЩЕ + ІМ'Я):");
        forEach(students, printFullName);

        int[] values = {5, 10, 15, 20, 25, 30};
        Predicate<Integer> greaterThan15 = n -> n > 15;
        Consumer<Integer> printNumber = n -> System.out.println("Число: " + n);
        System.out.println("Числа > 15:");
        processIf(values, greaterThan15, printNumber);

        Predicate<Integer> isEvenNumber = n -> n % 2 == 0;
        Consumer<Integer> printSquare = n -> System.out.println("Квадрат: " + (n * n));
        System.out.println("\nКвадрати парних чисел:");
        processIf(values, isEvenNumber, printSquare);

        int[] numbers1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] powers = processArray(numbers1, powerOfTwo);
        System.out.println("2^n для чисел від 0 до 9: " + Arrays.toString(powers));

        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] wordified = stringify(digits, numberToWord);
        System.out.println("Числа у вигляді слів: " + Arrays.toString(wordified));
    }

    public static int[] filter(int[] input, Predicate<Integer> predicate) {
        int[] result = new int[input.length];
        int counter = 0;
        for (int i : input) {
            if (predicate.test(i)) {
                result[counter++] = i;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    public static Student[] filterStudents(Student[] students, Predicate<Student> predicate) {
        Student[] result = new Student[students.length];
        int counter = 0;
        for (Student s : students) {
            if (predicate.test(s)) {
                result[counter++] = s;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    public static int[] filter(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        int[] result = new int[input.length];
        int counter = 0;
        for (int i : input) {
            if (p1.test(i) && p2.test(i)) {
                result[counter++] = i;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    public static void forEach(Student[] students, Consumer<Student> action) {
        for (Student s : students) {
            action.accept(s);
        }
    }

    public static void processIf(int[] input, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int i : input) {
            if (condition.test(i)) {
                action.accept(i);
            }
        }
    }

    public static int[] processArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    public static Function<Integer, String> numberToWord = n -> {
        String[] words = {
                "нуль", "один", "два", "три", "чотири",
                "п’ять", "шість", "сім", "вісім", "дев’ять"
        };
        return (n >= 0 && n <= 9) ? words[n] : "невідоме число";
    };
}
