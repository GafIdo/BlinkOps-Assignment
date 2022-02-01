import com.google.gson.Gson;
import models.Form;
import models.FormParser;
import models.Question;

import java.util.ArrayList;
import java.util.Scanner;

public class FormsApp {

    FormParser parser = new FormParser();

    private ArrayList<Form> formsList = getFormsFromDB();

    public FormsApp() {
        startSession();
    }

    private void startSession() {
        Scanner scanner = new Scanner(System.in);
        boolean finishProcess = true;
        while (finishProcess) {
            System.out.println("Choose Action Number:\n1.Import Form\n2.Export Form\n3.Fill A Form\n4.End Process");
            switch (scanner.nextLine()){
                case "1":
                    importForm(scanner);
                    break;
                case "2":
                    exportForm(scanner);
                    break;
                case "3":
                    fillForm(scanner);
                    break;
                case "4":
                    finishProcess = false;
                    break;
                default:
                    System.out.println("Invalid Input, Try Again");
                    break;
            }

        }
        scanner.close();
    }


    private ArrayList<Form> getFormsFromDB() {
        return new ArrayList<>();
    }


    public boolean importForm(Scanner scanner) {
        System.out.println("Please Insert Valid JSON Text:");
        String jsonText = scanner.nextLine();
        try {
            Form form = parser.parseJson(jsonText);
            formsList.add(form);
        }
        catch (Exception e){
            System.out.println("Invalid JSON");
            return false;
        }
        return true;
    }


    public String exportForm(Scanner scanner) {
        for (int i = 0; i<formsList.size(); i++) {
            System.out.println(i + ". " + formsList.get(i).getTitle());
        }
        System.out.println("Choose Form Number:");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= formsList.size() || index < 0){
            System.out.println("Invalid Index");
            return null;
        }
        System.out.println(parser.toJson(formsList.get(index)));
        return parser.toJson(formsList.get(index));
    }


    public void fillForm(Scanner scanner) {
        for (int i = 0; i<formsList.size(); i++) {
            System.out.println(i + ". " + formsList.get(i).getTitle());
        }
        System.out.println("Enter Form Number:");
        int index;
        try {
            index = Integer.parseInt(scanner.next());
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        Form form = formsList.get(index);
        for (Question question: form.getQuestionList()) {
            System.out.println(question.getQuestionText());
            System.out.println(question.getAnswerInstruction());
            String input;
            do {
                input = scanner.nextLine();
            }
            while (question.vaildateAndSetAnswer(input));
        }
    }
}
