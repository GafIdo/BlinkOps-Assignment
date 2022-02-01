package models;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FormParser {
    Gson gson = new Gson();

    public Form parseJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String id = object.getString("id");
            String title = object.getString("title");
            JSONArray questionArr = object.getJSONArray("questionList");
            ArrayList<Question> questions = new ArrayList<Question>(questionArr.length());
            Gson gson = new Gson();
            for (int i=0 ; i<questionArr.length() ; i++){
                JSONObject qObj = questionArr.getJSONObject(i);
                Question question;
                switch (qObj.getEnum(Type.class, "type")) {
                    case OPEN_QUESTION:
                        question = gson.fromJson(qObj.toString(), OpenQuestion.class);
                        break;
                    case MULTIPLECHOICE_QUESTION:
                        question = gson.fromJson(qObj.toString(), MultiplechoiceQuestion.class);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + qObj.getEnum(Type.class, "type"));
                }
                questions.add(question);
            }
            return new Form(id, title, questions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJson(Form form){
        return gson.toJson(form);
    }
}

