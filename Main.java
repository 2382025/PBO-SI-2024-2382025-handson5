import java.util.Scanner;

public class Main {
    public static String[] todos = new String[3];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showToDoList() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todo != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addToDoList(String todo) {
        resizeArrayIfFull();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    public static void resizeArrayIfFull() {
        if (isArrayFull()) {
            resizeArrayToTwoTimesBigger();
        }
    }

    public static boolean isArrayFull() {
        for (String todo : todos) {
            if (todo == null) {
                return false;
            }
        }
        return true;
    }

    public static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        System.arraycopy(temp, 0, todos, 0, temp.length);
    }

    public static boolean removeToDoList(Integer number) {
        if (isSelectedToDoNotValid(number)) {
            return false;
        }
        for (int i = number - 1; i < todos.length - 1; i++) {
            todos[i] = todos[i + 1];
        }
        todos[todos.length - 1] = null;
        return true;
    }

    public static boolean isSelectedToDoNotValid(Integer number) {
        if (number <= 0 || number > todos.length || todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editToDoList(Integer number, String newToDo) {
        if (isSelectedToDoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newToDo;
        return true;
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showToDoList();
            System.out.println("MENU: ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");
            switch (selectedMenu) {
                case "1":
                    showMenuAddToDoList();
                    break;
                case "2":
                    showMenuRemoveToDoList();
                    break;
                case "3":
                    showMenuEditToDoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
            }
        }
    }

    public static void showMenuRemoveToDoList() {
        System.out.println("MENGHAPUS TODO LIST");
        String number = input("Nomor yang dihapus (x jika batal)");
        if( number.equals("x")) {
            //batal
        }else{
            boolean success = removeToDoList(Integer.parseInt(number));
            if (!success) {
                System.out.println("Gagal menghapus todo list: " + number);
            }
        }
    }

    public static void showMenuAddToDoList() {
        System.out.println("MENAMBAH TODO LIST: ");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")) {
            //batal
        }else{
            addToDoList(todo);
        }
    }

    public static void showMenuEditToDoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Nomor todo yang akan diedit (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }
            String newTodo = input("Masukkan Todo baru (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }
            boolean isEditTodoSuccess = editToDoList(Integer.parseInt(selectedTodo), newTodo);
            if (isEditTodoSuccess) {
                System.out.println("Berhasil Mengedit Todo");
            } else {
                System.out.println("Gagal Mengedit Todo");
            }
        }
    }

