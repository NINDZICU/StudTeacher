package ru.kfpu.itis.studteacher.ui.uploadTask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Question;
import ru.kfpu.itis.studteacher.data.models.QuestionHandler;
import ru.kfpu.itis.studteacher.data.models.Test;
import ru.kfpu.itis.studteacher.data.models.TestAnswer;

public class AddTasksAdapter extends RecyclerView.Adapter<AddTasksAdapter.AddTasksViewHolder> {

    private final Context mContext;
    private List<QuestionHandler> questions;
    private List<Question> mTasks;
    private List<Test> tests;

    public AddTasksAdapter(Context mContext) {
        this.mContext = mContext;
        questions = Collections.emptyList();
        mTasks = new ArrayList<>();
        tests = new ArrayList<>();
    }

    @NonNull
    @Override
    public AddTasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_task_item, parent, false);
        return new AddTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddTasksViewHolder holder, int position) {
        final QuestionHandler task = questions.get(position);

        holder.tvNumberTask.setText(("Вопрос номер " + (position + 1)));
        holder.etTextQuestion.setText(task.getQuestion());
        holder.etTextAnswer.setText(task.getAnswer1());
        holder.etTextScore.setText(String.valueOf(task.getMark()));
        holder.etTextRecommendation.setText(task.getRecommendation());
        holder.etTestAnswer1.setText(task.getAnswer1());
        holder.etTestAnswer2.setText(task.getAnswer2());
        holder.etTestAnswer3.setText(task.getAnswer3());
        holder.etTestAnswer4.setText(task.getAnswer4());

        holder.aSwitch.setOnClickListener(v -> {
            if (holder.aSwitch.isChecked()) {
                System.out.println("SWITCH " + holder.aSwitch.isChecked());
                questions.get(position).setTest(true);
                holder.etTextAnswer.setVisibility(View.GONE);
                holder.constraintLayout.setVisibility(View.VISIBLE);
            } else {
                questions.get(position).setTest(false);
                holder.etTextAnswer.setVisibility(View.VISIBLE);
                holder.constraintLayout.setVisibility(View.GONE);
            }

        });
    }


    @Override
    public int getItemCount() {
        return questions.size();
    }

    class AddTasksViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNumberTask;
        final EditText etTextQuestion;
        final EditText etTextAnswer;
        final EditText etTextScore;
        final EditText etTextRecommendation;
        final Switch aSwitch;
        final ConstraintLayout constraintLayout;
        final EditText etTestAnswer1;
        final EditText etTestAnswer2;
        final EditText etTestAnswer3;
        final EditText etTestAnswer4;

        public AddTasksViewHolder(View itemView) {
            super(itemView);
            etTextScore = itemView.findViewById(R.id.et_text_score);
            tvNumberTask = itemView.findViewById(R.id.tv_answer_number);
            etTextAnswer = itemView.findViewById(R.id.et_text_answer);
            etTextQuestion = itemView.findViewById(R.id.et_text_task);
            etTextRecommendation = itemView.findViewById(R.id.et_text_recommendation);
            aSwitch = itemView.findViewById(R.id.switch_type_task);
            constraintLayout = itemView.findViewById(R.id.constrLayAnswers);
            etTestAnswer1 = itemView.findViewById(R.id.et_text_answer1);
            etTestAnswer2 = itemView.findViewById(R.id.et_text_answer2);
            etTestAnswer3 = itemView.findViewById(R.id.et_text_answer3);
            etTestAnswer4 = itemView.findViewById(R.id.et_text_answer4);

            etTextAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer1(etTextAnswer.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTextQuestion.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setQuestion(etTextQuestion.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTextRecommendation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setRecommendation(etTextRecommendation.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTextScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        questions.get(getAdapterPosition()).setMark(Integer.parseInt(etTextScore.getText().toString()));
                    } catch (NumberFormatException e) {
                        questions.get(getAdapterPosition()).setMark(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            etTextAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer1(etTextAnswer.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTextAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer1(etTextAnswer.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTestAnswer1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer1(etTestAnswer1.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTestAnswer2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer2(etTestAnswer2.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTestAnswer3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer3(etTestAnswer3.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            etTestAnswer4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    questions.get(getAdapterPosition()).setAnswer4(etTestAnswer4.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

        }
    }

    public void setmTasks(List<Question> mTasks) {
        this.mTasks = mTasks;
        notifyDataSetChanged();
    }

    public void setQuestions(List<QuestionHandler> questions) {
        this.questions = questions;
    }

    public void addItemToList() {
        mTasks.add(new Question("", 0, "", ""));
        notifyDataSetChanged();
    }

    public void addItemToListQuestions() {
        questions.add(new QuestionHandler(false, "", "", "", "", "", "", 1));
        notifyDataSetChanged();
    }

    public List<Question> getmTasks() {
        for (QuestionHandler question : questions) {
            if (!question.isTest())
                mTasks.add(new Question(question.getQuestion(), question.getMark(),
                        question.getAnswer1(), question.getRecommendation()));
        }
        return mTasks;
    }

    public List<Test> getTests() {
        for (QuestionHandler question : questions) {
            if (question.isTest()) {
                List<TestAnswer> answers = new ArrayList<>();
                answers.add(new TestAnswer(question.getAnswer1(), true));
                answers.add(new TestAnswer(question.getAnswer2(), false));
                answers.add(new TestAnswer(question.getAnswer3(), false));
                answers.add(new TestAnswer(question.getAnswer4(), false));
                tests.add(new Test(question.getQuestion(), question.getRecommendation(),
                        question.getMark(), answers));
            }
        }
        return tests;
    }

    public List<QuestionHandler> getQuestions() {
        return questions;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
