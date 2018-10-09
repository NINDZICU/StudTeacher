package ru.kfpu.itis.studteacher.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.ui.listClasses.ListClassesActivity;
import ru.kfpu.itis.studteacher.ui.statistics.StatisticsActivity;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    private final Context mContext;
    private List<Task> mTasks;
    private final TasksAdapterOnItemClickHandler mClickHandler;

    public TasksAdapter(Context mContext, TasksAdapterOnItemClickHandler clickHandler) {
        this.mContext = mContext;
        mTasks = Collections.emptyList();
        this.mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        final Task task = mTasks.get(position);
        holder.tvName.setText(task.getName());

        holder.btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ListClassesActivity.class);
            intent.putExtra("ID_TASK", task.getId());
            mContext.startActivity(intent);
        });

        holder.btnStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, StatisticsActivity.class);
            mContext.startActivity(intent);
        });
        holder.btnDelete.setOnClickListener(v -> {
            mClickHandler.onItemClick(task.getId());
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final Button btnAddStudent;
        final Button btnDelete;
        final Button btnStatistics;

        public TasksViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_task_name);
            btnAddStudent = itemView.findViewById(R.id.btn_add_student);
            btnDelete = itemView.findViewById(R.id.btn_task_delete);
            btnStatistics = itemView.findViewById(R.id.btn_task_statistics);
        }
    }

    public void setmTasks(List<Task> mTasks) {
        this.mTasks = mTasks;
        notifyDataSetChanged();
    }

    public interface TasksAdapterOnItemClickHandler {
        void onItemClick(int id);
    }

    public void deleteTaskById(int id) {
        for(Task task:mTasks){
            if(task.getId() == id) {
                mTasks.remove(task);
                notifyDataSetChanged();
                break;
            }
        }
    }
}
