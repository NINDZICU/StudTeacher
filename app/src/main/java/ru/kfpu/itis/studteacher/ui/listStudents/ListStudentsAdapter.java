package ru.kfpu.itis.studteacher.ui.listStudents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Student;
import ru.kfpu.itis.studteacher.data.network.pojo.IdForBody;

public class ListStudentsAdapter extends RecyclerView.Adapter<ListStudentsAdapter.ListStudentsViewHolder> {
    private Context mContext;
    private List<Student> students;
    private List<Student> selected;

    public ListStudentsAdapter(Context mContext) {
        this.mContext = mContext;
        students = Collections.emptyList();
        selected = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListStudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_class_item, parent, false);
        return new ListStudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStudentsViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.tvName.setText(student.getName());
        View.OnClickListener l = v -> {
            if (holder.cbStudent.isChecked()) selected.add(student);
            else selected.remove(student);
        };
        holder.cbStudent.setOnClickListener(l);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class ListStudentsViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CheckBox cbStudent;

        public ListStudentsViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_student);
            cbStudent = itemView.findViewById(R.id.checkbox_student_item);
        }
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public List<Student> getSelected() {
        return selected;
    }

    public List<IdForBody> getConvertToStudentForBody(List<Student> students){
        List<IdForBody> studentsForBody = new ArrayList<>();
        for(Student student:students){
            studentsForBody.add(new IdForBody(student.getId()));
        }
        return studentsForBody;
    }
}
