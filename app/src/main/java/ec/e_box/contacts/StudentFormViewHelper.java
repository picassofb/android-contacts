package ec.e_box.contacts;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Ed on 9/3/2016.
 */
public class StudentFormViewHelper {

    private final Activity activity;

    public StudentFormViewHelper(Activity activity) {
        this.activity = activity;
    }

    private String GetTextFieldValue(int FieldId){
        EditText field = (EditText) activity.findViewById(FieldId);
        String value = field.getText().toString();
        return value;
    }

    private String getName() {
        return GetTextFieldValue(R.id.students_form_name);
    }

    private String getAddress(){
        return GetTextFieldValue(R.id.studens_form_address);
    }

    private String getPhoneNumber(){
        return GetTextFieldValue(R.id.studens_form_phone);
    }

    private String getEmail(){
        return GetTextFieldValue(R.id.students_form_email);
    }

    private String getWebsite(){
        return GetTextFieldValue(R.id.students_form_website);
    }

    public Student createAStudent() {
        return new Student(getName(),getAddress(),getPhoneNumber(),getEmail(),getWebsite(), getGrading());
    }

    public double getGrading() {
        RatingBar rating = (RatingBar) activity.findViewById(R.id.students_form_ratebar);
        return rating.getRating();
    }

    public void fillInTheForm(Student student) {
        fill(R.id.students_form_name,student.getName());
        fill(R.id.studens_form_phone,student.getPhoneNumber());
        fill(R.id.students_form_website,student.getWebsite());
        fill(R.id.students_form_email,student.getEmail());
        fill(R.id.studens_form_address,student.getAddress());

        RatingBar rating = (RatingBar) activity.findViewById(R.id.students_form_ratebar);
        rating.setRating((float) student.getGrading());

    }

    private void fill(int id, String value) {
        EditText name_field = (EditText) activity.findViewById(id);
        name_field.setText(value);
    }
}
