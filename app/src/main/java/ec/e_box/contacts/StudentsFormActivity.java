package ec.e_box.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class StudentsFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);

        if(hasIntentionToUpdate()){
            Student student = getOriginalStudentToUpdate();
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            helper.fillInTheForm(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.studens_form_confirm) {
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            Student student = helper.createAStudent();

            StudentsDAO dao = new StudentsDAO(this);

            if(hasIntentionToUpdate()) {
                dao.update(student,getOriginalStudentToUpdate().getId());
            } else {
                dao.insert(student);
            }
            dao.close();


            String message = "'" + student.getName() + "' was saved! with grading " + student.getGrading();
            Toast.makeText(StudentsFormActivity.this,message,Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("student");
    }

    private Student getOriginalStudentToUpdate() {
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        return student;
    }

}
