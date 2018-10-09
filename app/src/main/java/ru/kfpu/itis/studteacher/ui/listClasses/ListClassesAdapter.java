package ru.kfpu.itis.studteacher.ui.listClasses;

import android.content.Context;
import android.content.Intent;
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
import ru.kfpu.itis.studteacher.data.models.StudClass;
import ru.kfpu.itis.studteacher.ui.listStudents.ListStudentsActivity;

public class ListClassesAdapter extends RecyclerView.Adapter<ListClassesAdapter.ListClassesViewHolder> {
    private final Context mContext;
    private List<StudClass> studClassList;
    private List<StudClass> selected;
    private ListClassesAdapterOnItemClickHandler clickHandler;

    public ListClassesAdapter(Context mContext, ListClassesAdapterOnItemClickHandler clickHandler) {
        this.mContext = mContext;
        studClassList = Collections.emptyList();
        selected = new ArrayList<>();
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public ListClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_class_item, parent, false);
        return new ListClassesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClassesViewHolder holder, int position) {
        final StudClass studClass = studClassList.get(position);
        holder.tvName.setText(studClass.getClassNumber());

        View.OnClickListener l = v -> {
            if (holder.cbStudent.isChecked()) selected.add(studClass);
            else selected.remove(studClass);
        };
        holder.cbStudent.setOnClickListener(l);

        holder.itemView.setOnClickListener(v->{
            clickHandler.onItemClick(studClass.getId());
//            intent.putExtra("students", (ArrayList) studClass.getStudents());
        });
    }

    @Override
    public int getItemCount() {
        return studClassList.size();
    }

    class ListClassesViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CheckBox cbStudent;

        public ListClassesViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_student);
            cbStudent = itemView.findViewById(R.id.checkbox_student_item);

        }
    }

    public void setStudClassList(List<StudClass> studClassList) {
        this.studClassList = studClassList;
    }

    public interface ListClassesAdapterOnItemClickHandler {
        void onItemClick(int id);
    }
}
