import java.io.*;

public class ClearConsole {

    private static final ClearConsole instance = new ClearConsole();

    private void clsOnBash() throws IOException {
        //Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    private void clsOnWindows() throws IOException, InterruptedException {
        //Runtime.getRuntime().exec("cls");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static ClearConsole getInstance() {
        return instance;
    }

    public void clearScreen() throws Exception {
        if (System.getProperty("os.name").contains("Windows"))
           clsOnWindows();
        else
           clsOnBash();
    }

    public static void main(String args[]) throws Exception {
        System.out.println("------------------------------------\nClear Console Test: \n");
        System.out.print("Type something: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();


        ClearConsole.getInstance().clearScreen();

        System.out.println(name);
    }
}
