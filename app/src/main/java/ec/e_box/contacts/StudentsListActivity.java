package ec.e_box.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    private ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        getStudentsList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> studentList, View item, int position, long id) {
                Student student = (Student) studentList.getItemAtPosition(position);
                //Toast.makeText(StudentsListActivity.this,student.getName(),Toast.LENGTH_SHORT).show();
                Intent intention = new Intent(StudentsListActivity.this, StudentsFormActivity.class);
                intention.putExtra("student",student);
                startActivity(intention);
            }
        });

        registerForContextMenu(getStudentsList());
        Button newStudent = (Button) findViewById(R.id.students_list_new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(StudentsListActivity.this,"New Student",Toast.LENGTH_SHORT).show();
                Intent intention = new Intent(StudentsListActivity.this, StudentsFormActivity.class);
                startActivity(intention);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    private void loadStudents() {
        StudentsDAO dao = new StudentsDAO(this);
        List<Student> students = dao.ListAll();
        dao.close();
        getStudentsList();
        ArrayAdapter<Student> adapter =  new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1,students);
        getStudentsList().setAdapter(adapter);
    }

    public ListView getStudentsList() {
        return (ListView) findViewById(R.id.students_list_list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.equals(getStudentsList())) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Student student = (Student) getStudentsList().getItemAtPosition(info.position);
            ShowContextMenuForStudents(menu, student);
        }
    }

    private void ShowContextMenuForStudents(ContextMenu menu, final Student student) {
        MenuItem remove = menu.add("remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StudentsDAO dao = new StudentsDAO(StudentsListActivity.this);
                dao.remove(student);
                dao.close();

                Toast.makeText(StudentsListActivity.this, "Eliminando " + student.getName(), Toast.LENGTH_SHORT).show();
                loadStudents();
                return true;
            }
        });
    }

}
